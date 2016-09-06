package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if ((!node.isEmpty()) && (element != null)) {
			remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		if (node.getRight().isEmpty() && node.getLeft().isEmpty()) {
			node.setData(null);
			node.setLeft(null);
			node.setRight(null);
			rebalanceUp(node);
		} else if (node.getRight().isEmpty()) {
			node.setData(node.getLeft().getData());
			node.setRight(node.getLeft().getRight());
			node.setLeft(node.getLeft().getLeft());
			node.getRight().setParent(node);
			node.getLeft().setParent(node);
			rebalanceUp(node);
		} else if (node.getLeft().isEmpty()) {
			node.setData(node.getRight().getData());
			node.setLeft(node.getRight().getLeft());
			node.setRight(node.getRight().getRight());
			node.getRight().setParent(node);
			node.getLeft().setParent(node);
			rebalanceUp(node);
		} else {
			T value = node.getData();
			BSTNode<T> sucessor = sucessor(value);
			node.setData(sucessor.getData());
			sucessor.setData(value);
			remove((BSTNode<T>) sucessor);
		}
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			insert(element, this.getRoot());
		}
	}
	
	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			
			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);
			
			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);
			
			rebalanceUp(node);
		} else {
			if (element.compareTo(node.getData()) != 0) {
				if (element.compareTo(node.getData()) < 0) {
					insert(element, (BSTNode<T>) node.getLeft());
				} else {
					insert(element, (BSTNode<T>) node.getRight());
				}
			}
		}
	}
	
	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node != null) {
			return height((BSTNode<T>) node.getRight()) - height((BSTNode<T>) node.getLeft()); 
		}
		return 0;
	}
	
	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int height = calculateBalance(node);
		if (height > 1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
				doubleLeftRotation(node);
			} else {
				leftRotation(node);
			}
		} else if (height < -1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) {
				doubleRightRotation(node);
			} else
				rightRotation(node);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}
	
	// AUXILIARY
	private void doubleLeftRotation(BSTNode<T> node) {
		rightRotation((BSTNode<T>) node.getRight());
		leftRotation(node);
	}

	// AUXILIARY
	private void doubleRightRotation(BSTNode<T> node) {
		leftRotation((BSTNode<T>) node.getLeft());
		rightRotation(node);
	}
	
	// AUXILIARY
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

	// AUXILIARY
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