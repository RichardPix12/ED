package p3Arboles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AVLTreeTest {

	/**
	 * Metodo de prueba para probar el metodo add node y todas las rotaciones al
	 * añadir nodo
	 */
	@Test
	void testAdd() {
		AVLTree<Integer> tree = new AVLTree<>();
		// Añadir nodos que no existe
		assertEquals(-2, tree.addNode(null));
		// Añadir nodos raiz
		assertEquals(0, tree.addNode(5));
		// Añado dos mayores para que haya un balance
		assertEquals(0, tree.addNode(7));
		assertEquals(0, tree.addNode(10));
		assertEquals("7:BF=0\t5:BF=0\t10:BF=0\t", tree.preOrder());
		// Añado dos menores para forzar a que haya mas balances y haya cambios en lo
		assertEquals(0, tree.addNode(2));
		assertEquals(0, tree.addNode(3));
		assertEquals("7:BF=-1\t3:BF=0\t2:BF=0\t5:BF=0\t10:BF=0\t", tree.preOrder());
		// Añado mas pequeños para cambiar la raiz
		assertEquals(0, tree.addNode(1));
		assertEquals("3:BF=0\t2:BF=-1\t1:BF=0\t7:BF=0\t5:BF=0\t10:BF=0\t", tree.preOrder());
		// Añado todo mayores para seguir forzando cambios y balances
		assertEquals(0, tree.addNode(4));
		assertEquals(0, tree.addNode(13));
		assertEquals(0, tree.addNode(6));
		assertEquals(0, tree.addNode(22));
		assertEquals(0, tree.addNode(21));
		assertEquals(0, tree.addNode(23));
		assertEquals(
				"7:BF=0\t3:BF=0\t2:BF=-1\t1:BF=0\t5:BF=0\t4:BF=0\t6:BF=0\t13:BF=1\t10:BF=0\t22:BF=0\t21:BF=0\t23:BF=0\t",
				tree.preOrder());
		// Añadir nodos que si existe
		assertEquals(-1, tree.addNode(21));
		assertEquals(-1, tree.addNode(23));
		// Añadir nodos null
		assertEquals(-2, tree.addNode(null));
	}

	/**
	 * Metodo de prueba para comprobar que se buscan nodos de forma correcta
	 */
	@Test
	void testSearchNode() {
		AVLTree<Integer> tree = new AVLTree<Integer>();

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
	 * Metodo para probar que el metodo preOrder tambien funciona en este árbol
	 */
	@Test
	void testPreOrder() {
		AVLTree<Integer> tree = new AVLTree<>();
		assertEquals(0, tree.addNode(4));
		assertEquals(0, tree.addNode(13));
		assertEquals(0, tree.addNode(6));
		assertEquals(0, tree.addNode(22));
		assertEquals(0, tree.addNode(21));
		assertEquals(0, tree.addNode(23));
		assertEquals("21:BF=0\t6:BF=0\t4:BF=0\t13:BF=0\t22:BF=1\t23:BF=0\t", tree.preOrder());
		assertEquals(0, tree.addNode(1));
		assertEquals(0, tree.addNode(25));
		assertEquals("21:BF=-1\t6:BF=-1\t4:BF=-1\t1:BF=0\t13:BF=0\t23:BF=0\t22:BF=0\t25:BF=0\t", tree.preOrder());
	}

	/**
	 * Metodo para probar que el metodo postOrder tambien funciona en este árbol
	 */
	@Test
	void testPostOrder() {
		AVLTree<Integer> tree = new AVLTree<>();
		assertEquals(0, tree.addNode(4));
		assertEquals(0, tree.addNode(13));
		assertEquals(0, tree.addNode(6));
		assertEquals(0, tree.addNode(22));
		assertEquals(0, tree.addNode(21));
		assertEquals(0, tree.addNode(23));
		assertEquals("4:BF=0\t13:BF=0\t6:BF=0\t23:BF=0\t22:BF=1\t21:BF=0\t", tree.postOrder());
		assertEquals(0, tree.addNode(1));
		assertEquals(0, tree.addNode(25));
		assertEquals("1:BF=0\t4:BF=-1\t13:BF=0\t6:BF=-1\t22:BF=0\t25:BF=0\t23:BF=0\t21:BF=-1\t", tree.postOrder());
	}

	/**
	 * Metodo para probar que el metodo inOrder tambien funciona en este árbol
	 */
	@Test
	void testInOrder() {
		AVLTree<Integer> tree = new AVLTree<>();
		assertEquals(0, tree.addNode(4));
		assertEquals(0, tree.addNode(13));
		assertEquals(0, tree.addNode(6));
		assertEquals(0, tree.addNode(22));
		assertEquals(0, tree.addNode(21));
		assertEquals(0, tree.addNode(23));
		assertEquals("4:BF=0\t6:BF=0\t13:BF=0\t21:BF=0\t22:BF=1\t23:BF=0\t", tree.inOrder());
		assertEquals(0, tree.addNode(1));
		assertEquals(0, tree.addNode(25));
		assertEquals("1:BF=0\t4:BF=-1\t6:BF=-1\t13:BF=0\t21:BF=-1\t22:BF=0\t23:BF=0\t25:BF=0\t", tree.inOrder());
	}

	/**
	 * Metodo de prueba para el probar el metodo remove node y las rotaciones al
	 * eliminarlo
	 */
	@Test
	void testRemove() {
		AVLTree<Integer> tree = new AVLTree<>();
		//Si no existe el nodo
		assertEquals(-2, tree.removeNode(null));
		assertEquals(-1, tree.removeNode(5));
		// - 2 1
		tree = new AVLTree<>();
		tree.addNode(5);
		tree.addNode(6);
		tree.addNode(2);
		tree.addNode(3);
		tree.removeNode(6);
		assertEquals("3:BF=0\t2:BF=0\t5:BF=0\t", tree.preOrder());

		// -2 0
		tree = new AVLTree<>();
		tree.addNode(5);
		tree.addNode(6);
		tree.addNode(2);
		tree.addNode(3);
		tree.addNode(1);
		tree.removeNode(6);
		assertEquals("2:BF=1\t1:BF=0\t5:BF=-1\t3:BF=0\t", tree.preOrder());

		// -2 , lo que sea
		tree = new AVLTree<>();
		tree.addNode(5);
		tree.addNode(6);
		tree.addNode(2);
		tree.addNode(1);

		tree.removeNode(6);
		assertEquals("2:BF=0\t1:BF=0\t5:BF=0\t", tree.preOrder());

		// 2, -1
		tree = new AVLTree<>();
		tree.addNode(5);
		tree.addNode(2);
		tree.addNode(8);
		tree.addNode(6);
		tree.removeNode(2);
		assertEquals("6:BF=0\t5:BF=0\t8:BF=0\t", tree.preOrder());

		// 2, 0
		tree = new AVLTree<>();
		tree.addNode(7);
		tree.addNode(1);
		tree.addNode(10);
		tree.addNode(11);
		tree.addNode(8);
		tree.removeNode(1);

		assertEquals("10:BF=-1\t7:BF=1\t8:BF=0\t11:BF=0\t", tree.preOrder());

		// 2, lo que sea

		tree = new AVLTree<>();
		tree.addNode(5);
		tree.addNode(1);
		tree.addNode(6);
		tree.addNode(7);
		tree.removeNode(1);
		assertEquals("6:BF=0\t5:BF=0\t7:BF=0\t", tree.preOrder());

	}

}
