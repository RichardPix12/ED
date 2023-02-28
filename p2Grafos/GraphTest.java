package p2Grafos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GraphTest {
	
	/**
	 * Test para comprobar si se añaden bien los nodos
	 */
	@Test
	void testAddNode() {
		Graph<Integer> g = new Graph<Integer>(6);
		assertEquals(0, g.addNode(7));
		assertEquals(0, g.addNode(40));
		assertEquals(-4, g.addNode(null));
		assertEquals(-1, g.addNode(7));
		assertEquals(0, g.addNode(20));
		assertEquals(-1, g.addNode(40));
		assertEquals(0, g.addNode(220));
		assertEquals(0, g.addNode(202));
		assertEquals(0, g.addNode(201));
		assertEquals(-2, g.addNode(1));
		assertEquals(-6, g.addNode(null));
	}

	/**
	 * Test para comprobar si se borran bien los nodos
	 */
	@Test
	void testRemoveNode() {
		Graph<Integer> g = new Graph<Integer>(6);
		g.addNode(5);
		g.addNode(15);
		g.addNode(31);
		g.addEdge(5, 15, 22);
		g.addEdge(15, 31, 10);
		// Borra nodo final
		assertEquals(0, g.removeNode(31));
		// No existe el nodo
		assertEquals(-1, g.removeNode(65));
		g.addNode(33);
		g.addNode(32);
		g.addEdge(32, 5, 2);
		assertEquals(0, g.removeNode(15));

	}

	/**
	 * Test para comprobar si existe el nodo
	 */
	@Test
	void testExistNode() {
		Graph<Integer> g = new Graph<Integer>(6);
		g.addNode(7);
		g.addNode(12);
		assertEquals(false, g.existsNode(15));
		assertEquals(true, g.existsNode(12));
		assertEquals(false, g.existsNode(17));
		assertEquals(true, g.existsNode(7));
	}

	/**
	 * Test para añadir una arista
	 */
	@Test
	void testAddEdge() {
		Graph<Integer> g = new Graph<Integer>(6);
		g.addNode(5);
		g.addNode(15);
		g.addNode(31);
		g.addNode(22);
		g.addNode(17);
		assertEquals(0, g.addEdge(5, 15, 10));
		assertEquals(0, g.addEdge(22, 31, 10));
		// no existe nodo raiz
		assertEquals(-1, g.addEdge(52, 15, 10));
		// no existe nodo llegada
		assertEquals(-2, g.addEdge(5, 152, 10));
		// ya existe la arista
		assertEquals(-4, g.addEdge(5, 15, 10));
		// peso invalido
		assertEquals(-8, g.addEdge(5, 17, 0));
		// varios errores
		assertEquals(-11, g.addEdge(2, 3, 0));

	}

	/**
	 * Test para saber si existe una arista
	 */
	@Test
	void testExistEdge() {
		Graph<Integer> g = new Graph<Integer>(6);
		g.addNode(5);
		g.addNode(15);
		g.addNode(31);
		g.addEdge(5, 15, 22);
		g.addEdge(15, 31, 10);
		assertEquals(true, g.existsEdge(5, 15));
		assertEquals(true, g.existsEdge(15, 31));
		assertEquals(false, g.existsEdge(5, 31));
	}

	/**
	 * Test para saber si devuelve bien la posicion de la arista en caso de que exista
	 */
	@Test
	void testGetEdge() {
		Graph<Integer> g = new Graph<Integer>(6);
		g.addNode(5);
		g.addNode(15);
		g.addNode(31);
		g.addEdge(5, 15, 22);
		g.addEdge(15, 31, 10);
		assertEquals(22, g.getEdge(5, 15));
		assertEquals(10, g.getEdge(15, 31));
		// no existe nodo raiz
		assertEquals(-1, g.getEdge(51, 15));
		// no existe nodo destino
		assertEquals(-2, g.getEdge(5, 101));
		// no existe arista
		assertEquals(-4, g.getEdge(5, 31));
		// varios errores
		assertEquals(-3, g.getEdge(100, 101));

	}

	/**
	 * Test para saber si se borran bien los nodos
	 */
	@Test
	void testRemoveEdge() {
		Graph<Integer> g = new Graph<Integer>(6);
		g.addNode(5);
		g.addNode(15);
		g.addNode(31);
		g.addEdge(5, 15, 22);
		g.addEdge(15, 31, 10);
		// no existe nodo raiz
		assertEquals(-1, g.removeEdge(51, 15));
		// no existe nodo destino
		assertEquals(-2, g.removeEdge(5, 101));
		// no existe arista
		assertEquals(-4, g.removeEdge(5, 31));
		// varios errores
		assertEquals(-3, g.removeEdge(100, 101));
		// caso valido
		assertEquals(0, g.removeEdge(5, 15));
		assertEquals(0, g.removeEdge(15, 31));

	}

	/**
	 * Test para comprobar el metodo dijkstra
	 */
	@Test
	void testDijkstra() {

		// Ejemplo 1 teoria
		Graph<Integer> g2 = new Graph<Integer>(5);
		assertEquals(0, g2.addNode(1));
		assertEquals(0, g2.addNode(2));
		assertEquals(0, g2.addNode(3));
		assertEquals(0, g2.addNode(4));
		assertEquals(0, g2.addNode(5));
		assertEquals(0, g2.addEdge(1, 2, 1));
		assertEquals(0, g2.addEdge(1, 4, 3));
		assertEquals(0, g2.addEdge(1, 5, 10));
		assertEquals(0, g2.addEdge(2, 3, 5));
		assertEquals(0, g2.addEdge(3, 5, 1));
		assertEquals(0, g2.addEdge(4, 3, 2));
		assertEquals(0, g2.addEdge(4, 5, 6));
		// Calcular todos los caminos
		// nodo 1
		assertEquals(g2.dijkstra(1)[0], 0);
		assertEquals(g2.dijkstra(1)[1], 1);
		assertEquals(g2.dijkstra(1)[2], 5);
		assertEquals(g2.dijkstra(1)[3], 3);
		assertEquals(g2.dijkstra(1)[4], 6);

		// nodo 2
		assertEquals(g2.dijkstra(2)[0], Double.POSITIVE_INFINITY);
		assertEquals(g2.dijkstra(2)[1], 0);
		assertEquals(g2.dijkstra(2)[2], 5);
		assertEquals(g2.dijkstra(2)[3], Double.POSITIVE_INFINITY);
		assertEquals(g2.dijkstra(2)[4], 6);

		// Nodo 3
		assertEquals(g2.dijkstra(3)[0], Double.POSITIVE_INFINITY);
		assertEquals(g2.dijkstra(3)[1], Double.POSITIVE_INFINITY);
		assertEquals(g2.dijkstra(3)[2], 0);
		assertEquals(g2.dijkstra(3)[3], Double.POSITIVE_INFINITY);
		assertEquals(g2.dijkstra(3)[4], 1);

		// Nodo 4
		assertEquals(g2.dijkstra(4)[0], Double.POSITIVE_INFINITY);
		assertEquals(g2.dijkstra(4)[1], Double.POSITIVE_INFINITY);
		assertEquals(g2.dijkstra(4)[2], 2);
		assertEquals(g2.dijkstra(4)[3], 0);
		assertEquals(g2.dijkstra(4)[4], 3);

		// Nodo 5
		assertEquals(g2.dijkstra(5)[0], Double.POSITIVE_INFINITY);
		assertEquals(g2.dijkstra(5)[1], Double.POSITIVE_INFINITY);
		assertEquals(g2.dijkstra(5)[2], Double.POSITIVE_INFINITY);
		assertEquals(g2.dijkstra(5)[3], Double.POSITIVE_INFINITY);
		assertEquals(g2.dijkstra(5)[4], 0);

	}

	/**
	 * Test para comprobar la evolución de un nodo
	 */
	@Test
	void testEvolucion() {
		Graph<Integer> g = new Graph<Integer>(3);
		assertEquals(false, g.existsNode(1));
		assertEquals(false, g.existsNode(2));

		assertEquals(0, g.addNode(1));
		assertEquals(0, g.addNode(2));

		assertEquals(true, g.existsNode(1));
		assertEquals(true, g.existsNode(2));

		assertEquals(-1, g.addNode(1));
		assertEquals(-1, g.addNode(2));

		assertEquals(0, g.addEdge(1, 2, 1.2));
		assertEquals(0, g.addEdge(2, 1, 2.1));
		assertEquals(0, g.addEdge(1, 1, 1.1));

		assertEquals(1.2, g.getEdge(1, 2));
		assertEquals(2.1, g.getEdge(2, 1));
		assertEquals(1.1, g.getEdge(1, 1));
		assertEquals(-2.0, g.getEdge(2, 3));

		assertEquals(true, g.existsEdge(1, 2));
		assertEquals(true, g.existsEdge(2, 1));
		assertEquals(true, g.existsEdge(1, 1));
		assertEquals(false, g.existsEdge(2, 2));

		assertEquals(-4.0, g.removeEdge(2, 2));

		assertEquals(0, g.addEdge(2, 2, 2.2));
		assertEquals(2.2, g.getEdge(2, 2));
		assertEquals(true, g.existsEdge(2, 2));

		assertEquals(0, g.removeEdge(2, 2));

		assertEquals(-1, g.removeNode(3));
		assertEquals(0, g.addNode(3));
		assertEquals(-2, g.addNode(4));

		assertEquals(-4.0, g.removeEdge(1, 3));

		assertEquals(0, g.addEdge(1, 3, 1.3));
		assertEquals(true, g.existsEdge(1, 3));
		assertEquals(1.3, g.getEdge(1, 3));

		assertEquals(-2.0, g.getEdge(1, 4));
		assertEquals(-1.0, g.getEdge(5, 1));
		assertEquals(-3.0, g.getEdge(5, 4));

		assertEquals(0, g.removeNode(3));
		assertEquals(-1, g.removeNode(3));
		assertEquals(false, g.existsEdge(1, 3));
		assertEquals(-2.0, g.getEdge(1, 3));

		assertEquals(0, g.removeNode(2));
		assertEquals(-1, g.removeNode(2));
		assertEquals(true, g.existsEdge(1, 1));

		assertEquals(0, g.removeNode(1));
		assertEquals(false, g.existsNode(1));
		assertEquals(false, g.existsNode(2));
		assertEquals(false, g.existsNode(3));
		assertEquals(false, g.existsNode(4));

		assertEquals(0, g.addNode(4));
		assertEquals(true, g.existsNode(4));
		assertEquals(0, g.addEdge(4, 4, 4.4));
		assertEquals(true, g.existsEdge(4, 4));
		assertEquals(4.4, g.getEdge(4, 4));

		assertEquals(0, g.removeNode(4));
		assertEquals(-1, g.removeNode(4));
		assertEquals(false, g.existsEdge(4, 4));

		assertEquals(0, g.addNode(7));
		assertEquals(-1, g.removeNode(2));
		assertEquals(-1, g.removeNode(3));
		assertEquals(-1, g.removeNode(4));

		assertEquals(false, g.existsEdge(7, 7));
		assertEquals(0, g.addEdge(7, 7, 7.7));
		assertEquals(-4.0, g.addEdge(7, 7, 17.17));
		assertEquals(7.7, g.getEdge(7, 7));

		assertEquals(0, g.addNode(8));
		assertEquals(0, g.addNode(9));

		assertEquals(false, g.existsEdge(7, 8));
		assertEquals(false, g.existsEdge(8, 7));
		assertEquals(false, g.existsEdge(8, 8));
		assertEquals(false, g.existsEdge(8, 9));
		assertEquals(false, g.existsEdge(9, 8));
		assertEquals(false, g.existsEdge(9, 9));

		assertEquals(0, g.addEdge(7, 8, 7.8));
		assertEquals(0, g.addEdge(8, 7, 8.7));
		assertEquals(0, g.addEdge(8, 8, 8.8));
		assertEquals(0, g.addEdge(8, 9, 8.9));
		assertEquals(0, g.addEdge(9, 8, 9.8));
		assertEquals(0, g.addEdge(9, 9, 9.9));
		assertEquals(0, g.addEdge(7, 9, 7.9));
		assertEquals(0, g.addEdge(9, 7, 9.7));

		assertEquals(7.7, g.getEdge(7, 7));
		assertEquals(7.8, g.getEdge(7, 8));
		assertEquals(7.9, g.getEdge(7, 9));
		assertEquals(8.7, g.getEdge(8, 7));
		assertEquals(8.8, g.getEdge(8, 8));
		assertEquals(8.9, g.getEdge(8, 9));
		assertEquals(9.7, g.getEdge(9, 7));
		assertEquals(9.8, g.getEdge(9, 8));
		assertEquals(9.9, g.getEdge(9, 9));

		assertEquals(0, g.removeNode(7));
		assertEquals(-1, g.removeNode(7));

		assertEquals(-3.0, g.getEdge(7, 7));
		assertEquals(-1.0, g.getEdge(7, 8));
		assertEquals(-1.0, g.getEdge(7, 9));
		assertEquals(-2.0, g.getEdge(8, 7));
		assertEquals(8.8, g.getEdge(8, 8));
		assertEquals(8.9, g.getEdge(8, 9));
		assertEquals(-2.0, g.getEdge(9, 7));
		assertEquals(9.8, g.getEdge(9, 8));
		assertEquals(9.9, g.getEdge(9, 9));

		assertEquals(-1, g.removeNode(null));
		assertEquals(-4, g.addNode(null));

		assertEquals(0, g.addNode(10));
		assertEquals(-4.0, g.getEdge(8, 10));
		assertEquals(-4.0, g.getEdge(10, 9));

		assertEquals(-6, g.addNode(null));

		assertEquals(-12, g.addEdge(8, 8, -8.8));
		assertEquals(-9, g.addEdge(7, 8, -7.8));
		assertEquals(-10, g.addEdge(8, 7, -8.7));
		assertEquals(-11, g.addEdge(1, 7, -1.7));
		assertEquals(-1, g.addEdge(null, 8, 0.8));
		assertEquals(-2, g.addEdge(8, null, 0.8));
		assertEquals(-3, g.addEdge(null, null, 0.8));

		assertEquals(-1.0, g.getEdge(null, 10));
		assertEquals(-2.0, g.getEdge(10, null));
		assertEquals(-3.0, g.getEdge(null, null));

		assertEquals(false, g.existsNode(null));
		assertEquals(false, g.existsEdge(null, null));

	}
	/**
	 * Test para comprobar si el metodo de Floyd funciona bien
	 */
	@Test
	void testFloyd() {
		Graph<Integer> g = new Graph<Integer>(5);
		double infinito = Double.POSITIVE_INFINITY;
		assertEquals(0, g.addNode(1));
		assertEquals(0, g.addNode(2));
		assertEquals(0, g.addNode(3));
		assertEquals(0, g.addNode(4));
		assertEquals(0, g.addNode(5));
		assertEquals(0, g.addEdge(1, 2, 1));
		assertEquals(0, g.addEdge(1, 4, 3));
		assertEquals(0, g.addEdge(1, 5, 10));
		assertEquals(0, g.addEdge(2, 3, 5));
		assertEquals(0, g.addEdge(3, 5, 1));
		assertEquals(0, g.addEdge(4, 3, 2));
		assertEquals(0, g.addEdge(4, 5, 6));
		// No se lanzo floyd
		assertEquals(null, g.getAFloyd());
		assertEquals(null, g.getPFloyd());
		assertEquals(0, g.floyd());
		// Comparamos a
		assertEquals(0,g.getAFloyd()[0][0]);
		assertEquals(1,g.getAFloyd()[0][1]);
		assertEquals(5,g.getAFloyd()[0][2]);
		assertEquals(3,g.getAFloyd()[0][3]);
		assertEquals(6,g.getAFloyd()[0][4]);
		
		assertEquals(infinito,g.getAFloyd()[1][0]);
		assertEquals(0,g.getAFloyd()[1][1]);
		assertEquals(5,g.getAFloyd()[1][2]);
		assertEquals(infinito,g.getAFloyd()[1][3]);
		assertEquals(6,g.getAFloyd()[1][4]);
		
		assertEquals(infinito,g.getAFloyd()[2][0]);
		assertEquals(infinito,g.getAFloyd()[2][1]);
		assertEquals(0,g.getAFloyd()[2][2]);
		assertEquals(infinito,g.getAFloyd()[2][3]);
		assertEquals(1,g.getAFloyd()[2][4]);

		assertEquals(infinito,g.getAFloyd()[3][0]);
		assertEquals(infinito,g.getAFloyd()[3][1]);
		assertEquals(2,g.getAFloyd()[3][2]);
		assertEquals(0,g.getAFloyd()[3][3]);
		assertEquals(3,g.getAFloyd()[3][4]);
		
		assertEquals(infinito,g.getAFloyd()[4][0]);
		assertEquals(infinito,g.getAFloyd()[4][1]);
		assertEquals(infinito,g.getAFloyd()[4][2]);
		assertEquals(infinito,g.getAFloyd()[4][3]);
		assertEquals(0,g.getAFloyd()[4][4]);
		
		//Comparamos p
		assertEquals(-1,g.getPFloyd()[0][0]);
		assertEquals(-1,g.getPFloyd()[0][1]);
		assertEquals(3,g.getPFloyd()[0][2]);
		assertEquals(-1,g.getPFloyd()[0][3]);
		assertEquals(3,g.getPFloyd()[0][4]);
		
		assertEquals(-1,g.getPFloyd()[1][0]);
		assertEquals(-1,g.getPFloyd()[1][1]);
		assertEquals(-1,g.getPFloyd()[1][2]);
		assertEquals(-1,g.getPFloyd()[1][3]);
		assertEquals(2,g.getPFloyd()[1][4]);
		
		assertEquals(-1,g.getPFloyd()[2][0]);
		assertEquals(-1,g.getPFloyd()[2][1]);
		assertEquals(-1,g.getPFloyd()[2][2]);
		assertEquals(-1,g.getPFloyd()[2][3]);
		assertEquals(-1,g.getPFloyd()[2][4]);
		
		assertEquals(-1,g.getPFloyd()[3][0]);
		assertEquals(-1,g.getPFloyd()[3][1]);
		assertEquals(-1,g.getPFloyd()[3][2]);
		assertEquals(-1,g.getPFloyd()[3][3]);
		assertEquals(2,g.getPFloyd()[3][4]);
		
		assertEquals(-1,g.getPFloyd()[4][0]);
		assertEquals(-1,g.getPFloyd()[4][1]);
		assertEquals(-1,g.getPFloyd()[4][2]);
		assertEquals(-1,g.getPFloyd()[4][3]);
		assertEquals(-1,g.getPFloyd()[4][4]);
		
	}
	/**
	 * Test para comprobar que el metodo path funciona correctamente
	 */
	@Test
	void testPath() {
		Graph<Integer> g = new Graph<Integer>(5);
		assertEquals(0, g.addNode(1));
		assertEquals(0, g.addNode(2));
		assertEquals(0, g.addNode(3));
		assertEquals(0, g.addNode(4));
		assertEquals(0, g.addNode(5));
		assertEquals(0, g.addEdge(1, 2, 1));
		assertEquals(0, g.addEdge(1, 4, 3));
		assertEquals(0, g.addEdge(1, 5, 10));
		assertEquals(0, g.addEdge(2, 3, 5));
		assertEquals(0, g.addEdge(3, 5, 1));
		assertEquals(0, g.addEdge(4, 3, 2));
		assertEquals(0, g.addEdge(4, 5, 6));
		//No existen los dos nodos
		assertEquals("",g.path(6, 6));
		//No existe nodo inicial
		assertEquals("",g.path(6, 2));
		//No existe nodo destino
		assertEquals("",g.path(2, 6));
		//Los nodos inicio y destino con el mismo
		assertEquals("1\t",g.path(1, 1));
		assertEquals("3\t",g.path(3, 3));
		//No existe camino entre los nodos
		assertEquals("1\tInfinity\t2\t",g.path(1,2));
		assertEquals("3\tInfinity\t2\t",g.path(3,2));
		//Existe camino entre dos nodos
		assertEquals("2\t(5.0)\t3\t(1.0)\t5\t",g.path(2, 5));
		assertEquals("4\t(2.0)\t3\t(1.0)\t5\t",g.path(4, 5));
		assertEquals("1\t(3.0)\t4\t(2.0)\t3\t",g.path(1, 3));


	}
	
	/**
	 * Test para comprobar que el metodo minCostPath funciona bien
	 */
	@Test
	void testMinCostPath() {
		Graph<Integer> g = new Graph<Integer>(5);
		assertEquals(0, g.addNode(1));
		assertEquals(0, g.addNode(2));
		assertEquals(0, g.addNode(3));
		assertEquals(0, g.addNode(4));
		assertEquals(0, g.addNode(5));
		assertEquals(0, g.addEdge(1, 2, 1));
		assertEquals(0, g.addEdge(1, 4, 3));
		assertEquals(0, g.addEdge(1, 5, 10));
		assertEquals(0, g.addEdge(2, 3, 5));
		assertEquals(0, g.addEdge(3, 5, 1));
		assertEquals(0, g.addEdge(4, 3, 2));
		assertEquals(0, g.addEdge(4, 5, 6));
		
		//caso 1: a un nodo mismo
		assertEquals(0.0,g.minCostPath(1, 1));
		//caso 2: a un nodo con camino
		assertEquals(1.0,g.minCostPath(1, 2));
		assertEquals(3.0,g.minCostPath(4, 5));
		assertEquals(6.0,g.minCostPath(1, 5));
		//caso 3: a un nodo sin camino
		assertEquals(Double.POSITIVE_INFINITY,g.minCostPath(2, 1));
		assertEquals(Double.POSITIVE_INFINITY,g.minCostPath(5, 3));
		
	}
	
	/**
	 * Test para comprobar que el metodo recorridoProfundidad funciona correctamente
	 */
	@Test
	void testRecorridoProfundidad() {
		Graph<Integer> g = new Graph<Integer>(4);
		assertEquals(0, g.addNode(1));
		assertEquals(0, g.addNode(2));
		assertEquals(0, g.addNode(3));
		assertEquals(0, g.addNode(4));
		assertEquals(0, g.addEdge(1, 2, 2));
		assertEquals(0, g.addEdge(1, 3, 1));
		assertEquals(0, g.addEdge(2, 4, 5));
		assertEquals(0, g.addEdge(3, 2, 4));
		assertEquals(0, g.addEdge(4, 3, 6));
		assertEquals(0, g.addEdge(4, 4, 5));
		//Desde el nodo 1
		assertEquals("1\t2\t4\t3\t",g.recorridoProfundidad(1));
		//Desde el nodo 2
		assertEquals("2\t4\t3\t",g.recorridoProfundidad(2));
		//Desde el nodo 3
		assertEquals("3\t2\t4\t",g.recorridoProfundidad(3));
		//Desde el nodo 4
		assertEquals("4\t3\t2\t",g.recorridoProfundidad(4));


	}
}
