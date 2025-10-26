# 372. Super Pow

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/super-pow/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0372-super-pow){ .md-button }

---

<h2><a href="https://leetcode.com/problems/super-pow">372. Super Pow</a></h2><h3>Medium</h3><hr><p>Your task is to calculate <code>a<sup>b</sup></code> mod <code>1337</code> where <code>a</code> is a positive integer and <code>b</code> is an extremely large positive integer given in the form of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = 2, b = [3]
<strong>Output:</strong> 8
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = 2, b = [1,0]
<strong>Output:</strong> 1024
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> a = 1, b = [4,3,3,8,5,2]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>1 &lt;= b.length &lt;= 2000</code></li>
	<li><code>0 &lt;= b[i] &lt;= 9</code></li>
	<li><code>b</code> does not contain leading zeros.</li>
</ul>


---

## Solution

```java
class Solution {
    static int mod = (int)(1337);
    public int superPow(int a, int[] b) {
        long res = 1;
        for (int i = b.length - 1; i >= 0; i--) {
            res = mul(res, fast_pow(a, b[i], mod));
            a = (int)(fast_pow(a, 10, mod));
        }
        return (int)(res);
    }
    static long fast_pow(long a, long p, long mod) {
        long res = 1;
        while (p > 0) {
            if (p % 2 == 0) {
                a = ((a % mod) * (a % mod)) % mod;
                p /= 2;
            }
            else {
                res = ((res % mod) * (a % mod)) % mod;
                p--;
            }
        }
        return res;
    }
    static long mul(long a, long b) {return (long) ((long) ((a % mod) * 1L * (b % mod)) % mod);}
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

