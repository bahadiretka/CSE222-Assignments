/**
 * @author Bahadir Etka Kilinc
 * Entry class that holds key and value implements Comparable.
 */
public class Entry<K extends Comparable<K>,V> implements Comparable<Entry<K,V>> {
        /** The key */
        private K key;
        /** The value */
        private V value;
        /** Creates a new key‐value pair.
         @param key The key
         @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        /** Retrieves the key.
         @return The key
         */
        public K getKey() {
            return key;
        }
        /** Retrieves the value.
         @return The value
         */
        public V getValue() {
            return value;
        }
        /** Sets the value.
         @param val The new value
         @return The old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

    /**
     * {@inheritDoc}
     */
    @Override
        public String toString(){
            return String.format(" Key: %s Value: %s ",key,value);
        }

    /**
     * Compare method for entry class that compares key values.
     * @param o item to be compared.
     * @return returns comparing result.
     */
    @Override
    public int compareTo(Entry<K, V> o) {
        return this.key.compareTo(o.getKey());
    }
}
