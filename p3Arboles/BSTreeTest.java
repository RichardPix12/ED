package p3Arboles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BSTreeTest {

	/**
	 * Metodo de prueba para comprobar que se añaden nodos de forma correcta
	 */
	@Test
	void testAddNode() {
		BSTree<Integer> tree = new BSTree<Integer>();
		assertEquals(-2, tree.addNode(null));

		// Añadir nodos que no existe
		assertEquals(0, tree.addNode(5));
		assertEquals(0, tree.addNode(2));
		assertEquals(0, tree.addNode(7));
		assertEquals(0, tree.addNode(3));
		assertEquals(0, tree.addNode(1));
		assertEquals(0, tree.addNode(4));
		assertEquals(0, tree.addNode(8));
		assertEquals(0, tree.addNode(6));
		assertEquals(0, tree.addNode(10));
		assertEquals(0, tree.addNode(9));
		assertEquals(0, tree.addNode(11));
		// Añadir nodos que si existe
		assertEquals(-1, tree.addNode(10));
		assertEquals(-1, tree.addNode(9));
		assertEquals(-1, tree.addNode(11));
		assertEquals(-2, tree.addNode(null));
	}

	/**
	 * Metodo de prueba para comprobar que se buscan nodos de forma correcta
	 */
	@Test
	void testSearchNode() {
		BSTree<Integer> tree = new BSTree<Integer>();

		assertEquals(null, tree.searchNode(null));

		// Añadir nodos que no existe
		assertEquals(0, tree.addNode(5));
		assertEquals(0, tree.addNode(2));
		assertEquals(0, tree.addNode(7));
		assertEquals(0, tree.addNode(3));
		assertEquals(0, tree.addNode(1));
		assertEquals(0, tree.addNode(4));
		assertEquals(0, tree.addNode(8));
		assertEquals(0, tree.addNode(6));
		assertEquals(0, tree.addNode(10));
		assertEquals(0, tree.addNode(9));
		assertEquals(0, tree.addNode(11));

		// Buscamos el nodo raiz
		assertEquals(5, tree.searchNode(5));
		// Buscamos un nodo en la izquierda
		assertEquals(3, tree.searchNode(3));
		// Buscamos un nodo en la derecha
		assertEquals(7, tree.searchNode(7));
		// Buscamos un nodo que no existe por la derecha
		assertEquals(null, tree.searchNode(15));
		// Buscamos un nodo que no existe por la izquierda
		assertEquals(null, tree.searchNode(-4));

		assertEquals(null, tree.searchNode(null));

	}

	/**
	 * Metodo de prueba para comprobar que se buscan nodos en forma preOrder nodos
	 * de forma correcta
	 */
	@Test
	void testSearchPreOrder() {
		BSTree<Integer> tree = new BSTree<Integer>();
		// Añadir nodos
		assertEquals(0, tree.addNode(5));
		assertEquals(0, tree.addNode(2));
		assertEquals(0, tree.addNode(7));
		assertEquals(0, tree.addNode(3));
		assertEquals(0, tree.addNode(1));
		assertEquals(0, tree.addNode(4));
		assertEquals(0, tree.addNode(8));
		assertEquals(0, tree.addNode(6));
		assertEquals(0, tree.addNode(10));
		assertEquals(0, tree.addNode(9));
		assertEquals(0, tree.addNode(11));
		// Comprobacion
		assertEquals("5\t2\t1\t3\t4\t7\t6\t8\t10\t9\t11\t", tree.preOrder());

	}

	/**
	 * Metodo de prueba para comprobar que se buscan nodos en forma inOrder nodos de
	 * forma correcta
	 */
	@Test
	void testSearchInOrder() {
		BSTree<Integer> tree = new BSTree<Integer>();
		// Añadir nodos
		assertEquals(0, tree.addNode(5));
		assertEquals(0, tree.addNode(2));
		assertEquals(0, tree.addNode(7));
		assertEquals(0, tree.addNode(3));
		assertEquals(0, tree.addNode(1));
		assertEquals(0, tree.addNode(4));
		assertEquals(0, tree.addNode(8));
		assertEquals(0, tree.addNode(6));
		assertEquals(0, tree.addNode(10));
		assertEquals(0, tree.addNode(9));
		assertEquals(0, tree.addNode(11));
		// Comprobacion
		assertEquals("1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t", tree.inOrder());

	}

	/**
	 * Metodo de prueba para comprobar que se buscan nodos en forma postOrder nodos
	 * de forma correcta
	 */
	@Test
	void testSearchPostOrder() {
		BSTree<Integer> tree = new BSTree<Integer>();
		// Añadir nodos
		assertEquals(0, tree.addNode(5));
		assertEquals(0, tree.addNode(2));
		assertEquals(0, tree.addNode(7));
		assertEquals(0, tree.addNode(3));
		assertEquals(0, tree.addNode(1));
		assertEquals(0, tree.addNode(4));
		assertEquals(0, tree.addNode(8));
		assertEquals(0, tree.addNode(6));
		assertEquals(0, tree.addNode(10));
		assertEquals(0, tree.addNode(9));
		assertEquals(0, tree.addNode(11));
		// Comprobacion
		assertEquals("1\t4\t3\t2\t6\t9\t11\t10\t8\t7\t5\t", tree.postOrder());

	}

	/**
	 * Metodo de prueba para comprobar que se borran nodos de forma correcta
	 */
	@Test
	void testRemoveNode() {
		BSTree<Integer> tree = new BSTree<Integer>();
		//Si no existe el nodo
		assertEquals(-2, tree.removeNode(null));
		assertEquals(-1, tree.removeNode(2));
		// Añadir nodos
		assertEquals(0, tree.addNode(5));
		assertEquals(0, tree.addNode(2));
		assertEquals(0, tree.addNode(7));
		assertEquals(0, tree.addNode(3));
		assertEquals(0, tree.addNode(1));
		assertEquals(0, tree.addNode(4));
		assertEquals(0, tree.addNode(8));
		assertEquals(0, tree.addNode(6));
		assertEquals(0, tree.addNode(10));
		assertEquals(0, tree.addNode(9));
		assertEquals(0, tree.addNode(11));
		// Borrar nodos
		// Borro raiz y busco si se recoloca todo
		assertEquals(0, tree.removeNode(5));
		assertEquals(null, tree.searchNode(5));
		assertEquals(4, tree.getRoot().getInfo());
		assertEquals(2, tree.getRoot().getLeft().getInfo());
		assertEquals(7, tree.getRoot().getRight().getInfo());

		// Borro nodo sin hijos

		assertEquals(0, tree.removeNode(9));
		assertEquals(null, tree.searchNode(9));
		assertEquals("4\t2\t1\t3\t7\t6\t8\t10\t11\t", tree.preOrder());

		// Borro nodo con solo un hijo

		assertEquals(0, tree.removeNode(8));
		assertEquals(null, tree.searchNode(8));
		assertEquals("4\t2\t1\t3\t7\t6\t10\t11\t", tree.preOrder());
	}

}
