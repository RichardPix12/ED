package p3Arboles;

/**
 * @author Prodesores ED (Español)
 * @version 2021-22
 */
public class BSTNode<T extends Comparable<T>> {
	private T info;
	private BSTNode<T> left;
	private BSTNode<T> right;

	/**
	 * Se le pasa un objeto comparable
	 * 
	 * @param info informaci�n del nodo
	 */
	public BSTNode(T info) {
		setInfo(info);
	}

	/**
	 * Se le pasa la información que se quiere meter en el nodo
	 * 
	 * @param info informacion que se quiere guardar en el nodo
	 */
	protected void setInfo(T info) {
		this.info = info;
	}

	/**
	 * Devuelve la información que almacena el nodo
	 * 
	 * @return info informacion guardada
	 */
	public T getInfo() {
		return info;
	}

	/**
	 * Se le pasa el nodo que se quiere enlazar en el subárbol izquierdo
	 * 
	 * @param left nodo que queremos apuntar a la izquierda
	 */
	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	/**
	 * Se le pasa el nodo que se quiere enlazar en el subárbol derecho
	 * 
	 * @param rigth nodo que queremos apuntar a la derecha
	 */
	public void setRight(BSTNode<T> rigth) {
		this.right = rigth;
	}

	/**
	 * Devuelve el subárbol izquierdo
	 * 
	 * @return left nodo al que apunta la izquierda
	 */
	public BSTNode<T> getLeft() {
		return this.left;
	}

	/**
	 * Devuelve el subárbol derecho
	 * 
	 * @return rigth nodo al que apunta la derecha
	 */
	public BSTNode<T> getRight() {
		return this.right;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return info.toString();
	}
}