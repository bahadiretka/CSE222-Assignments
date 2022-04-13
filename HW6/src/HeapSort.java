import java.util.ArrayList;

/**
 *Heap Sort algorithm
 * @author Bahadir Etka Kilinc
 */
public class HeapSort {

	public static <T extends Comparable<T>> void sort(ArrayList<T> table) {
		buildHeap(table);
		shrinkHeap(table);
	}
	private static <T extends Comparable<T>> void buildHeap(ArrayList<T>table) {
		int n = 1;
		while (n < table.size()) {
			n++; 
			int child = n - 1;
			int parent = (child - 1) / 2; 
			while (parent >= 0&& table.get(parent).compareTo(table.get(child)) < 0) {
				swap(table, parent, child);
				child = parent;
				parent = (child - 1) / 2;
			}
		}
	}
	private static <T extends Comparable<T>> void shrinkHeap(ArrayList<T> table) {
		int n = table.size();
		while (n > 0) {
			n--;
			swap(table, 0, n);
			int parent = 0;
			while (true) {
				int leftChild = 2 * parent + 1;
				if (leftChild >= n) {
					break;
				}
				int rightChild = leftChild + 1;
				int maxChild = leftChild;
				if (rightChild < n 
						&& compare((Product) table.get(leftChild),(Product) (table.get(rightChild))) < 0) {
					maxChild = rightChild;
				}
				if (table.get(parent).compareTo(table.get(maxChild)) < 0) {
					swap(table, parent, maxChild);
					parent = maxChild;
				} else { 
					break;
				}
			}
		}
	 }
	private static <T extends Comparable<T>> void swap(ArrayList<T> table,int i, int j) {
		 T temp = table.get(i);
		 table.set(i, table.get(j));
		 table.set(j, temp);
	}
	private static int compare(Product p1, Product p2) {
		Integer x = Integer.parseInt(p1.getDiscountedPrice().trim());
		Integer y = Integer.parseInt(p2.getDiscountedPrice().trim());
		return x.compareTo(y);
	}
}