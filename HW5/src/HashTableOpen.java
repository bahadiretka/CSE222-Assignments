/**
 * @author Bahadir Etka Kilinc
 * HashTableOpen class that holds set table on an array and implements KWHashMap
 */
public class HashTableOpen<K extends Comparable<K>, V> implements KWHashMap<K, V>{

    private Entry<K, V>[] table;
    private static final int START_CAPACITY = 10;

    private double LOAD_THRESHOLD = 0.75;
    private int numKeys;
    public HashTableOpen() {
        table = new Entry[START_CAPACITY];
    }

    /**
     *  Finds an index to add entry
     * @param key Key value
     * @return index to be added.
     */
    private int find(Object key) {
        int index = key.hashCode() % table.length;
        int k = 1;
        if (index < 0)
            index += table.length;
        while ((table[index] != null)
                && (!key.equals(table[index].getKey()))) {
            index+=k;
            k+=2;
            if (index >= table.length) {
                index = index % table.length;
            }
        }
        return index;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public V get(Object key) {
        int index = find(key);
        if (table[index] != null)
            return table[index].getValue();
        else
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
     *{@inheritDoc}
     */
    @Override
    public V put(K key, V value) {
        int index = find(key);
        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            numKeys++;
            double loadFactor =
                    (double) numKeys / table.length;
            if (loadFactor > LOAD_THRESHOLD)
                rehash();
            return null;
        }
        V oldVal = table[index].getValue();
        table[index].setValue(value);
        return oldVal;
    }
    /**
     *{@inheritDoc}
     */
    @Override
    public V remove(Object key){
        int index = key.hashCode() % table.length;
        V deleted = null;
        int lastIndex = -1;
        int k = 1;
        if (index < 0)
            index += table.length;
        while (table[index] != null){
            if(table[index].getKey().equals(key)){
                lastIndex = index;
                deleted = (V) table[index];
                break;
            }
            index+=k;
            k+=2;
            if (index >= table.length)
                index = index % table.length;

        }
        if(lastIndex != -1){
            --numKeys;
            int prev = lastIndex;
            while(table[index] != null && table[index].getKey().hashCode() % table.length == table[prev].getKey().hashCode() % table.length){
                table[prev] = table[index];
                prev = index;
                index+=k;
                k+=2;
                if (index >= table.length)
                    index = index % table.length;
            }
            table[prev] = null;
        }
        return deleted;
    }
    /**
     *{@inheritDoc}
     */
    @Override
    public int size() {
        return numKeys;
    }
    /**
     * Rehashes set table
     */
    private void rehash() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[2 * oldTable.length + 1];
        numKeys = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if ((oldTable[i] != null)) {
                put(oldTable[i].getKey(), oldTable[i].getValue());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        for(int i=0; i<table.length; ++i){
            if(table[i] != null) {
                res.append("index: " + i + " ");
                res.append(table[i]);
                res.append("\n");
            }
        }
        return res.toString();
    }

}
