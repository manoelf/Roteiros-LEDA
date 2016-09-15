package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	private void splay(BSTNode<T> node) {
		
		if (!(node == null || node.isEmpty() || node.equals(root))) {
			if (node.getParent().equals(root)) {
				
				if (node.equals(node.getParent().getRight())) {
					leftRotation(root);
				} else if (node.equals(node.getParent().getLeft())) {
					rightRotation(root);
				}
				
			} else if (node.getParent().equals(node.getParent().getParent().getRight())) {
				
				if (node.equals(node.getParent().getRight())) {
					leftRotation((BSTNode<T>) node.getParent().getParent());
					leftRotation((BSTNode<T>) node.getParent());
				} else if (node.equals(node.getParent().getLeft())) {
					rightRotation((BSTNode<T>) node.getParent());
					leftRotation((BSTNode<T>) node.getParent());
				}
				
			} else if (node.getParent().equals(node.getParent().getParent().getLeft())) {
				
				if (node.equals(node.getParent().getLeft())) {
					rightRotation((BSTNode<T>) node.getParent().getParent());
					rightRotation((BSTNode<T>) node.getParent());
				} else if (node.equals(node.getParent().getRight())) {
					leftRotation((BSTNode<T>) node.getParent());
					rightRotation((BSTNode<T>) node.getParent());
				}
				
			}
			splay(node);
		}
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			BSTNode<T> node = insert(element, this.getRoot());
			splay(node);
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> node = super.search(element);
		if (node.isEmpty()) {
			splay((BSTNode<T>) node.getParent());
		} else {
			splay(node);
		}
		return node;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> nodeProcurado = super.search(element);
		BSTNode<T> nodeSplay = (BSTNode<T>) nodeProcurado.getParent();
		if (element != null && nodeProcurado.getData() == element) {
			super.remove(nodeProcurado);
			splay(nodeSplay);
		} else {
			splay(nodeProcurado);
		}
	}

	protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();

		if (this.getRoot() == node) {
			this.root = pivot;
		} else {
			if (node.getParent().getData().compareTo(node.getData()) > 0) {
				node.getParent().setLeft(pivot);
			} else {
				node.getParent().setRight(pivot);
			}
		}

		pivot.setParent(node.getParent());

		node.setParent(pivot);

		node.setRight(pivot.getLeft());

		pivot.getLeft().setParent(node);

		pivot.setLeft(node);
	}

	protected void rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();

		if (this.getRoot() == node) {
			this.root = pivot;
		} else {
			if (node.getParent().getData().compareTo(node.getData()) > 0) {
				node.getParent().setLeft(pivot);
			} else {
				node.getParent().setRight(pivot);
			}
		}

		pivot.setParent(node.getParent());

		node.setParent(pivot);

		node.setLeft(pivot.getRight());

		pivot.getRight().setParent(node);

		pivot.setRight(node);
	}
}
