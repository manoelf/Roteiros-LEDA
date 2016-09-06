package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListNode;

/**
 * Para testar essa classe voce deve implementar seu comparador. Primeiro
 * implemente todos os métodos requeridos. Depois implemente dois comparadores
 * (com idéias opostas) e teste sua classe com eles. Dependendo do comparador
 * que você utilizar a lista funcionar como ascendente ou descendente, mas a
 * implemntação dos métodos é a mesma.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class OrderedDoubleLinkedListImpl<T> extends OrderedSingleLinkedListImpl<T>
		implements OrderedLinkedList<T>, DoubleLinkedList<T> {

	private DoubleLinkedListNode<T> last;

	public OrderedDoubleLinkedListImpl() {
	}

	public OrderedDoubleLinkedListImpl(Comparator<T> comparator) {
		super(comparator);
	}

	@Override
	public void insert(T element) {

		DoubleLinkedListNode<T> node = new DoubleLinkedListNode<T>();

		if (element != null) {
			if (isEmpty()) {
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, node, null);

				this.head = newNode;
				this.last = newNode;
				super.size += 1;
			} else {
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.getHead();
				if (getComparator().compare(aux.getData(), element) > 0) {
					insertFirst(element);
				} else {
					while (!aux.getNext().isNIL() && getComparator().compare(aux.getNext().getData(), element) < 0) {
						aux = (DoubleLinkedListNode<T>) aux.getNext();
					}
					DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element,
							(DoubleLinkedListNode<T>) aux.getNext(), null);
					((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(newNode);
					aux.setNext(newNode);
					newNode.setPrevious(aux);

					if (aux.getNext().getNext().isNIL()) {
						last = (DoubleLinkedListNode<T>) aux.getNext();
					}
					super.size += 1;
				}
			}

		}
	}

	/**
	 * Este método faz sentido apenas se o elemento a ser inserido pode
	 * realmente ficar na primeira posição (devido a ordem)
	 */
	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (this.getHead().isNIL()) {
				insert(element);
			} else {
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.getHead();
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) aux,
						null);
				((DoubleLinkedListNode<T>) aux).setPrevious(newNode);
				head = newNode;
				super.size += 1;
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.getHead().isNIL()) {
			if (this.getHead().getNext().isNIL()) {
				this.head = new DoubleLinkedListNode<T>();
				this.last = (DoubleLinkedListNode<T>) this.getHead();
			} else {
				this.setHead(getHead().getNext());
				((DoubleLinkedListNode<T>) this.getHead()).setPrevious(null);
			}
			super.size -= 1;
		}
	}

	@Override
	public void removeLast() {
		if (!this.getHead().isNIL()) {
			if (this.getHead().getNext().isNIL()) {
				this.head = new DoubleLinkedListNode<T>();
				this.last = (DoubleLinkedListNode<T>) this.getHead();
			} else {
				this.last = last.getPrevious();
				this.last.setNext(new DoubleLinkedListNode<T>());
			}
			super.size -= 1;
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			if (this.getHead().getData().equals(element)) {
				if (this.getHead().getNext().isNIL()) {
					this.head = new DoubleLinkedListNode<T>();
					this.last = (DoubleLinkedListNode<T>) this.getHead();
				} else {
					this.setHead(getHead().getNext());
					((DoubleLinkedListNode<T>) this.getHead()).setPrevious(null);
				}
				super.size -= 1;
			} else {

				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) getHead();
				DoubleLinkedListNode<T> tmp = (DoubleLinkedListNode<T>) getHead().getNext();

				while (!tmp.isNIL() && !(tmp.getData().equals(element))) {
					aux = tmp;
					tmp = (DoubleLinkedListNode<T>) tmp.getNext();
				}

				if (!tmp.isNIL()) {
					aux.setNext(tmp.getNext());
					((DoubleLinkedListNode<T>) tmp.getNext()).setPrevious(aux);
					super.size -= 1;
				}
				if (aux.getNext().isNIL()) {
					this.last = aux;
				}
			}
		}
	}
}