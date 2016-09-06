package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (!validacaoArray(array, leftIndex, rightIndex))
			return;
		
		boolean troca = false;

		for (int i = leftIndex; i <= rightIndex; i++) {
			troca = false;
			for (int j = leftIndex + 1; j <= rightIndex; j++) {
				if (array[j].compareTo(array[j - 1]) < 0) {
					Util.swap(array, j, j - 1);
					troca = true;
				}
			}
			if (troca == false) {
				return;
			}
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
