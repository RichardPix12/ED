package p3Arboles;

/**
 * @author Prodesores ED (EspaÃ±ol)
 * @version 2021-22
 */
public class BSTree<T extends Comparable<T>> {

	protected BSTNode<T> raiz;

	/**
	 * getter del nodo raiz del arbol
	 * 
	 * @return raiz, devuelve la raiz del arbol
	 */
	protected BSTNode<T> getRoot() {
		return raiz;
	}

	/**
	 * setter del nodo raiz del arbol
	 * 
	 * @param nodo, selecciona ese nodo como raiz del arbol
	 */
	protected void setRoot(BSTNode<T> nodo) {
		this.raiz = nodo;
	}

	/**
	 * Se le pasa el objeto comparable que hay que insertar
	 *
	 * Si lo inserta devolveria 0, y un codigo de error negativo si no lo hace (-1
	 * si ya existe, -2 otra causa)
	 * 
	 * @param infoNode, informacion del nodo que queremos añadir
	 * @return 0 si se ha añadido -1 si ya existe ese nodo, -2 en otro caso
	 */
	public int addNode(T infoNode) {
		if (infoNode == null) {
			return -2;
		}
		if (raiz == null) {
			this.raiz = new BSTNode<T>(infoNode);
			return 0;
		}

		return addNodeRecursive(infoNode, raiz);

	}

	/**
	 * Metodo recursivo para añadir nodo
	 * 
	 * @param info info del nodo que queremos añadir
	 * @param node nodo sobre el cual comparamos
	 * @return 0 si se ha añadido -1 si ya existe ese nodo, -2 en otro caso
	 */
	private int addNodeRecursive(T infoNode, BSTNode<T> node) {

		if (infoNode.compareTo(node.getInfo()) == 0) {
			return -1;
		}
		if (infoNode.compareTo(node.getInfo()) < 0) {
			if (node.getLeft() == null) {
				node.setLeft(new BSTNode<T>(infoNode));
				return 0;
			} else {
				return addNodeRecursive(infoNode, node.getLeft());
			}
		} else {
			if (node.getRight() == null) {
				node.setRight(new BSTNode<T>(infoNode));
				return 0;
			} else {
				return addNodeRecursive(infoNode, node.getRight());
			}

		}
	}

	/**
	 * Se le pasa un objeto comparable que se busca Devuelve el objeto encontrado
	 * que cumple que es "equals" con el buscado, null si no lo encuentra por
	 * cualquier motivo
	 * 
	 * @param infoNode informacion del nodo que buscamos
	 * @return informacion del nodo si se encuentra, null en caso de no encontrarlo
	 */
	public T searchNode(T infoNode) {
		if (raiz == null || infoNode == null) {
			return null;
		}
		if (infoNode.equals(raiz.getInfo())) {
			return raiz.getInfo();
		}

		return searchNodeRecursive(infoNode, raiz);

	}

	/**
	 * Metodo recursivo para la busqueda de un nodo
	 * 
	 * @param infoNode informacion del nodo que buscamos
	 * @param node     nodo raiz en ese momento
	 * @return node si existe, null si no
	 */
	public T searchNodeRecursive(T infoNode, BSTNode<T> node) {

		if (infoNode.compareTo(node.getInfo()) < 0) {
			if (node.getLeft() == null) {
				return null;
			} else if (infoNode.equals(node.getLeft().getInfo())) {
				return node.getLeft().getInfo();
			} else {
				return searchNodeRecursive(infoNode, node.getLeft());
			}
		} else {
			if (node.getRight() == null) {
				return null;
			} else if (infoNode.equals(node.getRight().getInfo())) {
				return node.getRight().getInfo();
			} else {
				return searchNodeRecursive(infoNode, node.getRight());
			}

		}
	}

	/**
	 * Genera un String con el recorrido en pre-orden (izquierda-derecha) (toString
	 * de los nodos separados por tabuladores)
	 * 
	 * @return orden, nodos ordenados de manera preOrder
	 */
	public String preOrder() {
		String orden = "";
		if (raiz == null) {
			return orden;
		}

		return preOrderRecursive(raiz);
	}

	/**
	 * Metodo recursivo para generar preOrder
	 * 
	 * @param node, node raiz sobre el que se genera
	 * @return orden, cadena con el recorrido en preorder
	 */
	private String preOrderRecursive(BSTNode<T> node) {
		String orden = "";
		if (node == null) {
			return orden;
		}
		orden += node.toString() + "\t";
		orden += preOrderRecursive(node.getLeft());
		orden += preOrderRecursive(node.getRight());
		return orden;
	}

