import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Bahadir Etka Kilinc
 * BKHashMap class extends from HashMap and implements MapIteratorInterface in it.
 *
 */
public class BKHashMap<K,V> extends HashMap<K,V> {

    private class MapIterator<K> implements MapIteratorInterface<K> {
        private Iterator<K> it;
        private int index = 0;
        public MapIterator()
        {
            it = (Iterator<K>) keySet().iterator();
        }
        public MapIterator(K key) {
            it = (Iterator<K>) keySet().iterator();
            while (hasNext()) {
                if (next().equals(key)) {
                    prev();
                    break;
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public K next(){
            ++index;
            return (K) it.next();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public K prev() {
            if(index < 1)
                return null;
            it = (Iterator<K>) keySet().iterator();
            for (int i = 0; i < index - 2; ++i) {
                it.next();
            }
            --index;
            return (K) it.next();

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext()
        {
            return it.hasNext();
        }
    }
    public MapIterator<K> iterator(){return new MapIterator<K>();}
    public MapIterator<K> iterator(K key)
    {
        return new MapIterator<K>(key);
    }
}
