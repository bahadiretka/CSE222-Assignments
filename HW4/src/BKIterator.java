import java.util.Iterator;

/**
 * @author Bahadir Etka Kilinc
 * An interface which extends from Java Iterator with a set method
 */
public interface BKIterator<E> extends Iterator<E>{
    E set(E data);
}
