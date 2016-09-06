package adt.hashtable.closed;

import util.Util;
import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size. For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings("rawtypes")
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int size = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			size = this.getPrimeAbove(desiredSize);
		}
		initiateInternalTable(size);
		HashFunction hashFunction = HashFunctionFactory.createHashFunction(method, size);
		this.hashFunction = hashFunction;
	}

	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		int result = number;
		
		while (!Util.isPrime(result)) {
			result++;
		}		
		return result;
	}

	@Override
	public void insert(T element) {
		if (!(element == null) && search(element) == null) {
			int hashKey = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);

			if (table[hashKey] == null) {
				ArrayList<T> array = new ArrayList<>();
				table[hashKey] = array;
			}
			if (!((ArrayList<T>) table[hashKey]).isEmpty()) {
				COLLISIONS++;
			}
			((ArrayList<T>) table[hashKey]).add(element);
			elements++;
		}
	}

	@Override
	public void remove(T element) {
		if(!(element == null) && !(search(element) == null)){
			int key = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);
			((ArrayList<T>) table[key]).remove(element);
			elements--;
		}
	}

	@Override
	public T search(T element) {
		T anwser = null;
		if(!(element == null)){
			
			int key = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);
			if(!(table[key] == null) && ((ArrayList<T>) table[key]).contains(element)){
				anwser = element;
			}
		}
		return anwser;
	}

	@Override
	public int indexOf(T element) {
		int hash = -1;
		if(!(element == null)){
			
			int key = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);
			
			if(!(table[key] == null) && ((ArrayList<T>) table[key]).contains(element)){
				hash = key;
			}		
		}
		return hash;
	}
}