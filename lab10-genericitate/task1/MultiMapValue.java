package task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MultiMapValue<K, V> {
    private Map<K, List<V>> map;

    public void add(K key, V value) {
        if (this.map == null) {
            this.map = new HashMap<>();
        }

        List<V> valuesList = this.getValues(key);
        valuesList.add(value);
        this.map.put(key, valuesList);
    }

    public void addAll(K key, List<V> values) {
        values.forEach(value -> add(key, value));
    }

    public void addAll(MultiMapValue<K, V> map) {
        for (K key : map.map.keySet()) {
            addAll(key, map.getValues(key));
        }
    }

    public V getFirst(K key) {
        if (!map.containsKey(key)) {
            return null;
        }

        return map.get(key).get(0);
    }

    public List<V> getValues(K key) {
        if (this.map.containsKey(key)) {
            return this.map.get(key);
        }
        return new ArrayList<>();
    }

    public boolean containsKey(K key) {
        return this.map.containsKey(key);
    }

    public boolean isEmpty() {
        if (this.map == null || this.map.size() == 0) {
            return true;
        }
        return false;
    }

    public List<V> remove(K key) {
        return map.remove(key);
    }

    public int size() {
        if (this.map == null) {
            return 0;
        }

        return this.map.size();
    }
}
