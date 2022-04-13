import java.util.Iterator;

/**
 * @author Bahadir Etka Kilinc
 * Map's iterators interface that extends from Standart Java Iterator.
 */
public interface MapIteratorInterface<K> extends Iterator<K>{
    /**
     * Returns previous value of last returned item next index.
     * @return returns previous item
     */
    K prev();
}
