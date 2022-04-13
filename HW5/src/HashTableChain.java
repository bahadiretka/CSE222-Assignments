import java.util.Iterator;
import java.util.LinkedList;
/**
 * @author Bahadir Etka Kilinc
 * HashTableChain class that hold set table in the linked list and implements KWHashMap.
 */
public class HashTableChain<K extends Comparable<K>, V> implements KWHashMap<K, V> {
    private LinkedList<Entry<K, V>>[] table;
    private int numKeys;
    private static final int CAPACITY = 10;
    private static final double LOAD_THRESHOLD = 3.0;

    public HashTableChain() {
        table = new LinkedList[CAPACITY];
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null;
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.getKey().equals(key))
                return nextItem.getValue();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return size() != 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.getKey().equals(key)) {
                V oldVal = nextItem.getValue();
                nextItem.setValue(value);
                return oldVal;
            }
        }
        table[index].addFirst(new Entry<>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length))
            rehash();
        return null;
    }

    /**
     * rehashes the set table.
     */
    private void rehash(){
        LinkedList<Entry<K, V> >[] oldTable = table;
        table = new LinkedList[2 * oldTable.length + 1];
        numKeys = 0;
        Iterator iter;
        Entry<K,V> temp;
        for (int i = 0; i < oldTable.length; i++) {
            if(oldTable[i] != null) {
                iter = oldTable[i].iterator();
                while (iter.hasNext()) {
                    temp = (Entry<K, V>) iter.next();
                    put(temp.getKey(), temp.getValue());
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V remove(Object key) {
        Iterator iter;
        Entry<K,V> old;
        int ind = key.hashCode() % table.length;
        if(table[ind] != null){
            iter =  table[ind].iterator();
            while(iter.hasNext()) {
                old = (Entry<K, V>) iter.next();
                if (old.getKey().equals(key)) {
                    --numKeys;
                    iter.remove();
                    if (table[ind].size() == 0)
                        table[ind] = null;
                    return (V) old.getValue();
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return numKeys;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        LinkedList<Entry<K,V>> temp;
        for(int i=0; i< table.length; ++i){
            temp = table[i];
            if(temp != null) {
                res.append("Index : " + i);
                res.append(temp);
                res.append("\n");
            }
        }
        return res.toString();
    }

}