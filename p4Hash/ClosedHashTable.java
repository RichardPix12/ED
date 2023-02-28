package p4Hash;

/**
 * @author Profesores ED
 * @version 2021-22
 *
 */
public class ClosedHashTable<T> extends AbstractHash<T> {
// IMPORTANTE
//	No cambiar el nombre ni visibilidad de los atributos protected

	protected HashNode<T> associativeArray[];

	protected int hashSize; // tamaño de la tabla, debe ser un numero primo
	protected int numElems; // numero de elementos en la tabla en cada momento.

	public static final int LINEAL = 0; // Tipo de exploracion en caso de colision, por defecto sera LINEAL
	public static final int CUADRATICA = 1;
	public static final int DOBLEHASH = 2;

	protected int exploracion; // exploracion que se realizara en caso de colision (LINEAL por defecto)

	protected double fcUP; // Factor de carga maximo
	protected double fcDOWN; // Factor de carga minimo

	/**
	 * Constructor para fijar el tamano al numero primo >= que el parametro y el
	 * tipo de exploración al indicado el tipo de exploracion(LINEAL=0,
	 * CUADRATICA=1, ...), si invalido LINEAL
	 */
	public ClosedHashTable(int tam, int expl) {
		this(tam, 1, 0, expl);

	}

	/**
	 * Constructor para fijar el tamaño al numero primo >= que el parametro Se le
	 * pasa el tamaño de la table Hash, si no es un numero primo lo ajusta al primo
	 * superior el factor de carga limite, por encima del cual hay que redispersar
	 * (directa) el factor de carga limite, por debajo del cual hay que redispersar
	 * (inversa) el tipo de exploracion(LINEAL=0, CUADRATICA=1, ...), si invalido
	 * LINEAL
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, double fcUP, double fcDOWN, int expl) { // Para la segunda clase

		hashSize = nextPrimeNumber(tam);// Establece un tamaño valido si tam no es primo

		associativeArray = (HashNode<T>[]) new HashNode[hashSize]; // Crea el array de HashNode's
		for (int i = 0; i < associativeArray.length; i++) {
			associativeArray[i] = new HashNode<T>();
		}
		// Completar lo que falta...
		this.exploracion = expl;
		this.fcUP = fcUP;
		this.fcDOWN = fcDOWN;
	}

	@Override
	public int getNumOfElems() {
		return this.numElems;
	}

	@Override
	public int getSize() {
		return this.associativeArray.length;
	}

	@Override
	public int add(T elem) {

		if (elem == null) {
			return -2;
		}

		if (getNumOfElems() == getSize()) {
			return -1;
		}

		int i = fHash(elem); // Posicion a colocar
		int pos = i; // indice del intento
		int intento = 0;

		while (pos != -1) {
			if (associativeArray[pos].getStatus() != HashNode.LLENO) {
				numElems++;
				associativeArray[pos].setInfo(elem);

				if (getFc() > fcUP) {
					reDispersion();
				}
				return 0;
			}
			intento++;
			pos = calcularIndice(i, intento);
		}

		return -1;
	}

	/**
	 * Metodo privado para calcular el factor de dispersion de la tabla
	 * 
	 * @return fc de tipo double
	 */
	private double getFc() {
		return (double) getNumOfElems() / getSize();
	}

	/**
	 * Metodo para calcular el siguiente indice de la tabla
	 * 
	 * @param pos     posicion anterior de la tabla
	 * @param intento numero de veces que se ha buscado un indice nuevo
	 * @return el nuevo indice de tipo int
	 */

	private int calcularIndice(int pos, int intento) {
		if (exploracion == LINEAL) {
			return indiceLineal(pos, intento);
		} else if (exploracion == CUADRATICA) {
			return indiceCuadratico(pos, intento);
		} else {
			return indiceDobleHash(pos, intento);
		}
	}

