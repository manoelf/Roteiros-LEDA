package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;
	
	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}
	
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}
		
		try {
			stack1.push(element);
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		
		//Passa elementos da pilha1 para a pilha2
		try {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		} catch (StackOverflowException | StackUnderflowException e1) {
			e1.printStackTrace();
		}
		
		T value = null;
		
		//Remove o elemento do topo da pilha2
		try {
			value = stack2.pop();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		
		//Passa elementos da pilha2 de volta para a pilha1
		try {
			while (!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
		} catch (StackOverflowException | StackUnderflowException e) {
			e.printStackTrace();
		}
		
		return value;
	}

	@Override
	public T head() {
		
		//Passa elementos da pilha1 para a pilha2
		try {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		} catch (StackOverflowException | StackUnderflowException e1) {
			e1.printStackTrace();
		}
		
		//Atribui o elemento do topo da pilha2 à variável
		T element = stack2.top();
		
		//Passa elementos da pilha2 de volta para a pilha1
		try {
			while (!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
		} catch (StackOverflowException | StackUnderflowException e) {
			e.printStackTrace();
		}
		return element;
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}
}
