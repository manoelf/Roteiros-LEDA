package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.size >= 0 && !isFull()) {
			((DoubleLinkedListImpl<T>) this.list).insert(element);
		} else {
			throw new StackOverflowException();
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T pop = null;
		if (this.size >= 0 && !this.list.isEmpty()) {
			pop = top();
			list.removeLast();
		} else {
			throw new StackUnderflowException();
		}
		if (pop == null) {
			throw new StackUnderflowException();
		}
		return pop;
	}

	@Override
	public T top() {
		if (this.size <= 0 || this.list.isEmpty()) {
			return null;
		}
		return ((DoubleLinkedListImpl<T>) this.list).getLast().getData();
	}
	
	@Override
	public boolean isEmpty() {
		if (list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		return this.size == ((DoubleLinkedListImpl<T>) this.list).size();
	}

}
