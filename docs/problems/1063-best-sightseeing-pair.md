# 1063. Best Sightseeing Pair

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/best-sightseeing-pair/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1063-best-sightseeing-pair){ .md-button }

---

<h2><a href="https://leetcode.com/problems/best-sightseeing-pair">1063. Best Sightseeing Pair</a></h2><h3>Medium</h3><hr><p>You are given an integer array <code>values</code> where values[i] represents the value of the <code>i<sup>th</sup></code> sightseeing spot. Two sightseeing spots <code>i</code> and <code>j</code> have a <strong>distance</strong> <code>j - i</code> between them.</p>

<p>The score of a pair (<code>i &lt; j</code>) of sightseeing spots is <code>values[i] + values[j] + i - j</code>: the sum of the values of the sightseeing spots, minus the distance between them.</p>

<p>Return <em>the maximum score of a pair of sightseeing spots</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> values = [8,1,5,2,6]
<strong>Output:</strong> 11
<strong>Explanation:</strong> i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> values = [1,2]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= values.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= values[i] &lt;= 1000</code></li>
</ul>


---

## Solution

```java
class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) arr[i] = values[i] - i;
        int maxi = Integer.MIN_VALUE;
        int suff_maxi[] = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            maxi = Math.max(maxi, arr[i]);
            suff_maxi[i] = maxi;
        }
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            int current_sum = values[i] + i;
            current_sum += (suff_maxi[i + 1]);
            res = Math.max(res, current_sum);
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

