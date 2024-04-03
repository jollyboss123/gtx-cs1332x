package gtxcs1332x.module7;

import java.util.NoSuchElementException;

/**
 * Your implementation of a ExternalChainingHashMap.
 */
public class ExternalChainingHashMap<K, V> {

    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of INITIAL_CAPACITY.
     */
    public ExternalChainingHashMap() {
        //DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the table would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is
     * okay if the load factor is equal to max LF). For example, let's say
     * the table is of length 5 and the current size is 3 (LF = 0.6). For
     * this example, assume that no elements are removed in between steps.
     * If another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the
     *         map, return the old value associated with it.
     * @throws java.lang.IllegalArgumentException If key or value is null.
     */
    public V put(K key, V value) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }

        int i = index(key, table);

        if (exceedLoadFactor()) {
            // rerun for resized table
            put(key, value);
        }

        // collision
        if (table[i] != null) {
            // chain
            ExternalChainingMapEntry<K, V> curr = table[i];
            while (curr != null) {
                // duplicated key
                if (curr.getKey().equals(key)) {
                    V val = curr.getValue();
                    curr.setValue(value);
                    return val;
                }
                curr = curr.getNext();
            }
            // add in front of chain
            ExternalChainingMapEntry<K, V> entry = new ExternalChainingMapEntry<>(key, value, table[i]);
            table[i] = entry;
            size++;
            return null;
        }

        // no collision
        table[i] = new ExternalChainingMapEntry<>(key, value);
        size++;
        return null;
    }

    /**
     * Check if {@link ExternalChainingHashMap#put(Object, Object)} operation will exceed max load factor. If true,
     * resize backing array to 2N + 1, N as the current backing array capacity.
     *
     * @return true if exceed max load factor, else false.
     */
    private boolean exceedLoadFactor() {
        if (((double) (size + 1) / table.length) > MAX_LOAD_FACTOR) {
            resizeBackingTable((2 * table.length) + 1);
            return true;
        }
        return false;
    }

    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key The key to remove.
     * @return The value associated with the key.
     * @throws java.lang.IllegalArgumentException If key is null.
     * @throws java.util.NoSuchElementException   If the key is not in the map.
     */
    public V remove(K key) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int i = index(key, table);

        if (table[i] == null) {
            throw new NoSuchElementException();
        }

        // chain
        if (table[i].getNext() != null) {
            // remove head
            if (table[i].getKey().equals(key)) {
                V val = table[i].getValue();
                ExternalChainingMapEntry<K, V> newHead = table[i].getNext();
                table[i] = newHead;
                size--;
                return val;
            }
            ExternalChainingMapEntry<K, V> curr = table[i];
            // skip checking head
            while (curr.getNext() != null) {
                // next key matches
                if (curr.getNext().getKey().equals(key)) {
                    V val = curr.getNext().getValue();
                    // have next next node, then link to current node
                    if (curr.getNext().getNext() != null) {
                        curr.setNext(curr.getNext().getNext());
                    } else {
                        // last node
                        // set next node to null for gc
                        curr.setNext(null);
                    }
                    size--;
                    return val;
                }
                curr = curr.getNext();
            }
            throw new NoSuchElementException();
        }

        // no chain
        if (table[i].getKey().equals(key)) {
            V val = table[i].getValue();
            table[i] = null;
            size--;
            return val;
        }
        throw new NoSuchElementException();
    }

    /**
     * Searches the key and returns the associated value.
     *
     * @param key the key to be searched.
     * @return The value associated with the key.
     * @throws java.lang.IllegalArgumentException If key is null.
     * @throws java.util.NoSuchElementException   If the key is not in the map.
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int i = index(key, table);
        if (table[i] == null) {
            throw new NoSuchElementException();
        }

        ExternalChainingMapEntry<K, V> curr = table[i];
        while (curr != null) {
            if (curr.getKey().equals(key)) {
                return curr.getValue();
            }
            curr = curr.getNext();
        }
        throw new NoSuchElementException();
    }

    /**
     * Helper method stub for resizing the backing table to length.
     *
     * This method should be called in put() if the backing table needs to
     * be resized.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you won't need to explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new table.
     *
     * @param length The new length of the backing table.
     */
    private void resizeBackingTable(int length) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        ExternalChainingMapEntry<K, V>[] arr = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[length];
        for (ExternalChainingMapEntry<K, V> kvExternalChainingMapEntry : table) {
            if (kvExternalChainingMapEntry == null) {
                continue;
            }

            // chain
            if (kvExternalChainingMapEntry.getNext() != null) {
                ExternalChainingMapEntry<K, V> curr = kvExternalChainingMapEntry;
                while (curr != null) {
                    reinsert(curr, arr);
                    curr = curr.getNext();
                }
            } else {
                // no chain
                reinsert(kvExternalChainingMapEntry, arr);
            }
        }

        table = arr;
    }

    /**
     * Helper method for recompressing existing keys and add to the new backing array.
     *
     * Don't have to check for duplicates since working with non-duplicate data.
     *
     * @param entry current entry in existing map array.
     * @param backingArray new backing array.
     */
    private void reinsert(ExternalChainingMapEntry<K, V> entry, ExternalChainingMapEntry<K, V>[] backingArray) {
        int i = index(entry.getKey(), backingArray);
        if (backingArray[i] != null) {
            ExternalChainingMapEntry<K, V> newHead = new ExternalChainingMapEntry<>(entry.getKey(), entry.getValue(), table[i]);
            table[i] = newHead;
            return;
        }
        backingArray[i] = new ExternalChainingMapEntry<>(entry.getKey(), entry.getValue());
    }

    /**
     * Calculate index based on given key's hash.
     *
     * @param key key to be hashed and compressed.
     * @param backingArray to retrieve capacity for compress operation.
     * @return absolute value of index.
     */
    private int index(K key, ExternalChainingMapEntry<K, V>[] backingArray) {
        // hash
        int h = key.hashCode();
        // compress
        int i = h % backingArray.length;
        if (i < 0) {
            i = i * -1;
        }
        return i;
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the map.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (ExternalChainingMapEntry<K, V> kvExternalChainingMapEntry : table) {
            if (kvExternalChainingMapEntry != null) {
                sb.append(kvExternalChainingMapEntry.getKey()).append("=").append(kvExternalChainingMapEntry.getValue() == null ? "null" : kvExternalChainingMapEntry.getValue());
                ExternalChainingMapEntry<K, V> curr = kvExternalChainingMapEntry.getNext();
                while (curr != null) {
                    sb.append(" -> ").append(curr.getKey()).append("=").append(curr.getValue() == null ? "null" : curr.getValue());
                    curr = curr.getNext();
                }
                sb.append(", ");
            } else {
                sb.append("null, ");
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2); // Remove trailing ", "
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        ExternalChainingHashMap<Integer, String> map = new ExternalChainingHashMap<>();

        // Test put and get
        map.put(14, "apple");
        map.put(15, "banana");
        map.put(16, "orange");
        System.out.println("Map contents: " + map);
        if (!map.get(14).equals("apple")) {
            throw new RuntimeException();
        }
        if (!map.get(15).equals("banana")) {
            throw new RuntimeException();
        }
        if (!map.get(16).equals("orange")) {
            throw new RuntimeException();
        }

        // Test duplicate key
        String v = map.put(1, "red apple");
        System.out.println("Map contents after putting 1: " + map);
        System.out.println("previous content: " + v);
        if (v != null) {
            throw new RuntimeException();
        }
        if (!map.get(1).equals("red apple")) {
            throw new RuntimeException();
        }

        v = map.put(15, "yellow banana");
        System.out.println("Map contents after updating 15: " + map);
        System.out.println("previous content: " + v);
        if (!v.equals("banana")) {
            throw new RuntimeException();
        }
        if (!map.get(15).equals("yellow banana")) {
            throw new RuntimeException();
        }

        // Test remove
        String r = map.remove(15);
        System.out.println("Map contents after removing 15: " + map);
        if (!r.equals("yellow banana")) {
            throw new RuntimeException();
        }

        // Test chains
        v = map.put(13, "key1");
        if (v != null) {
            throw new RuntimeException();
        }
        if (!map.get(13).equals("key1")) {
            throw new RuntimeException();
        }
        v = map.put(26, "key2");
        if (v != null) {
            throw new RuntimeException();
        }
        if (!map.get(26).equals("key2")) {
            throw new RuntimeException();
        }
        v = map.put(39, "key3");
        if (v != null) {
            throw new RuntimeException();
        }
        if (!map.get(39).equals("key3")) {
            throw new RuntimeException();
        }
        v = map.put(52, "key4");
        if (v != null) {
            throw new RuntimeException();
        }
        if (!map.get(52).equals("key4")) {
            throw new RuntimeException();
        }
        System.out.println("Map contents with chains: " + map);

        // Test update key in chain
        v = map.put(26, "new key2");
        System.out.println("Map contents after updating 26: " + map);
        System.out.println("previous content: " + v);
        if (!v.equals("key2")) {
            throw new RuntimeException();
        }
        if (!map.get(26).equals("new key2")) {
            throw new RuntimeException();
        }

        // Test update tail in chain
        v = map.put(13, "new key1");
        System.out.println("Map contents after updating 13: " + map);
        System.out.println("previous content: " + v);
        if (!v.equals("key1")) {
            throw new RuntimeException();
        }
        if (!map.get(13).equals("new key1")) {
            throw new RuntimeException();
        }

        // Test update head in chain
        v = map.put(52, "new key4");
        System.out.println("Map contents after updating 52: " + map);
        System.out.println("previous content: " + v);
        if (!v.equals("key4")) {
            throw new RuntimeException();
        }
        if (!map.get(52).equals("new key4")) {
            throw new RuntimeException();
        }

        // Test remove from chain
        r = map.remove(26);
        System.out.println("Map contents after removing 26: " + map);
        if (!r.equals("new key2")) {
            throw new RuntimeException();
        }

        // Test remove head from chain
        r = map.remove(52);
        System.out.println("Map contents after removing 52: " + map);
        if (!r.equals("new key4")) {
            throw new RuntimeException();
        }

        // Test remove tail from chain
        r = map.remove(13);
        System.out.println("Map contents after removing 13: " + map);
        if (!r.equals("new key1")) {
            throw new RuntimeException();
        }

        // Test resize
        for (int i = 0; i < 10; i++) {
            map.put(i, "key" + i);
        }
        System.out.println("Map contents after resizing: " + map);

        // Test remove non-existent key
        try {
            map.remove(100);
        } catch (NoSuchElementException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        // Test null key or value
        try {
            map.put(null, "value");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        try {
            map.put(40, null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}
