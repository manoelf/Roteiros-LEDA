package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight((RBNode<T>) root);
	}

	private int blackHeight(RBNode<T> node) {
		if (node.isEmpty()) {
			return 0;
		} else {
			if (node.getColour() == Colour.BLACK) {
				return 1 + blackHeight((RBNode<T>) node.getRight());
			} else {
				return 0 + blackHeight((RBNode<T>) node.getRight());
			}

		}
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes((RBNode<T>) root);
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		if (node != null && !node.isEmpty() && node.getColour() == Colour.RED) {
			return ((RBNode<T>) node.getLeft()).getColour() == Colour.BLACK
					&& ((RBNode<T>) node.getRight()).getColour() == Colour.BLACK;
		}
		if ((node.getColour() != Colour.RED) && !node.isEmpty()) {
			return verifyChildrenOfRedNodes((RBNode<T>) node.getLeft())
					&& verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
		}
		return true;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		if (!verifiBlackHeight((RBNode<T>) root)) {
			return false;
		}
		return true;
	}
	
	private boolean verifiBlackHeight(RBNode<T> node) {
		boolean height = node.isEmpty() || (blackHeight((RBNode<T>) node.getLeft()) == blackHeight((RBNode<T>) node.getRight()));
		if (height && !node.isEmpty()) {
			return verifiBlackHeight((RBNode<T>) node.getLeft()) && verifiBlackHeight((RBNode<T>) node.getRight());
		}
		return height;
	}

	@Override
	public void insert(T value) {
		RBNode<T> node = this.insert(value, (RBNode<T>) this.root);
		node.setColour(Colour.RED);
		fixUpCase1(node);
	}

	protected RBNode<T> insert(T element, RBNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);

			node.setLeft(new RBNode<T>());
			node.getLeft().setParent(node);

			node.setRight(new RBNode<T>());
			node.getRight().setParent(node);
		} else {
			if (element != node.getData()) {
				if (element.compareTo(node.getData()) < 0) {
					node = insert(element, (RBNode<T>) node.getLeft());
				} else {
					node = insert(element, (RBNode<T>) node.getRight());
				}
			}
		}
		return node;
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		return preOrderRBAux();
	}

	@SuppressWarnings("unchecked")
	public RBNode<T>[] preOrderRBAux() {
		RBNode<T>[] array = new RBNode[this.size()];
		if (root.isEmpty()) {
			return array;
		}
		preOrder(array, (RBNode<T>) root);
		return array;
	}

	private void preOrder(RBNode<T>[] array, RBNode<T> node) {
		add(array, node, 0);
		if (node.getLeft().getData() != null) {
			preOrder(array, (RBNode<T>) node.getLeft());
		}
		if (node.getRight().getData() != null) {
			preOrder(array, (RBNode<T>) node.getRight());
		}
	}

	private void add(RBNode<T>[] array, RBNode<T> element, int count) {
		if (array[count] == null) {
			array[count] = element;
		} else {
			add(array, element, count + 1);
		}
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.getParent() == null) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (((RBNode<T>) node.getParent()).getColour() == Colour.RED) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> aux;
		
		if (node.getParent().equals(node.getParent().getParent().getLeft())) {
			aux = (RBNode<T>) node.getParent().getParent().getRight();
		} else {
			aux = (RBNode<T>) node.getParent().getParent().getLeft();
		}
		
		if (aux.getColour() == Colour.RED) {
			((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
			aux.setColour(Colour.BLACK);
			((RBNode<T>) node.getParent().getParent()).setColour(Colour.RED);
			fixUpCase1((RBNode<T>) node.getParent().getParent());
		} else {
			fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> euMesmo = node;

		if (node.equals(node.getParent().getRight())
				&& node.getParent().equals(node.getParent().getParent().getLeft())) {
			Util.leftRotation((BSTNode<T>) node.getParent());
			euMesmo = (RBNode<T>) node.getLeft();
		} else if (node.equals(node.getParent().getLeft())
				&& node.getParent().equals(node.getParent().getParent().getRight())) {
			Util.rightRotation((BSTNode<T>) node.getParent());
			euMesmo = (RBNode<T>) node.getRight();
		}
		fixUpCase5(euMesmo);
	}

	protected void fixUpCase5(RBNode<T> node) {
		((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
		((RBNode<T>) node.getParent().getParent()).setColour(Colour.RED);

		if (node.equals(node.getParent().getRight())) {
			Util.leftRotation((BSTNode<T>) node.getParent().getParent());
		} else if (node.equals(node.getParent().getLeft())) {
			Util.rightRotation((BSTNode<T>) node.getParent().getParent());
		}

		if (node.getParent().getParent() == null) {
			this.root = (BSTNode<T>) node.getParent();
		}
	}
}