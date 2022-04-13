/**
 * @author Bahadir Etka Kilinc
 * A special Priority Queue class that holds a priority queue in
 * If there is same item in Priority Queue it will offer that item just increments number of item
 * The same rule for remove operation.
 */
public class BKMaxBSTHeapNode<E extends Comparable<E>>{

    private BKPriorityQueue<E> theData;

    public BKMaxBSTHeapNode(){
        theData = new BKPriorityQueue<>();
    }

    /**
     * Static Node class that holds an item with occurrences
     */
    public static class Node<E extends Comparable<E>> implements Comparable<E>{
        private E data;
        private int numberOfOccurrence;
        public Node(E item){
            data = item;
            numberOfOccurrence = 1;
        }
        public Node(E item, int num){
            data = item;
            numberOfOccurrence = num;
        }

        /**
         * Getter for item
         * @return data of node
         */
        public E getData(){
            return data;
        }

        /**
         * Getter for item's number of occurrences
         * @return returns item's number of occurrences
         */
        public int getOccur(){
            return numberOfOccurrence;
        }

        /**
         * String representation of a node
         * @return returns String representation of a node
         */
        public String toString(){
            return "[" + data.toString() + " " + String.format(",%d]", numberOfOccurrence);
        }

        /**
         * Compares two nodes
         * @param o item to be compared
         * @return result of compare
         */
        @Override
        public int compareTo(E o){
            return -(o.compareTo(data));
        }

        /**
         *  Compares two nodes
         * @param o item to be compared
         * @return returns true if the values are same
         */
        @Override
        public boolean equals(Object o){
            return o.equals(data);
        }
    }

    /**
     * Adds an item to queue if there is same item in it just increments its occurrences
     * @param item Item to be added
     * @return returns occurrences of item
     */
    public int add(E item){
        Node<E> temp = (Node<E>) theData.search(item);
        if(temp != null){
            return ++temp.numberOfOccurrence;
        }
        else{
            theData.offer((E) new Node<E>(item));
            return 1;
        }
    }

    /**
     * Removes an element from the queue if there is two or more just decrements its occurrences
     * @param item Item to be deleted
     * @return returns deleted item's occurrences
     */
    public int remove(E item){
        Node<E> temp = (Node<E>) theData.search(item);
        if(temp != null){
            if(temp.numberOfOccurrence > 1)
                return --temp.numberOfOccurrence;
            else
                theData.removeData(item);
            return 0;
        }
        else{
            return -1;
        }
    }

    /**
     * Gets size of the Queue
     * @return returns size of the queue
     */
    public int size(){
        return theData.size();
    }

    /**
     * Searches the item in the queue if there is then returns its occurrences
     * @param item Item to be searched
     * @return returns occurrences of item if item is found else returns -1
     */
    public int find(E item){
        if(contains(item)){
            Node<E> temp = (Node<E>) theData.search(item);
            return temp.numberOfOccurrence;
        }
        return -1;
    }

    /**
     * Finds the most repeating item and returns it
     * @return returns most repeated node
     */
    public Node<E> findMode(){
        BKIterator iter = theData.iterator();
        int max = 0;
        Node<E> result = null;
        while(iter.hasNext()){
            Node<E> temp = (Node<E>) iter.next();
            if(max < temp.numberOfOccurrence){
                max = temp.numberOfOccurrence;
                result = temp;
            }
        }
        return result;
    }

    /**
     * Checks if item is in or not
     * @param item Item to be searched
     * @return returns true if item is contained otherwise returns false
     */
    public boolean contains(E item){
        return theData.search(item) != null;
    }

    /**
     *  Gets first element of the Max Heap
     * @return returns first element
     */
    public E peek(){
        return theData.peek();
    }

    /**
     * String representation of the queue
     * @return returns string representation of the queue
     */
    public String toString(){
        return theData.toString();
    }

}
