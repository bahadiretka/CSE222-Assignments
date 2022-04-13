/**
 * A generic Hybrid List Class for holding data.
 * @author Bahadir Etka Kilinc
 */
public class HybridList<E>{
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    private int arrayIndex = -1;
    private int totalData = 0;
    private static final int MAX_NUMBER = 20;

    /**
     * Private inner Node Class
     * @param <E> Generic data type
     */
    private static class Node<E>{
        private KWArrayList<E> data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(KWArrayList<E> item){
            data=item;
            next=null;
        }
    }

    /**
     *  Gets Total Number of elements in the Hybrid List
     * @return Returns total number of elements in Hybrid List
     */
    public int totalSize(){
        return totalData;
    }

    /**
     * Gets number of Array List in the Hybrid List
     * @return returns number of Array List in the Hybrid List
     */
    public int size() {
        return size;
    }

    /**
     *  Adds an Array List to the Hybrid List
     * @param anEntry a new Array List
     */
    private void addFirst(KWArrayList<E> anEntry){
        if(head != null) {
            Node<E> temp = new Node<E>(anEntry);
            temp.next = head;
            head.prev = temp;
            head = temp;
        }
        else {
            head = new Node<E>(anEntry);
            tail = head;
        }
        size++;
    }

    /**
     * Adds an element to the Hybrid List
     * @param data the data to be added
     */
    public void add(E data){
        if(size == 0){
            ++arrayIndex;
            addFirst(new KWArrayList<E>());
        }
        if(get(arrayIndex).size() == MAX_NUMBER){
            addLast(new KWArrayList<E>());
            ++arrayIndex;
        }
        get(arrayIndex).add(data);
        totalData++;
    }

    /**
     * Removes element from the Hybrid List(If there is no element in Array List deletes from Hybrid List)
     * @param data the data to be removed
     */
    public void remove(E data){
        for(int i=0 ; i<=arrayIndex; ++i){
            if(get(arrayIndex).get(i).equals(data)){
                get(arrayIndex).remove(i);
                --totalData;
            }
        }
        if(get(arrayIndex).size() == 0){
            remove(get(arrayIndex));
            --arrayIndex;
            --size;
        }
    }

    /**
     * Gets the indexth of the elements in the Hybrid List
     * @param index Index to be got
     * @return returns indexth of the data from Hybrid List
     */
    public E at(int index){
        int num = index / MAX_NUMBER;
        int tempIndex = index % MAX_NUMBER;
        return get(num).get(tempIndex);
    }

    /**
     * Gets indexth Array List from the Hybrid List
     * @param index index of the Array List to be got
     * @return return an Array List
     */
    private KWArrayList<E> get(int index){
        if(index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<E> iter = head;
        for(int i=0 ; i<index ;++i){
            iter = iter.next;
        }
        return iter.data;
    }

    /**
     *  Adds an empty Array List to end of the Hybrid List
     * @param data an Array List to be added
     */
    private void addLast(KWArrayList<E> data){
        if(size == 0){
            addFirst(data);
            return;
        }
        tail.next = new Node<E>(data);
        tail.next.prev = tail;
        tail = tail.next;
        size++;
    }

    /**
     * Removes an Array List from the Hybrid List(Recursive)
     * @param data An Array List to be removed
     * @return Returns true if there is an Array List in Hybrid List else returns false
     */
    private boolean remove(KWArrayList<E> data){
        if(head == null)
            return false;
        else if(head.data.equals(data)){
            head = head.next;
            return true;
        }
        return remove(head.next,head,data);
    }
    private boolean remove(Node<E> node, Node<E> head, KWArrayList<E> data) {
        if (node == null) return false;

        if (node.data.equals(data)) {
            head.next = head.next.next;
            if (head.next != null)
                head.next.prev = head;
            else
                tail = head;
            return true;
        }
        return remove(node.next, node, data);
    }
    /**
     * String representation of the Hybrid List
     * @return String representation of the Hybrid List
     */
    public String toString(){
        return toString(head);
    }
    private String toString(Node<E> head){
        if(head != null){
            return head.data.toString() + "->" + toString(head.next);
        }
        return "";
    }
}
