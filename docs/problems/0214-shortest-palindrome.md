# 214. Shortest Palindrome

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/shortest-palindrome/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0214-shortest-palindrome){ .md-button }

---

<h2><a href="https://leetcode.com/problems/shortest-palindrome">214. Shortest Palindrome</a></h2><h3>Hard</h3><hr><p>You are given a string <code>s</code>. You can convert <code>s</code> to a <span data-keyword="palindrome-string">palindrome</span> by adding characters in front of it.</p>

<p>Return <em>the shortest palindrome you can find by performing this transformation</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "aacecaaa"
<strong>Output:</strong> "aaacecaaa"
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = "abcd"
<strong>Output:</strong> "dcbabcd"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists of lowercase English letters only.</li>
</ul>


---

## Solution

```java
import java.math.BigInteger;
class Solution {
    public String shortestPalindrome(String s) {
        int n = s.length();
        if(Is_Pallindrome(s)) return s;

        long pref[] = new long[n];
        long suff[] = new long[n];

        Hashing hash = new Hashing(s);
        Hashing hash1 = new Hashing(new StringBuilder(s).reverse().toString());

        for(int i = 0; i < n; i++) pref[i] = hash.getHashbounds(0, i);
        for(int i = n - 1; i >= 0; i--) suff[i] = hash1.getHashbounds(i , n - 1);
        for(int i = 0; i < n / 2; i++) {
            long temp = suff[i];
            suff[i] = suff[n - 1 - i];
            suff[n - 1 - i] = temp;
        }
        int maxi = 0;
        for(int i = 0; i < n - 1; i++) if (pref[i] == suff[i]) maxi = i;
        String current = s.substring(maxi + 1, n);
        String ans = new StringBuilder(current).reverse().toString();
        ans += s;
        return ans;
    }
    static boolean Is_Pallindrome(String s) {
        int n = s.length();
        int low = 0, high = n - 1;
        while(low <= high) {
            if(s.charAt(low) != s.charAt(high)) return false;
            low++;
            high--;
        }
        return true;
    }
    static class Hashing {
        long[] hash1, hash2;
        long[] inv1, inv2;
        int n;
        static int multiplier = 43;
        static final Random rnd = new Random();
        static final int mod1 = BigInteger.valueOf((int) (1e9 + rnd.nextInt((int) 1e9))).nextProbablePrime().intValue();
        static final int mod2 = BigInteger.valueOf((int) (1e9 + rnd.nextInt((int) 1e9))).nextProbablePrime().intValue();
        static final int invMultiplier1 = BigInteger.valueOf(multiplier).modInverse(BigInteger.valueOf(mod1)).intValue();
        static final int invMultiplier2 = BigInteger.valueOf(multiplier).modInverse(BigInteger.valueOf(mod2)).intValue();
        public Hashing(String s) {
            n = s.length();
            hash1 = new long[n + 1];hash2 = new long[n + 1];
            inv1 = new long[n + 1];inv2 = new long[n + 1];
            inv1[0] = 1;inv2[0] = 1;
            long p1 = 1;long p2 = 1;
            for (int i = 0; i < n; i++) {
                hash1[i + 1] = (hash1[i] + s.charAt(i) * p1) % mod1;
                p1 = p1 * multiplier % mod1;
                inv1[i + 1] = inv1[i] * invMultiplier1 % mod1;
                hash2[i + 1] = (hash2[i] + s.charAt(i) * p2) % mod2;
                p2 = p2 * multiplier % mod2;
                inv2[i + 1] = inv2[i] * invMultiplier2 % mod2;
            }
        }
        public long getHash(int i, int len) {
            return (((hash1[i + len] - hash1[i] + mod1) * inv1[i] % mod1) << 32) + (hash2[i + len] - hash2[i] + mod2) * inv2[i] % mod2;
        }
        public long getHashbounds(int x, int y) {
            return getHash(x,y-x+1);
        }
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

