package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int height;
	protected int maxHeight;

	protected boolean USE_MAX_HEIGHT_AS_HEIGHT = true;
	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		if (USE_MAX_HEIGHT_AS_HEIGHT) {
			this.height = maxHeight;
		} else {
			this.height = 1;
		}
		this.maxHeight = maxHeight;
		root = new SkipListNode<T>(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode<T>(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < this.height; i++) {
			root.forward[i] = NIL;
		}
	}

	private void connectRootToNil(int height) {
		for (int i = this.height + 1; i < height; i++) {
			root.forward[i] = NIL;
		}
	}

	/**
	 * Metodo que gera uma altura aleatoria para ser atribuida a um novo no no
	 * metodo insert(int,V)
	 */
	private int randomLevel() {
		int randomLevel = 1;
		while (Math.random() <= PROBABILITY && randomLevel < maxHeight) {
			randomLevel = randomLevel + 1;
		}
		return randomLevel;
	}

	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> x = root;
		for (int i = height - 1; i >= 0; i--) {
			while (x.forward[i].key < key) {
				x = x.forward[i];
			}
			update[i] = x;
		}
		x = x.forward[0];
		if (x.key == key) {
			x.value = newValue;
		} else {
			x = new SkipListNode<T>(key, height, newValue);
			for (int i = 0; i < height; i++) {
				x.forward[i] = update[i].forward[i];
				update[i].forward[i] = x;
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> x = root;
		for (int i = height - 1; i >= 0; i--) {
			while (x.forward[i].key < key) {
				x = x.forward[i];
			}
			update[i] = x;
		}
		x = x.forward[0];
		if (x.key == key) {
			for (int i = 0; i < height; i++) {
				if (!update[i].forward[i].equals(x)) {
					return;
				}
				update[i].forward[i] = x.forward[i];
			}

			while (height > 1 && root.forward[height].equals(NIL)) {
				height -= 1;
			}
		}
	}

	@Override
	public int height() {
		return height(height - 1);
	}

	private int height(int height) {
		if (root.forward[height].key == Integer.MAX_VALUE) {
			return height(height - 1);
		} else {
			return height + 1;
		}
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> x = root;
		for (int i = height - 1; i >= 0; i--) {
			while (x.forward[i].key < key) {
				x = x.forward[i];
			}
		}
		x = x.forward[0];
		if (x.key == key) {
			return x;
		} else {
			return null;
		}
	}

	@Override
	public int size() {
		return size(root);
	}

	private int size(SkipListNode<T> node) {
		if (node.forward[0].key == Integer.MAX_VALUE) {
			return 0;
		} else {
			return 1 + size(node.forward[0]);
		}
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T>[] array = new SkipListNode[size() + 2];
		SkipListNode<T> node = root;
		int index = 0;
		while (index < (size() + 2)) {
			array[index++] = node;
			node = node.forward[0];
		}
		return array;
	}
}
