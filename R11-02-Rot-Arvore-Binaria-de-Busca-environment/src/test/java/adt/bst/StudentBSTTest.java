package adt.bst;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.bt.BTNode;

public class StudentBSTTest {

    private BSTImpl<Integer> tree;
    private BTNode<Integer> NIL = new BTNode<Integer>();

    private void fillTree() {
        Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
        for (int i : array) {
            tree.insert(i);
        }
    }

    @Before
    public void setUp() {
        tree = new BSTImpl<>();
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
        assertEquals(null, tree.sucessor(243243));
        assertEquals(null, tree.sucessor(null));
        
        assertEquals(null, tree.predecessor(12));
        assertEquals(null, tree.predecessor(-23));
        assertEquals(null, tree.predecessor(0));
        assertEquals(null, tree.predecessor(243243));
        assertEquals(null, tree.predecessor(null));
    }

    @Test
    public void removeLeafsTest() {
        fillTree();

        assertEquals(4, tree.height());

        tree.remove(0);

        assertEquals(3, tree.height());

        tree.remove(2);
        tree.remove(12);
        tree.remove(67);
        tree.remove(232);

        assertEquals(2, tree.height());

        tree.remove(-40);
        tree.remove(5);
        tree.remove(9);
        tree.remove(76);

        assertEquals(1, tree.height());

        tree.remove(-34);
        tree.remove(23);

        assertEquals(0, tree.height());

        tree.remove(6);

        assertEquals(-1, tree.height());

    }

    @Test
    public void parentTest() {
        fillTree();

        assertEquals(null, tree.search(6).getParent());
        assertEquals(new Integer(6), tree.search(-34).getParent().getData());
        assertEquals(new Integer(-34), tree.search(-40).getParent().getData());
        assertEquals(new Integer(-34), tree.search(5).getParent().getData());
        assertEquals(new Integer(5), tree.search(2).getParent().getData());
        assertEquals(new Integer(2), tree.search(0).getParent().getData());
        assertEquals(new Integer(6), tree.search(23).getParent().getData());
        assertEquals(new Integer(23), tree.search(9).getParent().getData());
        assertEquals(new Integer(9), tree.search(12).getParent().getData());
        assertEquals(new Integer(23), tree.search(76).getParent().getData());
        assertEquals(new Integer(76), tree.search(67).getParent().getData());
        assertEquals(new Integer(76), tree.search(232).getParent().getData());

        assertEquals(new Integer(-34), tree.search(6).getLeft().getData());
        assertEquals(new Integer(-40), tree.search(-34).getLeft().getData());
        assertEquals(null, tree.search(-40).getLeft().getData());
        assertEquals(new Integer(2), tree.search(5).getLeft().getData());
        assertEquals(new Integer(0), tree.search(2).getLeft().getData());
        assertEquals(null, tree.search(0).getLeft().getData());
        assertEquals(new Integer(9), tree.search(23).getLeft().getData());
        assertEquals(null, tree.search(9).getLeft().getData());
        assertEquals(null, tree.search(12).getLeft().getData());
        assertEquals(new Integer(67), tree.search(76).getLeft().getData());
        assertEquals(null, tree.search(67).getLeft().getData());
        assertEquals(null, tree.search(232).getLeft().getData());

        assertEquals(null, tree.search(-40).getRight().getData());
        assertEquals(new Integer(5), tree.search(-34).getRight().getData());
        assertEquals(null, tree.search(5).getRight().getData());
        assertEquals(null, tree.search(2).getRight().getData());
        assertEquals(null, tree.search(0).getRight().getData());
        assertEquals(new Integer(23), tree.search(6).getRight().getData());
        assertEquals(new Integer(76), tree.search(23).getRight().getData());
        assertEquals(new Integer(232), tree.search(76).getRight().getData());
        assertEquals(null, tree.search(232).getRight().getData());
        assertEquals(null, tree.search(67).getRight().getData());
        assertEquals(new Integer(12), tree.search(9).getRight().getData());
        assertEquals(null, tree.search(12).getRight().getData());
    }

