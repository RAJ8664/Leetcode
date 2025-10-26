# 3218. Find Number Of Coins To Place In Tree Nodes

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-number-of-coins-to-place-in-tree-nodes/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3218-find-number-of-coins-to-place-in-tree-nodes){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-number-of-coins-to-place-in-tree-nodes">3218. Find Number of Coins to Place in Tree Nodes</a></h2><h3>Hard</h3><hr><p>You are given an <strong>undirected</strong> tree with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>, and rooted at node <code>0</code>. You are given a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>You are also given a <strong>0-indexed</strong> integer array <code>cost</code> of length <code>n</code>, where <code>cost[i]</code> is the <strong>cost</strong> assigned to the <code>i<sup>th</sup></code> node.</p>

<p>You need to place some coins on every node of the tree. The number of coins to be placed at node <code>i</code> can be calculated as:</p>

<ul>
	<li>If size of the subtree of node <code>i</code> is less than <code>3</code>, place <code>1</code> coin.</li>
	<li>Otherwise, place an amount of coins equal to the <strong>maximum</strong> product of cost values assigned to <code>3</code> distinct nodes in the subtree of node <code>i</code>. If this product is <strong>negative</strong>, place <code>0</code> coins.</li>
</ul>

<p>Return <em>an array </em><code>coin</code><em> of size </em><code>n</code><em> such that </em><code>coin[i]</code><em> is the number of coins placed at node </em><code>i</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/11/09/screenshot-2023-11-10-012641.png" style="width: 600px; height: 233px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[0,2],[0,3],[0,4],[0,5]], cost = [1,2,3,4,5,6]
<strong>Output:</strong> [120,1,1,1,1,1]
<strong>Explanation:</strong> For node 0 place 6 * 5 * 4 = 120 coins. All other nodes are leaves with subtree of size 1, place 1 coin on each of them.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/11/09/screenshot-2023-11-10-012614.png" style="width: 800px; height: 374px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[0,2],[1,3],[1,4],[1,5],[2,6],[2,7],[2,8]], cost = [1,4,2,3,5,7,8,-4,2]
<strong>Output:</strong> [280,140,32,1,1,1,1,1,1]
<strong>Explanation:</strong> The coins placed on each node are:
- Place 8 * 7 * 5 = 280 coins on node 0.
- Place 7 * 5 * 4 = 140 coins on node 1.
- Place 8 * 2 * 2 = 32 coins on node 2.
- All other nodes are leaves with subtree of size 1, place 1 coin on each of them.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/11/09/screenshot-2023-11-10-012513.png" style="width: 300px; height: 277px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[0,2]], cost = [1,2,-2]
<strong>Output:</strong> [0,1,1]
<strong>Explanation:</strong> Node 1 and 2 are leaves with subtree of size 1, place 1 coin on each of them. For node 0 the only possible product of cost is 2 * 1 * -2 = -4. Hence place 0 coins on node 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>cost.length == n</code></li>
	<li><code>1 &lt;= |cost[i]| &lt;= 10<sup>4</sup></code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
</ul>


---

## Solution

