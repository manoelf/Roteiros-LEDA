package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

class MyComparator<T extends Comparable<T>> implements Comparator<T> {
   @Override
   public int compare(T value1, T value2) {
      return value1.compareTo(value2);
   }
}

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
@SuppressWarnings({ "unchecked", "rawtypes" })
public class OrderedSingleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements OrderedLinkedList<T> {

   private Comparator<T> comparator;

   public OrderedSingleLinkedListImpl() {
      this.comparator = new MyComparator();
   }

   public OrderedSingleLinkedListImpl(Comparator<T> comparator) {
      this.comparator = comparator;
   }

   @Override
   public void insert(T element) {
      if (element != null) {
         if (isEmpty()) {
            this.head = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
         } else {
            SingleLinkedListNode<T> aux = this.getHead();
            while (!aux.isNIL() && getComparator().compare(aux.getData(), element) < 0) {
               aux = aux.getNext();
            }
            if (aux.isNIL()) {
               aux.setData(element);
               aux.setNext(new SingleLinkedListNode<T>());
            } else {
               aux.setNext(new SingleLinkedListNode<T>(aux.getData(), aux.getNext()));
               aux.setData(element);
            }
         }
         super.size += 1;
      }
   }

   @Override
   public T minimum() {
      Comparator<T> comparador = new MyComparator();

      if (isEmpty()) {
         return null;
      }

      T value1 = this.getHead().getData();

      SingleLinkedListNode<T> aux = this.getHead();

      while (!aux.getNext().isNIL()) {
         aux = aux.getNext();
      }

      T value2 = aux.getData();

      if (comparador.compare(value1, value2) < 0) {
         return value1;
      } else {
         return value2;
      }
   }

   @Override
   public T maximum() {
      Comparator<T> comparador = new MyComparator();

      if (isEmpty()) {
         return null;
      }

      T value1 = this.getHead().getData();

      SingleLinkedListNode<T> aux = this.getHead();

      while (!aux.getNext().isNIL()) {
         aux = aux.getNext();
      }

      T value2 = aux.getData();

      if (comparador.compare(value1, value2) > 0) {
         return value1;
      } else {
         return value2;
      }
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
   }
}