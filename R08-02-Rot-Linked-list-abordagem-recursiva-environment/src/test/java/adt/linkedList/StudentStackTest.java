package adt.linkedList;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import adt.stack.StackOverflowException;
import adt.stack.StackRecursiveDoubleLinkedListImpl;
import adt.stack.StackUnderflowException;

public class StudentStackTest {

	public StackRecursiveDoubleLinkedListImpl<Integer> stack1;
	public StackRecursiveDoubleLinkedListImpl<Integer> stack2;
	public StackRecursiveDoubleLinkedListImpl<Integer> stack3;

	private void getImplementations() {
		try {
			stack1 = new StackRecursiveDoubleLinkedListImpl<Integer>(2);
			stack1.push(new Integer(1));
			stack1.push(new Integer(2));

			stack2 = new StackRecursiveDoubleLinkedListImpl<Integer>(3);
			stack2.push(new Integer(1));
			stack2.push(new Integer(2));

			stack3 = new StackRecursiveDoubleLinkedListImpl<Integer>(6);
		} catch (StackOverflowException e) {
			// lololol
		}
	}
	
	@Test
	public void testNegativeSizes() {
		stack1 = new StackRecursiveDoubleLinkedListImpl<Integer>(0);
		assertTrue(stack1.isEmpty());
		assertTrue(stack1.isFull());
		assertNull(stack1.top());
		
		try {
			stack1.pop();
			Assert.fail();
		} catch (StackUnderflowException e) {
		}
		try {
			stack1.push(new Integer(1));
			Assert.fail();
		} catch (StackOverflowException e) {
		}
		
		stack2 = new StackRecursiveDoubleLinkedListImpl<Integer>(-4);
		
		try {
			stack2.pop();
			Assert.fail();
		} catch (StackUnderflowException e) {
		}
		try {
			stack2.push(new Integer(1));
			Assert.fail();
		} catch (StackOverflowException e) {
		}
	}

	// MÃ‰TODOS DE TESTE
	@Test
	public void testTop() throws StackUnderflowException, StackOverflowException {
		getImplementations();
		assertTrue(stack1.top().equals(new Integer(2)));
		stack1.pop();
		assertTrue(stack1.top().equals(new Integer(1)));
		assertTrue(stack2.top().equals(new Integer(2)));

		assertTrue(null == stack3.top());
		stack3.push(new Integer(10));
		assertTrue(stack3.top().equals(new Integer(10)));
	}

	@Test
	public void testIsEmpty() throws StackUnderflowException {
		getImplementations();
		assertFalse(stack1.isEmpty());
		stack1.pop();
		assertFalse(stack1.isEmpty());
		stack1.pop();
		assertTrue(stack1.isEmpty());

		assertFalse(stack2.isEmpty());
		assertTrue(stack3.isEmpty());
	}

	@Test
	public void testIsFull() throws StackOverflowException {
		getImplementations();
		assertTrue(stack1.isFull());

		assertFalse(stack2.isFull());
		stack2.push(new Integer(3));
		assertTrue(stack2.isFull());

		assertFalse(stack3.isFull());
	}

	@Test
	public void testPush() throws StackOverflowException {
		getImplementations();
		assertFalse(stack2.isFull());
		assertTrue(stack2.top().equals(new Integer(2)));
		stack2.push(new Integer(100));
		assertTrue(stack2.isFull());
		assertTrue(stack2.top().equals(new Integer(100)));
	}

	@Test(expected = StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		getImplementations();
		stack1.push(new Integer(5));
	}

	@Test
	public void testPop() throws StackUnderflowException {
		getImplementations();
		assertTrue(stack1.pop().equals(new Integer(2)));
		assertTrue(stack1.pop().equals(new Integer(1)));
		assertTrue(stack1.isEmpty());
	}

	@Test(expected = StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		getImplementations();
		assertEquals(new Integer(3), stack3.pop());
	}
}