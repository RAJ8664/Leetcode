# 1032. Satisfiability Of Equality Equations

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/satisfiability-of-equality-equations/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1032-satisfiability-of-equality-equations){ .md-button }

---

<h2><a href="https://leetcode.com/problems/satisfiability-of-equality-equations">1032. Satisfiability of Equality Equations</a></h2><h3>Medium</h3><hr><p>You are given an array of strings <code>equations</code> that represent relationships between variables where each string <code>equations[i]</code> is of length <code>4</code> and takes one of two different forms: <code>&quot;x<sub>i</sub>==y<sub>i</sub>&quot;</code> or <code>&quot;x<sub>i</sub>!=y<sub>i</sub>&quot;</code>.Here, <code>x<sub>i</sub></code> and <code>y<sub>i</sub></code> are lowercase letters (not necessarily different) that represent one-letter variable names.</p>

<p>Return <code>true</code><em> if it is possible to assign integers to variable names so as to satisfy all the given equations, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> equations = [&quot;a==b&quot;,&quot;b!=a&quot;]
<strong>Output:</strong> false
<strong>Explanation:</strong> If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
There is no way to assign the variables to satisfy both equations.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> equations = [&quot;b==a&quot;,&quot;a==b&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong> We could assign a = 1 and b = 1 to satisfy both equations.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= equations.length &lt;= 500</code></li>
	<li><code>equations[i].length == 4</code></li>
	<li><code>equations[i][0]</code> is a lowercase letter.</li>
	<li><code>equations[i][1]</code> is either <code>&#39;=&#39;</code> or <code>&#39;!&#39;</code>.</li>
	<li><code>equations[i][2]</code> is <code>&#39;=&#39;</code>.</li>
	<li><code>equations[i][3]</code> is a lowercase letter.</li>
</ul>


---

## Solution

```java
class Solution {
    static class DSU {
        private int parent[];
        private int size[];
        public DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find_parent(int u) {
            if (parent[u] == u) return parent[u] = u;
            return parent[u] = find_parent(parent[u]);
        }
        public void unite(int u , int v) {
            u = find_parent(u);
            v = find_parent(v);
            if (u == v) return;
            if (size[v] > size[u]) {
                int temp = u;
                u = v;
                v = temp;
            }
            parent[v] = u;
            size[u] += size[v];
        }
    }
    public boolean equationsPossible(String[] equations) {
        int n = equations.length;
        DSU dsu = new DSU(30);
        for (int i = 0; i < n; i++) {
            int u = equations[i].charAt(0) - 'a';
            int v = equations[i].charAt(3) - 'a';
            char current = equations[i].charAt(1);
            if (current == '=') dsu.unite(u, v);
        }
        for (int i = 0; i < n; i++) {
            int u = equations[i].charAt(0) - 'a';
            int v = equations[i].charAt(3) - 'a';
            char current = equations[i].charAt(1);
            if (current == '!') {
                u = dsu.find_parent(u);
                v = dsu.find_parent(v);
                if (u == v) return false;
            }
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

