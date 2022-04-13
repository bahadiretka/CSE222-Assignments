import java.security.PublicKey;
import java.util.*;

/**
 * Navigable SkipList class that implements NavigableSet.
 * @author Bahadir Etka Kilinc
 */

public class NavigableSkipList<E extends Comparable<E>> implements NavigableSet<E> {
    private SkipList<E> list;
    private int size;
    public NavigableSkipList(){
        list = new SkipList<>();
        size = 0;
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public boolean add(E e) {
        if(list.add(e)){
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
        if(list.remove((E) o)){
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
        return new NavigableSkipListIterator<>();
    }

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
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }
    /**
     *  @{inheritDoc}
     */
    @Override
    public SortedSet<E> tailSet(E fromElement) {
        throw new UnsupportedOperationException();
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
    public class NavigableSkipListIterator<E> implements Iterator<E>{

        ArrayList<E> arrayList = new ArrayList<>();
        int cursor = 0;

        public NavigableSkipListIterator() {
            Iterator<E> temp = (Iterator<E>) list.iterator();
            while (temp.hasNext()) {
                arrayList.add(0,temp.next());
            }
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
            return arrayList.size() > cursor;
        }
        /**
         *  @{inheritDoc}
         */
        @Override
        public E next() {
            return arrayList.get(cursor++);
        }
    }
}
