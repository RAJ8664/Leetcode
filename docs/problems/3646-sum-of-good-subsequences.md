# 3646. Sum Of Good Subsequences


---

<h2><a href="https://leetcode.com/problems/sum-of-good-subsequences">3646. Sum of Good Subsequences</a></h2><h3>Hard</h3><hr><p>You are given an integer array <code>nums</code>. A <strong>good subsequence</strong> is defined as a subsequence of <code>nums</code> where the absolute difference between any <strong>two</strong> consecutive elements in the subsequence is <strong>exactly</strong> 1.</p>

<p>A <strong>subsequence</strong> is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>Return the <strong>sum</strong> of all <em>possible</em> <strong>good subsequences</strong> of <code>nums</code>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note </strong>that a subsequence of size 1 is considered good by definition.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Good subsequences are: <code>[1]</code>, <code>[2]</code>, <code>[1]</code>, <code>[1,2]</code>, <code>[2,1]</code>, <code>[1,2,1]</code>.</li>
	<li>The sum of elements in these subsequences is 14.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">40</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Good subsequences are: <code>[3]</code>, <code>[4]</code>, <code>[5]</code>, <code>[3,4]</code>, <code>[4,5]</code>, <code>[3,4,5]</code>.</li>
	<li>The sum of elements in these subsequences is 40.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>


## Solution

```java
class Solution {
    private int mod = (int)(1e9 + 7);
    public int sumOfGoodSubsequences(int[] nums) {
        HashMap<Long, long[]> dp = new HashMap<>();
        long res = 0;
        for (long i : nums) {
            long sum = i, count = 1;
            if (dp.containsKey(i - 1)) {
                long[] temp = dp.get(i - 1);
                sum = (sum + temp[1] * i) % mod;
                sum = (sum + temp[0]) % mod;
                count = (count + temp[1]) % mod;
            }
            if (dp.containsKey(i + 1)) {
                long[] temp = dp.get(i + 1);
                sum = (sum + temp[1] * i) % mod;
                sum = (sum + temp[0]) % mod;
                count = (count + temp[1]) % mod;
            }
            res = (res + sum) % mod;
            long[] temp = dp.getOrDefault(i, new long[] {0, 0});
            temp[0] = (temp[0] + sum) % mod;
            temp[1] = (temp[1] + count) % mod;
            dp.put(i, temp);
        }
        return (int)res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

