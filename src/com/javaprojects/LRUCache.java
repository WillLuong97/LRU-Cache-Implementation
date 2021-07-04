package com.javaprojects;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
        /*
        Double Linked list data structure: to determine which key has been most recently used and least recently used
        The list will put the most recently used data structure onto the head and closest to the head, while the least recently
        used will be put to its tails.

        Each node will store the key-value pairs as the node value, this way, when we look up a key from the
        map, we should be get access to its value in the linked list in constant time.

        The hashmap will be used to store the key with its matching list node
        */
     class DoubleListNode{
        DoubleListNode prev;
        DoubleListNode next;
        int key;
        int value;

        //Constructor:
        DoubleListNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }


    //create a hashmap to map the key with is corresponding node
    HashMap<Integer, DoubleListNode> hashMap = new HashMap<>();
    DoubleListNode tail = new DoubleListNode(-1,-1);
    DoubleListNode head = new DoubleListNode(-1, -1);
    int capacity;


    // Initialize the LRU cache with positive size capacity
    public LRUCache(int capacity){
        this.capacity = capacity;
        join(head, tail);
    }

    //function to connect the node 1 to node 2 in a linked list
    private void join(DoubleListNode node1, DoubleListNode node2){
        node1.next = node2;
        node2.prev = node1;
    }

    //Return the value of the key if the key exists, otherwise return -1.
    public int get(int key){
        //if the key is not already in the hashmap, return -1
        if(!hashMap.containsKey(key)){
            return -1;
        }

        //if it does contains the key, then return it and move the key node closer to the head
        DoubleListNode keyNode = hashMap.get(key);
        //remove the current key from the linked list
        join(keyNode.prev, keyNode.next);
        //then move it to the head to mark it as the most recently used element
        moveToHead(keyNode);
        return keyNode.value;
    }


    //Function to move the current node to the head of the linked list
    private void moveToHead(DoubleListNode node){
        DoubleListNode nextOfHead = head.next;
        join(head, node);
        join(node, nextOfHead);
    }

    //Update the value of the key if the key exists.
    public void put(int key, int value){
        //check if the key exist: then override its value with the new value and update its recent-ness in the linked list
        if(hashMap.containsKey(key)){
            DoubleListNode keyNode = hashMap.get(key);
            //update its priority in the linked list
            join(keyNode.prev, keyNode.next);
            moveToHead(keyNode);
            //override the value with the
            keyNode.value = value;
        } else {
            //otherwise, if the capacity has been reached, we will have to evict the least recent node out of the list, i.e. the tail
            if(hashMap.size() == capacity){
                //removing the tail from the linked list and the hashmap
                hashMap.remove(tail.prev.key);
                join(tail.prev.prev, tail);
            }

            //just add element into the list and the hashmap if the capacity has not been reached.
            DoubleListNode node = new DoubleListNode(key, value);
            moveToHead(node);
            hashMap.put(key, node);
        }
    }
}
