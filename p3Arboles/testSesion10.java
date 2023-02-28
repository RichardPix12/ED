package p3Arboles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testSesion10 {

	/**
	 * Metodo de prueba para el probar el metodo remove node y las rotaciones al
	 * eliminarlo cuando hay un fb -2 y su hijo 1
	 */
	@Test
	void testRemoveMenos2_1() {
		AVLTree<Integer> tree = new AVLTree<>();
		tree.addNode(5);
		tree.addNode(6);
		tree.addNode(2);
		tree.addNode(3);
		tree.removeNode(6);
		assertEquals("3:BF=0\t2:BF=0\t5:BF=0\t", tree.preOrder());
	}

	/**
	 * Metodo de prueba para el probar el metodo remove node y las rotaciones al
	 * eliminarlo cuando hay un fb -2 y su hijo 0
	 */
	@Test
	void testRemoveMenos2_0() {
		AVLTree<Integer> tree = new AVLTree<>();
		tree.addNode(5);
		tree.addNode(6);
		tree.addNode(2);
		tree.addNode(3);
		tree.addNode(1);
		tree.removeNode(6);
		assertEquals("2:BF=1\t1:BF=0\t5:BF=-1\t3:BF=0\t", tree.preOrder());

	}

	/**
	 * Metodo de prueba para el probar el metodo remove node y las rotaciones al
	 * eliminarlo cuando hay un fb -2 y su hijo sea diferente a 1 o 0
	 */
	@Test
	void testRemoveMenos2_diferente() {
		AVLTree<Integer> tree = new AVLTree<>();
		tree.addNode(5);
		tree.addNode(6);
		tree.addNode(2);
		tree.addNode(1);

		tree.removeNode(6);
		assertEquals("2:BF=0\t1:BF=0\t5:BF=0\t", tree.preOrder());

	}

	/**
	 * Metodo de prueba para el probar el metodo remove node y las rotaciones al
	 * eliminarlo cuando hay un fb 2 y su hijo -1
	 */
	@Test
	void testRemove2_Menos1() {
		AVLTree<Integer> tree = new AVLTree<>();
		tree.addNode(5);
		tree.addNode(2);
		tree.addNode(8);
		tree.addNode(6);
		tree.removeNode(2);
		assertEquals("6:BF=0\t5:BF=0\t8:BF=0\t", tree.preOrder());

	}

	/**
	 * Metodo de prueba para el probar el metodo remove node y las rotaciones al
	 * eliminarlo cuando hay un fb 2 y su hijo 0
	 */
	@Test
	void testRemove2_0() {
		AVLTree<Integer> tree = new AVLTree<>();
		tree.addNode(7);
		tree.addNode(1);
		tree.addNode(10);
		tree.addNode(11);
		tree.addNode(8);
		tree.removeNode(1);

		assertEquals("10:BF=-1\t7:BF=1\t8:BF=0\t11:BF=0\t", tree.preOrder());
	}

	/**
	 * Metodo de prueba para el probar el metodo remove node y las rotaciones al
	 * eliminarlo cuando hay un fb 2 y su hijo sea diferente a -1 o 0
	 */
	@Test
	void testRemove2_diferente() {
		AVLTree<Integer> tree = new AVLTree<>();
		tree.addNode(5);
		tree.addNode(1);
		tree.addNode(6);
		tree.addNode(7);
		tree.removeNode(1);
		assertEquals("6:BF=0\t5:BF=0\t7:BF=0\t", tree.preOrder());

	}

}