```java
class Solution {
    private ArrayList<ArrayList<Integer>> adj;
    private int val[];
    private long res[];
    private int dp[][];
    private int subtree[];
    private MultiSet ms1[];
    private MultiSet ms2[];
    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
        adj = new ArrayList<>();
        for (int i = 0; i < cost.length + 2; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        val = new int[n];
        res = new long[n];
        subtree = new int[n];
        dp = new int[n][3];
        ms1 = new MultiSet[n];
        ms2 = new MultiSet[n];
        for (int i = 0; i < n; i++) {
            ms1[i] = new MultiSet();
            ms2[i] = new MultiSet();
        }

        for (int i = 0; i < cost.length; i++)
            val[i] = cost[i];
        
        dfs(0, -1);
        return res;
    }

    private void dfs(int u, int par) {
        if (adj.get(u).size() == 1 && u != 0) {
            subtree[u] = 1;
            res[u] = 1L;
            if (val[u] < 0) ms1[u].add(val[u]);
            else ms2[u].add(val[u]);
            return;
        }
        for (int v : adj.get(u)) {
            if (v != par) {
                dfs(v, u);
            }
        }
        for (int v : adj.get(u)) {
            if (v != par) {
                subtree[u] += subtree[v];
            }
        }
        subtree[u]++;
        if (subtree[u] < 3) {
            res[u] = 1;
            if (val[u] < 0) ms1[u].add(val[u]);
            else ms2[u].add(val[u]);
            for (int v : adj.get(u)) {
                if (v != par) {
                    ArrayList<Integer> m1 = ms1[v].getElements();
                    ArrayList<Integer> m2 = ms2[v].getElements();
                    for (int ele : m1) {
                        ms1[u].add(ele);
                        if (ms1[u].getSize() > 3) {
                            ms1[u].removeGreatest();
                        }
                    }
                    for (int ele : m2) {
                        ms2[u].add(ele);
                        if (ms2[u].getSize() > 3) {
                            ms2[u].removeSmallest();
                        }
                    }
                }
            }
        }
        else {
            if (val[u] < 0) ms1[u].add(val[u]);
            else ms2[u].add(val[u]);
            for (int v : adj.get(u)) {
                if (v != par) {
                    ArrayList<Integer> m1 = ms1[v].getElements();
                    ArrayList<Integer> m2 = ms2[v].getElements();
                    for (int ele : m1) {
                        ms1[u].add(ele);
                        if (ms1[u].getSize() > 3) {
                            ms1[u].removeGreatest();
                        }
                    }
                    for (int ele : m2) {
                        ms2[u].add(ele);
                        if (ms2[u].getSize() > 3) {
                            ms2[u].removeSmallest();
                        }
                    }
                }
            }
            long prod1 = 0, prod2 = 0;
            if (ms1[u].getSize() >= 2 && ms2[u].getSize() >= 1) {
                int first = ms1[u].getFirst(), second = ms1[u].getFirst(), third = ms2[u].getLast();
                prod1 = first * 1L * second * 1L * third;
                ms1[u].add(first); ms1[u].add(second); ms2[u].add(third);
            }

            if (ms2[u].getSize() >= 3) {
                int first = ms2[u].getLast(), second = ms2[u].getLast(), third = ms2[u].getLast();
                prod2 = first * 1L * second * 1L * third;
                ms2[u].add(first); ms2[u].add(second); ms2[u].add(third);
            }
            res[u] = Math.max(0, Math.max(prod1, prod2));
        }
    }

    static class MultiSet {
        TreeSet<Integer> set;
        HashMap<Integer, Integer> map;
        int size;
        public MultiSet() {
            set = new TreeSet<>();
            map = new HashMap<>();
            size = 0;
        }
        public void add(int val) {
            set.add(val);
            map.put(val, map.getOrDefault(val, 0) + 1);
            size++;
        }
        public void remove(int val) {
            map.put(val, map.getOrDefault(val, 0) -1);
            if (map.getOrDefault(val, 0) == 0) set.remove(val);
            size--;    
        }
        public void removeGreatest() {
            int toRemove = set.last();
            map.put(toRemove, map.getOrDefault(toRemove, 0) -1);
            if (map.getOrDefault(toRemove, 0) == 0) set.remove(toRemove);
            size--;
        }
        public void removeSmallest() {
            int toRemove = set.first();
            map.put(toRemove, map.getOrDefault(toRemove, 0) -1);
            if (map.getOrDefault(toRemove, 0) == 0) set.remove(toRemove);
            size--;
        }
        public int getSize() {
            int res = 0;
            for (Map.Entry<Integer, Integer> curr : map.entrySet()) {
                res += curr.getValue();
            }
            return res;
        }
        public int getFirst() {
            int res = set.first();
            map.put(res, map.getOrDefault(res, 0) -1);
            if (map.getOrDefault(res, 0) == 0) set.remove(res);
            return res;
        }
        public int getLast() {
            int res = set.last();
            map.put(res, map.getOrDefault(res, 0) -1);
            if (map.getOrDefault(res, 0) == 0) set.remove(res);
            return res;
        }
        public ArrayList<Integer> getElements() {
            ArrayList<Integer> res = new ArrayList<>();
            for (Map.Entry<Integer, Integer> curr : map.entrySet()) {
                int key = curr.getKey();
                int val = curr.getValue();
                for (int j = 0; j < val; j++) res.add(key);
            }
            return res;
        }
        public String toString() {
            String res = "";
            for (Map.Entry<Integer, Integer> curr : map.entrySet()) {
                int key = curr.getKey();
                int val = curr.getValue();
                for (int j = 0; j < val; j++) res += ":" + key;
            }
            return res;
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

