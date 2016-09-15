package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		
		if (node.getParent().getData().compareTo(node.getData()) > 0) {
			node.getParent().setLeft(pivot);
		} else {
			node.getParent().setRight(pivot);
		}
				
		pivot.setParent(node.getParent());
		
		node.setParent(pivot);
		
		node.setRight(pivot.getLeft());
		
		pivot.getLeft().setParent(node);
		
		pivot.setLeft(node);
		
		
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		//TODO Implemente sua rotacao a esquerda aqui
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
