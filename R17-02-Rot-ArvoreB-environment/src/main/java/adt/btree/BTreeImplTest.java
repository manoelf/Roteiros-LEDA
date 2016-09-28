package adt.btree;

import adt.btree.BNode;
import adt.btree.BTree;
import adt.btree.BTreeImpl;
import org.junit.Before;
import org.junit.Test;
 
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
 
public class BTreeImplTest{
 
    BTree<Integer> bt, tree1;
 
    @Before
    public void setUp(){
        bt = new BTreeImpl<>(3);
        tree1 = new BTreeImpl<>(4);
    }
 
    @Test
    public void testInsert(){
        bt.insert(1);
        bt.insert(2);
        assertEquals(0, bt.height());
        bt.insert(3);
        assertEquals(1, bt.height());
        assertArrayEquals(bt.getRoot().getElements().toArray(), new Integer[] {2});
        assertArrayEquals(bt.getRoot().getChildren().get(0).getElements().toArray(), new Integer[] {1});
        assertArrayEquals(bt.getRoot().getChildren().get(1).getElements().toArray(), new Integer[] {3});
 
 
 
        bt.insert(4);
        assertEquals(1, bt.height());
        assertArrayEquals(bt.getRoot().getElements().toArray(), new Integer[] {2});
        assertArrayEquals(bt.getRoot().getChildren().get(0).getElements().toArray(), new Integer[] {1});
        assertArrayEquals(bt.getRoot().getChildren().get(1).getElements().toArray(), new Integer[] {3,4});
 
        bt.insert(5);
        assertEquals(1, bt.height());
        assertArrayEquals(bt.getRoot().getElements().toArray(), new Integer[] {2,4});
        assertArrayEquals(bt.getRoot().getChildren().get(0).getElements().toArray(), new Integer[] {1});
        assertArrayEquals(bt.getRoot().getChildren().get(1).getElements().toArray(), new Integer[] {3});
        assertArrayEquals(bt.getRoot().getChildren().get(2).getElements().toArray(), new Integer[] {5});
 
        bt.insert(6);
        assertEquals(1, bt.height());
        assertArrayEquals(bt.getRoot().getElements().toArray(), new Integer[] {2,4});
        assertArrayEquals(bt.getRoot().getChildren().get(0).getElements().toArray(), new Integer[] {1});
        assertArrayEquals(bt.getRoot().getChildren().get(1).getElements().toArray(), new Integer[] {3});
        assertArrayEquals(bt.getRoot().getChildren().get(2).getElements().toArray(), new Integer[] {5,6});
 
        bt.insert(7);
        assertEquals(2, bt.height());
        assertArrayEquals(bt.getRoot().getElements().toArray(), new Integer[] {4});
        assertArrayEquals(bt.getRoot().getChildren().get(0).getElements().toArray(), new Integer[] {2});
        assertArrayEquals(bt.getRoot().getChildren().get(0).getChildren().get(0).getElements().toArray(), new Integer[] {1});
        assertArrayEquals(bt.getRoot().getChildren().get(0).getChildren().get(1).getElements().toArray(), new Integer[] {3});
 
 
        assertArrayEquals(bt.getRoot().getChildren().get(1).getElements().toArray(), new Integer[] {6});
        assertArrayEquals(bt.getRoot().getChildren().get(1).getChildren().get(0).getElements().toArray(), new Integer[] {5});
        assertArrayEquals(bt.getRoot().getChildren().get(1).getChildren().get(1).getElements().toArray(), new Integer[] {7});
 
    }
 
    @Test
    public void testDepthLeftOrder() {
 
        tree1.insert(13);
        tree1.insert(9);
        tree1.insert(5);
    }
 
