# 2691. Count Vowel Strings In Ranges

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/count-vowel-strings-in-ranges/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2691-count-vowel-strings-in-ranges){ .md-button }

---

<h2><a href="https://leetcode.com/problems/count-vowel-strings-in-ranges">2691. Count Vowel Strings in Ranges</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> array of strings <code>words</code> and a 2D array of integers <code>queries</code>.</p>

<p>Each query <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> asks us to find the number of strings present in the range <code>l<sub>i</sub></code> to <code>r<sub>i</sub></code> (both <strong>inclusive</strong>) of <code>words</code> that start and end with a vowel.</p>

<p>Return <em>an array </em><code>ans</code><em> of size </em><code>queries.length</code><em>, where </em><code>ans[i]</code><em> is the answer to the </em><code>i</code><sup>th</sup><em> query</em>.</p>

<p><strong>Note</strong> that the vowel letters are <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;aba&quot;,&quot;bcb&quot;,&quot;ece&quot;,&quot;aa&quot;,&quot;e&quot;], queries = [[0,2],[1,4],[1,1]]
<strong>Output:</strong> [2,3,0]
<strong>Explanation:</strong> The strings starting and ending with a vowel are &quot;aba&quot;, &quot;ece&quot;, &quot;aa&quot; and &quot;e&quot;.
The answer to the query [0,2] is 2 (strings &quot;aba&quot; and &quot;ece&quot;).
to query [1,4] is 3 (strings &quot;ece&quot;, &quot;aa&quot;, &quot;e&quot;).
to query [1,1] is 0.
We return [2,3,0].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;e&quot;,&quot;i&quot;], queries = [[0,2],[0,1],[2,2]]
<strong>Output:</strong> [3,2,1]
<strong>Explanation:</strong> Every string satisfies the conditions, so we return [3,2,1].</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 40</code></li>
	<li><code>words[i]</code> consists only of lowercase English letters.</li>
	<li><code>sum(words[i].length) &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt;&nbsp;words.length</code></li>
</ul>


---

## Solution

```java
class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int arr[] = new int[n];
        for(int i = 0; i < n; i++) {
            String current = words[i];
            if(isvowel(current.charAt(0)) && isvowel(current.charAt(current.length() - 1))) arr[i] = 1;
            else arr[i] = 0;
        }
        int sum = 0;
        int pre[] = new int[n];
        for(int i = 0; i < n; i++) {
            sum += arr[i];
            pre[i] = sum;
        }
        int res[] = new int[queries.length];
        int k = 0;
        for(int current[] : queries) {
            int l = current[0], r = current[1];
            if(l == 0) res[k++] = pre[r];
            else res[k++] = pre[r] - pre[l - 1];
        }
        return res;
    }
    private boolean isvowel(char current) {
        if ((current == 'a') || (current == 'e') || (current == 'i' ) || (current == 'o')  || (current == 'u')) return true;
        return false;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

