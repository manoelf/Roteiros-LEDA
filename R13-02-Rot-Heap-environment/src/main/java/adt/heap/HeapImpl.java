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
@SuppressWarnings({ "rawtypes", "unchecked", "null" })
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
		if (left > this.index) {
			return -1;
		}else {
			return left;			
		}
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		int right = (i + 1) * 2;
		if (right > this.index) {
			return -1;
		}else {
			return right;			
		}
	}

	@Override
	public boolean isEmpty() {
		return (this.index == -1);
	}

	@Override
	public T[] toArray() {
		T[] resp = (T[]) new Comparable[(index + 1)];
		for (int i = 0; i <= index; i++) {
			resp[i] = this.heap[i];
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
		int left = this.left(pos);
		int right = this.right(pos);
		
		if (!this.isEmpty()) {
			
			int minPos = pos;
			
			if (left != -1 && getComparator().compare(this.heap[minPos], this.heap[left]) > 0) {
				minPos = left;
			}
			if (right != -1 && getComparator().compare(this.heap[minPos], this.heap[right]) > 0) {
				minPos = right;
			}
			
			if (minPos != pos) {
				Util.swap(this.heap, minPos, pos);
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
			
			this.heap[++this.index] = element;
			
			int i = this.index;

			while (i > 0 && getComparator().compare(element, this.heap[parent(i)]) < 0) {
				Util.swap(this.heap, i, parent(i));
				i = parent(i);
			}
		}
	}

	@Override
	public void buildHeap(T[] array) {
		if (array != null || array.length != 0) {
		
			this.heap = Arrays.copyOf(array, array.length);
			this.index = array.length - 1;
			
			for (int i = this.parent(this.index); i > -1; i--)
				this.heapify(i);
		}
	}

	@Override
	public T extractRootElement() {
		if (this.isEmpty()) {
			return null;
		}
		
		T element = this.heap[0];
		
		Util.swap(this.heap, 0, this.index--);
		
		this.heapify(0);
		
		return element;
	}

	@Override
	public T rootElement() {
		if (this.isEmpty()) {
			return null;
		}
		
		return this.heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		
		Comparator<T> newComparator = this.comparator;
		
		setComparator(new MyComparator());
		
		HeapImpl<T> newHeap = new HeapImpl<>(getComparator());
		
		newHeap.buildHeap(array);
		
		T[] newArray = (T[]) new Comparable[array.length];
		
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = newHeap.extractRootElement();
		}
		System.out.println(Arrays.toString(newArray));
		
		setComparator(newComparator);
		
		return newArray;
	}

	@Override
	public int size() {
		return this.index + 1;
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
	
	public static void main(String[] args) {
		HeapImpl<Integer> a = new HeapImpl<>((value1, value2) -> (value2).compareTo(value1));
		a.buildHeap(new Integer[]{53, 2, 37, 33, 94, 36, 82, 99, 15, 74, 73, 32, 79, 12, 64, 100, 52, 60, 45, 84});
		System.out.println(Arrays.toString(a.heap));
	}
}

class MyComparator<T extends Comparable<T>> implements Comparator<T> {
	   @Override
	   public int compare(T value1, T value2) {
	      return value1.compareTo(value2);
	   }
}
