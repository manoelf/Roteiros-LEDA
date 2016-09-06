package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (!validacaoArray(array, leftIndex, rightIndex))
			return;

		quickSort(array, leftIndex, rightIndex);
	}

	private void quickSort(T[] array, int leftIndex, int rightIndex) {

		int i = leftIndex;
		int j = rightIndex;

		T pivo = array[leftIndex + (rightIndex - leftIndex) / 2];

		while (i <= j) {

			while (array[i].compareTo(pivo) < 0) {
				i++;
			}
			while (array[j].compareTo(pivo) > 0) {
				j--;
			}

			if (i <= j) {
				Util.swap(array, i, j);
				i++;
				j--;
			}
		}
		
		if (leftIndex < j)
			quickSort(array, leftIndex, j);
		if (i < rightIndex)
			quickSort(array, i, rightIndex);
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