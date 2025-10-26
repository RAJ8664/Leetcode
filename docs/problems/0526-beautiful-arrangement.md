# 526. Beautiful Arrangement

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/beautiful-arrangement/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0526-beautiful-arrangement){ .md-button }

---

<h2><a href="https://leetcode.com/problems/beautiful-arrangement">526. Beautiful Arrangement</a></h2><h3>Medium</h3><hr><p>Suppose you have <code>n</code> integers labeled <code>1</code> through <code>n</code>. A permutation of those <code>n</code> integers <code>perm</code> (<strong>1-indexed</strong>) is considered a <strong>beautiful arrangement</strong> if for every <code>i</code> (<code>1 &lt;= i &lt;= n</code>), <strong>either</strong> of the following is true:</p>

<ul>
	<li><code>perm[i]</code> is divisible by <code>i</code>.</li>
	<li><code>i</code> is divisible by <code>perm[i]</code>.</li>
</ul>

<p>Given an integer <code>n</code>, return <em>the <strong>number</strong> of the <strong>beautiful arrangements</strong> that you can construct</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 2
<b>Explanation:</b> 
The first beautiful arrangement is [1,2]:
    - perm[1] = 1 is divisible by i = 1
    - perm[2] = 2 is divisible by i = 2
The second beautiful arrangement is [2,1]:
    - perm[1] = 2 is divisible by i = 1
    - i = 2 is divisible by perm[2] = 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 15</code></li>
</ul>


---

## Solution

```java
class Solution {
    private int ans;
    public int countArrangement(int n) {
        ans = 0;
        int vis[] = new int[n + 1];
        solve(vis, n, 1);
        return ans;
    }
    private void solve(int vis[], int n, int curr_num) {
        if (curr_num == n + 1) {
            ans++;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (vis[i] == 0) {
                if (curr_num % i == 0 || i % curr_num == 0) {
                    vis[i] = 1;
                    solve(vis, n, curr_num + 1);
                    vis[i] = 0;
                }
            }
        }
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

