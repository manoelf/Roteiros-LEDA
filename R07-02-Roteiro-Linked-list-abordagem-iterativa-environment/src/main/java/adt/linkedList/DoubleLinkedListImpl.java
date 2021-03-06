package adt.linkedList;

@SuppressWarnings("unchecked")
public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	@Override
	public void insert(T element) {
		
		DoubleLinkedListNode<T> node = new DoubleLinkedListNode<T>();
		
		if (element != null) {
			if (isEmpty()) {
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, node, new DoubleLinkedListNode<T>());
				super.head = newNode;
				this.last = newNode;
			} else {
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, node, this.last);
				this.last.next = newNode;
				this.last = newNode;
			}
			super.size += 1;
		}
	}
	
	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (!isEmpty()) {
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) this.head, new DoubleLinkedListNode<T>());
				head = newNode;
				((DoubleLinkedListNode<T>)newNode.next).previous = newNode;
				size += 1;
			} else {
				this.insert(element);
			}
		}		
	}
	
	@Override
	public void remove(T element) {
		if (element != null) {
			if (!this.isEmpty()) {
				if (this.head.getData().equals(element)) {
					removeFirst();
				} else {
				
					DoubleLinkedListNode<T> prv = (DoubleLinkedListNode<T>) head;
					DoubleLinkedListNode<T> tmp = (DoubleLinkedListNode<T>) head.getNext();
					
					while (!tmp.isNIL() && !(tmp.getData().equals(element))) {
						prv = tmp;
						tmp = (DoubleLinkedListNode<T>) tmp.getNext();
					}
					
					if (tmp.getData() == last.getData()) {
						last = prv;
					}
					
					if (!tmp.isNIL()) {
						prv.setNext(tmp.getNext());
						((DoubleLinkedListNode<T>) tmp.next).previous = prv;
						this.size -= 1;
					}
				}
			}
		}
	}
	
	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			
			head = head.next;
			((DoubleLinkedListNode<T>) head).previous = new DoubleLinkedListNode<T>();
			size -= 1;
			
			if (size == 0) {
				last = (DoubleLinkedListNode<T>) head;
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (size == 1) {
				last.setData(null);
				head = last;
			} else {
				last = last.previous;
				last.next = new DoubleLinkedListNode<>();
			}
			size -= 1;
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	@Override
	public T[] toArray(){
		T[] array = (T[]) new Object[super.size()];
		
		if (!isEmpty()) {
			DoubleLinkedListNode<T> tmp = (DoubleLinkedListNode<T>) super.head;
			int indice = 0;
			while(!tmp.isNIL() & indice < super.size()) {
				array[indice++] = tmp.getData();
				tmp = (DoubleLinkedListNode<T>) tmp.getNext();
			}
		}	
		return array;
	}
	
	
	public DoubleLinkedListNode<T> getHead() {
		return (DoubleLinkedListNode<T>) head;
	}
	
}