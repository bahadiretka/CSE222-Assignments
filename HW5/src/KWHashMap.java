/**
 * @author Bahadir Etka Kilinc
 * KWHashMap interface
 */
public interface KWHashMap<K,V>{
    /**
     * Returns the value associated with the specified key. Returns null if the key is not
     * present
     * @param key Key to be searched.
     * @return returns value of key
     */
    V get(Object key);

    /**
     * Returns true if this table contains no key‐value mappings
     * @return returns true if table is not empty otherwise returns false
     */
    boolean isEmpty();

    /**
     *  Associates the specified value with the specified key. Returns the previous value
     *  associated with the specified key, or null if there was no mapping for the key
     * @param key Key
     * @param value Key's Value
     * @return returns null if key is not contained on table if key is contained changes its value and returns old value
     */
    V put(K key, V value);
    /**
     *  Removes the mapping for this key from this table if it is present.
     *  Returns the previous value associated with the specified key, or null if
     *  there was no mapping
     * @param key item to be deleted
     * @return returns removed value
     */
    V remove(Object key);

    /**
     * Returns the size of the table
     * @return size of table
     */
    int size();
}
