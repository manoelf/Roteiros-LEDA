package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (!validacaoArray(array, leftIndex, rightIndex))
			return;
		
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			T elemento = array[i];
			int j = i - 1;
			while (j >= leftIndex && elemento.compareTo(array[j]) < 0) {
				array[j + 1] = array[j];
				j-=1;
			}
			array[j + 1] = elemento;
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
