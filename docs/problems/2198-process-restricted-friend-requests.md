# 2198. Process Restricted Friend Requests

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/process-restricted-friend-requests/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2198-process-restricted-friend-requests){ .md-button }

---

<h2><a href="https://leetcode.com/problems/process-restricted-friend-requests">2198. Process Restricted Friend Requests</a></h2><h3>Hard</h3><hr><p>You are given an integer <code>n</code> indicating the number of people in a network. Each person is labeled from <code>0</code> to <code>n - 1</code>.</p>

<p>You are also given a <strong>0-indexed</strong> 2D integer array <code>restrictions</code>, where <code>restrictions[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> means that person <code>x<sub>i</sub></code> and person <code>y<sub>i</sub></code> <strong>cannot </strong>become <strong>friends</strong>,<strong> </strong>either <strong>directly</strong> or <strong>indirectly</strong> through other people.</p>

<p>Initially, no one is friends with each other. You are given a list of friend requests as a <strong>0-indexed</strong> 2D integer array <code>requests</code>, where <code>requests[j] = [u<sub>j</sub>, v<sub>j</sub>]</code> is a friend request between person <code>u<sub>j</sub></code> and person <code>v<sub>j</sub></code>.</p>

<p>A friend request is <strong>successful </strong>if <code>u<sub>j</sub></code> and <code>v<sub>j</sub></code> can be <strong>friends</strong>. Each friend request is processed in the given order (i.e., <code>requests[j]</code> occurs before <code>requests[j + 1]</code>), and upon a successful request, <code>u<sub>j</sub></code> and <code>v<sub>j</sub></code> <strong>become direct friends</strong> for all future friend requests.</p>

<p>Return <em>a <strong>boolean array</strong> </em><code>result</code>,<em> where each </em><code>result[j]</code><em> is </em><code>true</code><em> if the </em><code>j<sup>th</sup></code><em> friend request is <strong>successful</strong> or </em><code>false</code><em> if it is not</em>.</p>

<p><strong>Note:</strong> If <code>u<sub>j</sub></code> and <code>v<sub>j</sub></code> are already direct friends, the request is still <strong>successful</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, restrictions = [[0,1]], requests = [[0,2],[2,1]]
<strong>Output:</strong> [true,false]
<strong>Explanation:
</strong>Request 0: Person 0 and person 2 can be friends, so they become direct friends. 
Request 1: Person 2 and person 1 cannot be friends since person 0 and person 1 would be indirect friends (1--2--0).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, restrictions = [[0,1]], requests = [[1,2],[0,2]]
<strong>Output:</strong> [true,false]
<strong>Explanation:
</strong>Request 0: Person 1 and person 2 can be friends, so they become direct friends.
Request 1: Person 0 and person 2 cannot be friends since person 0 and person 1 would be indirect friends (0--2--1).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 5, restrictions = [[0,1],[1,2],[2,3]], requests = [[0,4],[1,2],[3,1],[3,4]]
<strong>Output:</strong> [true,false,true,false]
<strong>Explanation:
</strong>Request 0: Person 0 and person 4 can be friends, so they become direct friends.
Request 1: Person 1 and person 2 cannot be friends since they are directly restricted.
Request 2: Person 3 and person 1 can be friends, so they become direct friends.
Request 3: Person 3 and person 4 cannot be friends since person 0 and person 1 would be indirect friends (0--4--3--1).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= restrictions.length &lt;= 1000</code></li>
	<li><code>restrictions[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>x<sub>i</sub> != y<sub>i</sub></code></li>
	<li><code>1 &lt;= requests.length &lt;= 1000</code></li>
	<li><code>requests[j].length == 2</code></li>
	<li><code>0 &lt;= u<sub>j</sub>, v<sub>j</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>j</sub> != v<sub>j</sub></code></li>
</ul>


---

## Solution

```java
class Solution {
    static class DSU {
        int parent[], size[];
        public DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public DSU(DSU other) {
            this.parent = other.parent.clone();
            this.size = other.size.clone();
        }
        public void merge(int u, int v) {
            u = Leader(u);
            v = Leader(v);
            if (u == v)
                return;
            if (size[v] > size[u]) {
                int temp = u;
                u = v;
                v = temp;
            }
            size[u] += size[v];
            parent[v] = u;
        }
        public int Leader(int u) {
            if (u == parent[u])
                return u;
            return parent[u] = Leader(parent[u]);
        }
    }
    static class Pair {
        int u, v;
        public Pair(int u, int v) {
            this.u = u;
            this.v = v;
        }
        @Override
        public String toString() {
            return "(" + u + " " + v + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Pair current = (Pair)(obj);
            return current.u == u && current.v == v;
        }
        @Override
        public int hashCode() {
            return Objects.hash(u, v);
        }
    }
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        DSU dsu = new DSU(n + 1);
        DSU tempDSU = new DSU(n + 1);
        HashSet<Pair> set = new HashSet<>();
        for (int rest[] : restrictions) {
            set.add(new Pair(rest[0], rest[1])); 
        }

        boolean res[] = new boolean[requests.length];
        for (int i = 0; i < requests.length; i++) {
            int u = requests[i][0], v = requests[i][1];
            boolean flag = true;

            //They can never be friends
            if (set.contains(new Pair(u, v)) || set.contains(new Pair(v, u))) {
                res[i] = false;
                continue;
            }

            //if i make them friend do they violate the condition;
            if (dsu.Leader(u) == dsu.Leader(v)) {
                //they are friends;
                res[i] = true;
                continue;
            }
            tempDSU = new DSU(dsu);
            tempDSU.merge(u, v);
            for (Pair x : set) {
                int u1 = x.u, v1 = x.v;
                if (tempDSU.Leader(u1) == tempDSU.Leader(v1)) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                res[i] = true;
                dsu.merge(u, v); 
            }
            else {
                res[i] = false;
            }
        }
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

