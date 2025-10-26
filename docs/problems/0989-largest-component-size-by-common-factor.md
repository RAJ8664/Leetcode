# 989. Largest Component Size By Common Factor

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/largest-component-size-by-common-factor/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0989-largest-component-size-by-common-factor){ .md-button }

---

<h2><a href="https://leetcode.com/problems/largest-component-size-by-common-factor">989. Largest Component Size by Common Factor</a></h2><h3>Hard</h3><hr><p>You are given an integer array of unique positive integers <code>nums</code>. Consider the following graph:</p>

<ul>
	<li>There are <code>nums.length</code> nodes, labeled <code>nums[0]</code> to <code>nums[nums.length - 1]</code>,</li>
	<li>There is an undirected edge between <code>nums[i]</code> and <code>nums[j]</code> if <code>nums[i]</code> and <code>nums[j]</code> share a common factor greater than <code>1</code>.</li>
</ul>

<p>Return <em>the size of the largest connected component in the graph</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/01/ex1.png" style="width: 500px; height: 97px;" />
<pre>
<strong>Input:</strong> nums = [4,6,15,35]
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/01/ex2.png" style="width: 500px; height: 85px;" />
<pre>
<strong>Input:</strong> nums = [20,50,9,63]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/01/ex3.png" style="width: 500px; height: 260px;" />
<pre>
<strong>Input:</strong> nums = [2,3,6,7,4,12,21,39]
<strong>Output:</strong> 8
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li>All the values of <code>nums</code> are <strong>unique</strong>.</li>
</ul>


---

## Solution

```java
class Solution {
    private HashMap<Integer, ArrayList<Integer>> map;
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
        public void unite(int u, int v) {
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
    public int largestComponentSize(int[] nums) {
        int n = nums.length;
        map = new HashMap<>();
        int maxi_ele = 0; for (int ele : nums) maxi_ele = Math.max(maxi_ele, ele);
        DSU dsu = new DSU(maxi_ele + 1);
        for (int i = 0; i < n; i++) compute_div(nums[i]);
        for (Map.Entry<Integer, ArrayList<Integer>> curr : map.entrySet()) {
            ArrayList<Integer> res = curr.getValue();
            for (int i = 0; i < res.size() - 1; i++) {
                int u = res.get(i), v = res.get(i + 1);
                dsu.unite(u, v);
            }
        }
        int maxi = 0;
        for (int i = 0; i < n; i++) {
            int current = nums[i];
            if (dsu.find_parent(current) == current) maxi = Math.max(maxi, dsu.size[current]);
        }
        return maxi;
    }
    private void compute_div(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                if (!map.containsKey(i)) map.put(i, new ArrayList<>());
                map.get(i).add(n);
                if (n / i != i) {
                    if (!map.containsKey(n / i)) map.put(n / i, new ArrayList<>());
                    map.get(n / i).add(n);
                }
            }
        }
        if (!map.containsKey(n)) map.put(n, new ArrayList<>());
        map.get(n).add(n);
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

