
import java.util.Iterator;
import java.lang.Iterable;

public abstract class AbstractMap<K, V> implements Map<K, V> {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    protected static class MapEntry<K, V> implements Entry<K, V> {

        private K k;
        private V v;
        
        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }
        @Override
        public K getKey() {
            return k;
        }
        @Override
        public V getValue() {
            return v;
        }
        protected void setKey(K key) {
            k = key;
        }
        protected V setValue(V value) {
            V old = v;
            v = value;
            return old;
        }
    }
    
    private class KeyIterator implements Iterator<K> {

        private Iterator<Entry<K, V>> entries = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }
        @Override
        public K next() {
            return entries.next().getKey();
        }
    }
    
    private class KeyIterable implements Iterable<K> {
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    @Override
    public Iterable<K> keySet() {
        return new KeyIterable();
    }
    
    private class ValueIterator implements Iterator<V> {

        private Iterator<Entry<K, V>> entries = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }
        @Override
        public V next() {
            return entries.next().getValue();
        }
    }

    private class ValueIterable implements Iterable<V> {
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }
}
