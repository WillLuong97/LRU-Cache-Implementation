package com.javaprojects;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {
    @Test
    public void getKeyTest_1() throws Exception{
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        assertEquals(1, lRUCache.get(1));
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        assertEquals(-1, lRUCache.get(2));
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        assertEquals(-1, lRUCache.get(1));
        assertEquals(3, lRUCache.get(3));
        assertEquals(4, lRUCache.get(4));
    }

    @Test
    public void getKeyTest_2() throws Exception{
        LRUCache lRUCache = new LRUCache(5);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}

        assertEquals(1, lRUCache.get(1));
        lRUCache.put(3, 3); // cache = {1=1, 2=2, 3=3}
        assertEquals(2, lRUCache.get(2));
        lRUCache.put(4, 4); // cache = {1=1, 2=2, 3=3, 4=4}
        assertEquals(1, lRUCache.get(1));
        assertEquals(3, lRUCache.get(3));
        assertEquals(4, lRUCache.get(4));
        lRUCache.put(5, 5); // cache = {1=1, 2=2, 3=3, 4=4, 5=5}
        assertEquals(1, lRUCache.get(1));
        assertEquals(5, lRUCache.get(5));
        lRUCache.put(5, 7); // cache = {1=1, 2=2, 3=3, 4=4, 5=5}
        assertEquals(1, lRUCache.get(1));
        assertEquals(7, lRUCache.get(5));
        lRUCache.put(6, 6); // cache = {1=1, 2=2, 3=3, 4=4, 5=5}
        assertEquals(1, lRUCache.get(1));
        assertEquals(6, lRUCache.get(6));
    }

}