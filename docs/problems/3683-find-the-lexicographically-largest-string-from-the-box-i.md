# 3683. Find The Lexicographically Largest String From The Box I

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3683-find-the-lexicographically-largest-string-from-the-box-i){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i">3683. Find the Lexicographically Largest String From the Box I</a></h2><h3>Medium</h3><hr><p>You are given a string <code>word</code>, and an integer <code>numFriends</code>.</p>

<p>Alice is organizing a game for her <code>numFriends</code> friends. There are multiple rounds in the game, where in each round:</p>

<ul>
	<li><code>word</code> is split into <code>numFriends</code> <strong>non-empty</strong> strings, such that no previous round has had the <strong>exact</strong> same split.</li>
	<li>All the split words are put into a box.</li>
</ul>

<p>Find the <span data-keyword="lexicographically-smaller-string">lexicographically largest</span> string from the box after all the rounds are finished.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;dbca&quot;, numFriends = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;dbc&quot;</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p>All possible splits are:</p>

<ul>
	<li><code>&quot;d&quot;</code> and <code>&quot;bca&quot;</code>.</li>
	<li><code>&quot;db&quot;</code> and <code>&quot;ca&quot;</code>.</li>
	<li><code>&quot;dbc&quot;</code> and <code>&quot;a&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;gggg&quot;, numFriends = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;g&quot;</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p>The only possible split is: <code>&quot;g&quot;</code>, <code>&quot;g&quot;</code>, <code>&quot;g&quot;</code>, and <code>&quot;g&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 5&nbsp;* 10<sup>3</sup></code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= numFriends &lt;= word.length</code></li>
</ul>


---

## Solution

```java
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    private ArrayList<String> ans;

    public String answerString(String word, int numFriends) {
        int n = word.length();
        ans = new ArrayList<>();
        if (numFriends == 1)
            return word;
        int ind = -1;
        int count = 0;
        char ch = 'a';
        ArrayList<Integer> idx = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (word.charAt(i) > ch) {
                ch = word.charAt(i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (word.charAt(i) == ch)
                idx.add(i);
        }
        for (int ele : idx)
            solve(ele, word, numFriends);
        Collections.sort(ans);
        if (ans.size() == 0)
            return "";
        return ans.get(ans.size() - 1);
    }

    private void solve(int ind, String word, int numFriends) {
        int n = word.length();
        int count_prev = ind;
        StringBuilder res = new StringBuilder();
        if (count_prev >= numFriends) {
            for (int i = ind; i < n; i++)
                res.append(word.charAt(i));
            ans.add(res.toString());
        } else {
            int req = numFriends - count_prev - 1;
            for (int i = n - 1 - req; i >= ind; i--) {
                res.append(word.charAt(i));
            }
            ans.add(res.reverse().toString());
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

