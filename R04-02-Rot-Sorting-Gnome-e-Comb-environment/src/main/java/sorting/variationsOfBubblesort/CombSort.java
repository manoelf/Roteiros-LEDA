package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm. 
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	
	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		
		if (!validacaoArray(array, leftIndex, rightIndex))
			return;
		
		combSort(array, leftIndex, rightIndex);
	}
	
	public void combSort(T[] array,int leftIndex, int rightIndex) {
		int gap = rightIndex;
		boolean troca = false;
		
		while(gap > 1 || troca){
			
			if(gap > 1){
				gap =  (int)(gap/1.25);
			}
			
			int i = leftIndex;
			troca = false;
			
			for(int j = i + gap; j <= rightIndex; j++){
			
				if(array[i].compareTo(array[i + gap]) > 0){
	
					Util.swap(array, i, j);
					troca = true;
				}
				i++;
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