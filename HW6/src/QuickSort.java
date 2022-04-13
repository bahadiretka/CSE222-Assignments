import java.util.ArrayList;

/**
 * Quick sort algorithm
 * @author Bahadir Etka Kilinc
 */
public class QuickSort {
	private static int compare(Product p1, Product p2) {
		Integer x = Integer.parseInt(p1.getPrice().trim());
		Integer y = Integer.parseInt(p2.getPrice().trim());
		return x.compareTo(y); 
	}
	
    public static <T extends Comparable<T>> void sort(ArrayList<T> table)
    {
        quickSort(table, 0, table.size() - 1);
    }
    
    private static <T extends Comparable<T>> void quickSort(ArrayList<T> table, int first, int last) {
        if (first < last) {
            int pivIndex = partition(table, first, last);
            quickSort(table, first, pivIndex - 1);
            quickSort(table, pivIndex + 1, last);
        }
    }
    
    private static <T extends Comparable<T>> int partition(ArrayList<T> table,int first, int last) {
        Product pivot = (Product) table.get(first);
        int up = first;
        int down = last;
        do {
            while ((up < last) && (compare(pivot, (Product) table.get(up)) >= 0)) {
                up++;
            }
            while (compare(pivot, (Product) table.get(down)) < 0) {
                down--;
            }
            if (up < down) {
                swap(table, up, down);
            }
        }
        while (up < down);
        swap(table, first, down);
        return down;
    }
    
    private static <T extends Comparable<T>> void swap(ArrayList<T> table, int i, int j) {
        T temp = table.get(i);
        table.set(i, table.get(j));
        table.set(j, temp);
    }
}
