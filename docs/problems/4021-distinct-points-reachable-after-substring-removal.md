# 4021. Distinct Points Reachable After Substring Removal


---

<h2><a href="https://leetcode.com/problems/distinct-points-reachable-after-substring-removal">4021. Distinct Points Reachable After Substring Removal</a></h2><h3>Medium</h3><hr><p>You are given a string <code>s</code> consisting of characters <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code>, and <code>&#39;R&#39;</code>, representing moves on an infinite 2D Cartesian grid.</p>

<ul>
	<li><code>&#39;U&#39;</code>: Move from <code>(x, y)</code> to <code>(x, y + 1)</code>.</li>
	<li><code>&#39;D&#39;</code>: Move from <code>(x, y)</code> to <code>(x, y - 1)</code>.</li>
	<li><code>&#39;L&#39;</code>: Move from <code>(x, y)</code> to <code>(x - 1, y)</code>.</li>
	<li><code>&#39;R&#39;</code>: Move from <code>(x, y)</code> to <code>(x + 1, y)</code>.</li>
</ul>

<p>You are also given a positive integer <code>k</code>.</p>

<p>You <strong>must</strong> choose and remove <strong>exactly one</strong> contiguous substring of length <code>k</code> from <code>s</code>. Then, start from coordinate <code>(0, 0)</code> and perform the remaining moves in order.</p>

<p>Return an integer denoting the number of <strong>distinct</strong> final coordinates reachable.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;LUL&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>After removing a substring of length 1, <code>s</code> can be <code>&quot;UL&quot;</code>, <code>&quot;LL&quot;</code> or <code>&quot;LU&quot;</code>. Following these moves, the final coordinates will be <code>(-1, 1)</code>, <code>(-2, 0)</code> and <code>(-1, 1)</code> respectively. There are two distinct points <code>(-1, 1)</code> and <code>(-2, 0)</code> so the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;UDLR&quot;, k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>After removing a substring of length 4, <code>s</code> can only be the empty string. The final coordinates will be <code>(0, 0)</code>. There is only one distinct point <code>(0, 0)</code> so the answer is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;UU&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>After removing a substring of length 1, <code>s</code> becomes <code>&quot;U&quot;</code>, which always ends at <code>(0, 1)</code>, so there is only one distinct final coordinate.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code>, and <code>&#39;R&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>


## Solution

```java
class Solution {
    private int xP[], yP[];
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "(" + x + " " + y + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Pair current = (Pair)(obj);
            return current.x == x && current.y == y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    public int distinctPoints(String s, int k) {
        int n = s.length();

        xP = new int[n];
        yP = new int[n];
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (current == 'U') {
                xP[i] = 0;
                yP[i] = 1;
            } else if (current == 'D') {
                xP[i] = 0;
                yP[i] = -1; 
            } else if (current == 'L') {
                xP[i] = -1;
                yP[i] = 0;
            } else if (current == 'R') {
                xP[i] = 1;
                yP[i] = 0;
            }
        }

        for (int i = 1; i < n; i++) {
            xP[i] += xP[i - 1];
            yP[i] += yP[i - 1];
        }

        HashSet<Pair> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (i + k - 1 < n) {
                int l = i, r = i + k - 1;
                int windowSumX = xP[r], windowSumY = yP[r];
                if (l - 1 >= 0) {
                    windowSumX -= xP[l - 1];
                    windowSumY -= yP[l - 1];
                }
                int currX = xP[n - 1] - windowSumX, currY = yP[n - 1] - windowSumY;
                set.add(new Pair(currX, currY));
            }
        }

        return set.size(); 
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

