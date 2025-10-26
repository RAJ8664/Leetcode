# 1227. Number Of Equivalent Domino Pairs

!!! success "Difficulty: Easy"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/number-of-equivalent-domino-pairs/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1227-number-of-equivalent-domino-pairs){ .md-button }

---

<h2><a href="https://leetcode.com/problems/number-of-equivalent-domino-pairs">1227. Number of Equivalent Domino Pairs</a></h2><h3>Easy</h3><hr><p>Given a list of <code>dominoes</code>, <code>dominoes[i] = [a, b]</code> is <strong>equivalent to</strong> <code>dominoes[j] = [c, d]</code> if and only if either (<code>a == c</code> and <code>b == d</code>), or (<code>a == d</code> and <code>b == c</code>) - that is, one domino can be rotated to be equal to another domino.</p>

<p>Return <em>the number of pairs </em><code>(i, j)</code><em> for which </em><code>0 &lt;= i &lt; j &lt; dominoes.length</code><em>, and </em><code>dominoes[i]</code><em> is <strong>equivalent to</strong> </em><code>dominoes[j]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> dominoes = [[1,2],[2,1],[3,4],[5,6]]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= dominoes.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>dominoes[i].length == 2</code></li>
	<li><code>1 &lt;= dominoes[i][j] &lt;= 9</code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int first, second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
        @Override
        public String toString() {
            return "(" + first + " " + second + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair current = (Pair)(obj);
            return current.first == first && current.second == second;
        }
        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
    public int numEquivDominoPairs(int[][] arr) {
        int n = arr.length;
        HashMap<Pair, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(new Pair(arr[i][0], arr[i][1]), map.getOrDefault(new Pair(arr[i][0], arr[i][1]), 0) + 1);
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            int x = arr[i][0], y = arr[i][1];
            if (x == y) {
                count += map.getOrDefault(new Pair(x, y), 0) - 1;
                if (map.getOrDefault(new Pair(x, y), 0) > 0) map.put(new Pair(x, y), map.getOrDefault(new Pair(x, y), 0) -1);
            }        
            else {
                count += map.getOrDefault(new Pair(x, y), 0) - 1;
                count += map.getOrDefault(new Pair(y, x), 0);
                if (map.getOrDefault(new Pair(x, y), 0) > 0) map.put(new Pair(x, y), map.getOrDefault(new Pair(x, y), 0) -1);
            }
        }
        return count;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

