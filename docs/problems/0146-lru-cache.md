# 146. Lru Cache

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/lru-cache/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0146-lru-cache){ .md-button }

---

<h2><a href="https://leetcode.com/problems/lru-cache">146. LRU Cache</a></h2><h3>Medium</h3><hr><p>Design a data structure that follows the constraints of a <strong><a href="https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU" target="_blank">Least Recently Used (LRU) cache</a></strong>.</p>

<p>Implement the <code>LRUCache</code> class:</p>

<ul>
	<li><code>LRUCache(int capacity)</code> Initialize the LRU cache with <strong>positive</strong> size <code>capacity</code>.</li>
	<li><code>int get(int key)</code> Return the value of the <code>key</code> if the key exists, otherwise return <code>-1</code>.</li>
	<li><code>void put(int key, int value)</code> Update the value of the <code>key</code> if the <code>key</code> exists. Otherwise, add the <code>key-value</code> pair to the cache. If the number of keys exceeds the <code>capacity</code> from this operation, <strong>evict</strong> the least recently used key.</li>
</ul>

<p>The functions <code>get</code> and <code>put</code> must each run in <code>O(1)</code> average time complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;LRUCache&quot;, &quot;put&quot;, &quot;put&quot;, &quot;get&quot;, &quot;put&quot;, &quot;get&quot;, &quot;put&quot;, &quot;get&quot;, &quot;get&quot;, &quot;get&quot;]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
<strong>Output</strong>
[null, null, null, 1, null, -1, null, -1, 3, 4]

<strong>Explanation</strong>
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= capacity &lt;= 3000</code></li>
	<li><code>0 &lt;= key &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= value &lt;= 10<sup>5</sup></code></li>
	<li>At most <code>2 * 10<sup>5</sup></code> calls will be made to <code>get</code> and <code>put</code>.</li>
</ul>


---

## Solution

```java
class LRUCache {
    static class Node {
        Node prev, next;
        int val;
        public Node(int val) {
            this.val = val;
            prev = null;
            next = null;
        }
    }
    private Node head;
    private Node tail;
    private int totalNodes;
    private int n;
    private HashMap<Integer, Integer> map;
    private HashMap<Integer, Node> nodeMap;
    public LRUCache(int capacity) {
        this.head = null;
        this.tail = null; 
        this.totalNodes = 0;  
        this.n = capacity;
        map = new HashMap<>();
        nodeMap = new HashMap<>(); 
    }
    public int get(int key) {
        if (map.containsKey(key)) {
            int res = map.get(key);
            deletePrevRef(key);
            addFront(key);
            return res;
        }
        return -1; 
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            deletePrevRef(key);
            addFront(key);
            map.remove(key);
            map.put(key, value); 
        } 
        
        else if (totalNodes == n) {
            /* I need to delete something */
            int deletedKey = deleteLast();
            map.remove(deletedKey);

            map.put(key, value);
            addFront(key);   
        }  
        else {
            /* I want to make a node of this value */
            if (map.containsKey(key)) {
                map.remove(key);
                deletePrevRef(key);

                map.put(key, value);
                addFront(key);  
            }
            else {
                map.put(key, value);
                addFront(key);
            }
        }
    }
    private int deleteLast() {
        totalNodes--;
        int ans = tail.val;
        nodeMap.remove(ans);
        if (totalNodes == 0) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        return ans;
    }
    private void deletePrevRef(int key) {
        totalNodes--;
        if (totalNodes == 0) {
            head = null;
            tail = null;
            return;
        }
        Node refNode = nodeMap.get(key);
        if (refNode.prev == null) {
            /* want to delete the head node */
            head = refNode.next;
            head.prev = null;
            return;
        }
        if (refNode.next == null) {
            /* want to delete the tail node*/
            tail = tail.prev;
            tail.next = null;     
        }
        else {
            refNode.prev.next = refNode.next;
            refNode.next.prev = refNode.prev; 
        }
    }
    private void deletePrev(int key) {
        totalNodes--;
        if (totalNodes == 0) {
            head = null;
            tail = null;
            return;
        }
        Node temp = head;
        if (temp.val == key) {
            head = temp.next;
            head.prev = null;
            return;   
        }
        while (temp != null) {
            if (temp.val == key) {
                if (temp.next == null) {
                    tail = tail.prev;
                    tail.next = null;     
                }
                else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
            }
            temp = temp.next;
        }         
    } 
    private void addFront(int val) {
        if (head == null) {
            head = new Node(val);
            tail = head;
            nodeMap.put(val, head);
        }
        else {
            Node newNode = new Node(val);
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
            head.prev = null;
            nodeMap.put(val, head);
        }
        totalNodes++;
    }
    private void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

