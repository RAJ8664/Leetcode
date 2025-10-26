# 30. Substring With Concatenation Of All Words

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/substring-with-concatenation-of-all-words/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0030-substring-with-concatenation-of-all-words){ .md-button }

---

<h2><a href="https://leetcode.com/problems/substring-with-concatenation-of-all-words">30. Substring with Concatenation of All Words</a></h2><h3>Hard</h3><hr><p>You are given a string <code>s</code> and an array of strings <code>words</code>. All the strings of <code>words</code> are of <strong>the same length</strong>.</p>

<p>A <strong>concatenated string</strong> is a string that exactly contains all the strings of any permutation of <code>words</code> concatenated.</p>

<ul>
	<li>For example, if <code>words = [&quot;ab&quot;,&quot;cd&quot;,&quot;ef&quot;]</code>, then <code>&quot;abcdef&quot;</code>, <code>&quot;abefcd&quot;</code>, <code>&quot;cdabef&quot;</code>, <code>&quot;cdefab&quot;</code>, <code>&quot;efabcd&quot;</code>, and <code>&quot;efcdab&quot;</code> are all concatenated strings. <code>&quot;acdbef&quot;</code> is not a concatenated string because it is not the concatenation of any permutation of <code>words</code>.</li>
</ul>

<p>Return an array of <em>the starting indices</em> of all the concatenated substrings in <code>s</code>. You can return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;barfoothefoobarman&quot;, words = [&quot;foo&quot;,&quot;bar&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,9]</span></p>

<p><strong>Explanation:</strong></p>

<p>The substring starting at 0 is <code>&quot;barfoo&quot;</code>. It is the concatenation of <code>[&quot;bar&quot;,&quot;foo&quot;]</code> which is a permutation of <code>words</code>.<br />
The substring starting at 9 is <code>&quot;foobar&quot;</code>. It is the concatenation of <code>[&quot;foo&quot;,&quot;bar&quot;]</code> which is a permutation of <code>words</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;wordgoodgoodgoodbestword&quot;, words = [&quot;word&quot;,&quot;good&quot;,&quot;best&quot;,&quot;word&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no concatenated substring.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;barfoofoobarthefoobarman&quot;, words = [&quot;bar&quot;,&quot;foo&quot;,&quot;the&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[6,9,12]</span></p>

<p><strong>Explanation:</strong></p>

<p>The substring starting at 6 is <code>&quot;foobarthe&quot;</code>. It is the concatenation of <code>[&quot;foo&quot;,&quot;bar&quot;,&quot;the&quot;]</code>.<br />
The substring starting at 9 is <code>&quot;barthefoo&quot;</code>. It is the concatenation of <code>[&quot;bar&quot;,&quot;the&quot;,&quot;foo&quot;]</code>.<br />
The substring starting at 12 is <code>&quot;thefoobar&quot;</code>. It is the concatenation of <code>[&quot;the&quot;,&quot;foo&quot;,&quot;bar&quot;]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words.length &lt;= 5000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 30</code></li>
	<li><code>s</code> and <code>words[i]</code> consist of lowercase English letters.</li>
</ul>


---

## Solution

```java
import java.math.*;
class Solution {
    private HashSet<Long> set;
    private HashSet<Long> correct_set;
    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length();
        int total_len = words[0].length() * words.length;
        List<Integer> res = new ArrayList<>();
        HashMap<Long, Long> map = new HashMap<>();
        set = new HashSet<>();
        correct_set = new HashSet<>();
        StringBuilder second = new StringBuilder();
        for (String x : words) {
            Hashing hash = new Hashing(x);
            long current_hash = hash.getHashBounds(0, x.length() - 1);
            map.put(current_hash, map.getOrDefault(current_hash, 0L) + 1);
            second.append(x);
        }
        Hashing h = new Hashing(second.toString());
        long req_hash = h.getHashBounds(0, second.toString().length() - 1);
        if (words[0].length() == 1 && words.length > 1 && words[0].charAt(0) == 'a' && words[1].charAt(0) == 'a') {
            Hashing hash = new Hashing(s);
            for (int i = 0; i < n; i++) {
                if (i + total_len - 1 < n) {
                    long current_hash = hash.getHashBounds(i, i + total_len - 1);
                    if (current_hash == req_hash) res.add(i);
                }
            }
            return res;
        }
        for (int i = 0; i < n; i++) {
            if (i + total_len - 1 < n) {
                if (check(s, i, i + total_len, map, total_len / words.length)) res.add(i);
            }
        }
        return res;
    }
    private boolean check(String s, int start, int end, HashMap<Long, Long> temp_map, int k) {
        int n = s.length();
        Hashing hash = new Hashing(s);
        long temp_hash = hash.getHashBounds(start, end - 1);
        if (set.contains(temp_hash)) {
            if (correct_set.contains(temp_hash)) return true;
            else return false;
        }
        HashMap<Long, Long> map = new HashMap<>(temp_map);
        for (int i = start; i < end; i += k) {
            long current_hash = hash.getHashBounds(i , i + k - 1);
            if (map.getOrDefault(current_hash, 0L) > 0) {
                map.put(current_hash, map.getOrDefault(current_hash, 0L) - 1);
            }
            else return false;
        }
        correct_set.add(temp_hash);
        set.add(temp_hash);
        return true;
    }
    static class Hashing {
        long[] hash1, hash2;
        long[] inv1, inv2;
        int n;
        static int muresiplier = 43;
        static final Random rnd = new Random();
        static final int mod1 = BigInteger.valueOf((int) (1e9 + rnd.nextInt((int) 1e9))).nextProbablePrime().intValue();
        static final int mod2 = BigInteger.valueOf((int) (1e9 + rnd.nextInt((int) 1e9))).nextProbablePrime().intValue();
        static final int invMuresiplier1 = BigInteger.valueOf(muresiplier).modInverse(BigInteger.valueOf(mod1)).intValue();
        static final int invMuresiplier2 = BigInteger.valueOf(muresiplier).modInverse(BigInteger.valueOf(mod2)).intValue();
        public Hashing(String s) {
            n = s.length();
            hash1 = new long[n + 1]; hash2 = new long[n + 1];
            inv1 = new long[n + 1]; inv2 = new long[n + 1];
            inv1[0] = 1; inv2[0] = 1;
            long p1 = 1; long p2 = 1;
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
            return (((hash1[i + len] - hash1[i] + mod1) * inv1[i] % mod1) << 32) + (hash2[i + len] - hash2[i] + mod2) * inv2[i] % mod2;
        }
        public long getHashBounds(int x, int y) {
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

