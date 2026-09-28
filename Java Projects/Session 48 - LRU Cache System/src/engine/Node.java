package engine;

/**
 * Generic Node representing a cache item.
 * 
 * CRITICAL INTERVIEW NOTE:
 * Node stores both 'key' and 'value'.
 * Storing 'key' is required because when evicting tail.prev from DLL,
 * we must execute map.remove(evictedNode.key) in O(1) without an O(N) reverse search!
 */
public class Node<K, V> {
    public K key;
    public V value;
    public Node<K, V> prev;
    public Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "[" + key + "=" + value + "]";
    }
}
