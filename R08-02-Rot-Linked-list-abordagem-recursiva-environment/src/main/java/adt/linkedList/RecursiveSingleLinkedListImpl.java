package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {
	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.getData() == null;
	}

	@Override
	public int size() {
		if (!isEmpty()) {
			return this.getNext().size() + 1;
		} else {
			return 0;
		}
	}

	@Override
	public T search(T element) {
		if (isEmpty() || element == null) {
			return null;
		} else if (this.getData().equals(element)) {
			return element;
		} else {
			return this.getNext().search(element);
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				RecursiveSingleLinkedListImpl<T> node = new RecursiveSingleLinkedListImpl<T>();
				this.data = element;
				this.next = node;
			} else {
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (this.getData() == element) {
				this.data = getNext().getData();
				this.next = getNext().getNext();
			} else if (!isEmpty()) {
				this.getNext().remove(element);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];

		return array(array, 0, this);
	}

	public T[] array(T[] array, int indice, RecursiveSingleLinkedListImpl<T> tmp) {
		if (tmp.isEmpty()) {
			return array;
		} else {
			array[indice++] = tmp.getData();
			return array(array, indice, tmp.getNext());
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}
}