    @Test
    public void removedParentTest() {
        fillTree();

        assertEquals(12, tree.size());

        tree.remove(76);
        assertEquals(null, tree.search(76).getData());
        assertEquals(new Integer(232), tree.search(23).getRight().getData());
        assertEquals(new Integer(67), tree.search(232).getLeft().getData());
        assertEquals(null, tree.search(232).getRight().getData());
        assertEquals(new Integer(23), tree.search(232).getParent().getData());

        assertEquals(11, tree.size());

        tree.remove(232);
        assertEquals(null, tree.search(232).getData());
        assertEquals(new Integer(67), tree.search(23).getRight().getData());
        assertEquals(null, tree.search(67).getLeft().getData());
        assertEquals(null, tree.search(67).getRight().getData());
        assertEquals(new Integer(23), tree.search(67).getParent().getData());

        assertEquals(10, tree.size());

        tree.remove(67);
        assertEquals(null, tree.search(67).getData());
        assertEquals(null, tree.search(23).getRight().getData());

        assertEquals(9, tree.size());

        tree.remove(9);
        assertEquals(null, tree.search(9).getData());
        assertEquals(new Integer(12), tree.search(23).getLeft().getData());
        assertEquals(null, tree.search(12).getLeft().getData());
        assertEquals(new Integer(23), tree.search(12).getParent().getData());

        assertEquals(8, tree.size());

        tree.remove(23);
        assertEquals(null, tree.search(23).getData());
        assertEquals(new Integer(12), tree.search(6).getRight().getData());
        assertEquals(null, tree.search(12).getRight().getData());
        assertEquals(null, tree.search(12).getLeft().getData());
        assertEquals(new Integer(6), tree.search(12).getParent().getData());

        assertEquals(7, tree.size());

        tree.remove(-34);
        assertEquals(null, tree.search(-34).getData());
        assertEquals(new Integer(0), tree.search(6).getLeft().getData());
        assertEquals(new Integer(5), tree.search(0).getRight().getData());
        assertEquals(new Integer(-40), tree.search(0).getLeft().getData());
        assertEquals(new Integer(6), tree.search(0).getParent().getData());

        assertEquals(6, tree.size());

        tree.remove(6);
        assertEquals(null, tree.search(6).getData());
        assertEquals(new Integer(12), tree.getRoot().getData());
        assertEquals(null, tree.search(12).getRight().getData());
        assertEquals(new Integer(0), tree.search(12).getLeft().getData());
        assertEquals(new Integer(12), tree.search(0).getParent().getData());

        assertEquals(5, tree.size());

        tree.remove(0);
        assertEquals(null, tree.search(0).getData());
        assertEquals(new Integer(12), tree.getRoot().getData());
        assertEquals(new Integer(2), tree.getRoot().getLeft().getData());
        assertEquals(new Integer(12), tree.search(2).getParent().getData());
        assertEquals(new Integer(-40), tree.search(2).getLeft().getData());
        assertEquals(new Integer(5), tree.search(2).getRight().getData());
        assertEquals(new Integer(2), tree.search(-40).getParent().getData());
        assertEquals(new Integer(2), tree.search(5).getParent().getData());

        assertEquals(4, tree.size());

        tree.remove(12);
        assertEquals(null, tree.search(12).getData());
        assertEquals(new Integer(2), tree.getRoot().getData());
        assertEquals(new Integer(-40), tree.search(2).getLeft().getData());
        assertEquals(new Integer(5), tree.search(2).getRight().getData());
        assertEquals(new Integer(2), tree.search(-40).getParent().getData());
        assertEquals(new Integer(2), tree.search(5).getParent().getData());

        assertEquals(3, tree.size());

        tree.remove(tree.getRoot().getData());
        assertEquals(null, tree.search(2).getData());
        assertEquals(new Integer(5), tree.getRoot().getData());
        assertEquals(new Integer(-40), tree.search(5).getLeft().getData());
        assertEquals(null, tree.search(5).getRight().getData());
        assertEquals(new Integer(5), tree.search(-40).getParent().getData());

        assertEquals(2, tree.size());

        tree.remove(tree.getRoot().getData());
        assertEquals(null, tree.search(5).getData());
        assertEquals(new Integer(-40), tree.getRoot().getData());
        assertEquals(null, tree.search(-40).getLeft().getData());
        assertEquals(null, tree.search(-40).getRight().getData());
        assertEquals(null, tree.search(-40).getParent());

        assertEquals(1, tree.size());

        tree.remove(-40);
        assertEquals(null, tree.search(-40).getData());
        assertEquals(null, tree.getRoot().getData());
        assertEquals(null, tree.getRoot().getLeft());
        assertEquals(null, tree.getRoot().getRight());

        assertEquals(0, tree.size());

    }

    @Test
    public void heightTest() {
        fillTree();

        tree.remove(-40);
        tree.remove(23);
        tree.remove(9);
        tree.remove(76);
        tree.remove(67);
        tree.remove(12);
        tree.remove(232);
        assertEquals(4, tree.height());

        tree.insert(3);
        assertEquals(4, tree.height());

        tree.insert(4);
        assertEquals(5, tree.height());

        tree.remove(0);
        assertEquals(5, tree.height());

        tree.remove(2);
        assertEquals(4, tree.height());
    }

