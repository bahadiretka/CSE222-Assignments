

/**
 * A generic interface.
 * @author Bahadir Etka Kilinc
 * @param <T> the type of elements stored by this container.
 */
public interface MyContainerInterface<T>
{
    boolean empty();
    int size();
    T at(int index) throws ArrayIndexOutOfBoundsException;
    boolean insert(T newContent);
    boolean erase(T content);
    void clear();
    int contains(Object o);
}
