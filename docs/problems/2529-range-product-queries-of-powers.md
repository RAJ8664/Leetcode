# 2529. Range Product Queries Of Powers

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/range-product-queries-of-powers/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2529-range-product-queries-of-powers){ .md-button }

---

<h2><a href="https://leetcode.com/problems/range-product-queries-of-powers">2529. Range Product Queries of Powers</a></h2><h3>Medium</h3><hr><p>Given a positive integer <code>n</code>, there exists a <strong>0-indexed</strong> array called <code>powers</code>, composed of the <strong>minimum</strong> number of powers of <code>2</code> that sum to <code>n</code>. The array is sorted in <strong>non-decreasing</strong> order, and there is <strong>only one</strong> way to form the array.</p>

<p>You are also given a <strong>0-indexed</strong> 2D integer array <code>queries</code>, where <code>queries[i] = [left<sub>i</sub>, right<sub>i</sub>]</code>. Each <code>queries[i]</code> represents a query where you have to find the product of all <code>powers[j]</code> with <code>left<sub>i</sub> &lt;= j &lt;= right<sub>i</sub></code>.</p>

<p>Return<em> an array </em><code>answers</code><em>, equal in length to </em><code>queries</code><em>, where </em><code>answers[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query</em>. Since the answer to the <code>i<sup>th</sup></code> query may be too large, each <code>answers[i]</code> should be returned <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 15, queries = [[0,1],[2,2],[0,3]]
<strong>Output:</strong> [2,4,64]
<strong>Explanation:</strong>
For n = 15, powers = [1,2,4,8]. It can be shown that powers cannot be a smaller size.
Answer to 1st query: powers[0] * powers[1] = 1 * 2 = 2.
Answer to 2nd query: powers[2] = 4.
Answer to 3rd query: powers[0] * powers[1] * powers[2] * powers[3] = 1 * 2 * 4 * 8 = 64.
Each answer modulo 10<sup>9</sup> + 7 yields the same answer, so [2,4,64] is returned.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, queries = [[0,0]]
<strong>Output:</strong> [2]
<strong>Explanation:</strong>
For n = 2, powers = [2].
The answer to the only query is powers[0] = 2. The answer modulo 10<sup>9</sup> + 7 is the same, so [2] is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt; powers.length</code></li>
</ul>


---

## Solution

```java
class Solution {
    private ArrayList<Integer> arr;
    private int mod = (int)(1e9 + 7);
    public int[] productQueries(int n, int[][] queries) {
        arr = new ArrayList<>();
        while (n > 0) 
            n -= change(n);
        Collections.sort(arr);
        long pre[] = new long[arr.size()];
        long prod = 1;
        for (int i = 0; i < arr.size(); i++) {
            prod = (prod * arr.get(i)) % mod;
            pre[i] = prod;
        }
        int res[] = new int[queries.length];
        int k = 0;
        for (int[] temp : queries) {
            int l = temp[0], r = temp[1];
            if (l == 0) {
                res[k++] = (int) pre[r];
            } else {
                long numerator = pre[r];
                long denominator = pre[l - 1];
                long inv = modPow(denominator, mod - 2, mod);
                res[k++] = (int) ((numerator * inv) % mod);
            }
        }
        return res;
    }
    private int change(int n) {
        int current = 1;
        while (current * 2 <= n) {
            current *= 2;
        }
        arr.add(current);
        return current;
    }
    private long modPow(long base, long exp, int m) {
        long result = 1;
        base %= m;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % m;
            base = (base * base) % m;
            exp >>= 1;
        }
        return result;
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