    @Test
    public void predecessorSucessorTest() {
        fillTree();
        assertEquals(new Integer(9), tree.sucessor(6).getData());
        assertEquals(new Integer(5), tree.predecessor(6).getData());

        assertEquals(new Integer(0), tree.sucessor(-34).getData());
        assertEquals(new Integer(-40), tree.predecessor(-34).getData());

        assertEquals(new Integer(-34), tree.sucessor(-40).getData());
        assertEquals(null, tree.predecessor(-40));

        assertEquals(new Integer(6), tree.sucessor(5).getData());
        assertEquals(new Integer(2), tree.predecessor(5).getData());

        assertEquals(new Integer(5), tree.sucessor(2).getData());
        assertEquals(new Integer(0), tree.predecessor(2).getData());

        assertEquals(new Integer(2), tree.sucessor(0).getData());
        assertEquals(new Integer(-34), tree.predecessor(0).getData());

        assertEquals(new Integer(67), tree.sucessor(23).getData());
        assertEquals(new Integer(12), tree.predecessor(23).getData());

        assertEquals(new Integer(12), tree.sucessor(9).getData());
        assertEquals(new Integer(6), tree.predecessor(9).getData());

        assertEquals(new Integer(23), tree.sucessor(12).getData());
        assertEquals(new Integer(9), tree.predecessor(12).getData());

        assertEquals(new Integer(232), tree.sucessor(76).getData());
        assertEquals(new Integer(67), tree.predecessor(76).getData());

        assertEquals(new Integer(76), tree.sucessor(67).getData());
        assertEquals(new Integer(23), tree.predecessor(67).getData());

        assertEquals(null, tree.sucessor(232));
        assertEquals(new Integer(76), tree.predecessor(232).getData());
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

        fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

        assertEquals(null, tree.predecessor(-40));
        assertEquals(null, tree.sucessor(232));
        assertEquals(new Integer(-34), tree.sucessor(-40).getData());

        assertEquals(new Integer(-40), tree.predecessor(-34).getData());
        assertEquals(new Integer(0), tree.sucessor(-34).getData());

        assertEquals(new Integer(-34), tree.predecessor(0).getData());
        assertEquals(new Integer(2), tree.sucessor(0).getData());

        assertEquals(new Integer(0), tree.predecessor(2).getData());
        assertEquals(new Integer(5), tree.sucessor(2).getData());
    }

    @Test
    public void testSize() {
        fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

        int size = 12;
        assertEquals(size, tree.size());

        while (!tree.isEmpty()) {
            tree.remove(tree.getRoot().getData());
            assertEquals(--size, tree.size());
        }
    }

    @Test
    public void equalsElementTest() {
        assertEquals(-1, tree.height());
        assertEquals(0, tree.size());

        tree.insert(10);

        assertEquals(0, tree.height());
        assertEquals(1, tree.size());

        tree.insert(9);
        
        assertEquals(1, tree.height());
        assertEquals(2, tree.size());
        
        tree.insert(10);
        
        assertEquals(1, tree.height());
        assertEquals(2, tree.size());
        
        tree.insert(9);
        
        assertEquals(1, tree.height());
        assertEquals(2, tree.size());


    }

    @Test
    public void invalidInsert() {
        tree.insert(null);
        tree.insert(null);

        assertEquals(0, tree.size());
        assertEquals(-1, tree.height());
        assertEquals(null, tree.getRoot().getData());
        assertEquals(null, tree.getRoot().getLeft());
        assertEquals(null, tree.getRoot().getRight());
        assertEquals(null, tree.maximum());
        assertEquals(null, tree.minimum());
        assertTrue(tree.isEmpty());
    }

    @Test
    public void minimumMaximum() {
        fillTree();// -40 -34 0 2 5 6 9 12 23 67 76 232

        assertEquals(new Integer(-40), tree.minimum().getData());
        assertEquals(new Integer(232), tree.maximum().getData());

        tree.remove(-40);
        tree.remove(232);

        assertEquals(new Integer(-34), tree.minimum().getData());
        assertEquals(new Integer(76), tree.maximum().getData());

        tree.remove(-34);
        tree.remove(76);

        assertEquals(new Integer(0), tree.minimum().getData());
        assertEquals(new Integer(67), tree.maximum().getData());

        tree.remove(0);
        tree.remove(67);

        assertEquals(new Integer(2), tree.minimum().getData());
        assertEquals(new Integer(23), tree.maximum().getData());

        tree.remove(2);
        tree.remove(23);

        assertEquals(new Integer(5), tree.minimum().getData());
        assertEquals(new Integer(12), tree.maximum().getData());

        tree.remove(5);
        tree.remove(12);

        assertEquals(new Integer(6), tree.minimum().getData());
        assertEquals(new Integer(9), tree.maximum().getData());

        tree.remove(6);
        tree.remove(9);

        assertEquals(null, tree.minimum());
        assertEquals(null, tree.maximum());
        assertTrue(tree.isEmpty());
    }

