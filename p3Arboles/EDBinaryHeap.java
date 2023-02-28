package p3Arboles;

/**
 * @author Profesores ED
 * @version 2021-22
 */
public class EDBinaryHeap<T extends Comparable<T>> implements EDPriorityQueue<T> {
	protected T[] elementos;
	protected int numElementos;

	@SuppressWarnings("unchecked")
	public EDBinaryHeap(int numMaxElementos) {
		elementos = (T[]) new Comparable[numMaxElementos];
		this.numElementos = 0;
	}

	@Override
	public int add(T info) {
		int codeError = 0;
		if (numElementos == elementos.length) {
			codeError += -1;
		}
		if (info == null)
			codeError += -2;

		if (codeError == 0) {
			elementos[numElementos] = info;
			numElementos++;
			filtradoAscendente(numElementos - 1);
		}
		return codeError;

	}

	@Override
	public T poll() {
		if (isEmpty()) {
			return null;
		}	
		T raiz = elementos[0];
		numElementos--;
		
		if (! isEmpty()) {
			elementos[0] = elementos[numElementos];
			filtradoDescendente(0);
		}
		
		return raiz;
	}
	/**
	 * Borra un elemento de la cola
	 * Se le pasa el elemento que se quiere eliminar de la cola
	 * devuelve 0 si estaba y lo elimina, negativo en caso contrario (-1 si no estaba, -2 otras causas)
	 */
	@Override
	public int remove(T info) {
		int codeError = 0;
		if (info == null) {	
			codeError += -2;
		}
		
		int i = buscarNodo(info);
		
		if (i == -1) {	
			codeError += -1;
		}
		if(codeError == 0) {
			numElementos--;
			if (! isEmpty()) {	
				if (elementos[numElementos].compareTo(elementos[i]) > 0) {
					elementos[i] = elementos[numElementos];
					filtradoDescendente(i);
				} else {
					elementos[i] = elementos[numElementos];
					filtradoAscendente(i);
				}		
			}
		}
		return codeError;
	}

	@Override
	public boolean isEmpty() {
		if(numElementos == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		for(int i = 0 ; i<numElementos; i++) {
			elementos[i] = null;		
		}
		numElementos = 0;
	}

	/**
	 * Devuelve una cadena con la informacion de los elementos que contiene el
	 * monticulo en forma visible (recomendado inorden-derecha-izquierda tabulado)
	 */
	public String toString() {
		// Por ejemplo el arbol tumbado...
		StringBuilder cadena = new StringBuilder();
		cadena.append(inOrdenDerechaTabulado(0, 0));
		return cadena.toString();
	}

	// el arbol que empieza en indice p tumbado con esp tabulaciones...
	private String inOrdenDerechaTabulado(int p, int esp) {
		String cadena = "";
		if (p < numElementos) {
			int izq = Math.abs(2 * p + 1);
			int der = Math.abs(2 * p + 2);
			cadena += inOrdenDerechaTabulado(der, esp + 1);
			for (int i = 0; i < esp; i++)
				cadena += "\t";
			cadena += elementos[p] + "\n";
			cadena += inOrdenDerechaTabulado(izq, esp + 1);
		}
		return cadena;
	}

	/**
	 * Realiza una filtrado ascendente de minimos en el monticulo
	 * 
	 * Se le pasa el indice del elemento a filtrar
	 */
	protected void filtradoAscendente(int indice) {
		//si el padre es mayor que el hijo
    	if (elementos[indice].compareTo(elementos[(indice-1) / 2]) < 0) {
    		T padre = elementos[(indice-1) / 2];
    		elementos[(indice-1) / 2] = elementos[indice];
    		elementos[indice] = padre;
    		if ((indice-1) / 2 != 0) {    			
    			filtradoAscendente((indice-1) / 2);
    		}
    	}
	}

	/**
	 * Realiza una filtrado descendente de minimos en el monticulo
	 * 
	 * Se le pasa el indice del elemento a filtrar
	 */
	protected void filtradoDescendente(int indice) {
		int indicePH = 2*indice + 1;
    	int indiceHMen = indicePH;
    	if (indicePH >= numElementos)	//no tiene ningun hijo (fin de la recursividad)
    		return;
    	
    	if (indicePH+1 == numElementos) {	//solo tiene 1 hijo (fin de la recursividad despues de comprobar el hijo)
    		if (elementos[indicePH].compareTo(elementos[indice]) < 0) {
    			//el hijo es menor que es padre
    			T hijo = elementos[indicePH];
    			elementos[indicePH] = elementos[indice];
    			elementos[indice] = hijo;
    		} return;
		}
    	
    	//tiene 2 hijos
    	if(elementos[indicePH].compareTo(elementos[indicePH+1])<=0) {
    		indiceHMen = indicePH;
    	}
    	else {
    		indiceHMen = indicePH + 1;
    	}
    	if (elementos[indiceHMen].compareTo(elementos[indice]) < 0) {
    		//el hijo es menor que es padre
    		T hijo = elementos[indiceHMen];
    		elementos[indiceHMen] = elementos[indice];
    		elementos[indice] = hijo;
    		filtradoDescendente(indiceHMen);
    	}
    
	}
	
	/**
	 * Devuelve el �ndice en el array donde se almacena un objeto equals al que se busca
	 * @param info objeto equals al que se busca
	 * @return el �ndice del objeto en el array, si no est� almacenado devuelve -1
	 */
	private int buscarNodo(T info) {
		for (int i=0 ; i<numElementos ; i++) {
			if (elementos[i].equals(info))
				return i;
		}
		
		return -1;
	}


}
