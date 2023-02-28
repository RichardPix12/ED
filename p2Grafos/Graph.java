package p2Grafos;

import java.text.DecimalFormat;
/**
 * 
 * @author Ricardo Marqués Garay
 * 
 *
 * @param <T> parametro generico para el grafo
 */
public class Graph<T> {

	protected T[] nodes; // Vector de nodos

	protected boolean[][] edges; // matriz de aristas

	protected double[][] weights; // matriz de pesos

	protected int numNodes; // número de elementos en un momento dado

	protected double aFloyd[][]; // Matriz A de Floyd

	protected int pFloyd[][]; // matriz P de Floyd

	/**
	 * 
	 * Se le pasa el numero maximo de nodos del grafo
	 * 
	 * @param tam tamaño de la matriz
	 */

	@SuppressWarnings("unchecked")

	public Graph(int tam) {

		edges = new boolean[tam][tam];
		weights = new double[tam][tam];
		nodes = (T[]) new Object[tam];
		numNodes = 0;
	}
	
	/**
	 * Constructor con parametros
	 * @param tam tamaño de la matriz
	 * @param initialNodes vector de nodos
	 * @param initialEdges matriz de aristas
	 * @param initialWeights matriz de pesos
	 */
	public Graph(int tam, T initialNodes[], boolean[][] initialEdges, double[][] initialWeights) {
		this(tam);
		numNodes = initialNodes.length;
		for (int i = 0; i < numNodes; i++) {
			nodes[i] = initialNodes[i];
			for (int j = 0; j < numNodes; j++) {
				edges[i][j] = initialEdges[i][j];
				weights[i][j] = initialWeights[i][j];
			}
		}

	}
	/**
	 * Constructor con parametros
	 * @param tam tamaño de la matriz
	 * @param initialNodes vector de nodos
	 * @param initialEdges matriz de aristas
	 * @param initialWeights matriz de pesos
	 * @param initialAfloyd matriz a de floyd
	 * @param initialPfloyd matriz p de floyd
	 */
	public Graph(int tam, T initialNodes[], boolean[][] initialEdges, double[][] initialWeights,
			double[][] initialAfloyd, int[][] initialPfloyd) {
		// Llama al constructor anterior de inicializaciÃ³n
		this(tam, initialNodes, initialEdges, initialWeights);

		if (initialAfloyd != null && initialPfloyd != null) {

			aFloyd = initialAfloyd;

			pFloyd = initialPfloyd;
		}

	}

	/**
	 * 
	 * Inserta un nuevo nodo que se le pasa como parámetro
	 * 
	 * si lo inserta devuelve 0.
	 * 
	 * Error 1: si ya existe, valor -1
	 * 
	 * Error 2: no cabe, valor -2,
	 * 
	 * Error 3: el nodo a insertar no es válido, valor –4
	 * 
	 * Se suman los errores en caso de que se den varios,
	 * 
	 * por ejemplo: si además de no ser válido no cabe, devuelve -6 (suma errores 2
	 * y 3)
	 * 
	 * @param node, nodo a añadir
	 * @return errorCode codigo de error
	 */

	public int addNode(T node) {
		int errorCode = 0;

		if (existsNode(node)) {
			errorCode += -1;
		}
		if (!fitNode(node)) {
			errorCode += -2;
		}
		if (!validNode(node)) {
			errorCode += -4;
		}
		if (errorCode == 0) {
			nodes[numNodes] = node;
			numNodes++;

			return 0;
		}
		return errorCode;
	}

	/**
	 * 
	 * Borra el nodo deseado del vector de nodos así como las aristas de las que
	 * 
	 * forma parte, devolviendo 0 si lo hace y –1 si no lo hace
	 * 
	 * @param node, nodo a eliminar
	 * @return 0, si se borra , -1 si no
	 */