    @Test
    public void testHeight() {
        fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

        Integer[] preOrder = new Integer[] { 6, -34, -40, 5, 2, 0, 23, 9, 12,
                76, 67, 232 };
        assertArrayEquals(preOrder, tree.preOrder());
        assertEquals(4, tree.height());

        tree.remove(0);
        assertEquals(3, tree.height());

        tree.remove(2);
        assertEquals(3, tree.height());
    }

    @Test
    public void testRemove() {
        fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

        Integer[] order = { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 };
        assertArrayEquals(order, tree.order());

        tree.remove(6);
        order = new Integer[] { -40, -34, 0, 2, 5, 9, 12, 23, 67, 76, 232 };
        assertArrayEquals(order, tree.order());

        tree.remove(9);
        order = new Integer[] { -40, -34, 0, 2, 5, 12, 23, 67, 76, 232 };
        assertArrayEquals(order, tree.order());
        assertEquals(NIL, tree.search(6));
        assertEquals(NIL, tree.search(9));

    }

    @Test
    public void testSearch() {

        fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

        assertEquals(new Integer(-40), tree.search(-40).getData());
        assertEquals(new Integer(-34), tree.search(-34).getData());
        assertEquals(NIL, tree.search(2534));
    }
    
    @Test
	public void testeRemove() {
		fillTree();
		Integer[] array = {-40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		this.tree.remove(0);
		array = new Integer[] {-40, -34, 2, 5, 6, 9, 12, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		this.tree.remove(12);
		array = new Integer[] {-40, -34, 2, 5, 6, 9, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());

		this.tree.remove(2);
		array = new Integer[] {-40, -34, 5, 6, 9, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		this.tree.remove(-34);
		array = new Integer[] {-40, 5, 6, 9, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		
		
		
		this.tree.remove(232);
		array = new Integer[] {-40, 5, 6, 9, 23, 67, 76};
		Assert.assertArrayEquals(array, this.tree.order());
		Assert.assertTrue(this.tree.search(232).getData() == null);
		
		
	}
	
	
	@Test
	public void testeRemove2() {
		fillTree();
		Assert.assertTrue(this.tree.sucessor(232) == null);
		this.tree.remove(232);
		assertEquals(null, tree.search(232).getData());
		Assert.assertTrue(this.tree.search(232).getData() == null);
		
		
		this.tree.remove(9);
		Integer[] array = {-40, -34, 0, 2, 5, 6, 12, 23, 67, 76};
		Assert.assertArrayEquals(array, this.tree.order());
		
	}
	
	@Test
	public void testeRemoveRoots() {
		fillTree();
		Integer[] array = {-40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5, 9, 12, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5, 12, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		

		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5};
		Assert.assertArrayEquals(array, this.tree.order());		
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, 0, 2, 5};
		Assert.assertArrayEquals(array, this.tree.order());
		
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, 2, 5};
		Assert.assertArrayEquals(array, this.tree.order());
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, 5};
		Assert.assertArrayEquals(array, this.tree.order());	
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40};
		Assert.assertArrayEquals(array, this.tree.order());
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{};
		Assert.assertArrayEquals(array, this.tree.order());
	}
	
	@Test
	public void testeSucessorPredecessor() {
		fillTree();
		Assert.assertEquals(new Integer(9), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(23));
		Assert.assertEquals(new Integer(9), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(67));
		Assert.assertEquals(new Integer(9), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(76));
		Assert.assertEquals(new Integer(9), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(232));
		Assert.assertEquals(new Integer(9), this.tree.sucessor(this.tree.getRoot().getData()).getData());
		
		this.tree.remove(new Integer(9));
		Assert.assertEquals(new Integer(12), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(12));
		Assert.assertEquals(null, this.tree.sucessor(new Integer(6)));
		
		this.tree.remove(new Integer(6));
		Assert.assertEquals(new Integer(-34), this.tree.getRoot().getData());
		Assert.assertEquals(new Integer(0), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(0));
		Assert.assertEquals(new Integer(2), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(2));
		Assert.assertEquals(new Integer(5) , this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(5));
		Assert.assertEquals(new Integer(-34), this.tree.getRoot().getData());
		
		Assert.assertEquals(null, this.tree.sucessor(this.tree.getRoot().getData()));

		this.tree.remove(new Integer(-40));
		Assert.assertEquals(null , this.tree.sucessor(this.tree.getRoot().getData()));

		this.tree.remove(new Integer(5));
		Assert.assertEquals(null, this.tree.sucessor(this.tree.getRoot().getData()));
	
	}
}