package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
			this.list = new RecursiveDoubleLinkedListImpl<T>();
			this.size = size;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.size >= 0 && !isFull()) {
			((RecursiveDoubleLinkedListImpl<T>) this.list).insert(element);
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
		if (this.size >= 0 && this.list.isEmpty()) {
			return null;
		}
		return top((RecursiveDoubleLinkedListImpl<T>) this.list);
	}

	public T top(RecursiveDoubleLinkedListImpl<T> tmp) {
		if (tmp.getNext().isEmpty()) {
			return tmp.getData();
		}
		return top((RecursiveDoubleLinkedListImpl<T>) tmp.getNext());
	}

	@Override
	public boolean isEmpty() {
		return ((RecursiveDoubleLinkedListImpl<T>) this.list).isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.size == ((RecursiveDoubleLinkedListImpl<T>) this.list).size();
	}
}