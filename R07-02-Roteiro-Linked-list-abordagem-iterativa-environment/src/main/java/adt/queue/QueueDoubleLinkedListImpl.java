package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	
	protected DoubleLinkedList<T> list;
	protected int size;
	
	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}
	
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (!this.isFull()) {
			if (element != null) {
				list.insert(element);
			}
		} else {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (!this.isEmpty()) {
			T element = ((DoubleLinkedListImpl<T>) list).getHead().getData();
			this.list.removeFirst();
			return element;
		} else {
			throw new QueueUnderflowException();
		}
	}

	@Override
	public T head() {
		if (!list.isEmpty()) {
			return ((DoubleLinkedListImpl<T>) list).getHead().getData();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		if (this.list.size() <= 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if (list.size() == size) {
			return true;
		}
		return false;
	}

}
