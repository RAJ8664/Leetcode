# 3555. Final Array State After K Multiplication Operations I

!!! success "Difficulty: Easy"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3555-final-array-state-after-k-multiplication-operations-i){ .md-button }

---

<h2><a href="https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i">3555. Final Array State After K Multiplication Operations I</a></h2><h3>Easy</h3><hr><p>You are given an integer array <code>nums</code>, an integer <code>k</code>, and an integer <code>multiplier</code>.</p>

<p>You need to perform <code>k</code> operations on <code>nums</code>. In each operation:</p>

<ul>
	<li>Find the <strong>minimum</strong> value <code>x</code> in <code>nums</code>. If there are multiple occurrences of the minimum value, select the one that appears <strong>first</strong>.</li>
	<li>Replace the selected minimum value <code>x</code> with <code>x * multiplier</code>.</li>
</ul>

<p>Return an integer array denoting the <em>final state</em> of <code>nums</code> after performing all <code>k</code> operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,3,5,6], k = 5, multiplier = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[8,4,6,5,6]</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<tbody>
		<tr>
			<th>Operation</th>
			<th>Result</th>
		</tr>
		<tr>
			<td>After operation 1</td>
			<td>[2, 2, 3, 5, 6]</td>
		</tr>
		<tr>
			<td>After operation 2</td>
			<td>[4, 2, 3, 5, 6]</td>
		</tr>
		<tr>
			<td>After operation 3</td>
			<td>[4, 4, 3, 5, 6]</td>
		</tr>
		<tr>
			<td>After operation 4</td>
			<td>[4, 4, 6, 5, 6]</td>
		</tr>
		<tr>
			<td>After operation 5</td>
			<td>[8, 4, 6, 5, 6]</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2], k = 3, multiplier = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[16,8]</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<tbody>
		<tr>
			<th>Operation</th>
			<th>Result</th>
		</tr>
		<tr>
			<td>After operation 1</td>
			<td>[4, 2]</td>
		</tr>
		<tr>
			<td>After operation 2</td>
			<td>[4, 8]</td>
		</tr>
		<tr>
			<td>After operation 3</td>
			<td>[16, 8]</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 10</code></li>
	<li><code>1 &lt;= multiplier &lt;= 5</code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int ind;
        int ele;
        public Pair(int ind, int ele) {
            this.ind = ind;
            this.ele = ele;
        }
        @Override
        public String toString() {
            return "(" + ind + " " + ele + ")";
        }
    }
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int op1 =  Integer.compare(first.ele, second.ele);
            if (op1 != 0) return op1;
            return Integer.compare(first.ind, second.ind);
        }
    }
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        int n = nums.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>(new custom_sort());
        for (int i = 0; i < n; i++) pq.offer(new Pair(i, nums[i]));
        while (k-->0) {
            Pair current = pq.poll();
            current.ele = current.ele * multiplier;
            pq.offer(current);
        }
        int res[] = new int[n];
        while (pq.size() > 0) {
            res[pq.peek().ind] = pq.peek().ele;
            pq.poll();
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

