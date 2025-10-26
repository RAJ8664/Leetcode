# 1529. Max Difference You Can Get From Changing An Integer

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1529-max-difference-you-can-get-from-changing-an-integer){ .md-button }

---

<h2><a href="https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer">1529. Max Difference You Can Get From Changing an Integer</a></h2><h3>Medium</h3><hr><p>You are given an integer <code>num</code>. You will apply the following steps to <code>num</code> <strong>two</strong> separate times:</p>

<ul>
	<li>Pick a digit <code>x (0 &lt;= x &lt;= 9)</code>.</li>
	<li>Pick another digit <code>y (0 &lt;= y &lt;= 9)</code>. Note <code>y</code> can be equal to <code>x</code>.</li>
	<li>Replace all the occurrences of <code>x</code> in the decimal representation of <code>num</code> by <code>y</code>.</li>
</ul>

<p>Let <code>a</code> and <code>b</code> be the two results from applying the operation to <code>num</code> <em>independently</em>.</p>

<p>Return <em>the max difference</em> between <code>a</code> and <code>b</code>.</p>

<p>Note that neither <code>a</code> nor <code>b</code> may have any leading zeros, and <strong>must not</strong> be 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 555
<strong>Output:</strong> 888
<strong>Explanation:</strong> The first time pick x = 5 and y = 9 and store the new integer in a.
The second time pick x = 5 and y = 1 and store the new integer in b.
We have now a = 999 and b = 111 and max difference = 888
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 9
<strong>Output:</strong> 8
<strong>Explanation:</strong> The first time pick x = 9 and y = 9 and store the new integer in a.
The second time pick x = 9 and y = 1 and store the new integer in b.
We have now a = 9 and b = 1 and max difference = 8
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>8</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int maxDiff(int num) {
        String  s = Integer.toString(num);
        int maxi = 0, mini = 0;
        int maxi_ind = -1, mini_ind = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '9') {
                maxi_ind = i;
                break;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (maxi_ind == -1)
                maxi = maxi * 10 + (ch - '0');
            else if (ch == s.charAt(maxi_ind))
                maxi = maxi * 10 + 9;
            else
                maxi = maxi * 10 + ch - '0';
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '1' && s.charAt(i) != '0') {
                mini_ind = i;
                break;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (mini_ind == -1)
                mini = mini * 10 + (ch - '0');
            else if (ch == s.charAt(mini_ind) && mini_ind == 0)
                mini = mini * 10 + 1;
            else if (ch == s.charAt(mini_ind) && mini_ind != 0)
                mini = mini * 10 + 0;
            else
                mini = mini * 10 + ch - '0';
        }
        return maxi - mini;
    }
}


```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

