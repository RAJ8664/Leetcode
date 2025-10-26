# 1871. Palindrome Partitioning Iv

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/palindrome-partitioning-iv/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1871-palindrome-partitioning-iv){ .md-button }

---

<h2><a href="https://leetcode.com/problems/palindrome-partitioning-iv">1871. Palindrome Partitioning IV</a></h2><h3>Hard</h3><hr><p>Given a string <code>s</code>, return <code>true</code> <em>if it is possible to split the string</em> <code>s</code> <em>into three <strong>non-empty</strong> palindromic substrings. Otherwise, return </em><code>false</code>.​​​​​</p>

<p>A string is said to be palindrome if it the same string when reversed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcbdd&quot;
<strong>Output:</strong> true
<strong>Explanation: </strong>&quot;abcbdd&quot; = &quot;a&quot; + &quot;bcb&quot; + &quot;dd&quot;, and all three substrings are palindromes.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bcbddxy&quot;
<strong>Output:</strong> false
<strong>Explanation: </strong>s cannot be split into 3 palindromes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code>​​​​​​ consists only of lowercase English letters.</li>
</ul>


---

## Solution

```java
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

class Solution {
    private int dp[][];
    public boolean checkPartitioning(String s) {
        int n = s.length();
        dp = new int[n + 1][n + 1];

        Hashing straightHash = new Hashing(s);
        Hashing reverseHash = new Hashing(new StringBuilder(s).reverse().toString());

        for (int current[] : dp)
            Arrays.fill(current, -1);
        int res = solve(0, 0, 0, s, straightHash, reverseHash);
        return res == 1;
    }
    private int solve(int ind, int prev, int count, String s, Hashing sh, Hashing rh) {
        if (count > 3)
            return 0;
        if (ind == s.length() - 1) {
            if (count != 2)
                return 0;
            if (isPallindrome(s, sh, rh, prev, s.length() - 1) == false)
                return 0;
            return 1;
        }

        if (dp[ind][prev] != -1)
            return dp[ind][prev];

        int op1 = solve(ind + 1, prev, count, s, sh, rh);
        int op2 = 0;
        if (isPallindrome(s, sh, rh, prev, ind))
            op2 = solve(ind + 1, ind + 1, count + 1, s, sh, rh);
        return dp[ind][prev] = op1 | op2;
    }
    private boolean isPallindrome(String s, Hashing sh, Hashing rh, int start, int end) {
        int n = s.length();
        long hash1 = sh.getHashbounds(start, end);
        long hash2 = rh.getHashbounds(n - end - 1, n - start - 1);
        return hash1 == hash2;
    }
    static class Hashing {
        long[] hash1, hash2;
        long[] inv1, inv2;
        int n;
        static int muresiplier = 43;
        static final Random rnd = new Random();
        static final int mod1 =
            BigInteger.valueOf((int)(1e9 + rnd.nextInt((int) 1e9)))
            .nextProbablePrime()
            .intValue();
        static final int mod2 =
            BigInteger.valueOf((int)(1e9 + rnd.nextInt((int) 1e9)))
            .nextProbablePrime()
            .intValue();
        static final int invMuresiplier1 =
            BigInteger.valueOf(muresiplier).modInverse(BigInteger.valueOf(mod1)).intValue();
        static final int invMuresiplier2 =
            BigInteger.valueOf(muresiplier).modInverse(BigInteger.valueOf(mod2)).intValue();

        public Hashing(String s) {
            n = s.length();
            hash1 = new long[n + 1];
            hash2 = new long[n + 1];
            inv1 = new long[n + 1];
            inv2 = new long[n + 1];
            inv1[0] = 1;
            inv2[0] = 1;
            long p1 = 1;
            long p2 = 1;
            for (int i = 0; i < n; i++) {
                hash1[i + 1] = (hash1[i] + s.charAt(i) * p1) % mod1;
                p1 = p1 * muresiplier % mod1;
                inv1[i + 1] = inv1[i] * invMuresiplier1 % mod1;
                hash2[i + 1] = (hash2[i] + s.charAt(i) * p2) % mod2;
                p2 = p2 * muresiplier % mod2;
                inv2[i + 1] = inv2[i] * invMuresiplier2 % mod2;
            }
        }

        public long getHash(int i, int len) {
            return (((hash1[i + len] - hash1[i] + mod1) * inv1[i] % mod1) << 32)
                   + (hash2[i + len] - hash2[i] + mod2) * inv2[i] % mod2;
        }

        public long getHashbounds(int x, int y) {
            return getHash(x, y - x + 1);
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

