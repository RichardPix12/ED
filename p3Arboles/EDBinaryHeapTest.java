package p3Arboles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class EDBinaryHeapTest {

	@Test
	void testAdd() {
		EDBinaryHeap<Integer> bh = new EDBinaryHeap<>(10);
		//Inserta un null
		assertEquals(-2, bh.add(null));
		assertEquals(0, bh.add(10));
		assertEquals(0, bh.add(12));
		assertEquals(0, bh.add(1));
		assertEquals(0, bh.add(3));
		assertEquals(0, bh.add(4));
		//Inserta un null por la mitad
		assertEquals(-2, bh.add(null));
		assertEquals(0, bh.add(22));
		assertEquals(0, bh.add(223));
		assertEquals(0, bh.add(3));
		assertEquals(0, bh.add(2));
		assertEquals(0, bh.add(4));
		//Supera el limite 
		assertEquals(-1, bh.add(1));
		//Inserta un null
		assertEquals(-3, bh.add(null));
	}
	
	@Test
	void testPoll() {
		EDBinaryHeap<Integer> bh = new EDBinaryHeap<>(11);
		assertEquals(0, bh.add(10));
		assertEquals(0, bh.add(20));
		assertEquals(0, bh.add(10));
		assertEquals(0, bh.add(15));
		assertEquals(0, bh.add(14));
		assertEquals(0, bh.add(5));
		assertEquals(0, bh.add(8));
		assertEquals(0, bh.add(12));
		assertEquals(0, bh.add(2));
		assertEquals(0, bh.add(2));
		assertEquals(0, bh.add(1));
		//Borramos una vez la base
		assertEquals(1, bh.poll());
		Integer[] arrayDespuesDePoll = new Integer[] {2,2,8,12,5,10,10,20,14,15};
		assertArrayEquals(arrayDespuesDePoll, Arrays.copyOfRange((Comparable<Integer>[])bh.elementos, 0, bh.numElementos));
		assertEquals(10,bh.numElementos);
		//Borramos otra vez la base
		assertEquals(2, bh.poll());
		arrayDespuesDePoll = new Integer[] {2,5,8,12,15,10,10,20,14};
		assertArrayEquals(arrayDespuesDePoll, Arrays.copyOfRange((Comparable<Integer>[])bh.elementos, 0, bh.numElementos));
		assertEquals(9,bh.numElementos);
		
		
	}
	
	@Test
	void testIsEmpty() {
		
		EDBinaryHeap<Integer> bh = new EDBinaryHeap<>(10);
		//No se añade nada
		assertEquals(true, bh.isEmpty());
		//Se añaden nodos
		assertEquals(0, bh.add(8));
		assertEquals(0, bh.add(31));
		assertEquals(0, bh.add(1));
		assertEquals(false, bh.isEmpty());
		//Se eliminan nodos
		assertEquals(0, bh.remove(8));
		assertEquals(0, bh.remove(31));
		assertEquals(false, bh.isEmpty());
		//Se eliminan todos
		assertEquals(0, bh.remove(1));
		assertEquals(true, bh.isEmpty());
	}
	
	@Test
	void testClear() {
		EDBinaryHeap<Integer> bh = new EDBinaryHeap<>(10);
		//No se añade nada
		assertEquals(true, bh.isEmpty());
		//Se añaden nodos
		assertEquals(0, bh.add(8));
		assertEquals(0, bh.add(31));
		assertEquals(0, bh.add(1));
		assertEquals(false, bh.isEmpty());
		//Se hace un clear
		bh.clear();
		assertEquals(true, bh.isEmpty());
	}
	@Test
	void testRemove() {
		EDBinaryHeap<Integer> bh = new EDBinaryHeap<>(11);
		assertEquals(true, bh.isEmpty());
		//Nodo invalido
		assertEquals(-3, bh.remove(null));
		//Nodo inexistente
		assertEquals(-1, bh.remove(345));
		assertEquals(0, bh.add(10));
		assertEquals(0, bh.add(20));
		assertEquals(0, bh.add(10));
		assertEquals(0, bh.add(15));
		assertEquals(0, bh.add(14));
		assertEquals(0, bh.add(5));
		assertEquals(0, bh.add(8));
		assertEquals(0, bh.add(12));
		assertEquals(0, bh.add(2));
		assertEquals(0, bh.add(2));
		assertEquals(0, bh.add(1));
		assertEquals(false, bh.isEmpty());
		//Borramos nodo invalido
		assertEquals(-3, bh.remove(null));
		//Borramos nodo no existe
		assertEquals(-1, bh.remove(8885));
		//Borramos el nodo raiz
		assertEquals(1, bh.poll());
		Integer[] arrayDespuesDePoll = new Integer[] {2,2,8,12,5,10,10,20,14,15};
		assertArrayEquals(arrayDespuesDePoll, Arrays.copyOfRange((Comparable<Integer>[])bh.elementos, 0, bh.numElementos));
		assertEquals(10,bh.numElementos);
		//Borramos un nodo no raiz
		assertEquals(0, bh.remove(2));		
		arrayDespuesDePoll = new Integer[] {2,5,8,12,15,10,10,20,14};
		assertArrayEquals(arrayDespuesDePoll, Arrays.copyOfRange((Comparable<Integer>[])bh.elementos, 0, bh.numElementos));
		assertEquals(9,bh.numElementos);
		//Borramos un nodo final
		assertEquals(0, bh.remove(14));		
		arrayDespuesDePoll = new Integer[] {2,5,8,12,15,10,10,20};
		assertArrayEquals(arrayDespuesDePoll, Arrays.copyOfRange((Comparable<Integer>[])bh.elementos, 0, bh.numElementos));
		assertEquals(8,bh.numElementos);
		
		//Borramos un nodo de la zona final
		assertEquals(0, bh.remove(10));		
		arrayDespuesDePoll = new Integer[] {2,5,8,12,15,20,10};
		assertArrayEquals(arrayDespuesDePoll, Arrays.copyOfRange((Comparable<Integer>[])bh.elementos, 0, bh.numElementos));
		assertEquals(7,bh.numElementos);
		
		//Borramos un nodo central
		assertEquals(0, bh.remove(15));		
		arrayDespuesDePoll = new Integer[] {2,5,8,12,10,20};
		assertArrayEquals(arrayDespuesDePoll, Arrays.copyOfRange((Comparable<Integer>[])bh.elementos, 0, bh.numElementos));
		assertEquals(6,bh.numElementos);
		
		//Borramos el nodo raiz de nuevo
		assertEquals(2, bh.poll());		
		arrayDespuesDePoll = new Integer[] {5,10,8,12,20};
		assertArrayEquals(arrayDespuesDePoll, Arrays.copyOfRange((Comparable<Integer>[])bh.elementos, 0, bh.numElementos));
		assertEquals(5,bh.numElementos);
		
		//Borramos el nodo raiz de nuevo dos veces
		assertEquals(5, bh.poll());
		assertEquals(8, bh.poll());
		arrayDespuesDePoll = new Integer[] {10,12,20};
		assertArrayEquals(arrayDespuesDePoll, Arrays.copyOfRange((Comparable<Integer>[])bh.elementos, 0, bh.numElementos));
		assertEquals(3,bh.numElementos);
		
		//Borramos el nodo final 
		assertEquals(0, bh.remove(20));
		arrayDespuesDePoll = new Integer[] {10,12};
		assertArrayEquals(arrayDespuesDePoll, Arrays.copyOfRange((Comparable<Integer>[])bh.elementos, 0, bh.numElementos));
		assertEquals(2,bh.numElementos);
		
		assertEquals(0, bh.remove(10));
		assertEquals(0, bh.remove(12));
		
		assertEquals(true, bh.isEmpty());
	}
	

}
