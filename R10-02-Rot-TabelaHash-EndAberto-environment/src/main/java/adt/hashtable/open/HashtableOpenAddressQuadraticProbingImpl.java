package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
      super(size);
      hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {
      if (!(element == null)) {
         if (!isFull()) {

            int probe = 0;
            int key = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, probe);

            while (probe < this.capacity() && (search(element) == null)) {

               if (super.table[key] == null || deletedElement.equals(super.table[key])) {
                  super.table[key] = element;
                  super.elements++;
               } else {
                  probe++;
                  key = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, probe);
                  super.COLLISIONS++;
               }
            }
         } else {
            throw new HashtableOverflowException();
         }
      }
   }

   @Override
   public void remove(T element) {
      if (!(element == null)) {
         if (!isEmpty()) {
            int indexKey = indexOf(element);

            if (!(indexKey == -1)) {
               super.table[indexKey] = deletedElement;
               super.elements--;
            }
         }
      }
   }

   @Override
   public T search(T element) {
      if (element == null || isEmpty()) {
         return null;
      }

      int indexKey = indexOf(element);

      if (!(indexKey == -1)) {
         return element;
      } else {
         return null;
      }
   }

   @Override
   public int indexOf(T element) {
      if (!(element == null)) {
         if (!isEmpty()) {

            int probe = 0;
            int key = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, probe);

            while (probe < this.capacity()) {
               if (!(super.table[key] == null) && super.table[key].equals(element)) {
                  return key;
               } else {
                  probe++;
                  key = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, probe);
               }
            }
         }
      }
      return -1;
   }
}
