package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm simulates a logical partitioning of the input array by
 * considering different indexing, that is, the first sub-array is indexed by
 * even elements and the second sub-array is indexed by odd elements. Then, it
 * applies a complete selectionsort in the first sub-array considering
 * neighbours (even). After that, it applies a complete selectionsort in the
 * second sub-array considering neighbours (odd). After that, the algorithm
 * performs a merge between elements indexed by even and odd numbers.
 */
public class OddEvenSelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (!validacaoArray(array, leftIndex, rightIndex))
			return;

		T par;
		T impar;
		int indicePar;
		int indiceImpar;

		for (int i = leftIndex + 1; i <= rightIndex; i += 2) {
			impar = array[i];
			indiceImpar = i;

			for (int j = i + 2; j <= rightIndex; j += 2) {
				if (array[j].compareTo(impar) < 0) {
					impar = array[j];
					indiceImpar = j;
				}
			}

			array[indiceImpar] = array[i];
			array[i] = impar;
		}

		for (int i = leftIndex; i <= rightIndex; i += 2) {
			par = array[i];
			indicePar = i;

			for (int j = i + 2; j <= rightIndex; j += 2) {
				if (array[j].compareTo(par) < 0) {
					par = array[j];
					indicePar = j;
				}
			}

			array[indicePar] = array[i];
			array[i] = par;
		}
		merge(array, leftIndex, rightIndex);
	}

	public void merge(T[] array, int leftIndex, int rightIndex) {
		
		int total = array.length;
		int par = leftIndex;
		int impar = leftIndex + 1;
		T[] arrayOrdenado = Util.makeArray(total);
		int cont = leftIndex;
		
		while (par <= rightIndex && impar <= rightIndex) {
			if (array[par].compareTo(array[impar]) < 0) {
				arrayOrdenado[cont] = array[par];
				cont++;
				par += 2;
			} else {
				arrayOrdenado[cont] = array[impar];
				cont++;
				impar += 2;
			}
		}

		if (par <= rightIndex) {
			while (par <= rightIndex) {
				arrayOrdenado[cont] = array[par];
				cont++;
				par += 2;
			}
		}

		if (impar <= rightIndex) {
			while (impar <= rightIndex) {
				arrayOrdenado[cont] = array[impar];
				cont++;
				impar += 2;
			}
		}
		
		for (int i = 0; i < array.length; i++) {
			if (arrayOrdenado[i] == null)
				arrayOrdenado[i] = array[i];
			array[i] = arrayOrdenado[i];
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