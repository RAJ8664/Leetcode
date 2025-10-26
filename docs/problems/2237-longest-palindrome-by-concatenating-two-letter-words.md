# 2237. Longest Palindrome By Concatenating Two Letter Words

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2237-longest-palindrome-by-concatenating-two-letter-words){ .md-button }

---

<h2><a href="https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words">2237. Longest Palindrome by Concatenating Two Letter Words</a></h2><h3>Medium</h3><hr><p>You are given an array of strings <code>words</code>. Each element of <code>words</code> consists of <strong>two</strong> lowercase English letters.</p>

<p>Create the <strong>longest possible palindrome</strong> by selecting some elements from <code>words</code> and concatenating them in <strong>any order</strong>. Each element can be selected <strong>at most once</strong>.</p>

<p>Return <em>the <strong>length</strong> of the longest palindrome that you can create</em>. If it is impossible to create any palindrome, return <code>0</code>.</p>

<p>A <strong>palindrome</strong> is a string that reads the same forward and backward.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;lc&quot;,&quot;cl&quot;,&quot;gg&quot;]
<strong>Output:</strong> 6
<strong>Explanation:</strong> One longest palindrome is &quot;lc&quot; + &quot;gg&quot; + &quot;cl&quot; = &quot;lcggcl&quot;, of length 6.
Note that &quot;clgglc&quot; is another longest palindrome that can be created.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;ab&quot;,&quot;ty&quot;,&quot;yt&quot;,&quot;lc&quot;,&quot;cl&quot;,&quot;ab&quot;]
<strong>Output:</strong> 8
<strong>Explanation:</strong> One longest palindrome is &quot;ty&quot; + &quot;lc&quot; + &quot;cl&quot; + &quot;yt&quot; = &quot;tylcclyt&quot;, of length 8.
Note that &quot;lcyttycl&quot; is another longest palindrome that can be created.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;cc&quot;,&quot;ll&quot;,&quot;xx&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> One longest palindrome is &quot;cc&quot;, of length 2.
Note that &quot;ll&quot; is another longest palindrome that can be created, and so is &quot;xx&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>words[i].length == 2</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>


---

## Solution

```java
class Solution {
    public int longestPalindrome(String[] arr) {
        int n = arr.length;
        HashMap<String, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            String current = arr[i];
            if (current.charAt(0) == current.charAt(1)) {
                map.put(current, map.getOrDefault(current, 0) + 1);
                continue;
            }
            StringBuilder sb = new StringBuilder(current);
            sb.reverse();
            if (map.getOrDefault(sb.toString(), 0) > 0) {
                count += 4;
                map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) -1);
            }
            else map.put(current, map.getOrDefault(current, 0) + 1);
        }
        HashMap<String, Integer> newMap = new HashMap<>();
        for (Map.Entry<String, Integer> curr : map.entrySet()) {
            String key = curr.getKey();
            if (key.charAt(0) == key.charAt(1)) {
                newMap.put(key, curr.getValue());
            }
        }
        int maxi = 0, odd = 0;
        for (Map.Entry<String, Integer> curr : newMap.entrySet()) {
            int val = curr.getValue();
            if (val % 2 == 0 && val != 0) count += val * 2;
            if (val % 2 == 1) {
                odd = 1;
                maxi = Math.max(maxi, val);
                count += (val - 1) * 2;
            }
        }
        if (odd > 0) count += 2;
        return count;
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

