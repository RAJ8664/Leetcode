# 1946. Minimum Absolute Sum Difference

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-absolute-sum-difference/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1946-minimum-absolute-sum-difference){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-absolute-sum-difference">1946. Minimum Absolute Sum Difference</a></h2><h3>Medium</h3><hr><p>You are given two positive integer arrays <code>nums1</code> and <code>nums2</code>, both of length <code>n</code>.</p>

<p>The <strong>absolute sum difference</strong> of arrays <code>nums1</code> and <code>nums2</code> is defined as the <strong>sum</strong> of <code>|nums1[i] - nums2[i]|</code> for each <code>0 &lt;= i &lt; n</code> (<strong>0-indexed</strong>).</p>

<p>You can replace <strong>at most one</strong> element of <code>nums1</code> with <strong>any</strong> other element in <code>nums1</code> to <strong>minimize</strong> the absolute sum difference.</p>

<p>Return the <em>minimum absolute sum difference <strong>after</strong> replacing at most one<strong> </strong>element in the array <code>nums1</code>.</em> Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><code>|x|</code> is defined as:</p>

<ul>
	<li><code>x</code> if <code>x &gt;= 0</code>, or</li>
	<li><code>-x</code> if <code>x &lt; 0</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,7,5], nums2 = [2,3,5]
<strong>Output:</strong> 3
<strong>Explanation: </strong>There are two possible optimal solutions:
- Replace the second element with the first: [1,<u><strong>7</strong></u>,5] =&gt; [1,<u><strong>1</strong></u>,5], or
- Replace the second element with the third: [1,<u><strong>7</strong></u>,5] =&gt; [1,<u><strong>5</strong></u>,5].
Both will yield an absolute sum difference of <code>|1-2| + (|1-3| or |5-3|) + |5-5| = </code>3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
<strong>Output:</strong> 0
<strong>Explanation: </strong>nums1 is equal to nums2 so no replacement is needed. This will result in an 
absolute sum difference of 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
<strong>Output:</strong> 20
<strong>Explanation: </strong>Replace the first element with the second: [<u><strong>1</strong></u>,10,4,4,2,7] =&gt; [<u><strong>10</strong></u>,10,4,4,2,7].
This yields an absolute sum difference of <code>|10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20</code>
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length</code></li>
	<li><code>n == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    private static int mod = (int)(1e9 + 7);
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) set.add(nums1[i]);
        long current_res = 0, current_mini = 0;
        for (int i = 0; i < n; i++) current_res += Math.abs(nums1[i] - nums2[i]);
        current_mini = current_res;
        for (int i = 0; i < n; i++) {
            Integer ceil = set.ceiling(nums2[i]);
            Integer floor = set.floor(nums2[i]);
            if (ceil != null) {
                long temp = current_res;
                temp -= Math.abs(nums1[i] - nums2[i]);
                temp += Math.abs(nums2[i] - ceil);
                current_mini = Math.min(current_mini, temp);
            }
            if (floor != null) {
                long temp = current_res;
                temp -= Math.abs(nums1[i] - nums2[i]);
                temp += Math.abs(nums2[i] - floor);
                current_mini = Math.min(current_mini, temp);
            }
        }
        return (int)(current_mini % mod);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

