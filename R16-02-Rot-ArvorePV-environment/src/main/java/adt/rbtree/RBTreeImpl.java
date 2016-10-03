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
		boolean height = node.isEmpty()
				|| (blackHeight((RBNode<T>) node.getLeft()) == blackHeight((RBNode<T>) node.getRight()));

		if (height && !node.isEmpty()) {
			return verifiBlackHeight((RBNode<T>) node.getLeft()) && verifiBlackHeight((RBNode<T>) node.getRight());
		}

		return height;
	}

	@Override
	public void insert(T value) {
		if (value != null) {
			this.insert(value, (RBNode<T>) getRoot());
		}
	}

	private void insert(T element, RBNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);

			node.setLeft(new RBNode<T>());
			node.getLeft().setParent(node);

			node.setRight(new RBNode<T>());
			node.getRight().setParent(node);

			node.setColour(Colour.RED);
			fixUpCase1(node);
		} else {
			if (element != node.getData()) {
				if (element.compareTo(node.getData()) < 0) {
					insert(element, (RBNode<T>) node.getLeft());
				} else {
					insert(element, (RBNode<T>) node.getRight());
				}
			}
		}
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

	public RBNode<T> search(T element) {
		if (element == null || this.getRoot().isEmpty()) {
			return new RBNode<T>();
		} else {
			return search(element, (RBNode<T>) this.getRoot());
		}
	}

	private RBNode<T> search(T element, RBNode<T> node) {
		if (node.getData().equals(element)) {
			return node;
		} else if (!node.getLeft().isEmpty() && node.getData().compareTo(element) > 0) {
			return search(element, (RBNode<T>) node.getLeft());
		} else if (!node.getRight().isEmpty() && node.getData().compareTo(element) < 0) {
			return search(element, (RBNode<T>) node.getRight());
		} else {
			return new RBNode<T>();
		}
	}

	public void remove(T element) {
		RBNode<T> node = search(element);
		if ((!node.isEmpty()) && (element != null)) {
			this.remove(node);
		}
	}

	private void remove(RBNode<T> node) {
		if (node.isLeaf()) {
			fixUpRemove(node);
		} else if (node.getRight().isEmpty()) {
			node.setData(node.getLeft().getData());
			node.setRight(node.getLeft().getRight());
			node.setLeft(node.getLeft().getLeft());
			node.getRight().setParent(node);
			node.getLeft().setParent(node);
		} else if (node.getLeft().isEmpty()) {
			node.setData(node.getRight().getData());
			node.setLeft(node.getRight().getLeft());
			node.setRight(node.getRight().getRight());
			node.getRight().setParent(node);
			node.getLeft().setParent(node);
		} else {
			T value = node.getData();
			RBNode<T> sucessor = (RBNode<T>) sucessor(value);
			node.setData(sucessor.getData());
			sucessor.setData(value);
			this.remove(sucessor);
		}
	}

	private void fixUpRemove(RBNode<T> node) {
		if (node.isLeaf()) {

			if (node.getColour() == Colour.RED) {

				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
				node.setColour(Colour.BLACK);

			} else {

				node.setData(null);
				node.setLeft(null);
				node.setRight(null);

				if (node.getColour() == Colour.BLACK && !brother(node).isEmpty() && !brother(node).isLeaf()
						&& brother(node).getColour() == Colour.BLACK) {
					if (isRightChildren(node)) {
						if (brother(node).getLeft().isEmpty() && !brother(node).getRight().isEmpty()) {

							if (((RBNode<T>) node.getParent()).getColour() == Colour.RED) {
								((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
							} else {
								((RBNode<T>) brother(node).getRight()).setColour(Colour.BLACK);
							}

							leftRotation(brother(node));
							rightRotation((BSTNode<T>) node.getParent());

						} else {

							if (((RBNode<T>) node.getParent()).getColour() == Colour.RED) {
								((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
								brother(node).setColour(Colour.RED);
								((RBNode<T>) brother(node).getLeft()).setColour(Colour.BLACK);
							} else {
								((RBNode<T>) brother(node).getLeft()).setColour(Colour.BLACK);
							}

							rightRotation((BSTNode<T>) node.getParent());

						}
					} else {
						if (brother(node).getRight().isEmpty() && !brother(node).getLeft().isEmpty()) {

							if (((RBNode<T>) node.getParent()).getColour() == Colour.RED) {
								((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
							} else {
								((RBNode<T>) brother(node).getLeft()).setColour(Colour.BLACK);
							}

							rightRotation(brother(node));
							leftRotation((BSTNode<T>) node.getParent());

						} else {

							if (((RBNode<T>) node.getParent()).getColour() == Colour.RED) {
								((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
								brother(node).setColour(Colour.RED);
								((RBNode<T>) brother(node).getRight()).setColour(Colour.BLACK);
							} else {
								((RBNode<T>) brother(node).getRight()).setColour(Colour.BLACK);
							}

							leftRotation((BSTNode<T>) node.getParent());
						}
					}
				}

				else if (node.getColour() == Colour.BLACK && !brother(node).isEmpty() && brother(node).isLeaf()
						&& brother(node).getColour() == Colour.BLACK) {
					if (((RBNode<T>) node.getParent()).getColour() == Colour.BLACK) {
						brother(node).setColour(Colour.RED);
					} else {
						((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
						brother(node).setColour(Colour.RED);
					}
				}

				else if (node.getColour() == Colour.BLACK && !brother(node).isEmpty() && !brother(node).isLeaf()
						&& brother(node).getColour() == Colour.RED) {
					if (isRightChildren(node)) {
						if (brother(node).getLeft().isEmpty() && !brother(node).getRight().isEmpty()) {

							brother(node).setColour(Colour.BLACK);
							((RBNode<T>) brother(node).getRight()).setColour(Colour.RED);

							leftRotation(brother(node));
							rightRotation((BSTNode<T>) node.getParent());

						} else {

							rightRotation((BSTNode<T>) node.getParent());

						}
					} else {
						if (brother(node).getRight().isEmpty() && !brother(node).getLeft().isEmpty()) {

							brother(node).setColour(Colour.BLACK);
							((RBNode<T>) brother(node).getLeft()).setColour(Colour.RED);

							rightRotation(brother(node));
							leftRotation((BSTNode<T>) node.getParent());
						} else {

							leftRotation((BSTNode<T>) node.getParent());
						}
					}
				}
			}
		}

		if (verifyProperties() == false) {
			if (node.getParent().getData().compareTo(root.getData()) < 0) {
				leftRotation(root);
				((RBNode<T>) root).setColour(Colour.BLACK);
			} else {
				rightRotation(root);
				((RBNode<T>) root).setColour(Colour.BLACK);
			}
		}
	}

	private RBNode<T> brother(RBNode<T> node) {
		if (node.getParent().getRight().equals(node)) {
			return (RBNode<T>) node.getParent().getLeft();
		} else {
			return (RBNode<T>) node.getParent().getRight();
		}
	}

	private boolean isRightChildren(RBNode<T> node) {
		if (node.getParent().getRight().equals(node)) {
			return true;
		} else {
			return false;
		}
	}

	// AUXILIARY
	private void leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();

		if (this.getRoot() == node) {
			this.root = pivot;
		}

		Util.leftRotation(node);
	}

	// AUXILIARY
	private void rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();

		if (this.getRoot() == node) {
			this.root = pivot;
		}

		Util.rightRotation(node);
	}
}