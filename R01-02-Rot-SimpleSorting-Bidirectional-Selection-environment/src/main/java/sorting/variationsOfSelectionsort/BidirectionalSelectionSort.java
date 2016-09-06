package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This selection sort variation has two internal iterations. In the first, it takes the
 * smallest elements from the array, and puts it in the first position. In the second,
 * the iteration is done backwards, that is, from right to left, and this time the biggest
 * element is selected and stored in the last position. Then it repeats the process,
 * excluding the positions already filled in, until the whole array is ordered.
 */
public class BidirectionalSelectionSort<T extends Comparable<T>> extends AbstractSorting<T>{
 
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (!validacaoArray(array, leftIndex, rightIndex))
			return;
		
		int cont = 0;
		for (int i = leftIndex; i < rightIndex; i++) {
			
			int minIndex = i;
			
			for (int index = i + 1; index <= rightIndex - cont; index++) {
				if(array[index].compareTo(array[minIndex]) < 0) {
					minIndex = index;
				}
			}
			
			Util.swap(array, i, minIndex);
			
			int maxIndex = rightIndex - cont;
			
			for (int index = rightIndex - cont - 1; index > i; index--) {
				if(array[index].compareTo(array[maxIndex]) > 0) {
					maxIndex = index;
				}
			}
			Util.swap(array, rightIndex - cont, maxIndex);
			
			++cont;
		}
	}

	public boolean validacaoArray(T[] array, int leftIndex, int rightIndex) {
		if (array == null) {
			return false;
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				return false;
			}
		}
		if (rightIndex >= array.length || leftIndex >= array.length) {
			return false;
		}
		if (leftIndex < 0 || rightIndex < 0) {
			return false;
		}
		if (leftIndex >= rightIndex) {
			return false;
		}
		if (array.length < 2) {
			return false;
		}
		return true;
	}
}