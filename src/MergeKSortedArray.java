/**
 * Given k sorted arrays of size n each, merge them and print the sorted output.
 * 
 * @author vimalbera
 *
 */
public class MergeKSortedArray {

	public static void main(String[] args) {
		int k = 4, n = 4;
		int arr[][] = { { 1, 3, 5, 7 }, { 2, 4, 6, 8 }, { 0, 9, 10, 11 }, { 1, 1, 1, 1 } };

		int[] answer = mergeKSortedArray(arr, k, n);
		for (int i = 0; i < n * k; i++) {
			System.out.println(answer[i]);
		}

	}

	private static int[] mergeKSortedArray(int[][] arr, int k, int n) {
		Heap heap = new Heap(k);
		int[] sortedArray = new int[n * k];
		for (int i = 0; i < k; i++) {
			MinHeapNode node = new MinHeapNode(arr[i][0], i, 1);
			heap.insert(node);
		}

		int index = 0;
		while (index < k * n) {
			MinHeapNode node = heap.getMin();
			sortedArray[index++] = node.element;
			MinHeapNode newNode;
			if (node.j < arr[node.i].length) {
				newNode = new MinHeapNode(arr[node.i][node.j], node.i, node.j + 1);
			} else {
				newNode = new MinHeapNode(Integer.MAX_VALUE, 0, 0);
			}
			heap.insert(newNode);
		}
		return sortedArray;
	}
}

class Heap {
	private int capacity;
	private int size;
	private MinHeapNode[] array;

	public Heap(int capacity) {
		this.capacity = capacity;
		this.array = new MinHeapNode[capacity];
	}

	public void insert(MinHeapNode value) {
		if (size == capacity) {
			throw new IndexOutOfBoundsException();
		}
		array[size++] = value;

		int currentIndex = size - 1;
		while (currentIndex > 0 && array[currentIndex].element < array[parent(currentIndex)].element) {
			swap(currentIndex, parent(currentIndex));
			currentIndex = parent(currentIndex);
		}
	}

	public MinHeapNode getMin() {
		MinHeapNode min = array[0];
		array[0] = array[size-- - 1];
		heapify(0);
		return min;
	}

	private void heapify(int i) {
		int left = 2 * i + 1;
		int right = 2 * i + 2;

		int smallest = i;
		if (left < size && array[i].element > array[left].element) {
			smallest = left;
		}
		if (right < size && array[smallest].element > array[right].element) {
			smallest = right;
		}
		if (smallest != i) {
			swap(i, smallest);
			heapify(smallest);
		}
	}

	private void swap(int currentIndex, int parent) {
		MinHeapNode t = array[currentIndex];
		array[currentIndex] = array[parent];
		array[parent] = t;
	}

	private int parent(int currentIndex) {
		return (currentIndex - 1) / 2;
	}
}

class MinHeapNode {
	int element; // The element to be stored
	int i; // index of the array from which the element is taken
	int j; // index of the next element to be picked from array

	public MinHeapNode(int k, int i2, int l) {
		this.element = k;
		this.i = i2;
		this.j = l;
	}
};
