package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		if (!validacaoArray(array, leftIndex, rightIndex))
			return;
		
		countingSort(array, leftIndex, rightIndex);
	}
	
	private void countingSort(Integer[] array, int leftIndex, int rightIndex) {
		
		int smallerValue = smallerValue(array, leftIndex, rightIndex);
		
		int[] c = new int[biggestValue(array, leftIndex, rightIndex) - smallerValue + 1];
		int[] b = new int[array.length];

		for (int i = leftIndex; i <= rightIndex; i++) {
			c[array[i] - smallerValue] += 1;
		}
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(c));

		for (int i = 1; i < c.length; i++) {
			c[i] += c[i - 1];
		}

		System.out.println(Arrays.toString(c));
		for (int i = rightIndex; i >= leftIndex; i--) {
			b[--c[array[i] - smallerValue]] = array[i];
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

		System.out.println(biggestValue);
		return biggestValue;
	}
	
	private int smallerValue(Integer[] array, int leftIndex, int rightIndex) {
		int smallerValue = array[leftIndex];

		for (int i = leftIndex; i <= rightIndex; i++) {

			if (array[i].compareTo(smallerValue) <= 0)
				smallerValue = array[i];
		}

		System.out.println(smallerValue);
		return smallerValue;
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