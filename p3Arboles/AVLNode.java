package p3Arboles;

/**
 * Clase derivada de BSTNode añadiendo funcionalidad de AVL
 * 
 * @author Profesores ED (Español)
 * @version 2021-22
 *
 */
public class AVLNode<T extends Comparable<T>> extends BSTNode<T> {

	/**
	 * Para almacenar la altura del nodo
	 */
	protected int height;

	/**
	 * Para almacenar al Factor de balance. Puede no existir y calcularse cada vez a
	 * partir de la altura de los hijos.
	 */
	protected int balanceFactor;

	/**
	 * Llama al padre y añade la informacion propia se le pasa la informacion que
	 * se mete en el nuevo nodo
	 * 
	 * @param info, info del nodo
	 */
	public AVLNode(T info) {
		super(info);
		this.height = 0;
		this.balanceFactor = 0;		
	}

	/**
	 * devuelve la altura del arbol del cual es raiz el nodo en cuestion
	 * 
	 * @return height, altura del nodo
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Devuelve el factor de balance segun equilibrio del arbol del cual es raiz
	 * 
	 * @return balanceFactor, factor balande del nodo
	 */
	public int getBF() {
		return balanceFactor;
	}

	/**
	 * Actualiza la altura del nodo en el arbol utilizando la altura de los hijos y
	 * si es preciso actualiza el FB
	 */
	protected void updateHeight() {
		this.height = calcularAltura() + 1;
		this.balanceFactor = factorBalance();
	}

	/**
	 * Metodo privado para calcular el FB del nodo
	 * 
	 * @return fb, nuevo factor balance del nodo
	 */
	private int factorBalance() {
		int fb = 0;
		int altIzq = -1;
		int altDcha = -1;
		if (getRight() != null) {
			altDcha = getRight().getHeight();
		}
		if (getLeft() != null) {
			altIzq = getLeft().getHeight();
		}
		fb = altDcha - altIzq;
		return fb;
	}

	/**
	 * Metodo privado para calcular la altura del nodo
	 * 
	 * @return height, nueva altura del nodo
	 */
	private int calcularAltura() {
		int height;
		int altIzq = -1;
		int altDcha = -1;
		if (getRight() != null) {
			altDcha = getRight().getHeight();
		}
		if (getLeft() != null) {
			altIzq = getLeft().getHeight();
		}
		if (altIzq > altDcha) {
			height = altIzq;
		} else {
			height = altDcha;
		}
		return height;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTNode#getLeft()
	 */
	public AVLNode<T> getLeft() {
		return (AVLNode<T>) super.getLeft();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTNode#getRight()
	 */
	public AVLNode<T> getRight() {
		return (AVLNode<T>) super.getRight();
	}

// No se necesitan los setters, valen los heredados

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTNode#toString() Añade factor de balance
	 */
	public String toString() {
		return super.toString() + ":BF=" + getBF();
	}
}