	/**
	 * Genera un String con el recorrido en post-orden (izquierda-derecha) (toString
	 * de los nodos separados por tabuladores)
	 * 
	 * @return orden, nodos ordenados de manera inOrder
	 */
	public String inOrder() {
		String orden = "";
		if (raiz == null) {
			return orden;
		}
		return inOrderRecursive(raiz);
	}

	/**
	 * Metodo recursivo para generar inOrder
	 * 
	 * @param node, node raiz sobre el que se genera
	 * @return orden, cadena con el recorrido inOrder
	 */
	private String inOrderRecursive(BSTNode<T> node) {
		String orden = "";
		if (node == null) {
			return orden;
		}
		orden += inOrderRecursive(node.getLeft());
		orden += node.toString() + "\t";
		orden += inOrderRecursive(node.getRight());
		return orden;

	}

	/**
	 * Genera un String con el recorrido en in-orden (izquierda-derecha) (toString
	 * de los nodos separados por tabuladores)
	 * 
	 * @return orden, nodos ordenados de manera postOrder
	 */
	public String postOrder() {
		String orden = "";
		if (raiz == null) {
			return orden;
		}
		return postOrderRecursive(raiz);
	}

	/**
	 * Metodo recursivo para generarpostOrder
	 * 
	 * @param node, node raiz sobre el que se genera
	 * @return orden, cadena con el recorrido postOrder
	 */
	private String postOrderRecursive(BSTNode<T> node) {
		String orden = "";
		if (node == null) {
			return orden;
		}
		orden += postOrderRecursive(node.getLeft());
		orden += postOrderRecursive(node.getRight());
		orden += node.toString() + "\t";
		return orden;
	}

	/**
	 * Se le pasa el objeto que se quiere borrar que coincida con equals Devuelve 0
	 * si lo ha borrado, negativo en caso contrario (-1 si no existe, -2 otra causa)
	 * 
	 * @param node nodo a borrar
	 * @return 0 si lo borra, -1 si no existe -2 otra cause
	 */
	public int removeNode(T node) {
		if (node == null) {
			return -2;
		}
		if (searchNode(node) == null) {
			return -1;
		}
		if (removeNodeRecursive(node, raiz) == -1) {
			raiz = null;
		}
		return 0;

	}

	/**
	 * Metodo recursivo para recorrer el arbol y borrar el nodo
	 * 
	 * @param infoNode info del nodo a borrar
	 * @param node     nodo raiz del subarbol
	 * @return 0 si lo borra, -1 si no
	 */
	private int removeNodeRecursive(T infoNode, BSTNode<T> node) {

		if (infoNode.compareTo(node.getInfo()) == 0) {
			if (node.getLeft() != null) {
				T mayor = buscarMayorMenor(node.getLeft());
				if (removeNodeRecursive(mayor, node.getLeft()) == -1) {
					node.setLeft(null);
				}
				node.setInfo(mayor);
				return 0;
			}
			if (node.getRight() != null) {
				node.setInfo(node.getRight().getInfo());
				node.setLeft(node.getRight().getLeft());
				node.setRight(node.getRight().getRight());
				return 0;
			}
			return -1;

		}
		if (infoNode.compareTo(node.getInfo()) < 0) {
			if (removeNodeRecursive(infoNode, node.getLeft()) == -1) {
				node.setLeft(null);

			}
		} else {
			if (removeNodeRecursive(infoNode, node.getRight()) == -1) {
				node.setRight(null);

			}
		}
		return 0;

	}

	/**
	 * Metodo para buscar el nodo mayor del subarbol por la izquierda de la raiz
	 * 
	 * @param left nodo raiz del subarbol
	 * @return info del nodo mayor
	 */
	protected T buscarMayorMenor(BSTNode<T> left) {
		if (left.getRight() == null) {
			return left.getInfo();
		}
		return buscarMayorMenor(left.getRight());
	}

	public String toString() {
		return tumbarArbol(raiz, 0);
	}

	/**
	 * Genera un String con el arbol "tumbado" (la raiz a la izquierda y las ramas
	 * hacia la derecha) Es un recorrido InOrden-Derecha-Izquierda, tabulando para
	 * mostrar los distintos niveles Utiliza el toString de la informacion
	 * almacenada
	 *
	 * @param p   La raiz del arbol a mostrar tumbado
	 * @param esp El espaciado en numero de tabulaciones para indicar la profundidad
	 * @return El String generado
	 */
	protected String tumbarArbol(BSTNode<T> p, int esp) {
		StringBuilder cadena = new StringBuilder();

		if (p != null) {
			cadena.append(tumbarArbol(p.getRight(), esp + 1));
			for (int i = 0; i < esp; i++)
				cadena.append("\t");
			cadena.append(p + "\n");
			cadena.append(tumbarArbol(p.getLeft(), esp + 1));
		}
		return cadena.toString();
	}

}