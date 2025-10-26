# 920. Uncommon Words From Two Sentences

!!! success "Difficulty: Easy"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/uncommon-words-from-two-sentences/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0920-uncommon-words-from-two-sentences){ .md-button }

---

<h2><a href="https://leetcode.com/problems/uncommon-words-from-two-sentences">920. Uncommon Words from Two Sentences</a></h2><h3>Easy</h3><hr><p>A <strong>sentence</strong> is a string of single-space separated words where each word consists only of lowercase letters.</p>

<p>A word is <strong>uncommon</strong> if it appears exactly once in one of the sentences, and <strong>does not appear</strong> in the other sentence.</p>

<p>Given two <strong>sentences</strong> <code>s1</code> and <code>s2</code>, return <em>a list of all the <strong>uncommon words</strong></em>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s1 = &quot;this apple is sweet&quot;, s2 = &quot;this apple is sour&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;sweet&quot;,&quot;sour&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p>The word <code>&quot;sweet&quot;</code> appears only in <code>s1</code>, while the word <code>&quot;sour&quot;</code> appears only in <code>s2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s1 = &quot;apple apple&quot;, s2 = &quot;banana&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;banana&quot;]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 200</code></li>
	<li><code>s1</code> and <code>s2</code> consist of lowercase English letters and spaces.</li>
	<li><code>s1</code> and <code>s2</code> do not have leading or trailing spaces.</li>
	<li>All the words in <code>s1</code> and <code>s2</code> are separated by a single space.</li>
</ul>


---

## Solution

```java
class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        s1 += " "; s2 += " ";
        int n = s1.length(), m = s2.length();
        HashSet<String> first = new HashSet<>();
        HashSet<String> second = new HashSet<>();
        HashSet<String> firstR = new HashSet<>();
        HashSet<String> secondR = new HashSet<>();
        String current = "";
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) == ' ') {
                if (first.contains(current)) {
                    first.remove(current);
                    firstR.add(current);
                }
                if (!firstR.contains(current)) first.add(current);
                current = "";
            }
            else current += s1.charAt(i);
        }
        current = "";
        for (int i = 0; i < m; i++) {
            if (s2.charAt(i) == ' ') {
                if (second.contains(current)) {
                    secondR.add(current);
                    second.remove(current);
                }
                if (!secondR.contains(current)) second.add(current);
                current = "";
            }
            else current += s2.charAt(i);
        }
        ArrayList<String> res = new ArrayList<>();
        for (String temp : first) if (!second.contains(temp)) if (!res.contains(temp) && !firstR.contains(temp) && !secondR.contains(temp)) res.add(temp);
        for (String temp : second) if (!first.contains(temp)) if (!res.contains(temp) && !firstR.contains(temp) && !secondR.contains(temp)) res.add(temp);
        String answer[] = new String[res.size()];
        for (int i = 0; i < res.size(); i++) answer[i] = res.get(i);
        return answer;
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

