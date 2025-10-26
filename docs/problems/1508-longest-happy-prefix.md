# 1508. Longest Happy Prefix

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/longest-happy-prefix/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1508-longest-happy-prefix){ .md-button }

---

<h2><a href="https://leetcode.com/problems/longest-happy-prefix">1508. Longest Happy Prefix</a></h2><h3>Hard</h3><hr><p>A string is called a <strong>happy prefix</strong> if is a <strong>non-empty</strong> prefix which is also a suffix (excluding itself).</p>

<p>Given a string <code>s</code>, return <em>the <strong>longest happy prefix</strong> of</em> <code>s</code>. Return an empty string <code>&quot;&quot;</code> if no such prefix exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;level&quot;
<strong>Output:</strong> &quot;l&quot;
<strong>Explanation:</strong> s contains 4 prefix excluding itself (&quot;l&quot;, &quot;le&quot;, &quot;lev&quot;, &quot;leve&quot;), and suffix (&quot;l&quot;, &quot;el&quot;, &quot;vel&quot;, &quot;evel&quot;). The largest prefix which is also suffix is given by &quot;l&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ababab&quot;
<strong>Output:</strong> &quot;abab&quot;
<strong>Explanation:</strong> &quot;abab&quot; is the largest prefix which is also suffix. They can overlap in the original string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
</ul>


---

## Solution

```java
import java.math.BigInteger;
class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        
        char arr[] = s.toCharArray();
        Hashing hash = new Hashing(arr);

        long pref_hash[] = new long[n];
        long suff_hash[] = new long[n];

        for(int i = 0; i < n - 1; i++) {
            pref_hash[i] = hash.getHashbounds(0, i);
        }

        for(int i = n - 1; i >= 1; i--) {
            suff_hash[i] = hash.getHashbounds(i, n - 1);
        }

        for(int i = 0; i < n / 2; i++) {
            long temp = suff_hash[i];
            suff_hash[i] = suff_hash[n - 1 - i];
            suff_hash[n - 1 - i] = temp;
        }


        int ans = -1;
        for(int i = 0; i < n - 1; i++) {
            if(pref_hash[i] == suff_hash[i]) {
                ans = i;
            }
        }
        if(ans == -1) {
            return "";
        }

        String res = "";
        for(int i = 0; i <= ans; i++) res += arr[i];
        return res;
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
        public Hashing(char s[]) {
            n = s.length;
            hash1 = new long[n + 1];
            hash2 = new long[n + 1];
            inv1 = new long[n + 1];
            inv2 = new long[n + 1];
            inv1[0] = 1;inv2[0] = 1;
            long p1 = 1;
            long p2 = 1;
            for (int i = 0; i < n; i++) {
                hash1[i + 1] = (hash1[i] + s[i] * p1) % mod1;
                p1 = p1 * multiplier % mod1;
                inv1[i + 1] = inv1[i] * invMultiplier1 % mod1;
                hash2[i + 1] = (hash2[i] + s[i] * p2) % mod2;
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

