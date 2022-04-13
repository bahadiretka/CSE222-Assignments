import java.util.Arrays;
/**
 * A generic ArrayList class for holding data.
 * @author Bahadir Etka Kilinc
 */
public class KWArrayList<E> {

    /** The default initial capacity */
    private static final int INITIAL_CAPACITY = 10;
    /** The underlying data array */
    private E[] theData;
    /** The current size */
    private int size = 0;
    /** The current capacity */
    private int capacity = 0;

    /**
     *
     */
    public KWArrayList() {
        capacity = INITIAL_CAPACITY;
        theData = (E[]) new Object[capacity];
    }
    /**
     *  Adds an element to List
     * @param anEntry data to be added to List
     * @return if data has added returns true
     */
    public boolean add(E anEntry) {
        if (size == capacity)
            reallocate();
        theData[size] = anEntry;
        size++;
        return true;
    }

    /**
     *  Adds an element to indexth of List
     * @param index index of the element to be added
     * @param anEntry a data to be added to list
     */
    public void add(int index, E anEntry) {
        if (index < 0 || index > size)
            throw new ArrayIndexOutOfBoundsException(index);

        if (size == capacity)
            reallocate();

        // Shift data in elements from index to size ‐ 1
        for (int i = size; i > index; i--)
            theData[i] = theData[i - 1];

        // Insert the new item.
        theData[index] = anEntry;
        size++;
    }

    /**
     * Gets indexth of List's element
     * @param index index of the got element from the List
     * @return  indexth of element from the List
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(index);

        return theData[index];
    }

    /**
     * Sets a new value of the index of element from the List
     * @param index index of the element to be setted.
     * @param newValue to be changed data
     * @return Old value of index element
     */
    public E set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E oldValue = theData[index];
        theData[index] = newValue;
        return oldValue;
    }

    /**
     *  Removes an element from List with an index
     * @param index remove indexth of element in the List
     * @return returns deleted element
     */
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(index);

        E returnValue = theData[index];
        for (int i = index + 1; i < size; i++)
            theData[i - 1] = theData[i];
        size--;
        return returnValue;
    }

    /**
     * if size is not enough to save data reallocate the List
     */
    private void reallocate() {
        capacity = 2 * capacity;
        theData = Arrays.copyOf(theData, capacity);
    }

    /**
     *  Gets size of the List
     * @return returns size of the List
     */
    public int size()
    {
        return size;
    }

    /**
     * Searchs an element on the list if there is returns index of it
     * @param entry for to be searched element from the List
     * @return returns index of entry
     */
    public int indexOf(E entry) {
        for (int i = 0; i < size(); ++i)
            if (entry.equals(theData[i]))
                return i;
        return -1;
    }

    /**
     * String representation of the List
     * @return string representation of the List
     */
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int i=0; i<size(); i++){
            result.append(theData[i].toString());
        }
        return result.toString();
    }
}
