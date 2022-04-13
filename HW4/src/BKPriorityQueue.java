import java.util.*;
/**
 * @author Bahadir Etka Kilinc
 * The BKPriorityQueue  by building a heap in an ArrayList.
 * The heap is structured so that the “biggest” item is at the top.
 */
public class BKPriorityQueue<E>{
    /** The ArrayList to hold the data. */
    private ArrayList<E> theData;
    /** An optional reference to a Comparator object. */
    Comparator<E> comparator = null;
    public BKPriorityQueue() {
        theData = new ArrayList<>();
    }
    /** Creates a heap‐based priority queue with the specified initial
     capacity that orders its elements according to the specified
     comparator.
     @param cap The initial capacity for this priority queue
     @param comp The comparator used to order this priority queue
     @throws IllegalArgumentException if cap is less than 1
     */
    public BKPriorityQueue(int cap, Comparator<E> comp) {
        if (cap < 1)
            throw new IllegalArgumentException();
        theData = new ArrayList<>();
        comparator = comp;
    }

    public int size() {
        return theData.size();
    }
    /** Insert an item into the priority queue.
     pre: The ArrayList theData is in heap order.
     post: The item is in the priority queue and
     theData is in heap order.
     @param item The item to be inserted
     @throws NullPointerException if the item to be inserted is null.
     */
    public boolean offer(E item) {
        // Add the item to the heap.
        theData.add(item);
        // child is newly inserted item.
        int child = theData.size() - 1;
        heapUp(child);
        return true;
    }
    /** Remove an item from the priority queue
     pre: The ArrayList theData is in heap order.
     post: Removed smallest item, theData is in heap order.
     @return The item with the smallest priority value or null if empty.
     */

    public E poll() {
        if (theData.isEmpty()) {
            return null;
        }
        // Save the top of the heap.
        E result = theData.get(0);
        // If only one item then remove it.
        if (theData.size() == 1) {
            theData.remove(0);
            return result;
        }
        removeData(theData.get(0));
        return result;
    }
    public E peek() {
        return theData.get(0);
    }
    /** Compare two items using either a Comparator object's compare method
     or their natural ordering using method compareTo.
     @param left One item
     @param right The other item
     @return Negative int if left less than right,
     0 if left equals right,
     positive int if left > right
     @throws ClassCastException if items are not Comparable
     */
    @SuppressWarnings("unchecked")
    private int compare(E left, E right) {
        if (comparator != null) { // A Comparator is defined.
            return comparator.compare(left, right);
        } else { // Use left's compareTo method.
            return ((Comparable<E>) left).compareTo(right);
        }
    }

    /**
     *  Removes an item from the Queue
     * @param item The item to be deleted.
     */
    public void removeData(E item){
        E data = search(item);
        int parent = theData.indexOf(data);
        parent = takeDownData(parent);
        swap(parent,size()-1);
        heapUp(parent);
        theData.remove(size()-1);
    }

    /**
     *  Deletes the largest indexth element
     * @param index the largest indexth element
     * @return returns deleted item.
     */
    public E removeLargestOf(int index){
        if(index < 1 || index > size())
            throw new ArrayIndexOutOfBoundsException();

        if(size() > 0){
            ArrayList<E> temp = new ArrayList<>();
            for(int i=0; i<size(); i++){
                temp.add(theData.get(i));
            }
            temp.sort(comparator);
            int parent = theData.indexOf(temp.get(size()-index));
            parent = takeDownData(parent);
            swap(parent,size()-1);
            heapUp(parent);
            E res = theData.get(size()-1);
            theData.remove(size()-1);
            return res;
        }
        return null;
    }

    /**
     * Searches an item if there is then returns.
     * @param data The item to be searched
     * @return returns if the item found else returns null
     */
    public E search(E data){
        for(int i=0; i<theData.size(); ++i){
            if(theData.get(i).equals(data))
                return theData.get(i);
        }
        return null;
    }
    /**
     *  Merges two Priority Queue.
     * @param other queue to be merged
     */
    public void merge(BKPriorityQueue<E> other){
        ArrayList<E> temp = new ArrayList<>();
        BKIterator<E> iter = other.iterator();
        while(iter.hasNext()){
            temp.add(iter.next());
        }
        for(int i=0; i<temp.size(); ++i){
            this.offer(temp.get(i));
        }
    }

    /**
     * Iterator of Priority Queue implements BKIterator(extends Java Iterator with set method)
     */
    private class BKQueueIterator implements BKIterator<E>{

        private Iterator iter;
        private E lastReturned;

        /**
         * Constructor of Priority Queue
         * takes an iterator from ArrayList
         */
        public BKQueueIterator(){
            iter = theData.iterator();
        }

        /**
         *  Set the item with last element returned by the next methods.
         * @param data Item to be set
         * @return returns old Item
         */
        @Override
        public E set(E data) {
            int tempIndex = theData.indexOf(lastReturned);
            theData.set(tempIndex,data);
            int child = tempIndex;
            heapUp(child);
            int parent = child;
            while (true) {
                int leftChild = 2 * parent + 1;
                if (leftChild >= theData.size()) {
                    break;
                }
                int rightChild = leftChild + 1;
                int maxChild = leftChild;

                if (rightChild < theData.size()
                        && compare(theData.get(rightChild),
                        theData.get(leftChild)) > 0) {
                    maxChild = rightChild;
                }
                if (compare(theData.get(parent),
                        theData.get(maxChild)) < 0) {
                    swap(parent, maxChild);
                    parent = maxChild;
                } else {
                    break;
                }
            }
            return lastReturned;
        }

        /**
         * Checks next element is valid from returned from next
         * @return If there is an item
         */
        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        /**
         * Returns next item
         * @return next item
         */
        @Override
        public E next() {
            lastReturned = (E) iter.next();
            return lastReturned;
        }
    }

    /**
     *  Takes an iterator from Priority Queue
     * @return an iterator
     */
    public BKIterator<E> iterator(){
        return new BKQueueIterator();
    }

    /**
     *  Take Down item to end of the queue
     * @param parent starting index
     * @return last index of parent
     */
    private int takeDownData(int parent){
        while (true) {
            int leftChild = 2 * parent + 1;
            if (leftChild >= theData.size()) {
                break;
            }
            int rightChild = leftChild + 1;
            int minChild = leftChild;
            if (rightChild < theData.size()
                    && compare(theData.get(leftChild),
                    theData.get(rightChild)) < 0) {
                minChild = rightChild;
            }
            if (compare(theData.get(parent),
                    theData.get(minChild)) > 0) {
                swap(parent, minChild);
                parent = minChild;
            } else {
                break;
            }
        }
        return parent;
    }

    /**
     *  Takes up item to starting of the queue
     * @param child starting index
     */
    private void heapUp(int child){
        int parent = (child - 1) / 2;
        while (parent >= 0 && compare(theData.get(parent),theData.get(child)) < 0) {
            swap(parent, child);
            child = parent;
            parent = (child - 1) / 2;
        }
    }
    /**
     * Swaps two item.
     * @param parent index of first item
     * @param minChild index of second item
     */
    private void swap(int parent, int minChild) {
        E temp = theData.get(parent);
        theData.set(parent, theData.get(minChild));
        theData.set(minChild,temp);
    }

    /**
     * String representation of Priority Queue
     * @return String representation of Priority Queue
     */
    public String toString(){
        return theData.toString();
    }

}
