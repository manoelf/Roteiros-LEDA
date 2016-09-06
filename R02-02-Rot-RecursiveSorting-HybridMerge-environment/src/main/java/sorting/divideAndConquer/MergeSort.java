package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Merge sort is based on the divide-and-conquer paradigm.  
 * The algorithm consists of recursively dividing the unsorted list in the middle,
 * sorting each sublist, and then merging them into one single sorted list.
 * Notice that if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		
		if (!validacaoArray(array, leftIndex, rightIndex))
			return;
				
		T[] tmp = Util.makeArray(array.length);
		mergeSort(array, tmp,  leftIndex, rightIndex);
	}

	private void mergeSort(T[] array, T[] tmp, int leftIndex, int rightIndex) {
		
		if( leftIndex < rightIndex ) {
			int center = (leftIndex + rightIndex) / 2;
			mergeSort(array, tmp, leftIndex, center);
			mergeSort(array, tmp, center + 1, rightIndex);
			merge(array, tmp, leftIndex, center + 1, rightIndex);
		}
	}

	private void merge(T[] a, T[] tmp, int leftIndex, int rightIndex, int rightIndexEnd ) {
       
    	int leftIndexEnd = rightIndex - 1;
        int index = leftIndex;
        int tamanho = rightIndexEnd - leftIndex + 1;

        while(leftIndex <= leftIndexEnd && rightIndex <= rightIndexEnd)
            if(a[leftIndex].compareTo(a[rightIndex]) <= 0)
                tmp[index++] = a[leftIndex++];
            else
                tmp[index++] = a[rightIndex++];

        while(leftIndex <= leftIndexEnd)
            tmp[index++] = a[leftIndex++];

        while(rightIndex <= rightIndexEnd)
            tmp[index++] = a[rightIndex++];

        for(int i = 0; i < tamanho; i++, rightIndexEnd--)
            a[rightIndexEnd] = tmp[rightIndexEnd];
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