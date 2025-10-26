# 1426. Find N Unique Integers Sum Up To Zero

!!! success "Difficulty: Easy"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1426-find-n-unique-integers-sum-up-to-zero){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero">1426. Find N Unique Integers Sum up to Zero</a></h2><h3>Easy</h3><hr><p>Given an integer <code>n</code>, return <strong>any</strong> array containing <code>n</code> <strong>unique</strong> integers such that they add up to <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> [-7,-1,1,3,4]
<strong>Explanation:</strong> These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> [-1,0,1]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>


---

## Solution

```java
class Solution {
    public int[] sumZero(int n) {
        int res[] = new int[n];
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            res[i] = (i + 1) * -1;
            sum += (i + 1);
        } 
        res[n - 1] = sum;
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

