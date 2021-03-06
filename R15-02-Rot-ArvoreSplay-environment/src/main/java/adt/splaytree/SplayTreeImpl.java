package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	private void splay(BSTNode<T> node) {
		
		if (!(node == null || node.isEmpty() || node.equals(root))) {
			if (node.getParent().equals(root)) {
				
				if (node.equals(node.getParent().getRight())) {
					Util.leftRotation(root);
				} else if (node.equals(node.getParent().getLeft())) {
					Util.rightRotation(root);
				}
				
				this.root = node;
				
			} else if (node.getParent().equals(node.getParent().getParent().getRight())) {
				
				if (node.equals(node.getParent().getRight())) {
					Util.leftRotation((BSTNode<T>) node.getParent().getParent());
					Util.leftRotation((BSTNode<T>) node.getParent());
				} else if (node.equals(node.getParent().getLeft())) {
					Util.rightRotation((BSTNode<T>) node.getParent());
					Util.leftRotation((BSTNode<T>) node.getParent());
				}
				
			} else if (node.getParent().equals(node.getParent().getParent().getLeft())) {
				
				if (node.equals(node.getParent().getLeft())) {
					Util.rightRotation((BSTNode<T>) node.getParent().getParent());
					Util.rightRotation((BSTNode<T>) node.getParent());
				} else if (node.equals(node.getParent().getRight())) {
					Util.leftRotation((BSTNode<T>) node.getParent());
					Util.rightRotation((BSTNode<T>) node.getParent());
				}
				
			}
			
			if (node.getParent() == null) {
				this.root = node;
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
		splay(node);
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
}