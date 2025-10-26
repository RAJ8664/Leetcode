# 2174. Next Greater Numerically Balanced Number

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/next-greater-numerically-balanced-number/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2174-next-greater-numerically-balanced-number){ .md-button }

---

<h2><a href="https://leetcode.com/problems/next-greater-numerically-balanced-number">2174. Next Greater Numerically Balanced Number</a></h2><h3>Medium</h3><hr><p>An integer <code>x</code> is <strong>numerically balanced</strong> if for every digit <code>d</code> in the number <code>x</code>, there are <strong>exactly</strong> <code>d</code> occurrences of that digit in <code>x</code>.</p>

<p>Given an integer <code>n</code>, return <em>the <strong>smallest numerically balanced</strong> number <strong>strictly greater</strong> than </em><code>n</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 22
<strong>Explanation:</strong> 
22 is numerically balanced since:
- The digit 2 occurs 2 times. 
It is also the smallest numerically balanced number strictly greater than 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1000
<strong>Output:</strong> 1333
<strong>Explanation:</strong> 
1333 is numerically balanced since:
- The digit 1 occurs 1 time.
- The digit 3 occurs 3 times. 
It is also the smallest numerically balanced number strictly greater than 1000.
Note that 1022 cannot be the answer because 0 appeared more than 0 times.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3000
<strong>Output:</strong> 3133
<strong>Explanation:</strong> 
3133 is numerically balanced since:
- The digit 1 occurs 1 time.
- The digit 3 occurs 3 times.
It is also the smallest numerically balanced number strictly greater than 3000.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>6</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int nextBeautifulNumber(int n) {
        n++;
        while (true) {
            if (check(n) == true)
                return n;
            n++;
        }
    }
    private boolean check(int n) {
        int freq[] = new int[10];
        int temp = n;
        while (temp > 0) {
            freq[temp % 10]++;
            temp /= 10;
        }
        for (int i = 0; i < 10; i++) {
            if (freq[i] == 0)
                continue;
            if (freq[i] != i)
                return false;
        }
        return true;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

