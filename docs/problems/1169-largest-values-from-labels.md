# 1169. Largest Values From Labels

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/largest-values-from-labels/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1169-largest-values-from-labels){ .md-button }

---

<h2><a href="https://leetcode.com/problems/largest-values-from-labels">1169. Largest Values From Labels</a></h2><h3>Medium</h3><hr><p>You are given <code>n</code> item&#39;s value and label as two integer arrays <code>values</code> and <code>labels</code>. You are also given two integers <code>numWanted</code> and <code>useLimit</code>.</p>

<p>Your task is to find a subset of items with the <strong>maximum sum</strong> of their values such that:</p>

<ul>
	<li>The number of items is <strong>at most</strong> <code>numWanted</code>.</li>
	<li>The number of items with the same label is <strong>at most</strong> <code>useLimit</code>.</li>
</ul>

<p>Return the maximum sum.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>The subset chosen is the first, third, and fifth items with the sum of values 5 + 3 + 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>The subset chosen is the first, second, and third items with the sum of values 5 + 4 + 3.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>

<p>The subset chosen is the first and fourth items with the sum of values 9 + 7.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == values.length == labels.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= values[i], labels[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= numWanted, useLimit &lt;= n</code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int first, second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
        @Override
        public String toString() {
            return "(" + first + " " + second + ")";
        }
    }
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(second.first, first.first);
        }
    }
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        ArrayList<Pair> res = new ArrayList<>(); 
        for (int i = 0; i < n; i++) res.add(new Pair(values[i], labels[i]));
        Collections.sort(res, new custom_sort());
        int sum = 0, count = 0;
        int freq[] = new int[(int)(1e5 + 1)];
        for (int i = 0; i < n; i++) {
            if (count == numWanted) break;
            Pair current = res.get(i);
            if (freq[current.second] == useLimit) continue;
            sum += current.first;
            count++;
            freq[current.second]++;
        }
        return sum;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

