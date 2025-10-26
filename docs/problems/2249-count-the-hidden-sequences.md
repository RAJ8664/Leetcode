# 2249. Count The Hidden Sequences

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/count-the-hidden-sequences/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2249-count-the-hidden-sequences){ .md-button }

---

<h2><a href="https://leetcode.com/problems/count-the-hidden-sequences">2249. Count the Hidden Sequences</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> array of <code>n</code> integers <code>differences</code>, which describes the <strong>differences </strong>between each pair of <strong>consecutive </strong>integers of a <strong>hidden</strong> sequence of length <code>(n + 1)</code>. More formally, call the hidden sequence <code>hidden</code>, then we have that <code>differences[i] = hidden[i + 1] - hidden[i]</code>.</p>

<p>You are further given two integers <code>lower</code> and <code>upper</code> that describe the <strong>inclusive</strong> range of values <code>[lower, upper]</code> that the hidden sequence can contain.</p>

<ul>
	<li>For example, given <code>differences = [1, -3, 4]</code>, <code>lower = 1</code>, <code>upper = 6</code>, the hidden sequence is a sequence of length <code>4</code> whose elements are in between <code>1</code> and <code>6</code> (<strong>inclusive</strong>).

	<ul>
		<li><code>[3, 4, 1, 5]</code> and <code>[4, 5, 2, 6]</code> are possible hidden sequences.</li>
		<li><code>[5, 6, 3, 7]</code> is not possible since it contains an element greater than <code>6</code>.</li>
		<li><code>[1, 2, 3, 4]</code> is not possible since the differences are not correct.</li>
	</ul>
	</li>
</ul>

<p>Return <em>the number of <strong>possible</strong> hidden sequences there are.</em> If there are no possible sequences, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> differences = [1,-3,4], lower = 1, upper = 6
<strong>Output:</strong> 2
<strong>Explanation:</strong> The possible hidden sequences are:
- [3, 4, 1, 5]
- [4, 5, 2, 6]
Thus, we return 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> differences = [3,-4,5,1,-2], lower = -4, upper = 5
<strong>Output:</strong> 4
<strong>Explanation:</strong> The possible hidden sequences are:
- [-3, 0, -4, 1, 2, 0]
- [-2, 1, -3, 2, 3, 1]
- [-1, 2, -2, 3, 4, 2]
- [0, 3, -1, 4, 5, 3]
Thus, we return 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> differences = [4,-7,2], lower = 3, upper = 6
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no possible hidden sequences. Thus, we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == differences.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= differences[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= lower &lt;= upper &lt;= 10<sup>5</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int numberOfArrays(int[] arr, int lower, int upper) {
        int n = arr.length;
        int pref[] = new int[n];
        pref[0] = arr[0];
        for (int i = 1; i < n; i++) pref[i] = pref[i - 1] + arr[i];
        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            a = Math.min(a, pref[i]);
            b = Math.max(b, pref[i]);
            if (b - a > upper - lower) return 0;
        }
        return (upper - lower) - (b - a) + 1;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

