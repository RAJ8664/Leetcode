# 1500. Count Largest Group

!!! success "Difficulty: Easy"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/count-largest-group/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1500-count-largest-group){ .md-button }

---

<h2><a href="https://leetcode.com/problems/count-largest-group">1500. Count Largest Group</a></h2><h3>Easy</h3><hr><p>You are given an integer <code>n</code>.</p>

<p>Each number from <code>1</code> to <code>n</code> is grouped according to the sum of its digits.</p>

<p>Return <em>the number of groups that have the largest size</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 13
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
[1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9].
There are 4 groups with largest size.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 groups [1], [2] of size 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int countLargestGroup(int n) {
        int freq[] = new int[100];
        for (int i = 1; i <= n; i++) {
            freq[solve(i)]++;
        }
        int maxi = 0, count = 0;
        for (int ele : freq) maxi = Math.max(maxi, ele);
        for (int ele : freq) if (ele == maxi) count++;
        return count;
    }
    private int solve(int n) {
        int temp = n;
        int sum = 0;
        while (temp > 0) {
            sum += temp % 10;
            temp /= 10;
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

