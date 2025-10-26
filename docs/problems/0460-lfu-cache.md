# 460. Lfu Cache

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/lfu-cache/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0460-lfu-cache){ .md-button }

---

<h2><a href="https://leetcode.com/problems/lfu-cache">460. LFU Cache</a></h2><h3>Hard</h3><hr><p>Design and implement a data structure for a <a href="https://en.wikipedia.org/wiki/Least_frequently_used" target="_blank">Least Frequently Used (LFU)</a> cache.</p>

<p>Implement the <code>LFUCache</code> class:</p>

<ul>
	<li><code>LFUCache(int capacity)</code> Initializes the object with the <code>capacity</code> of the data structure.</li>
	<li><code>int get(int key)</code> Gets the value of the <code>key</code> if the <code>key</code> exists in the cache. Otherwise, returns <code>-1</code>.</li>
	<li><code>void put(int key, int value)</code> Update the value of the <code>key</code> if present, or inserts the <code>key</code> if not already present. When the cache reaches its <code>capacity</code>, it should invalidate and remove the <strong>least frequently used</strong> key before inserting a new item. For this problem, when there is a <strong>tie</strong> (i.e., two or more keys with the same frequency), the <strong>least recently used</strong> <code>key</code> would be invalidated.</li>
</ul>

<p>To determine the least frequently used key, a <strong>use counter</strong> is maintained for each key in the cache. The key with the smallest <strong>use counter</strong> is the least frequently used key.</p>

<p>When a key is first inserted into the cache, its <strong>use counter</strong> is set to <code>1</code> (due to the <code>put</code> operation). The <strong>use counter</strong> for a key in the cache is incremented either a <code>get</code> or <code>put</code> operation is called on it.</p>

<p>The functions&nbsp;<code data-stringify-type="code">get</code>&nbsp;and&nbsp;<code data-stringify-type="code">put</code>&nbsp;must each run in <code>O(1)</code> average time complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;LFUCache&quot;, &quot;put&quot;, &quot;put&quot;, &quot;get&quot;, &quot;put&quot;, &quot;get&quot;, &quot;get&quot;, &quot;put&quot;, &quot;get&quot;, &quot;get&quot;, &quot;get&quot;]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
<strong>Output</strong>
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

<strong>Explanation</strong>
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
&nbsp;                // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[4,3], cnt(4)=2, cnt(3)=3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= capacity&nbsp;&lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= key &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= value &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>2 * 10<sup>5</sup></code>&nbsp;calls will be made to <code>get</code> and <code>put</code>.</li>
</ul>

<p>&nbsp;</p>
<span style="display: none;">&nbsp;</span>

---

## Solution

```java
class LFUCache {
    private TreeSet<Node> set;
    private HashMap<Integer, Integer> map;
    private int currTime;
    private HashMap<Integer, Integer> freq;
    private HashMap<Integer, Integer> time;
    private int totalCap, currCap;

    static class Node {
        int key, value, count, currTime;
        public Node(int key, int value, int count, int currTime) {
            this.key = key;
            this.value = value;
            this.count = count;
            this.currTime = currTime;
        }
        @Override
        public String toString() {
            return "(" + key + " " + value + " " + count + " " + currTime + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Node current = (Node)(obj);
            return current.key == key && current.value == value && current.count == count && current.currTime == currTime;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value, count, currTime);
        }
    }

    static class customSort implements Comparator<Node> {
        @Override
        public int compare(Node first, Node second) {
            int op1 = Integer.compare(second.count, first.count);
            if (op1 != 0)
                return op1;
            return Integer.compare(second.currTime, first.currTime);
        }
    }

    public LFUCache(int capacity) {
        set = new TreeSet<>(new customSort()); 
        map = new HashMap<>();
        currTime = 0;
        freq = new HashMap<>();
        time = new HashMap<>();
        currCap = 0;
        totalCap = capacity;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Node prev = new Node(key, map.get(key), freq.get(key), time.get(key));
            int res = map.get(key);
            set.remove(prev);
            time.put(key, currTime);
            set.add(new Node(key, map.get(key), freq.get(key) + 1, currTime));
            freq.put(key, freq.get(key) + 1);
            currTime++;
            return res;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node prev = new Node(key, map.get(key), freq.get(key), time.get(key));
            set.remove(prev);
            map.put(key, value);
            freq.put(key, freq.get(key) + 1);
            time.put(key, currTime);
            set.add(new Node(key, value, freq.get(key), currTime));
            currTime++;
        } else {
            if (currCap < totalCap) {
                map.put(key, value);
                freq.put(key, 1);
                time.put(key, currTime);
                set.add(new Node(key, value, 1, currTime));
                currTime++;
                currCap++; 
            } else {
                Node last = set.pollLast();
                map.remove(last.key);
                map.put(key, value);
                freq.put(key, 1);
                time.put(key, currTime);
                set.add(new Node(key, value, 1, currTime));
                currTime++;
                currCap++; 
            }
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