	/**
	 * Metodo para calcular el siguiente indice de la tabla cuando tiene una
	 * exploracion dobleHash
	 * 
	 * @param pos     posicion anterior de la tabla
	 * @param intento numero de veces que se ha buscado un indice nuevo
	 * @return el nuevo indice de tipo int
	 */
	private int indiceDobleHash(int pos, int intento) {
		if (intento > getSize())
			return -1;
		int r = previousPrimeNumber(getSize() - 1);
		return (pos + intento * (r - (pos % r))) % getSize();
	}

	/**
	 * Metodo para calcular el siguiente indice de la tabla cuando tiene una
	 * exploracion cuadratica
	 * 
	 * @param pos     posicion anterior de la tabla
	 * @param intento numero de veces que se ha buscado un indice nuevo
	 * @return el nuevo indice de tipo int
	 */
	private int indiceCuadratico(int pos, int intento) {
		if (intento > getSize() / 2) {
			return -1;
		}
		return (int) ((pos + Math.pow(intento, 2)) % getSize());
	}

	/**
	 * Metodo para calcular el siguiente indice de la tabla cuando tiene una
	 * exploracion lineal
	 * 
	 * @param pos     posicion anterior de la tabla
	 * @param intento numero de veces que se ha buscado un indice nuevo
	 * @return el nuevo indice de tipo int
	 */
	private int indiceLineal(int pos, int intento) {
		if (intento <= getSize()) {
			return (pos + intento) % getSize();
		}
		return -1;

	}

	@Override
	public T find(T elem) {
		int pos = search(elem);
		if (pos == -1) {
			return null;
		} else {
			return associativeArray[pos].getInfo();
		}
	}

	/**
	 * Metodo para buscar un elemento en la tabla hash
	 * 
	 * @param elem de tipo T, elemento que se busca
	 * @return indice de tipo int, donde se encuentra el elemento en la tabla
	 */
	private int search(T elem) {
		if (elem == null) {
			return -1;
		}
		int i = fHash(elem);
		int intento = 0;
		int pos = i;
		while (pos != -1 && associativeArray[pos].getStatus() != HashNode.VACIO) {
			if (elem.equals(associativeArray[pos].getInfo()) && associativeArray[pos].getStatus() == HashNode.LLENO) {
				return pos;
			}
			intento++;
			pos = calcularIndice(i, intento);
		}
		return -1;
	}

	@Override
	public int remove(T elem) {
		if (elem == null)
			return -2;

		int pos = search(elem);
		if (pos == -1)
			return -1;

		this.numElems--;
		this.associativeArray[pos].remove();
		if (getFc() < fcDOWN) {
			inverseReDispersion();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean reDispersion() {

		HashNode<T>[] copia = associativeArray;
		this.numElems = 0;
		this.hashSize = nextPrimeNumber(getSize() * 2); // tama�o del primo anterior a la mitad
		this.associativeArray = (HashNode<T>[]) new HashNode[hashSize]; // cambios el array para el tama�o nuevo

		for (int i = 0; i < associativeArray.length; i++) {
			associativeArray[i] = new HashNode<T>(); // creamos cada posicion del array
		}

		// Bucle para recuperar los nodos guardados en la copia
		for (int i = 0; i < copia.length; i++) {
			if (copia[i].getStatus() == HashNode.LLENO) {
				add(copia[i].getInfo());
			}
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean inverseReDispersion() {

		HashNode<T>[] copia = associativeArray;
		this.numElems = 0;
		this.hashSize = previousPrimeNumber(getSize() / 2); // tama�o del primo anterior a la mitad
		this.associativeArray = (HashNode<T>[]) new HashNode[hashSize]; // cambios el array para el tama�o nuevo
		for (int i = 0; i < associativeArray.length; i++) {
			associativeArray[i] = new HashNode<T>(); // creamos cada posicion del array
		}

		// Bucle para recuperar los nodos guardados en la copia
		for (int i = 0; i < copia.length; i++) {
			if (copia[i].getStatus() == HashNode.LLENO) {
				add(copia[i].getInfo());
			}
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < getSize(); i++) {
			cadena.append(associativeArray[i]);
			cadena.append(";");
		}
		cadena.append("[Size: ");
		cadena.append(getSize());
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElems());
		cadena.append("]");
		return cadena.toString();
	}
}
