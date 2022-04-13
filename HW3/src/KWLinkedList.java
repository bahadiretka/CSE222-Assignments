import java.util.ListIterator;
/**
 * A generic Double Linked Class class for holding data.
 * @author Bahadir Etka Kilinc
 */
public class KWLinkedList<E>{
    protected Node<E> head = null;
    protected Node<E> tail = null;
    protected int size = 0;

    /**
     * Inner node class
     * @param <E> parameter of the generic node
     */
    private static class Node<E>{
        private E data;
        private Node<E> next;
        private Node<E> prev = null;

        /**
         * Creates a node with a item
         * @param item node will be created with an item
         */
        private Node(E item){
            data=item;
            next=null;
        }
        private Node(E item,Node<E> node){
            data=item;
            next=node;
        }

    }
    /**
     * Gets size of elements of the Linked List
     * @return Returns size of elements of the Linked List
     */
    public int size(){
        return size;
    }

    /**
     * Adds an element to the first node of the Linked List
     * @param anEntry an entry to be added to Linked List
     */
    public void addFirst(E anEntry){
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
     * Replace elements from the list
     * @param old element to be changed
     * @param newO new element to be changed with old element
     */
    public void replace(E old, E newO){
        replace(head,old,newO);
    }
    private void replace(Node<E> node, E old, E newO){
        if(node != null){
            if(node.data.equals(old)){
                node.data = newO;
                //return; Big-O(N)
            }
            replace(node.next,old,newO);
        }
    }

    /**
     * Adds an element to end of the Linked List
     * @param data element to be added
     */
    public void addLast(E data){
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
     * Adds an element to indexth of the Linked List
     * @param index index of the List to be added
     * @param data element to be added
     */
    public void add(int index, E data){
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == 0){
            addFirst(data);
        }
        else if (index == size-1){
            addLast(data);
        }
        else {
            int count = 0;
            Node <E> iter = head;
            while (count != index) {
                iter = iter.next;
                ++count;
            }
            Node<E> temp = new Node<E>(data);
            iter.prev.next = temp;
            temp.next = iter;
            temp.prev = iter.prev;
            iter.prev = temp;
            ++size;
        }
    }


    /**
     *  Gets indexth element of the Linked list
     * @param index indexth element of the List
     * @return returns indexth data of the List
     */
    public E get(int index){
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
     * Sets indexth element of the List
     * @param index index of the List
     * @param res new element to be changed
     * @return old value
     */
    public E set(int index, E res){
        if(index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<E> iter = head;
        for(int i=0 ; i<index ;++i){
            iter = iter.next;
        }
        iter.data = res;
        return res;
    }

    /**
     * Finds index of data on the List
     * @param data data to be searched
     * @return returns index of data
     */
    public int indexOf(E data){
        Node<E> iter = head;
        int index = 0;
        while(iter != null){
            if(iter.data.equals(data)){
                return index;
            }
            iter = iter.next;
            index++;
        }
        return -1;
    }

    /**
     * Removes indexth element of the Linked List
     * @param index deletes indexth element of the List
     */
    public void remove(int index){
        if(index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(index == 0){
            removeFirst();
        }
        else if(index == size -1){
            removeLast();
        }
        else{
            Node<E> iter = head;
            for (int i = 0 ; i < index-1 ;++i){
                iter = iter.next;
            }
            iter.next = iter.next.next;
            iter.next.prev = iter;
        }
        --size;
    }

    /**
     * Removes last element of the Linked List
     */
    public void removeLast(){
        tail = tail.prev;
        tail.next = null;

    }

    /**
     * Removes first element of the Linked List
     */
    public void removeFirst(){
        if(head == null){
            System.out.println("The List is Empty!!");
        }
        else{
            head.prev = null;
            head = head.next;
        }
    }

    /**
     * Removes an element from the Linked List
     * @param data element to be removed from Linked List
     * @return returns true if the element has removed else returns false
     */
    public boolean remove(E data){
        if(head == null)
            return false;
        else if(head.data.equals(data)){
            head = head.next;
            return true;
        }
        return remove(head.next,head,data);
    }
    private boolean remove(Node<E> node,Node<E> myhead, E data){
        if(node == null ) return false;

        if(node.data.equals(data)){
            myhead.next = myhead.next.next;
            if(myhead.next != null)
                myhead.next.prev = myhead;
            else
                tail = myhead;
            return true;
        }
        return remove(node.next,node,data);
    }

    /**
     * Iterator of the Linked List
     * @param <E>
     */
    private class KWListIterator<E> implements ListIterator<E> {
        private Node<E> nextItem;
        private Node<E> lastItemReturned;
        private int index = 0;

        /**
         * Constructor for the current index iterator
         * @param index current index of iterator
         */
        public KWListIterator(int index) {
            if (index < 0 || index > size) {
                throw new ArrayIndexOutOfBoundsException();
            }
            lastItemReturned = null;
            if (index == size) {
                this.index = size;
                nextItem = null;
            }
            else {
                nextItem = (Node<E>) head;
                for (int i = 0; i < index; ++i)
                    nextItem = nextItem.next;
            }
        }

        /**
         * Returns if there is an element on the next
         * @return if there is a node on the next iterator returns true else returns false
         */
        @Override
        public boolean hasNext() {
            return nextItem != null;
        }

        /**
         * Returns next iterator
         * @return next item
         */
        @Override
        public E next() {
            if (!hasNext()) {
                System.out.println("There is no element to next!!");
                return null;
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            ++index;

            return lastItemReturned.data;
        }

        /**
         * Checks there is an element before the current iterator
         * @return Returns true if there is an element on previous iterator
         */
        @Override
        public boolean hasPrevious() {
            return (nextItem == null && size != 0)
                    || nextItem.prev != null;
        }

        /**
         * Gets previous data from current iterator
         * @return returns previous data from the current iterator
         */
        @Override
        public E previous() {
            if (!hasPrevious()) {
                System.out.printf("hata%n");
                return null;
            }
            if (nextItem == null)
                nextItem = (Node<E>) tail;
            else
                nextItem = nextItem.prev;
            lastItemReturned = nextItem;
            --index;
            return lastItemReturned.data;

        }

        /**
         * Gets next index of current iterator
         * @return next index of next iterator
         */
        @Override
        public int nextIndex() {

            return index;
        }

        /**
         * Gets previous index of current iterator
         * @return previous index of current iterator
         */
        @Override
        public int previousIndex() {
            return index-1;
        }

        /**
         * Removes current iterator data from the Linked List
         */
        @Override
        public void remove() {
            if(lastItemReturned==null){
                System.out.println("Invalid iterator!");
                return;
            }
            if(lastItemReturned.next!=null){
                lastItemReturned.next.prev=lastItemReturned.prev;

            }
            else{
                tail=(Node) lastItemReturned.prev;
                if(tail!=null){
                    tail.next=null;
                }
                else
                    head=null;
            }
            if(lastItemReturned.prev!=null){
                lastItemReturned.prev.next=lastItemReturned.next;
            }
            else{
                head=(Node) lastItemReturned.next;
                if(head!=null){
                    head.prev=null;
                }
                else{
                    tail=null;
                }
            }
            lastItemReturned=null;
            size--;
            index--;
        }

        /**
         * Sets current iterator data
         * @param e Sets current iterator data with e
         */
        @Override
        public void set(E e) {
            lastItemReturned.data=e;
            return;
        }

        /**
         * Adds an element to current iterator
         * @param e elements to be added Linked List
         */
        @Override
        public void add(E e) {
            if(head==null){
                head=(Node) new Node<E>(e);
                tail=head;
            }
            else if(nextItem==head){
                Node<E>tmp=new Node<E>(e);
                tmp.next=nextItem;
                head=(Node) tmp;
            }
            else if(nextItem==null){
                Node<E>tmp=new Node<E>(e);
                tail.next=(Node) tmp;
                tmp.prev=(Node) tail;
                tail=(Node) tmp;
            }
            else{
                Node<E>tmp=new Node<E>(e);
                tmp.prev=nextItem.prev;
                tmp.next=nextItem;
                nextItem.prev.next=tmp;
                nextItem.prev=tmp;
            }
            size++;
            index++;
            lastItemReturned=null;

        }

    }
    /**
     * List Iterator with 0'th index
     * @return List Iterator reference from the Linked List
     */
    public ListIterator<E> listIterator()
    {
        return new KWListIterator<>(0);
    }

    /**
     * List Iterator with index'th index
     * @param index current index of iterator
     * @return List Iterator reference from the Linked List
     */
    public ListIterator<E> listIterator(int index){
        return new KWListIterator<>(index);
    }
    /**
     * String representation of the Linked List (recursive)
     * @return returns string representation of the Linked List
     */
    public String toString(){
        return toString(head);
    }
    private String toString(Node<E> head){
        if(head != null){
            return tail.data + "->" + toString(head.next);
        }
        return "";
    }
}
