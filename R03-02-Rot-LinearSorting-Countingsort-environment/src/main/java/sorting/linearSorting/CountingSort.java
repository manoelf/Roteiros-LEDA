package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		if (!validacaoArray(array, leftIndex, rightIndex))
			return;
		
		countingSort(array, leftIndex, rightIndex);
	}
	
	private void countingSort(Integer[] array, int leftIndex, int rightIndex) {

		int[] c = new int[biggestValue(array, leftIndex, rightIndex) + 1];
		int[] b = new int[array.length];

		for (int i = leftIndex; i <= rightIndex; i++) {
			c[array[i]] += 1;
		}

		for (int i = 1; i < c.length; i++) {
			c[i] += c[i - 1];
		}

		for (int i = rightIndex; i >= leftIndex; i--) {
			b[--c[array[i]]] = array[i];
		}

		for (int i = 0; i <= rightIndex - leftIndex; i++) {
			array[i + leftIndex] = b[i];
		}

	}
	
	private int biggestValue(Integer[] array, int leftIndex, int rightIndex) {
		int biggestValue = 0;

		for (int i = leftIndex; i <= rightIndex; i++) {

			if (array[i].compareTo(biggestValue) > 0)
				biggestValue = array[i];
		}

		return biggestValue;
	}

	public boolean validacaoArray(Integer[] array, int leftIndex, int rightIndex) {
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