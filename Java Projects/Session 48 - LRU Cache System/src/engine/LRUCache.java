package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PRODUCTION-GRADE GENERIC LRU CACHE
 * 
 * Performance Guarantee:
 * - get(key)      : Strictly O(1)
 * - put(key, val) : Strictly O(1)
 * 
 * Architectural Highlights:
 * 1. HashMap<K, Node<K,V>> provides instant O(1) pointer address lookup.
 * 2. Doubly Linked List (DLL) provides O(1) node extraction and head insertion.
 * 3. Sentinel Nodes (Dummy Head & Tail) eliminate all null-check edge cases!
 * 4. Thread-Safe: Core mutations are synchronized for multi-threaded safety.
 */
public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> map;
    private final Node<K, V> head; // Dummy Sentinel (MRU Side)
    private final Node<K, V> tail; // Dummy Sentinel (LRU Side)

    // Telemetry & Metrics
    private long hitCount = 0;
    private long missCount = 0;
    private long evictionCount = 0;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Cache capacity must be greater than 0");
        }
        this.capacity = capacity;
        this.map = new HashMap<>();

        // Initialize Dummy Sentinels
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);

        // Native Sentinel Interconnection
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * Retrieves the value from cache.
     * Side-effect: Marks node as Most Recently Used (promotes to Head).
     * Time: O(1)
     */
    public synchronized V get(K key) {
        if (!map.containsKey(key)) {
            missCount++;
            return null;
        }

        hitCount++;
        Node<K, V> node = map.get(key);

        // Move to Head (MRU)
        removeNode(node);
        addFirst(node);

        return node.value;
    }

    /**
     * Inserts or updates the key-value pair.
     * If capacity is breached, evicts the Least Recently Used (tail.prev) node.
     * Time: O(1)
     */
    public synchronized void put(K key, V value) {
        if (map.containsKey(key)) {
            // Update existing value and promote to MRU
            Node<K, V> existingNode = map.get(key);
            existingNode.value = value;
            removeNode(existingNode);
            addFirst(existingNode);
        } else {
            // Check Capacity Breach
            if (map.size() >= capacity) {
                // tail.prev is the LRU node!
                Node<K, V> lruNode = tail.prev;
                removeNode(lruNode);
                map.remove(lruNode.key); // Critical: avoid memory leak!
                evictionCount++;
            }

            // Create brand new node and insert at Head (MRU)
            Node<K, V> newNode = new Node<>(key, value);
            addFirst(newNode);
            map.put(key, newNode);
        }
    }

    /**
     * Pointer Helper: Inserts node immediately after dummy head.
     * Exactly 4 pointer updates.
     */
    private void addFirst(Node<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * Pointer Helper: Extracts node from DLL in 2 pointer changes.
     * Because head and tail sentinels are permanent, node.prev and node.next
     * are GUARANTEED non-null for any real data node!
     */
    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public synchronized boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public synchronized int size() {
        return map.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public synchronized double getHitRatio() {
        long total = hitCount + missCount;
        return total == 0 ? 0.0 : (double) hitCount / total;
    }

    public synchronized void printCacheState() {
        System.out.printf("  [LRU State] Size: %d/%d | Hits: %d | Misses: %d | Evictions: %d\n",
                map.size(), capacity, hitCount, missCount, evictionCount);
        System.out.print("  [Order: MRU -> LRU]: HEAD <-> ");
        Node<K, V> curr = head.next;
        while (curr != tail) {
            System.out.print(curr + " <-> ");
            curr = curr.next;
        }
        System.out.println("TAIL");
    }

    public synchronized List<K> getKeysInOrder() {
        List<K> keys = new ArrayList<>();
        Node<K, V> curr = head.next;
        while (curr != tail) {
            keys.add(curr.key);
            curr = curr.next;
        }
        return keys;
    }
}
