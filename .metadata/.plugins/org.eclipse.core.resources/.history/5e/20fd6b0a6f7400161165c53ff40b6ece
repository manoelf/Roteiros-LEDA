package adt.avltree;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import adt.bst.BSTNode;

public class teste3 {

    private AVLTree<Integer> tree;
	private BSTNode<Integer> NIL = new BSTNode<Integer>();
	
	private void fillTree() {
		
		for(int i = 0; i < 10 ; i++) {
			tree.insert(i);
		}
		
		// nível 0:				3
		// nível 1:		1|>>>>>> <<<<<<|7
		// nível 2: 0|>> <<|2		5|>> <<|8
		// nível 3:				4|>> <<|6	 <<|9
		// pré-ordem: 3 1 0 2 7 5 4 6 8 9
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
	public void testInsert() throws Exception {
		fillTree();
		Integer[] expecteds = {3, 1, 0, 2, 7, 5, 4, 6, 8, 9};
		assertArrayEquals(expecteds, tree.preOrder());
		
		Integer[] expect2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		assertArrayEquals(expect2, tree.order());
		
		Integer[] expect3 = {0, 2, 1, 4, 6, 5, 9, 8, 7, 3};
		assertArrayEquals(expect3, tree.postOrder());
		
		assertEquals(expecteds.length, tree.size());
		assertEquals(3, tree.height());
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
	public <T> void testSize() {
		
		fillTree(); 

		int size = 10;
		assertEquals(tree.size(), 10);
		
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
		
		tree.remove(0);
		assertEquals(3, tree.height());

		tree.remove(2);
		assertEquals(3, tree.height());
		
		Integer[] preOrder = new Integer[]{ 7,3,1,5,4,6,8,9 };
		assertArrayEquals(preOrder, tree.preOrder());
		
		tree.remove(7);
		assertEquals(2, tree.height());
		
		Integer[] preOrder2 = new Integer[]{ 5, 3, 1, 4, 8, 6, 9 };
		assertArrayEquals(preOrder2, tree.preOrder());
	}

	@Test
	public void testSearch() {

		fillTree(); 

		assertEquals(NIL, tree.search(-40));
		assertEquals(new Integer(4), tree.search(4).getData());
	}
	
	@SuppressWarnings("unused")
	private void printTree() {
		System.out.print("{ ");
		for (Comparable<Integer> e : tree.preOrder()) {
			System.out.print(e + ",");
		}
		System.out.println(" }");
	}
	
}