	public int removeNode(T node) {
		int i = getNode(node);

		// COMPRUEBO SI EXISTE EL NODO
		if (i == -1) {
			return -1;
		} else {
			if (i == numNodes) { // SI ES EL NODO FINAL
				numNodes--;
				for (int j = 0; j < numNodes; j++) {
					edges[i][j] = false;
				}
			} else {
				numNodes--;
				nodes[i] = nodes[numNodes]; // coloco el nodo final en esa posicion
				for (int j = 0; j < numNodes; j++) { // coloco las dos matrices
					edges[i][j] = edges[numNodes][j];
					edges[j][i] = edges[j][numNodes];
					weights[i][j] = weights[numNodes][j];
					weights[j][i] = weights[j][numNodes];
					// Arreglo, al borrar el nodo, las aristas se renuevan a no tener ninguna, por
					// lo
					// cual no deberia tener aristas al crearse
					// y el add edge funciona en grafo evolucionado ya que no hay aristas y si se
					// pueden crear
					edges[j][numNodes] = false;
					weights[j][numNodes] = 0;
					edges[numNodes][j] = false;
					weights[numNodes][j] = 0;
				}
				// coloco las diagonales
				edges[i][i] = edges[numNodes][numNodes];
				weights[i][i] = weights[numNodes][numNodes];

				// Borro las aristas de la diagonal del nodo borrado
				edges[numNodes][numNodes] = false;
				weights[numNodes][numNodes] = 0;

			}

			return 0;
		}
	}

	/**
	 * 
	 * indica si existe o no el nodo en el grafo
	 * @param node nodo que buscamos
	 * 
	 * @return true si existe
	 */

