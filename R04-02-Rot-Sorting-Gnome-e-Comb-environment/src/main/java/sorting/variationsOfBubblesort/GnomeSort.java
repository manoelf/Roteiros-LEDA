package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place! 
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T>{
	
	@Override
	public void sort(T[] array,int leftIndex, int rightIndex){
		
		if (!validacaoArray(array, leftIndex, rightIndex))
			return;
		
		gnomeSort(array, leftIndex, rightIndex);
	}
	
	public void gnomeSort(T[] array,int leftIndex, int rightIndex) {
		
		int i = leftIndex + 1;
		
		while(i <= rightIndex){
			
			if(i == leftIndex || array[i-1].compareTo(array[i]) <= 0){
				i++;
			}else{
				Util.swap(array, i-1, i);
				i--;
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
