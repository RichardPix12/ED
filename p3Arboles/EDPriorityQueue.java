package p3Arboles;

/**
 * @author Profesores ED
 * @version 2021-22
 */
public interface EDPriorityQueue<T extends Comparable<T>> {

	/**
	 * Se le pasa el elemento que se quiere insertar en la cola
	 * devuelve 0 si consigue insertarlo, negativo en caso contrario (-1 si no cabe, -2 otra causa)
	 * Si hay varios errores, se suman
	 */
	public int add(T elemento);

	/** 
	 * devuelve y elimina el elemento con mayor prioridad (cima del monticulo), o null si no hay elementos
	 */
	public T poll();
	
	/**
	 * Borra un elemento de la cola
	 * Se le pasa el elemento que se quiere eliminar de la cola
	 * devuelve 0 si estaba y lo elimina, negativo en caso contrario (-1 si no estaba, -2 otras causas)
	 */
	public int remove (T elemento);

	/**
	 * Indica si no hay ningun elemento
	 */
	public boolean isEmpty();
	
	/**
	 * Elimina todos los elementos de la cola
	 */
	public void clear();
	
	/**
	 * Devuelve un string con la cola de alguna forma visible
	 */
	public String toString();
}

