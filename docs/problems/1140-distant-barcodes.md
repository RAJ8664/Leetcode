# 1140. Distant Barcodes

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/distant-barcodes/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1140-distant-barcodes){ .md-button }

---

<h2><a href="https://leetcode.com/problems/distant-barcodes">1140. Distant Barcodes</a></h2><h3>Medium</h3><hr><p>In a warehouse, there is a row of barcodes, where the <code>i<sup>th</sup></code> barcode is <code>barcodes[i]</code>.</p>

<p>Rearrange the barcodes so that no two adjacent barcodes are equal. You may return any answer, and it is guaranteed an answer exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> barcodes = [1,1,1,2,2,2]
<strong>Output:</strong> [2,1,2,1,2,1]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> barcodes = [1,1,1,1,2,2,3,3]
<strong>Output:</strong> [1,3,1,3,1,2,1,2]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= barcodes.length &lt;= 10000</code></li>
	<li><code>1 &lt;= barcodes[i] &lt;= 10000</code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int node, freq, used;
        public Pair(int node, int freq, int used) {
            this.node = node;
            this.freq = freq;
            this.used = used;
        }
        @Override
        public String toString() {
            return "(" + node + " " + freq + " " + used + ")";
        }
    }
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(second.freq, first.freq);
        }
    }
    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>(new custom_sort());
        PriorityQueue<Pair> pq1 = new PriorityQueue<>(new custom_sort());
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : barcodes) map.put(ele, map.getOrDefault(ele, 0) + 1);
        for (Map.Entry<Integer, Integer> curr : map.entrySet()) {
            int key = curr.getKey();
            int val = curr.getValue();
            pq.offer(new Pair(key, val, 0));
        }
        int res[] = new int[n];
        int k = 0;
        while (pq.size() > 0) {
            int key = pq.peek().node;
            int freq = pq.peek().freq;
            int used = pq.peek().used;
            pq.poll();
            res[k++] = key;
            if (pq1.size() > 0) pq.offer(pq1.poll());
            if (freq - 1 > 0) pq1.offer(new Pair(key, freq - 1, 1));
        }
        return res;
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

