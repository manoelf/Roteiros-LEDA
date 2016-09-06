package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
		
	
	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		if (size >= 0) {
			array = (T[])new Object[size];
		}
		tail = -1;
	}

	@Override
	public T head() {
		T value_head = null;
		if (!this.isEmpty()) {
			value_head = array[0];
		}
		return value_head;
	}

	@Override
	public boolean isEmpty() {
		return tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.tail == this.array.length - 1;
	}
	
	private void shiftLeft(){
		for (int i = 0; i < tail; i++) {
			this.array[i] = array[i+1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}
		this.tail+=1;
		this.array[this.tail] = element;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		T value_tail = array[0];
		shiftLeft();
		tail-=1;
		return value_tail;
	}
}