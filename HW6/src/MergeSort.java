import java.util.ArrayList;
/** implements the recursive merge sort algorithm. in this version, copies 
*of the sub.tables are made, sorted, and then merged.
 * @author Bahadir Etka Kilinc
*/
public class MergeSort {
	/** Sort the array using the merge sort algorithm.
	post: table is sorted.
	@param table The array to be sorted
	 */ 
	public static <T extends Comparable<T>> void sort(ArrayList<T> table) {
		if (table.size()> 1) {
			int halfSize = table.size()/2;
			ArrayList<T> leftTable =new ArrayList<T>();
			int i=0;
			for(;i<halfSize;++i) {
				leftTable.add(table.get(i));
			}
			ArrayList<T> rightTable = new ArrayList<T>();
			for(;i<table.size();++i) {
				rightTable.add(table.get(i));
			}
			sort(leftTable);
			sort(rightTable);
			merge(table, leftTable, rightTable);
		}
	}
	/** Merge two sequences.
	@post outputSequence is the merged result and is sorted.
	@param outputSequence The destination
	@param leftSequence The left input
	@param rightSequence The right input
	 */
	private static <T extends Comparable<T>> void merge(ArrayList<T> outputSequence,
			ArrayList<T>leftSequence,
			ArrayList<T> rightSequence) { 
		int i = 0; 
		int j = 0;
		int k = 0;
		while (i < leftSequence.size() && j < rightSequence.size()) {
			if (compare((Product) leftSequence.get(i),(Product) (rightSequence.get(j))) < 0) {
				outputSequence.set(k++, leftSequence.get(i++));
			} else {
				outputSequence.set(k++, rightSequence.get(j++));
			}
		}
		while (i < leftSequence.size()) {
			outputSequence.set(k++, leftSequence.get(i++));
		}
		while (j < rightSequence.size()) {
			outputSequence.set(k++, rightSequence.get(j++));
		}
	}
	private static int compare(Product p1, Product p2) {
		return p1.getName().compareTo(p2.getName());
	}
}