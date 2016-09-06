package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T>{

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (!validacaoArray(array, leftIndex, rightIndex))
			return;
		
		for (int i = leftIndex; i < rightIndex; i++) {
			int minIndex = i;
			for (int index = i + 1; index <= rightIndex; index++) {
				if(array[index].compareTo(array[minIndex]) < 0) {
					minIndex = index;
				}
			}
			Util.swap(array, i, minIndex);
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