	public boolean existsNode(T node) {

		if (getNode(node) != -1) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Inserta una arista con el peso indicado (> 0) entre dos nodos, uno origen y
	 * 
	 * otro destino. Si la arista existe, no la inserta.
	 * 
	 * Devuelve 0 si la inserta
	 * 
	 * Se suman los valores de los errores si se dan varios simultaneamente,
	 * 
	 * por ejemplo si se dan errores 1 y 2 el resultado es la suma de los valores
	 * correspondientes (-3)
	 * 
	 * Error 1: No existe nodo origen, valor -1
	 * 
	 * Error 2: No existe nodo destino, valor -2
	 * 
	 * Error 3: Ya existe la arista, valor -4
	 * 
	 * Error 4: El peso no es válido, valor -8
	 * 
	 * @param source, nodo origen
	 * @param target, nodo destino
	 * @param edgeWeight, peso de la arista
	 * 
	 * @return error, codigo de error
	 * 
	 */

	public int addEdge(T source, T target, double edgeWeight) {
		int error = 0;

		// Compruebo si existe nodo origen return -1
		if (!existsNode(source)) {
			error += -1;
		}
		// compruebo si existe nodo destino return -2
		if (!existsNode(target)) {
			error += -2;
		}
		// compruebo si existe la arista return -4
		if (existsEdge(source, target)) {
			error += -4;
		}
		// compruebo si el peso es valido return -8
		if (edgeWeight <= 0) {
			error += -8;
		}
		if (error == 0) {
			// obtengo la posicion de cada nodo
			int i = getNode(source);
			int j = getNode(target);
			// inserto en la matriz de arista true y de peso su peso
			edges[i][j] = true;
			weights[i][j] = edgeWeight;
			return 0;
		}
		return error;
	}

	/**
	 * 
	 * Borra la arista del grafo que conecta dos nodos
	 * 
	 * Se suman los valores de los errores si se dan varios simultaneamente
	 * 
	 * Error 1: No existe nodo origen, valor –1,
	 * 
	 * Error 2: No existe nodo destino, valor -2
	 * 
	 * Error 3: No existe la arista pero sí los nodos origen y destino, valor –4
	 * 
	 * devuelve 0 si la borra
	 * 
	 * @param source, nodo origen
	 * @param target, nodo destino
	 * 
	 * @return error, codigo de error
	 * 
	 */

	public int removeEdge(T source, T target) {
		int error = 0;

		// Compruebo si existe nodo origen return -1
		if (!existsNode(source)) {
			error += -1;
		}
		// compruebo si existe nodo destino return -2
		if (!existsNode(target)) {
			error += -2;
		}
		// no existe la arista
		if (existsNode(source) && existsNode(target) && (!existsEdge(source, target))) {
			error += -4;
		}
		if (error == 0) {
			int i = getNode(source);
			int j = getNode(target);
			edges[i][j] = false;
			weights[i][j] = 0;
		}

		return error;
	}

	/**
	 * 
	 * Comprueba si existe una arista entre dos nodos que se pasan como parámetro
	 * 
	 * si alguno de los nodos no existe, no existe la arista evidentemente
	 * 
	 * @param source, nodo origen
	 * @param target, nodo destino
	 * 
	 * @return true, si existe
	 */

	public boolean existsEdge(T source, T target) {
		if (!existsNode(source)) {
			return false;
		}
		// compruebo si existe nodo destino return -2
		else if (!existsNode(target)) {
			return false;
		}
		int i = getNode(source);
		int j = getNode(target);
		if (edges[i][j] == true) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Devuelve el peso de la arista que conecta dos nodos
	 * 
	 * Se suman los valores de los errores si se dan varios simultaneamente
	 * 
	 * Error 1: No existe nodo origen, valor –1,
	 * 
	 * Error 2: No existe nodo destino, valor -2
	 * 
	 * Error 3: No existe la arista pero sí los nodos origen y destino, valor –4
	 * 
	 * @param source, nodo origen
	 * @param target, nodo destino
	 * 
	 * @return error, codigo de error
	 */

	public double getEdge(T source, T target) {

		int error = 0;

		// Compruebo si existe nodo origen return -1
		if (!existsNode(source)) {
			error += -1;
		}
		// compruebo si existe nodo destino return -2
		if (!existsNode(target)) {
			error += -2;
		}
		// no existe la arista pero si los nodos
		if (existsNode(source) && existsNode(target) && (!existsEdge(source, target))) {
			error += -4;
		}
		if (error == 0) {
			int i = getNode(source);
			int j = getNode(target);
			return weights[i][j];
		}

		return error;

	}

	/**
	 * 
	 * Algoritmo de Dijkstra para encontrar el camino de coste mínimo desde
	 * nodoOrigen
	 * 
	 * hasta el resto de nodos. Devuelve el vector D de Dijkstra
	 * 
	 * Si no existe el nodoOrigen (o es inválido como parámetro) devuelve null *
	 * @param nodoOrigen, nodo origen
	 * 
	 * @return d, matriz de costes
	 * 	
	 */

	public double[] dijkstra(T nodoOrigen) {
		if (getNode(nodoOrigen) == -1) {
			return null;
		}
		// inicializo dijkstra
		double[] d = new double[numNodes];
		boolean[] s = new boolean[numNodes];
		int[] p = new int[numNodes];
		int i = getNode(nodoOrigen);
		for (int j = 0; j < numNodes; j++) {
			p[j] = -1;
			if (weights[i][j] != 0) {
				d[j] = weights[i][j];
			} else {
				d[j] = Double.POSITIVE_INFINITY;
			}
			if (i == j) {
				d[i] = 0;
			}
		}

		// comienza el algoritmo de dijkstra
		int w = minCost(d, s);

		while (w != -1) {
			s[w] = true;
			for (int m = 0; m < numNodes; m++) {
				if (!s[m] && edges[w][m] && (d[w] + weights[w][m] < d[m])) {
					d[m] = d[w] + weights[w][m];
					p[m] = w;
				}
			}
			w = minCost(d, s);
		}

		return d;
	}

	/**
	 * 
	 * Busca el nodo con distancia minima en D al resto de nodos, se le pasa el
	 * vector D de dijkstra y
	 * 
	 * el conjunto de visitados (como un vector de booleanos) y devuelve el indice
	 * del nodo buscado
	 * 
	 * Si hay varios con mismo coste devuelve el que tenga índica más bajo en el
	 * vector de nodos
	 * 
	 * o -1 si el grafo es no conexo o no quedan nodos sin visitar
	 * 
	 * @param vectorD, vector de caminos de nodos
	 * @param visited, vector de nodos visitados
	 * 
	 * @return minCost, coste minimo
	 * 
	 */
	private int minCost(double[] vectorD, boolean[] visited) {
		int minCost = -1;
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i] && (minCost == -1 || vectorD[i] < vectorD[minCost])) {
				minCost = i;
			}
		}
		return minCost;
	}

	/**
	 * 
	 * Aplica el algoritmo de Floyd al grafo y devuelve 0 si lo aplica y genera
	 * matrices A y P; y –1 si no lo hace
	 * 
	 * @return 0 si aplica
	 */

	public int floyd() {
		if (numNodes == 0) {
			return -1;
		}
		// inicializo a
		aFloyd = new double[numNodes][numNodes];
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				if (i == j) {
					aFloyd[i][j] = 0.0;
				} else {
					if (!existsEdge(nodes[i], nodes[j])) {
						aFloyd[i][j] = Double.POSITIVE_INFINITY;
					} else {
						aFloyd[i][j] = weights[i][j];
					}
				}
			}
		}

		// inicializo p
		pFloyd = new int[numNodes][numNodes];
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				pFloyd[i][j] = -1;
			}
		}

		// Se ejecuta el algoritmo de floyd
		for (int k = 0; k < numNodes; k++) {
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					if (aFloyd[i][k] + aFloyd[k][j] < aFloyd[i][j]) {
						aFloyd[i][j] = aFloyd[i][k] + aFloyd[k][j];
						pFloyd[i][j] = k;
					}
				}
			}
		}

		return 0;
	}

	/**
	 * 
	 * Devuelve la matriz A de Floyd, con infinito si no hay camino
	 * 
	 * Si no se ha invocado a Floyd debe devolver null (OJO que no lo invoca)
	 * 
	 * @return aFloyd, matriz a de floyd
	 */

	protected double[][] getAFloyd() {

		return aFloyd;
	}

	/**
	 * 
	 * Devuelve la matriz P de Floyd, con -1 en las posiciones en las que no hay
	 * nodo intermedio
	 * 
	 * Si no se ha invocado a Floyd debe devolver null (OJO que no lo invoca)
	 * 
	 * @return pFloyd, matriz p de floyd
	 */

	protected int[][] getPFloyd() {

		return pFloyd;
	}

	/**
	 * 
	 * Devuelve el coste del camino de coste mínimo entre origen y destino según
	 * Floyd
	 * 
	 * Si no están generadas las matrices de Floyd, las genera.
	 * 
	 * Si no puede obtener el valor por alguna razón, devuelve –1 (OJO que es
	 * distinto de infinito)
	 * 
	 * @param origen, nodo origen
	 * @param destino, nodo destino
	 * 
	 * @return int, coste minimo entre dos nodos
	 * 
	 **/

	public double minCostPath(T origen, T destino) {
		if(origen == null || destino == null) {
			return -1;
		}
		if (getAFloyd() == null || getPFloyd() == null) {
			floyd();
		}
		int i = getNode(origen);
		int j= getNode(destino);
		return aFloyd[i][j];
	}

	/**
	 * 
	 * Indica el camino entre los nodos que se le pasan como parámetros en un String
	 * de esta forma:
	 * 
	 * 
	 * Si no están generadas las matrices de Floyd, las genera, si se puede. ///////////
	 * 
	 * 
	 * Si Origen y Destino coinciden: Origen
	 * 
	 * Si no existen los 2 nodos devuelve una cadena vacía
	 * 
	 * @param origen, nodo origen
	 * @param destino, nodo destino
	 * 
	 * @return str, camino entre dos nodos
	 * 
	 */

	public String path(T origen, T destino) {
		String str = "";
		if (getAFloyd() == null || getPFloyd() == null) {
			floyd();
		}
		if(!existsNode(origen) || !existsNode(destino)) {
			return str;
		}
		if(origen.equals(destino)) {
			return origen + "\t";
		}

		int i = getNode(origen);
		int j = getNode(destino);
		int k = pFloyd[i][j];
		if(k == -1) {
			return origen +"\tInfinity\t" + destino +"\t";
		}

		
		
		
		str+= origen+"\t";
		str+=pathRecursive(i,j); 
		
		str+=destino +"\t";
		
		return str;
	}

	
	/**
	 * Metodo recursivo para el path intermedio entre 2 nodos
	 * @param i, indice de nodo origen
	 * @param j, indice de nodo destino
	 * @return str, path intermedio entre 2 nodos
	 */
	private String pathRecursive(int i, int j) {
		String str = "";
		int k = pFloyd[i][j];
		if (k > 0) {
			str += pathRecursive(i, k);
			str += nodes[k];
			str += pathRecursive(k, j);
		} else if (getAFloyd()[i][j] != 0) {
			str += "\t(" + minCostPath(nodes[i], nodes[j]) + ")\t";
		}

		return str;
	}

	/**
	 * 
	 * Lanza el recorrido en profundidad de un grafo desde un nodo determinado,
	 * 
	 * No necesariamente recorre todos los nodos.
	 * 
	 * Al recorrer cada nodo añade el toString del nodo y un tabulador
	 * 
	 * Se puede usar un método privado recursivo...
	 * 
	 * Se recorren vecinos empezando por el principio del vector de nodos (antes
	 * índices bajos)
	 * 
	 * Si no existe el nodo devuelve una cadena vacia
	 * 
	 * @param origen, nodo origen
	 * 
	 * @return str, recorrido desde un nodo
	 */

	public String recorridoProfundidad(T origen) {
		String str = "";
		if(!existsNode(origen)) {
			return str;
		}
		
		boolean [] visited = new boolean [numNodes];
		
		str += recorridoProfRecursivo(origen,visited);
		
		return str;
	}

	private String recorridoProfRecursivo(T origen, boolean[] visited) {
		String str = "";
		str += origen + "\t";
		int i = getNode(origen);
		visited[i] = true;
		
		for(int j=0;j<numNodes;j++) {
			if(visited[j] == false) {
				if(existsEdge(origen, nodes[j])) {
					str += recorridoProfRecursivo(nodes[j], visited);
				}
			}
		}
		
		return str;
	}

	/**
	 * 
	 * Devuelve un String con la informacion del grafo
	 * 
	 * @return toString de la clase
	 * 
	 */

	public String toStringViejo() {

		DecimalFormat df = new DecimalFormat("#.##");

		String cadena = "";

		cadena += "NODES\n";

		for (int i = 0; i < numNodes; i++) {

			cadena += nodes[i].toString() + "\t";

		}

		cadena += "\n\nEDGES\n";

		for (int i = 0; i < numNodes; i++) {

			for (int j = 0; j < numNodes; j++) {

				if (edges[i][j])

					cadena += "T\t";

				else

					cadena += "F\t";

			}

			cadena += "\n";

		}

		cadena += "\nWEIGHTS\n";

		for (int i = 0; i < numNodes; i++) {

			for (int j = 0; j < numNodes; j++) {

				cadena += (edges[i][j] ? df.format(weights[i][j]) : "-") + "\t";

			}

			cadena += "\n";

		}

		return cadena;

	}

	/**
	 * 
	 * Devuelve un String con la informacion del grafo (incluyendo matrices de
	 * Floyd)
	 * 
	 * Si se ha usado para pruebas el toString anterior, sería conveniente
	 * renombrarlo antes (mediante refactorizacion)
	 * 
	 * antes de sustituirlo por este, para que sigan pasando las pruebas anteriores
	 * sin problemas.
	 * 
	 * @return toString de la clase
	 */

	public String toString() {

		DecimalFormat df = new DecimalFormat("#.##");

		String cadena = "";

		cadena += "NODES\n";

		for (int i = 0; i < numNodes; i++) {

			cadena += nodes[i].toString() + "\t";

		}

		cadena += "\n\nEDGES\n";

		for (int i = 0; i < numNodes; i++) {

			for (int j = 0; j < numNodes; j++) {

				if (edges[i][j])

					cadena += "T\t";

				else

					cadena += "F\t";

			}

			cadena += "\n";

		}

		cadena += "\nWEIGHTS\n";

		for (int i = 0; i < numNodes; i++) {

			for (int j = 0; j < numNodes; j++) {

				cadena += (edges[i][j] ? df.format(weights[i][j]) : "-") + "\t";

			}

			cadena += "\n";

		}

		double[][] aFloyd = getAFloyd();

		if (aFloyd != null) {

			cadena += "\nAFloyd\n";

			for (int i = 0; i < numNodes; i++) {

				for (int j = 0; j < numNodes; j++) {

					cadena += df.format(aFloyd[i][j]) + "\t";

				}

				cadena += "\n";

			}

		}

		int[][] pFloyd = getPFloyd();

		if (pFloyd != null) {

			cadena += "\nPFloyd\n";

			for (int i = 0; i < numNodes; i++) {

				for (int j = 0; j < numNodes; j++) {

					cadena += (pFloyd[i][j] >= 0 ? df.format(pFloyd[i][j]) : "-") + "\t";

				}

				cadena += "\n";

			}

		}

		return cadena;

	}

	/* METODOS PRIVADOS PARA AYUDA */

	/**
	 * Metodo que busca un bule
	 * 
	 * @param node nodo que buscamos
	 * @return posicion en la que se encuentra, si no existe devuelve -1
	 * 
	 * 
	 */
	private int getNode(T node) {
		for (int i = 0; i < numNodes; i++) {
			if (nodes[i].equals(node)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Metodo que comprueba si el nodo cabe
	 * 
	 * @param node nodo a comprobar
	 * @return true si cabe, false si no cabe
	 */
	private boolean fitNode(T node) {
		if (numNodes <= nodes.length - 1) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo que comprueba si el nodo es valido o no
	 * 
	 * @param node nodo a comprobar
	 * @return true si es valido, false si es invalido
	 */
	private boolean validNode(T node) {
		if (node != null) {
			return true;
		}
		return false;
	}
	
}