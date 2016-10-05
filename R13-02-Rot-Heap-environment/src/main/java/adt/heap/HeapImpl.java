package adt.heap;

import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Essa comparação não é feita diretamente com os elementos armazenados,
 * mas sim usando um comparator. Dessa forma, dependendo do comparator, a heap
 * pode funcionar como uma max-heap ou min-heap.
 */
@SuppressWarnings({ "unchecked" })
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		int parent = (i - 1) / 2;
		return parent;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		int left = (i * 2) + 1;
		if (left > index) {
			return -1;
		} else {
			return left;
		}
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		int right = (i + 1) * 2;
		if (right > index) {
			return -1;
		} else {
			return right;
		}
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		T[] resp = (T[]) new Comparable[(index + 1)];
		for (int i = 0; i <= index; i++) {
			resp[i] = heap[i];
		}
		return resp;
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int pos) {
		int left = left(pos);
		int right = right(pos);

		if (!isEmpty()) {

			int minPos = pos;

			if (left != -1 && getComparator().compare(heap[minPos], heap[left]) > 0) {
				minPos = left;
			}
			if (right != -1 && getComparator().compare(heap[minPos], heap[right]) > 0) {
				minPos = right;
			}

			if (minPos != pos) {
				Util.swap(heap, minPos, pos);
				this.heapify(minPos);
			}
		}
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}

		if (element != null) {

			heap[++index] = element;

			int i = index;

			while (i > 0 && getComparator().compare(element, this.heap[parent(i)]) < 0) {
				Util.swap(heap, i, parent(i));
				i = parent(i);
			}

			// do {
			// i = parent(i);
			// this.heapify(i);
			// } while(i != 0);
		}
	}

	@Override
	public void buildHeap(T[] array) {
		if (array != null) {
			index = -1;
			for (T elem : array)
				insert(elem);
		}
	}

	@Override
	public T extractRootElement() {
		if (isEmpty()) {
			return null;
		}

		T element = heap[0];

		Util.swap(heap, 0, index--);

		heapify(0);

		return element;
	}

	@Override
	public T rootElement() {
		if (isEmpty()) {
			return null;
		}

		return heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		T[] newArray = array;

		if (array != null && array.length != 0) {
			index = -1;
			for (T element : array) {
				insert(element);
			}
			newArray = (T[]) (new Comparable[size()]);

			if (getHeap()[0].compareTo(getHeap()[index]) > 0) {

				for (int i = array.length - 1; i >= 0; i--)
					newArray[i] = extractRootElement();

			} else {

				for (int i = 0; i < array.length; i++)
					newArray[i] = extractRootElement();
			}
		}
		return newArray;
	}

	@Override
	public int size() {
		return index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

	public boolean isHeap(int[] array, int i, int n) {

		if (array[0] > array[1]) {

			if (i > (n - 2) / 2) {
				return true;
			}

			if (array[i] >= array[2 * i + 1] && array[i] >= array[2 * i + 2] && isHeap(array, 2 * i + 1, n) && isHeap(array, 2 * i + 2, n)) {
				return true;
			}

			return false;

		} else {

			if (2 * i + 1 == n) {
				if (array[i] <= array[2 * i + 1]) {
					return true;
				}
			}
			
			if (i > (n - 2) / 2) {
				return true;
			}

			if (array[i] <= array[2 * i + 1] && array[i] <= array[2 * i + 2] && isHeap(array, 2 * i + 1, n) && isHeap(array, 2 * i + 2, n)) {
				return true;
			}

			return false;
		}

		// If a leaf node

	}

	public boolean isHeapIterative(int[] array, int n) {
		// Start from root and go till the last internal
		// node

		if (array[0] > array[1]) {

			for (int i = 0; i <= (n - 2) / 2; i++) {
				// If left child is greater, return false
				if (array[2 * i + 1] > array[i])
					return false;

				// If right child is greater, return false
				if (array[2 * i + 2] > array[i])
					return false;
			}
			return true;

		} else {

			for (int i = 0; i <= (n - 2) / 2; i++) {
				// If left child is greater, return false
				if (array[2 * i + 1] < array[i])
					return false;

				// If right child is greater, return false
				if (array[2 * i + 2] < array[i])
					return false;
			}
			return true;
		}
	}

	public static void main(String[] args) {
		Comparator<Integer> comparator = (Integer i1, Integer i2) -> ((Comparable<Integer>) i1).compareTo(i2);
		HeapImpl<Integer> a = new HeapImpl<>(comparator);

		int[] b = new int[] { 45, 40, 35, 4, 25, 20, 15, 100 };

		System.out.println(a.isHeap(b, 0, b.length - 1));
	}
}