    @Test
    public void testInsert2() {
 
        tree1.insert(13);
        tree1.insert(9);
        tree1.insert(5);
 
        assertEquals(tree1.getRoot().getElementAt(0), new Integer(5));
        assertEquals(tree1.getRoot().getElementAt(1), new Integer(9));
        assertEquals(tree1.getRoot().getElementAt(2), new Integer(13));
 
        assertEquals(tree1.size(), 3);
        assertEquals(tree1.height(), 0);
 
        tree1.insert(12);
 
        assertEquals(tree1.size(), 4);
        assertEquals(tree1.height(), 1);
        assertEquals(tree1.getRoot().getElementAt(0), new Integer(12));
        assertArrayEquals(tree1.getRoot().getChildren().get(0).getElements().toArray(), new Integer[] {5,9});
        assertArrayEquals(tree1.getRoot().getChildren().get(1).getElements().toArray(), new Integer[] {13});
 
        tree1.insert(122);
        tree1.insert(3);
 
        assertEquals(tree1.size(), 6);
        assertEquals(tree1.height(), 1);
        assertEquals(tree1.getRoot().getElementAt(0), new Integer(12));
        assertArrayEquals(tree1.getRoot().getChildren().get(0).getElements().toArray(), new Integer[] {3,5,9});
        assertArrayEquals(tree1.getRoot().getChildren().get(1).getElements().toArray(), new Integer[] {13,122});
 
        tree1.insert(76);
        assertEquals(tree1.getRoot().getElementAt(0), new Integer(12));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(0), new Integer(3));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(1), new Integer(5));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(2), new Integer(9));
        assertEquals(tree1.getRoot().getChildren().get(1).getElementAt(0), new Integer(13));
        assertEquals(tree1.getRoot().getChildren().get(1).getElementAt(1), new Integer(76));
        assertEquals(tree1.getRoot().getChildren().get(1).getElementAt(2), new Integer(122));
 
 
        tree1.insert(10);
        assertEquals(1, tree1.height());
        assertEquals(tree1.getRoot().getElementAt(0), new Integer(9));
        assertEquals(tree1.getRoot().getElementAt(1), new Integer(12));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(0), new Integer(3));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(1), new Integer(5));
        assertEquals(tree1.getRoot().getChildren().get(1).getElementAt(0), new Integer(10));
        assertEquals(tree1.getRoot().getChildren().get(2).getElementAt(0), new Integer(13));
        assertEquals(tree1.getRoot().getChildren().get(2).getElementAt(1), new Integer(76));
        assertEquals(tree1.getRoot().getChildren().get(2).getElementAt(2), new Integer(122));
 
 
        tree1.insert(7);
        assertEquals(1, tree1.height());
        assertEquals(tree1.getRoot().getElementAt(0), new Integer(9));
        assertEquals(tree1.getRoot().getElementAt(1), new Integer(12));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(0), new Integer(3));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(1), new Integer(5));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(2), new Integer(7));
        assertEquals(tree1.getRoot().getChildren().get(1).getElementAt(0), new Integer(10));
        assertEquals(tree1.getRoot().getChildren().get(2).getElementAt(0), new Integer(13));
        assertEquals(tree1.getRoot().getChildren().get(2).getElementAt(1), new Integer(76));
        assertEquals(tree1.getRoot().getChildren().get(2).getElementAt(2), new Integer(122));
 
 
        tree1.insert(8);
        assertEquals(1, tree1.height());
        assertEquals(tree1.getRoot().getElementAt(0), new Integer(7));
        assertEquals(tree1.getRoot().getElementAt(1), new Integer(9));
        assertEquals(tree1.getRoot().getElementAt(2), new Integer(12));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(0), new Integer(3));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(1), new Integer(5));
        assertEquals(tree1.getRoot().getChildren().get(1).getElementAt(0), new Integer(8));
        assertEquals(tree1.getRoot().getChildren().get(2).getElementAt(0), new Integer(10));
        assertEquals(tree1.getRoot().getChildren().get(3).getElementAt(0), new Integer(13));
        assertEquals(tree1.getRoot().getChildren().get(3).getElementAt(1), new Integer(76));
        assertEquals(tree1.getRoot().getChildren().get(3).getElementAt(2), new Integer(122));
 
        tree1.insert(6);
        assertEquals(1, tree1.height());
        assertEquals(tree1.getRoot().getElementAt(0), new Integer(7));
        assertEquals(tree1.getRoot().getElementAt(1), new Integer(9));
        assertEquals(tree1.getRoot().getElementAt(2), new Integer(12));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(0), new Integer(3));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(1), new Integer(5));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(2), new Integer(6));
        assertEquals(tree1.getRoot().getChildren().get(1).getElementAt(0), new Integer(8));
        assertEquals(tree1.getRoot().getChildren().get(2).getElementAt(0), new Integer(10));
        assertEquals(tree1.getRoot().getChildren().get(3).getElementAt(0), new Integer(13));
        assertEquals(tree1.getRoot().getChildren().get(3).getElementAt(1), new Integer(76));
        assertEquals(tree1.getRoot().getChildren().get(3).getElementAt(2), new Integer(122));
 
        tree1.insert(1000);
        assertEquals(2, tree1.height());
        assertEquals(tree1.getRoot().getElementAt(0), new Integer(12));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(0), new Integer(7));
        assertEquals(tree1.getRoot().getChildren().get(0).getElementAt(1), new Integer(9));
        assertEquals(tree1.getRoot().getChildren().get(1).getElementAt(0), new Integer(122));
 
        assertEquals(tree1.getRoot().getChildren().get(0).getChildren().get(0).getElementAt(0), new Integer(3));
        assertEquals(tree1.getRoot().getChildren().get(0).getChildren().get(0).getElementAt(1), new Integer(5));
        assertEquals(tree1.getRoot().getChildren().get(0).getChildren().get(0).getElementAt(2), new Integer(6));
 
        assertEquals(tree1.getRoot().getChildren().get(0).getChildren().get(1).getElementAt(0), new Integer(8));
 
        assertEquals(tree1.getRoot().getChildren().get(0).getChildren().get(2).getElementAt(0), new Integer(10));
 
 
        assertEquals(tree1.getRoot().getChildren().get(1).getChildren().get(0).getElementAt(0), new Integer(13));
        assertEquals(tree1.getRoot().getChildren().get(1).getChildren().get(0).getElementAt(1), new Integer(76));
 
        assertEquals(tree1.getRoot().getChildren().get(1).getChildren().get(1).getElementAt(0), new Integer(1000));
 
        for(int i = 0 ; i < 4332 ; i++){
			tree1.insert(null);
        }
 
 
    }
 
    @Test
    public void testDfs(){
 
        assertArrayEquals(new BNode[]{}, bt.depthLeftOrder());
 
        bt.insert(1);
        bt.insert(2);
        bt.insert(3);
        bt.insert(4);
        bt.insert(5);
 
        BNode<Integer> first = bt.getRoot();
        BNode<Integer> se = bt.getRoot().getChildren().get(0);
        BNode<Integer> t = bt.getRoot().getChildren().get(1);
        BNode<Integer> fo = bt.getRoot().getChildren().get(2);
 
        assertArrayEquals(new BNode[]{first,se,t,fo}, bt.depthLeftOrder());
 
    }
 
 
}