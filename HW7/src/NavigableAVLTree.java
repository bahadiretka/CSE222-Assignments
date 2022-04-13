import java.util.*;

/**
 * Navigable AVLTree class that implements NavigableSet.
 * @author Bahadir Etka Kilinc
 */
public class NavigableAVLTree<E extends Comparable<E>> implements NavigableSet<E> {
    AVLTree<E> avlTree;
    private int size;
    public NavigableAVLTree(){
        avlTree = new AVLTree<>();
        size = 0;
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public boolean add(E e) {
        if(avlTree.add(e)){
            ++size;
            return true;
        }
        return false;
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public boolean remove(Object o) {
        if(avlTree.remove((E) o)){
            --size;
            return true;
        }
        return false;
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public E lower(E e) {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public E floor(E e) {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public E ceiling(E e) {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public E higher(E e) {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public E pollFirst() {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public E pollLast() {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return size != 0;
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new NavigableAVLTreeIterator<>();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public NavigableSet<E> descendingSet() {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        NavigableAVLTree<E> result = new NavigableAVLTree<>();
        Iterator<E> iterator = iterator();

        while(iterator.hasNext()){
            E temp = iterator.next();
            if((temp.compareTo(toElement) == 0 && inclusive ) || (temp.compareTo(toElement) < 0)){
                result.add(temp);
            }
        }
        return result;
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        NavigableAVLTree<E> result = new NavigableAVLTree<>();
        Iterator<E> iterator = iterator();

        while(iterator.hasNext()){
            E temp = iterator.next();
            if((temp.compareTo(fromElement) == 0 && inclusive ) || temp.compareTo(fromElement) > 0)
                result.add(temp);
        }
        return result;
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public Comparator<? super E> comparator() {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public SortedSet<E> headSet(E toElement) {
        NavigableAVLTree<E> result = new NavigableAVLTree<>();
        Iterator<E> iterator = iterator();

        while(iterator.hasNext()){
            E tempItem = iterator.next();
            if(tempItem.compareTo(toElement) < 0)
                result.add(tempItem);
        }
        return result;
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public SortedSet<E> tailSet(E fromElement) {
        NavigableAVLTree<E> result = new NavigableAVLTree<>();
        Iterator<E> iterator = iterator();

        while(iterator.hasNext()){
            E temp = iterator.next();
            if(temp.compareTo(fromElement) >= 0 )
                result.add(temp);
        }
        return result;
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public E first() {
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public E last() {
        throw new UnsupportedOperationException();
    }
    class NavigableAVLTreeIterator<E extends Comparable<E>> implements Iterator<E>{
        Queue<E> queue = new LinkedList<>();
        public NavigableAVLTreeIterator() {
            BinaryTree.Node<E> root = (BinaryTree.Node<E>) avlTree.root;
            addToQueue(root);
        }
        private void addToQueue(BinaryTree.Node<E> root){
            if(root == null) return;
            addToQueue(root.left);
            queue.add(root.data);
            addToQueue(root.right);
        }
        /**
         *  @{inheritDoc}
         */
        @Override
        public void remove() {
            Iterator.super.remove();
        }
        /**
         *  @{inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return queue.size() > 0;
        }
        /**
         *  @{inheritDoc}
         */
        @Override
        public E next() {
            return queue.poll();
        }

    }
}
