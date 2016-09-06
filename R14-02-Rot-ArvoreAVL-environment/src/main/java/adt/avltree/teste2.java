package adt.avltree;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import adt.bst.BSTNode;

/*
 * Author: Ordan Santos
 */
public class teste2 {

    private AVLTree<Integer> tree;
	private BSTNode<Integer> NIL = new BSTNode<Integer>();
	
	private void fillTree() {
		
		for(int i = 0; i < 10 ; i++) {
			tree.insert(i);
		}
	}

	@Before
	public void setUp() {
		tree = new AVLTreeImpl<>();
	}

	@Test
	public void testInit() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(-1, tree.height());

		assertEquals(NIL, tree.getRoot());

		assertArrayEquals(new Integer[] {}, tree.order());
		assertArrayEquals(new Integer[] {}, tree.preOrder());
		assertArrayEquals(new Integer[] {}, tree.postOrder());

		assertEquals(NIL, tree.search(12));
		assertEquals(NIL, tree.search(-23));
		assertEquals(NIL, tree.search(0));

		assertEquals(null, tree.minimum());
		assertEquals(null, tree.maximum());

		assertEquals(null, tree.sucessor(12));
		assertEquals(null, tree.sucessor(-23));
		assertEquals(null, tree.sucessor(0));

		assertEquals(null, tree.predecessor(12));
		assertEquals(null, tree.predecessor(-23));
		assertEquals(null, tree.predecessor(0));
	}

	@Test
	public void testMinMax() {
		tree.insert(6);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(6), tree.maximum().getData());

		tree.insert(23);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(-34);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(5);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(9);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());
	}

	@Test
	public void testSucessorPredecessor() {

		fillTree(); 

		assertEquals(null, tree.predecessor(15));
		assertEquals(new Integer(3), tree.sucessor(2).getData());

		assertEquals(new Integer(3), tree.predecessor(4).getData());
		assertEquals(new Integer(5), tree.sucessor(4).getData());
	}

	@Test
	public void testSize() {
		fillTree(); 

		int size = 10;
		assertEquals(size, tree.size());

		while(!tree.isEmpty()) {
			tree.remove(tree.getRoot().getData());
			assertEquals(--size, tree.size());
		}
	}

	@Test
	public void testHeight() {
		fillTree(); 

		Integer[] preOrder = new Integer[] {3,1,0,2,7,5,4,6,8,9};
		assertArrayEquals(preOrder, tree.preOrder());
		assertEquals(3, tree.height());

		tree.remove(0);
		assertEquals(3, tree.height());

		tree.remove(2);
		assertEquals(3, tree.height());
		
		tree.remove(1);
		assertEquals(3, tree.height());
		Integer[] preOrder2 = new Integer[] {7,5,3,4,6,8,9};
		assertArrayEquals(preOrder2, tree.preOrder());
	}

	@Test
	public void testRemove() {
		fillTree(); 
		//tente remover elementos e verificar se as rotacoes produzem uma AVL correta
	}

	@Test
	public void testSearch() {

		fillTree(); 

		assertEquals(NIL, tree.search(-40));
		assertEquals(new Integer(4), tree.search(4).getData());
	}
	
	
	@Test
	public void bruteHeightTest(){
		for (int i = 1; i <= 10000; i++){
			tree.insert(i);
			Assert.assertTrue(tree.height() <= maxPossibleHeight(i));
			Assert.assertEquals(i, tree.size());
			Assert.assertTrue(testBalance((BSTNode<Integer>) tree.getRoot()));
		}
	}
	
	private double log2(double n){
		return Math.log(n) / Math.log(2);
	}
	
	private int maxPossibleHeight(int n){
		if (n==0)
			return 0;
		
		return (int) (2 * log2(n));
	}
	
	@Test
	public void left(){
		tree.insert(2);
		tree.insert(1);
		tree.insert(0);
		Assert.assertEquals(1, tree.height());
		Assert.assertEquals(new Integer(1), tree.getRoot().getData());
		Assert.assertEquals(new Integer(0), tree.getRoot().getLeft().getData());
		Assert.assertEquals(new Integer(2), tree.getRoot().getRight().getData());
		Assert.assertEquals(tree.getRoot().getLeft().getParent(), tree.getRoot());
		Assert.assertEquals(tree.getRoot().getRight().getParent(), tree.getRoot());
		Assert.assertEquals(tree.getRoot().getParent(), null);
	}
	
	@Test
	public void right(){
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		Assert.assertEquals(1, tree.height());
		Assert.assertEquals(new Integer(2), tree.getRoot().getData());
		Assert.assertEquals(new Integer(1), tree.getRoot().getLeft().getData());
		Assert.assertEquals(new Integer(3), tree.getRoot().getRight().getData());
		Assert.assertEquals(tree.getRoot().getLeft().getParent(), tree.getRoot());
		Assert.assertEquals(tree.getRoot().getRight().getParent(), tree.getRoot());
		Assert.assertEquals(tree.getRoot().getParent(), null);
	}
	
	@Test
	public void rightleft(){
		tree.insert(5);
		tree.insert(6);
		tree.insert(0);
		tree.insert(1);
		tree.insert(-1);
		tree.insert(2);
		
		Assert.assertEquals(2, tree.height());
		Assert.assertEquals(new Integer(1), tree.getRoot().getData());
		Assert.assertEquals(new Integer(0), tree.getRoot().getLeft().getData());
		Assert.assertEquals(new Integer(5), tree.getRoot().getRight().getData());
		Assert.assertEquals(new Integer(-1), tree.getRoot().getLeft().getLeft().getData());
		Assert.assertEquals(new Integer(2), tree.getRoot().getRight().getLeft().getData());
		Assert.assertEquals(new Integer(6), tree.getRoot().getRight().getRight().getData());
		
		Assert.assertArrayEquals(new Integer[]{1,  0, -1, 5, 2, 6}, tree.preOrder());
		
	}
	
	@Test
	public void leftRight(){
		tree.insert(3);
		tree.insert(2);
		tree.insert(7);
		tree.insert(5);
		tree.insert(8);
		tree.insert(4);
		
		Assert.assertEquals(2, tree.height());
		Assert.assertEquals(new Integer(5), tree.getRoot().getData());
		Assert.assertEquals(new Integer(3), tree.getRoot().getLeft().getData());
		Assert.assertEquals(new Integer(7), tree.getRoot().getRight().getData());
		Assert.assertEquals(new Integer(2), tree.getRoot().getLeft().getLeft().getData());
		Assert.assertEquals(new Integer(4), tree.getRoot().getLeft().getRight().getData());
		Assert.assertEquals(new Integer(8), tree.getRoot().getRight().getRight().getData());
		
		Assert.assertArrayEquals(new Integer[]{5,  3, 2, 4, 7, 8}, tree.preOrder());
		
	}
	
	private boolean testParents(BSTNode<Integer> node, BSTNode<Integer> parent){
		
		if (node.isEmpty()) return true;
		
		if (!node.getParent().equals(parent)) return false;
		
		return testParents((BSTNode<Integer>) node.getLeft(), node) && testParents((BSTNode<Integer>) node.getRight(), node);
		
	}
	
	private boolean testBalance(BSTNode<Integer> node){
		if (node.isEmpty()) return true;
		int balance = height((BSTNode<Integer>) node.getLeft()) - height((BSTNode<Integer>) node.getRight());
		if (Math.abs(balance) > 1) return false;
		return testBalance((BSTNode<Integer>) node.getLeft()) && testBalance((BSTNode<Integer>) node.getRight());
	}
	
	@Test
	public void fuckingTest(){
		
		ArrayList<Integer> myarray = new ArrayList<Integer>();
		
		Random gerador = new Random();
		
		for (int i = 0; i < 10000; i++){
			
			int k = gerador.nextInt(2);
			int element = gerador.nextInt(30);
			
			if (k == 0){	
				
				if (myarray.contains(element) == true)
					myarray.remove(myarray.indexOf(element));
				
				tree.remove(element);
				
				Collections.sort(myarray);
				Assert.assertArrayEquals(myarray.toArray(), tree.order());
				Assert.assertTrue(tree.height() <= maxPossibleHeight(myarray.size()));
				Assert.assertEquals(tree.size(), myarray.size());
				
			} else {
				if (myarray.contains(element)) {
					myarray.add(element);
					tree.insert(element);
				}
				
				this.printArray(myarray.toArray());
				this.printArray(tree.order());
				
				Collections.sort(myarray);
				Assert.assertArrayEquals(myarray.toArray(), tree.order());
				
				Assert.assertTrue(tree.height() <= maxPossibleHeight(myarray.size()));
				Assert.assertEquals(tree.size(), myarray.size());
				
			}
			
			Assert.assertTrue(testParents((BSTNode<Integer>) tree.getRoot(), NIL));
			Assert.assertTrue(testBalance((BSTNode<Integer>) tree.getRoot()));
		}
		
	}
	

	private void printArray(Object[] objects) {
		for (Object e : objects) {
			System.out.print(e + " ");
		}
		System.out.println();
	}

	private int height(BSTNode<Integer> node) {
		if (node.isEmpty()) return -1;
		return 1 + Math.max(height((BSTNode<Integer>)node.getLeft()), height((BSTNode<Integer>)node.getRight()));
	}
	
}