package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do MergeSort 
 * que pode fazer uso do InsertionSort (um algoritmo híbrido) da seguinte forma: 
 * o MergeSort é aplicado a entradas maiores a um determinado limite. Caso a entrada 
 * tenha tamanho menor ou igual ao limite o algoritmo usa o InsertionSort. 
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de 
 *   forma que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada
 *   chamada interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e 
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 *  - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends AbstractSorting<T> {
    
	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;
	
	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;
	
	@SuppressWarnings("unchecked")
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if (!validacaoArray(array, leftIndex, rightIndex))
			return;
		
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		
		T[] tmp = (T[]) new Comparable[array.length];
		
		if (array.length > 4) {
			mergeSort(array, tmp, leftIndex, rightIndex);
		} else {
			insertionSort(array, leftIndex, rightIndex);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static void mergeSort(Comparable[] array, Comparable[] tmp, int leftIndex, int rightIndex) {
		if( leftIndex < rightIndex ) {
			int center = (leftIndex + rightIndex) / 2;
			
			if (center > 4) {
				mergeSort(array, tmp, leftIndex, center);
				mergeSort(array, tmp, center + 1, rightIndex);
				merge(array, tmp, leftIndex, center + 1, rightIndex);
			} else {
				insertionSort(array, leftIndex, rightIndex);
			}
		}		
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static void merge(Comparable[] array, Comparable[] tmp, int leftIndex, int rightIndex, int rightIndexEnd ) {
       
    	int leftIndexEnd = rightIndex - 1;
        int index = leftIndex;
        int tamanho = rightIndexEnd - leftIndex + 1;

        while(leftIndex <= leftIndexEnd && rightIndex <= rightIndexEnd)
            if(array[leftIndex].compareTo(array[rightIndex]) <= 0)
                tmp[index++] = array[leftIndex++];
            else
                tmp[index++] = array[rightIndex++];

        while(leftIndex <= leftIndexEnd)
            tmp[index++] = array[leftIndex++];

        while(rightIndex <= rightIndexEnd)
            tmp[index++] = array[rightIndex++];

        for(int i = 0; i < tamanho; i++, rightIndexEnd--)
            array[rightIndexEnd] = tmp[rightIndexEnd];
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static void insertionSort(Comparable[] array, int leftIndex, int rightIndex) {
    	for (leftIndex++; leftIndex <= rightIndex; leftIndex++) {
            for(int index = leftIndex ; index > 0 && array[index].compareTo(array[index-1]) < 0; index--){
                    Util.swap(array, index, index - 1);
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
