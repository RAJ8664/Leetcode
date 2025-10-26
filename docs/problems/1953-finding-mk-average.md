# 1953. Finding Mk Average

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/finding-mk-average/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1953-finding-mk-average){ .md-button }

---

<h2><a href="https://leetcode.com/problems/finding-mk-average">1953. Finding MK Average</a></h2><h3>Hard</h3><hr><p>You are given two integers, <code>m</code> and <code>k</code>, and a stream of integers. You are tasked to implement a data structure that calculates the <strong>MKAverage</strong> for the stream.</p>

<p>The <strong>MKAverage</strong> can be calculated using these steps:</p>

<ol>
	<li>If the number of the elements in the stream is less than <code>m</code> you should consider the <strong>MKAverage</strong> to be <code>-1</code>. Otherwise, copy the last <code>m</code> elements of the stream to a separate container.</li>
	<li>Remove the smallest <code>k</code> elements and the largest <code>k</code> elements from the container.</li>
	<li>Calculate the average value for the rest of the elements <strong>rounded down to the nearest integer</strong>.</li>
</ol>

<p>Implement the <code>MKAverage</code> class:</p>

<ul>
	<li><code>MKAverage(int m, int k)</code> Initializes the <strong>MKAverage</strong> object with an empty stream and the two integers <code>m</code> and <code>k</code>.</li>
	<li><code>void addElement(int num)</code> Inserts a new element <code>num</code> into the stream.</li>
	<li><code>int calculateMKAverage()</code> Calculates and returns the <strong>MKAverage</strong> for the current stream <strong>rounded down to the nearest integer</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MKAverage&quot;, &quot;addElement&quot;, &quot;addElement&quot;, &quot;calculateMKAverage&quot;, &quot;addElement&quot;, &quot;calculateMKAverage&quot;, &quot;addElement&quot;, &quot;addElement&quot;, &quot;addElement&quot;, &quot;calculateMKAverage&quot;]
[[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
<strong>Output</strong>
[null, null, null, -1, null, 3, null, null, null, 5]

<strong>Explanation</strong>
<code>MKAverage obj = new MKAverage(3, 1); 
obj.addElement(3);        // current elements are [3]
obj.addElement(1);        // current elements are [3,1]
obj.calculateMKAverage(); // return -1, because m = 3 and only 2 elements exist.
obj.addElement(10);       // current elements are [3,1,10]
obj.calculateMKAverage(); // The last 3 elements are [3,1,10].
                          // After removing smallest and largest 1 element the container will be [3].
                          // The average of [3] equals 3/1 = 3, return 3
obj.addElement(5);        // current elements are [3,1,10,5]
obj.addElement(5);        // current elements are [3,1,10,5,5]
obj.addElement(5);        // current elements are [3,1,10,5,5,5]
obj.calculateMKAverage(); // The last 3 elements are [5,5,5].
                          // After removing smallest and largest 1 element the container will be [5].
                          // The average of [5] equals 5/1 = 5, return 5
</code></pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k*2 &lt; m</code></li>
	<li><code>1 &lt;= num &lt;= 10<sup>5</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls will be made to <code>addElement</code> and <code>calculateMKAverage</code>.</li>
</ul>


---

## Solution

```java
class MKAverage {
    private TreeMap<Integer, Integer> map;
    private Deque<Integer> dq;
    int m, k, sum;
    public MKAverage(int m, int k) {
        map = new TreeMap<>();
        dq = new ArrayDeque<>();
        this.m = m;
        this.k = k;
        sum = 0;
    }
    public void addElement(int num) {
        dq.addLast(num);
        sum += num;
        map.put(num, map.getOrDefault(num, 0) + 1);
        if (dq.size() > m) {
            int to_remove = dq.pollFirst();
            sum -= to_remove;
            map.put(to_remove, map.getOrDefault(to_remove, 0) -1);
            if (map.getOrDefault(to_remove, 0) == 0) map.remove(to_remove);
        }
    }
    public int calculateMKAverage() {
        if (dq.size() < m) return -1;
        int first_smallest_sum = 0, first_largest_sum = 0, count = 0, current_key = map.firstKey();
        while (count < k) {
            if (map.get(current_key) + count <= k) {
                count += map.get(current_key);
                first_smallest_sum += map.get(current_key) * current_key;
                current_key = map.higherKey(current_key);
            }
            else {
                first_smallest_sum += current_key * (k - count);
                break;
            }
        }
        current_key = map.lastKey();
        count = 0;
        while (count < k) {
            if (map.get(current_key) + count <= k) {
                first_largest_sum += map.get(current_key) * current_key;
                count += map.get(current_key);
                current_key = map.lowerKey(current_key);
            }
            else {
                first_largest_sum += current_key * (k - count);
                break;
            }
        }
        int res = sum - first_smallest_sum - first_largest_sum;
        return res / (m - 2 * k);
    }
}

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

