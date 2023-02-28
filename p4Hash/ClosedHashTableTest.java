package p4Hash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClosedHashTableTest {

	/**
	 * Metodo para probar la clase add con cada una de sus exploraciones, pero sin
	 * redispersiones
	 */
	@Test
	void testAddNoRedispersion() {
		// Exploracion Lineal
		ClosedHashTable<Integer> ht = new ClosedHashTable<>(7, 0);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(-1, ht.add(3));
		assertEquals("{-7};{0};{9};{7};{6};{-5};{-8};[Size: 7 Num.Elems.: 7]", ht.toString());
		// Borramos algun caso
		assertEquals(0, ht.remove(-7));
		assertEquals(6, ht.getNumOfElems());
		assertEquals(null, ht.find(-7));
		// Añadimos otro
		assertEquals(0, ht.add(73));
		assertEquals(7, ht.getNumOfElems());
		assertEquals(73, ht.find(73));

		// Exploracion Cuadratica
		ht = new ClosedHashTable<>(7, 1);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(-1, ht.add(-5));
		assertEquals(0, ht.add(3));
		assertEquals("{-7};{0};{9};{6};{7};{3};{-8};[Size: 7 Num.Elems.: 7]", ht.toString());
		// Borramos algun caso
		assertEquals(0, ht.remove(-7));
		assertEquals(6, ht.getNumOfElems());
		assertEquals(null, ht.find(-7));
		// Añadimos otro
		assertEquals(0, ht.add(73));
		assertEquals(7, ht.getNumOfElems());
		assertEquals(73, ht.find(73));
		// Exploracion DobleHash
		ht = new ClosedHashTable<>(7, 2);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(-1, ht.add(3));
		assertEquals("{-7};{-5};{9};{7};{6};{0};{-8};[Size: 7 Num.Elems.: 7]", ht.toString());
		// Borramos algun caso
		assertEquals(0, ht.remove(-7));
		assertEquals(6, ht.getNumOfElems());
		assertEquals(null, ht.find(-7));
		// Añadimos otro
		assertEquals(0, ht.add(73));
		assertEquals(7, ht.getNumOfElems());
		assertEquals(73, ht.find(73));
	}

	/**
	 * Metodo para probar la clase add con cada una de sus exploraciones, pero con
	 * redispersiones
	 */
	@Test
	void testAddRedispersion() {
		// Exploracion Lineal
		ClosedHashTable<Integer> ht = new ClosedHashTable<>(7, 0.5, 0.16, 0);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(7, ht.getSize());
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(17, ht.getSize());
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(0, ht.add(3));
		assertEquals(
				"{0};{_E_};{_E_};{3};{_E_};{_E_};{6};{7};{_E_};{9};{-7};{-8};{-5};{_E_};{_E_};{_E_};{_E_};[Size: 17 Num.Elems.: 8]",
				ht.toString());
		// Borramos algun caso
		assertEquals(0, ht.remove(-7));
		assertEquals(7, ht.getNumOfElems());
		assertEquals(null, ht.find(-7));
		// Añadimos otro
		assertEquals(0, ht.add(73));
		assertEquals(8, ht.getNumOfElems());
		assertEquals(73, ht.find(73));

		// Exploracion Cuadratica
		ht = new ClosedHashTable<>(7, 0.5, 0.16, 1);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(7, ht.getSize());
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(17, ht.getSize());
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(0, ht.add(3));
		assertEquals(
				"{0};{_E_};{_E_};{3};{_E_};{_E_};{6};{7};{_E_};{9};{-7};{_E_};{-5};{-8};{_E_};{_E_};{_E_};[Size: 17 Num.Elems.: 8]",
				ht.toString());
		// Borramos algun caso
		assertEquals(0, ht.remove(-7));
		assertEquals(7, ht.getNumOfElems());
		assertEquals(null, ht.find(-7));
		// Añadimos otro
		assertEquals(0, ht.add(73));
		assertEquals(8, ht.getNumOfElems());
		assertEquals(73, ht.find(73));
		// Exploracion DobleHash
		ht = new ClosedHashTable<>(7, 0.5, 0.16, 2);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(7, ht.getSize());
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(17, ht.getSize());
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(0, ht.add(3));
		assertEquals(
				"{0};{_E_};{_E_};{3};{_E_};{_E_};{6};{7};{_E_};{9};{-7};{_E_};{-5};{-8};{_E_};{_E_};{_E_};[Size: 17 Num.Elems.: 8]",
				ht.toString());
		// Borramos algun caso
		assertEquals(0, ht.remove(-7));
		assertEquals(7, ht.getNumOfElems());
		assertEquals(null, ht.find(-7));
		// Añadimos otro
		assertEquals(0, ht.add(73));
		assertEquals(8, ht.getNumOfElems());
		assertEquals(73, ht.find(73));
	}

	/**
	 * Metodo para probar la clase find con cada una de sus exploraciones, pero con
	 * redispersiones
	 */
	@Test
	void testFindRedispersion() {
		// Exploracion Lineal
		ClosedHashTable<Integer> ht = new ClosedHashTable<>(7, 0.5, 0.16, 0);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(0, ht.add(3));
		assertEquals(
				"{0};{_E_};{_E_};{3};{_E_};{_E_};{6};{7};{_E_};{9};{-7};{-8};{-5};{_E_};{_E_};{_E_};{_E_};[Size: 17 Num.Elems.: 8]",
				ht.toString());
		assertEquals(null, ht.find(null));
		assertEquals(0, ht.find(0));
		assertEquals(7, ht.find(7));
		assertEquals(-8, ht.find(-8));
		assertEquals(6, ht.find(6));
		assertEquals(null, ht.find(123213));

		// Exploracion Cuadratica
		ht = new ClosedHashTable<>(7, 0.5, 0.16, 1);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(0, ht.add(3));
		assertEquals(
				"{0};{_E_};{_E_};{3};{_E_};{_E_};{6};{7};{_E_};{9};{-7};{_E_};{-5};{-8};{_E_};{_E_};{_E_};[Size: 17 Num.Elems.: 8]",
				ht.toString());
		assertEquals(null, ht.find(null));
		assertEquals(0, ht.find(0));
		assertEquals(7, ht.find(7));
		assertEquals(-8, ht.find(-8));
		assertEquals(6, ht.find(6));
		assertEquals(null, ht.find(123213));
		// Exploracion DobleHash
		ht = new ClosedHashTable<>(7, 0.5, 0.16, 2);
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(0, ht.add(3));
		assertEquals(
				"{0};{_E_};{_E_};{3};{_E_};{_E_};{6};{7};{_E_};{9};{-7};{_E_};{-5};{-8};{_E_};{_E_};{_E_};[Size: 17 Num.Elems.: 8]",
				ht.toString());
		assertEquals(null, ht.find(null));
		assertEquals(0, ht.find(0));
		assertEquals(7, ht.find(7));
		assertEquals(-8, ht.find(-8));
		assertEquals(6, ht.find(6));
		assertEquals(null, ht.find(123213));
	}

	/**
	 * Metodo para probar la clase find con cada una de sus exploraciones, pero sin
	 * redispersiones
	 */
	@Test
	void testFindNoRedispersion() {
		// Exploracion Lineal
		ClosedHashTable<Integer> ht = new ClosedHashTable<>(7, 0);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(-1, ht.add(3));
		assertEquals("{-7};{0};{9};{7};{6};{-5};{-8};[Size: 7 Num.Elems.: 7]", ht.toString());
		assertEquals(null, ht.find(null));
		assertEquals(0, ht.find(0));
		assertEquals(7, ht.find(7));
		assertEquals(-8, ht.find(-8));
		assertEquals(6, ht.find(6));
		assertEquals(null, ht.find(123213));

		// Exploracion Cuadratica
		ht = new ClosedHashTable<>(7, 1);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(-1, ht.add(-5));
		assertEquals(0, ht.add(3));
		assertEquals("{-7};{0};{9};{6};{7};{3};{-8};[Size: 7 Num.Elems.: 7]", ht.toString());
		assertEquals(null, ht.find(null));
		assertEquals(0, ht.find(0));
		assertEquals(7, ht.find(7));
		assertEquals(-8, ht.find(-8));
		assertEquals(6, ht.find(6));
		assertEquals(null, ht.find(123213));
		// Exploracion DobleHash
		ht = new ClosedHashTable<>(7, 2);
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(-1, ht.add(3));
		assertEquals("{-7};{-5};{9};{7};{6};{0};{-8};[Size: 7 Num.Elems.: 7]", ht.toString());
		assertEquals(null, ht.find(null));
		assertEquals(0, ht.find(0));
		assertEquals(7, ht.find(7));
		assertEquals(-8, ht.find(-8));
		assertEquals(6, ht.find(6));
		assertEquals(null, ht.find(123213));
	}

	/**
	 * Metodo para probar la clase remove con cada una de sus exploraciones, pero
	 * sin redispersiones
	 */
	@Test
	void testRemoveNoRedispersion() {
		// Exploracion Lineal
		ClosedHashTable<Integer> ht = new ClosedHashTable<>(7, 0);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(-1, ht.add(3));
		assertEquals("{-7};{0};{9};{7};{6};{-5};{-8};[Size: 7 Num.Elems.: 7]", ht.toString());
		assertEquals(7, ht.getNumOfElems());
		assertEquals(-2, ht.remove(null));
		assertEquals(-1, ht.remove(4423423));
		// Borramos algun caso
		assertEquals(0, ht.remove(-7));
		assertEquals(6, ht.getNumOfElems());
		assertEquals(null, ht.find(-7));

		// Borramos otro caso
		assertEquals(0, ht.remove(7));
		assertEquals(5, ht.getNumOfElems());
		assertEquals(null, ht.find(7));

		// Exploracion Cuadratica
		ht = new ClosedHashTable<>(7, 1);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(-1, ht.add(-5));
		assertEquals(0, ht.add(3));
		assertEquals("{-7};{0};{9};{6};{7};{3};{-8};[Size: 7 Num.Elems.: 7]", ht.toString());
		assertEquals(7, ht.getNumOfElems());
		assertEquals(-2, ht.remove(null));
		assertEquals(-1, ht.remove(4423423));
		// Borramos algun caso
		assertEquals(0, ht.remove(-7));
		assertEquals(6, ht.getNumOfElems());
		assertEquals(null, ht.find(-7));

		// Borramos otro caso
		assertEquals(0, ht.remove(7));
		assertEquals(5, ht.getNumOfElems());
		assertEquals(null, ht.find(7));

		// Exploracion DobleHash
		ht = new ClosedHashTable<>(7, 2);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(-1, ht.add(3));
		assertEquals("{-7};{-5};{9};{7};{6};{0};{-8};[Size: 7 Num.Elems.: 7]", ht.toString());
		assertEquals(7, ht.getNumOfElems());
		assertEquals(-2, ht.remove(null));
		assertEquals(-1, ht.remove(4423423));
		// Borramos algun caso
		assertEquals(0, ht.remove(-7));
		assertEquals(6, ht.getNumOfElems());
		assertEquals(null, ht.find(-7));

		// Borramos otro caso
		assertEquals(0, ht.remove(7));
		assertEquals(5, ht.getNumOfElems());
		assertEquals(null, ht.find(7));

	}

	/**
	 * Metodo para probar la clase remove con cada una de sus exploraciones, pero
	 * con redispersiones
	 */
	@Test
	void testRemoveRedispersion() {
		// Exploracion Lineal
		ClosedHashTable<Integer> ht = new ClosedHashTable<>(7, 0.5, 0.16, 0);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(0, ht.add(3));
		assertEquals(
				"{0};{_E_};{_E_};{3};{_E_};{_E_};{6};{7};{_E_};{9};{-7};{-8};{-5};{_E_};{_E_};{_E_};{_E_};[Size: 17 Num.Elems.: 8]",
				ht.toString());
		assertEquals(8, ht.getNumOfElems());
		assertEquals(-2, ht.remove(null));
		assertEquals(-1, ht.remove(4423423));
		// Borramos algun caso
		assertEquals(0, ht.remove(-7));
		assertEquals(7, ht.getNumOfElems());
		assertEquals(null, ht.find(-7));

		// Borramos otro caso
		assertEquals(0, ht.remove(7));
		assertEquals(6, ht.getNumOfElems());
		assertEquals(null, ht.find(7));

		// Exploracion Cuadratica
		ht = new ClosedHashTable<>(7, 0.5, 0.16, 1);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(0, ht.add(3));
		assertEquals(
				"{0};{_E_};{_E_};{3};{_E_};{_E_};{6};{7};{_E_};{9};{-7};{_E_};{-5};{-8};{_E_};{_E_};{_E_};[Size: 17 Num.Elems.: 8]",
				ht.toString());
		assertEquals(8, ht.getNumOfElems());
		assertEquals(-2, ht.remove(null));
		assertEquals(-1, ht.remove(4423423));
		// Borramos algun caso
		assertEquals(0, ht.remove(-7));
		assertEquals(7, ht.getNumOfElems());
		assertEquals(null, ht.find(-7));

		// Borramos otro caso
		assertEquals(0, ht.remove(7));
		assertEquals(6, ht.getNumOfElems());
		assertEquals(null, ht.find(7));

		// Exploracion DobleHash
		ht = new ClosedHashTable<>(7, 0.5, 0.16, 2);
		assertEquals(-2, ht.add(null));
		assertEquals(0, ht.add(-7));
		assertEquals(0, ht.add(0));
		assertEquals(0, ht.add(9));
		assertEquals(0, ht.add(7));
		assertEquals(0, ht.add(-8));
		assertEquals(0, ht.add(6));
		assertEquals(0, ht.add(-5));
		assertEquals(0, ht.add(3));
		assertEquals(
				"{0};{_E_};{_E_};{3};{_E_};{_E_};{6};{7};{_E_};{9};{-7};{_E_};{-5};{-8};{_E_};{_E_};{_E_};[Size: 17 Num.Elems.: 8]",
				ht.toString());
		assertEquals(8, ht.getNumOfElems());
		assertEquals(-2, ht.remove(null));
		assertEquals(-1, ht.remove(4423423));
		// Borramos algun caso
		assertEquals(0, ht.remove(-7));
		assertEquals(7, ht.getNumOfElems());
		assertEquals(null, ht.find(-7));

		// Borramos otro caso
		assertEquals(0, ht.remove(7));
		assertEquals(6, ht.getNumOfElems());
		assertEquals(null, ht.find(7));

	}

}
