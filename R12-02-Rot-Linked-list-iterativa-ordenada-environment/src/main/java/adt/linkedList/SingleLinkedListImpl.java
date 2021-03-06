package adt.linkedList;

@SuppressWarnings("unchecked")
public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;
	protected int size;
	
	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> tmp = head;
		while (!tmp.isNIL() && !(tmp.getData().equals(element))) {
			tmp = tmp.getNext();
		}
		return tmp.getData();
	}

	@Override
	public void insert(T element) {
		
		SingleLinkedListNode<T> node = new SingleLinkedListNode<T>();
		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, node);
		
        if (element != null) {
	        if (isEmpty()) {
	            this.head = newNode;
	        } else {
	        	SingleLinkedListNode<T> tmp = head;
	        
	            while (!tmp.getNext().isNIL()) {
	                tmp = tmp.getNext();
	            }
	            tmp.setNext(newNode);
	        }
	        this.size += 1;
        }
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (!this.isEmpty()) {
				if (this.head.getData().equals(element)) {
					this.head = head.getNext();
					this.size -= 1;
				} else {
				
					SingleLinkedListNode<T> prv = head;
					SingleLinkedListNode<T> tmp = head.getNext();
					
					while (!tmp.isNIL() && !(tmp.getData().equals(element))) {
						prv = tmp;
						tmp = tmp.getNext();
					}
					
					if (!tmp.isNIL()) {
						prv.setNext(tmp.getNext());
						this.size -= 1;
					}
				}
			}
		}
	}
	
	@Override
	public T[] toArray(){
		T[] array = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> tmp = head;
		int indice = 0;
		
		while(!tmp.isNIL()) {
			array[indice++] = tmp.getData();
			tmp = tmp.getNext();
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}	
}