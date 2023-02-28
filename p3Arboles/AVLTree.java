package p3Arboles;

/**
 * Clase derivada de BSTree aÃ±adiendo funcionalidad de AVL
 * 
 * @author Profesores ED (EspaÃ±ol)
 * @version 2021-22
 */
public class AVLTree<T extends Comparable<T>> extends BSTree<T> {

	/**
	 * Constructor de la clase, hereda de la clase BSTree
	 */
	public AVLTree() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTree#addNode(java.lang.Comparable) Redefine inserciÃ³n para
	 * funcionalidad AVL
	 */
	public int addNode(T info) {
		if (info == null) {
			return -2;
		}
		if (raiz == null) {
			raiz = new AVLNode<T>(info);
			return 0;
		}
		if (searchNode(info) != null) {
			return -1;
		}
		raiz = (AVLNode<T>) addNodeRevursive(info, (AVLNode<T>) raiz);
		return 0;
	}

	/**
	 * Metodo recursivo para recorrer el arbol y añadir un nodo donde sea necesario,
	 * y en el caso de necesitar rotación que rote la raiz
	 * 
	 * @param info del nodo a añadir
	 * @param node raiz del subarbol
	 * @return nodo que será raiz
	 */
	private AVLNode<T> addNodeRevursive(T info, AVLNode<T> node) {

		if (info.compareTo(node.getInfo()) < 0) {
			if (node.getLeft() == null) {
				node.setLeft(new AVLNode<T>(info));
			} else {
				node.setLeft(addNodeRevursive(info, node.getLeft()));
			}
		} else {
			if (node.getRight() == null) {
				node.setRight(new AVLNode<T>(info));
			} else {
				node.setRight(addNodeRevursive(info, node.getRight()));
			}
		}
		return updateAndBalanceIfNecesary(node);
	}

	/**
	 * se le pasa el arbol que se quiere actualizar Height, BF y balancear si fuese
	 * necesario y devuelve la raiz del arbol por si ha cambiado
	 * 
	 * @param node, nodo raiz del arbol actual que queremos actualizar
	 * @return nodo raiz nuevo
	 */
	private AVLNode<T> updateAndBalanceIfNecesary(AVLNode<T> node) {
		if (node == null) {
			return null;
		}

		node.updateHeight();

		// Miramos el FB
		if (node.getBF() == -2) {
			// si su hijo izq tiene BF = 1 single
			if (node.getLeft().getBF() == 1) {
				node = doubleLeftRotation(node);
			} else if (node.getLeft().getBF() == 0) {
				node = singleLeftRotation(node);
			} else {
				node = singleLeftRotation(node);
			}
		} else if (node.getBF() == 2) {
			if (node.getRight().getBF() == -1) {
				node = doubleRightRotation(node);
			} else if (node.getRight().getBF() == 0) {
				node = singleRightRotation(node);
			} else {
				node = singleRightRotation(node);
			}
		}
		return node;
	}

	/**
	 * Se le pasa la raiz del arbol a balancear con rotacion simple devuelve la raiz
	 * del nuevo arbol que ha cambiado
	 * 
	 * @param node, nodo raiz a rotar
	 * @return aux, nueva raiz
	 */
	private AVLNode<T> singleLeftRotation(AVLNode<T> node) {
		AVLNode<T> aux = node.getLeft();
		node.setLeft(aux.getRight());
		node.updateHeight();
		aux.setRight(node);
		aux.updateHeight();
		return aux;
	}

	/**
	 * Se le pasa la raiz del arbol a balancear con rotacion doble devuelve la raiz
	 * del nuevo arbol que ha cambiado
	 * 
	 * @param node, nodo raiz a rotar
	 * @return aux, nueva raiz
	 */
	private AVLNode<T> doubleLeftRotation(AVLNode<T> node) {
		node.setLeft(singleRightRotation(node.getLeft()));
		return singleLeftRotation(node);
	}

	/**
	 * Se le pasa la raiz del arbol a balancear con rotacion simple devuelve la raiz
	 * del nuevo arbol que ha cambiado
	 * 
	 * @param node, nodo raiz a rotar
	 * @return aux, nueva raiz
	 */
	private AVLNode<T> singleRightRotation(AVLNode<T> node) {
		AVLNode<T> aux = (AVLNode<T>) node.getRight();
		node.setRight(aux.getLeft());
		node.updateHeight();
		aux.setLeft(node);
		aux.updateHeight();
		return aux;
	}

	/**
	 * Se le pasa la raiz del arbol a balancear con rotacion doble Devuelve la raiz
	 * del nuevo arbol que ha cambiado
	 * 
	 * @param node, nodo raiz a rotar
	 * @return aux, nueva raiz
	 */
	private AVLNode<T> doubleRightRotation(AVLNode<T> node) {
		node.setRight(singleLeftRotation(node.getRight()));
		return singleRightRotation(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTree#removeNode(java.lang.Comparable) RedefiniciÃ³n para incluir
	 * caracterÃ­sticas AVL
	 */
	public int removeNode(T info) {
		if (info == null) {
			return -2;
		}
		if (searchNode(info) == null) {
			return -1;
		}
		raiz = (AVLNode<T>) removeNodeRecursive(info, (AVLNode<T>) raiz);
		return 0;
	}

	/**
	 * Metodo recursivo que recorre el arbol y borra el nodo deseado, y pasa la
	 * nueva raiz del arbol despues de ser actualizado
	 * 
	 * @param info info del nodo a borrar
	 * @param node raiz del subarbol que estamos recorriendo
	 * @return nuevo nodo raiz
	 */
	private AVLNode<T> removeNodeRecursive(T info, AVLNode<T> node) {
		if (info.compareTo(node.getInfo()) == 0) {
			if (node.getLeft() == null && node.getRight() == null) {
				node = null;

			} else if (node.getRight() == null) {
				node = node.getLeft();
			}

			else if (node.getLeft() == null) {
				node = node.getRight();
			}

			else {
				T maximo = buscarMayorMenor(node.getLeft());
				node.setInfo(maximo);
				node.setLeft(removeNodeRecursive(maximo, node.getLeft()));
			}
		}

		else if (info.compareTo(node.getInfo()) > 0) {
			node.setRight(removeNodeRecursive(info, node.getRight()));
		} else {
			node.setLeft(removeNodeRecursive(info, node.getLeft()));
		}
		return updateAndBalanceIfNecesary(node);
	}
}
