# LRU-Cache-Implementation
Contains the code for a basic implementation of a cache system using the LRU approach


## What is a cache and why is it useful? 

- In computing, a cache is hardware or software componentthat stores data so that future requests for that data can be served faster; the data stored in a cache might be the result of an earlier computation or a copy of data stored elsewhere

- The technique of storing cache data or cache files as history on a phone or web browser to improve the user experience on future visits to a website or an app is known as caching. 

## What is Least Recently Used cache? 

- A Least Recently Used (LRU) Cache organizes items in order of use, allowing you to quickly identify which item hasn't been used for the longest amount of time. Picture a clothes rack, where clothes are always hung up on one side. To find the least-recently used item, look at the item on the other end of the rack.


## Tools:

- For this project, we will use Java as the main the code to implement the algorithm as well as JUNIT 5 for writing unit test cases.


## Requirements:
 
- The objectives is to implement a LRU class that has the following implementaions and requirements

	+ LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
	+  int get(int key) Return the value of the key if the key exists, otherwise return -1.
	+ void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.


## Observation: 

-  Let talk about usage:
        + Whenever the key is used, we want to count it.
        + A key is used when it is being put and get in and out of the cache data structure
        + Depends on the number of usage, the cache will evicts the key with the smallest number of use, when the cache has reached its max capacity 
- The algorithm must be able to know when the cache data structure has reached its capacity.
            => Whenever a put request is called, first check to see if the capacity has been reached or not, if not, then put it into the ds and  substract the capacity down by 1. If it has been reached, then perform the eviction process and keep track of the number of the usage for each key.

## Implementation:

- Double Linked list data structure: to determine which key has been most recently used and least recently used. The list will put the most recently used data structure onto the head and closest to the head, while the least recentlY used will be put to its tails.
- Each node will store the key-value pairs as the node value, this way, when we look up a key from the map, we should be get access to its value in the linked list in constant time.
- The hashmap will be used to store the key with its matching list node
