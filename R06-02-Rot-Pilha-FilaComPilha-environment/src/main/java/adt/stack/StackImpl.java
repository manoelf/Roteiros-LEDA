package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		if (size >= 0) {
			array = (T[]) new Object[size];
		}
		top = -1;
	}

	@Override
	public T top() {
		T value_top = null;
		if (!this.isEmpty()) {
			value_top = array[top];
		}
		return value_top;
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return this.top == this.array.length - 1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull()) {
			throw new StackOverflowException();
		}
		this.top += 1;
		this.array[this.top] = element;
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		}
		T value_top = this.array[this.top];
		this.top -= 1;
		return value_top;
	}
}
