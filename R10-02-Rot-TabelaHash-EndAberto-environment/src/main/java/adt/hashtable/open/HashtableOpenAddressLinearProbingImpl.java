package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
      super(size);
      hashFunction = new HashFunctionLinearProbing<T>(size, method);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {
      if (!(element == null)) {
         if (!isFull()) {

            int probe = 0;
            int key = ((HashFunctionLinearProbing<T>) super.getHashFunction()).hash(element, probe);

            while (probe < this.capacity() && (search(element) == null)) {

               if (super.table[key] == null || super.deletedElement.equals(super.table[key])) {
                  super.table[key] = element;
                  super.elements++;
               } else {
                  probe++;
                  key = ((HashFunctionLinearProbing<T>) super.getHashFunction()).hash(element, probe);
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
               super.table[indexKey] = super.deletedElement;
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
            int key = ((HashFunctionLinearProbing<T>) super.getHashFunction()).hash(element, probe);

            while (probe < this.capacity()) {
               if (!(super.table[key] == null) && super.table[key].equals(element)) {
                  return key;
               } else {
                  probe++;
                  key = ((HashFunctionLinearProbing<T>) super.getHashFunction()).hash(element, probe);
               }
            }
         }
      }
      return -1;
   }
}