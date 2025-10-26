# 875. Longest Mountain In Array

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/longest-mountain-in-array/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0875-longest-mountain-in-array){ .md-button }

---

<h2><a href="https://leetcode.com/problems/longest-mountain-in-array">875. Longest Mountain in Array</a></h2><h3>Medium</h3><hr><p>You may recall that an array <code>arr</code> is a <strong>mountain array</strong> if and only if:</p>

<ul>
	<li><code>arr.length &gt;= 3</code></li>
	<li>There exists some index <code>i</code> (<strong>0-indexed</strong>) with <code>0 &lt; i &lt; arr.length - 1</code> such that:
	<ul>
		<li><code>arr[0] &lt; arr[1] &lt; ... &lt; arr[i - 1] &lt; arr[i]</code></li>
		<li><code>arr[i] &gt; arr[i + 1] &gt; ... &gt; arr[arr.length - 1]</code></li>
	</ul>
	</li>
</ul>

<p>Given an integer array <code>arr</code>, return <em>the length of the longest subarray, which is a mountain</em>. Return <code>0</code> if there is no mountain subarray.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,1,4,7,3,2,5]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The largest mountain is [1,4,7,3,2] which has length 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,2,2]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no mountain.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>Can you solve it using only one pass?</li>
	<li>Can you solve it in <code>O(1)</code> space?</li>
</ul>


---

## Solution

```java
class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        if (n < 3)
            return 0;
        int count[] = new int[n];
        int cnt = 1;
        for (int i = 1; i < n; i++) {
           if (arr[i] > arr[i - 1]) cnt++;
           else cnt = 1;
           count[i] += cnt; 
        }  
        cnt = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) cnt++;
            else cnt = 1;
            count[i] += cnt;
        }
        int maxi = 0;
        for (int i = 1; i < n - 1; i++) {
            if (count[i] > 2 && arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                maxi = Math.max(maxi, count[i] - 1);
            }
        } 
        return maxi;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

