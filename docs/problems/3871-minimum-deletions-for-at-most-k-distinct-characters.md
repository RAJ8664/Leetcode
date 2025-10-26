# 3871. Minimum Deletions For At Most K Distinct Characters


---

<h2><a href="https://leetcode.com/problems/minimum-deletions-for-at-most-k-distinct-characters">3871. Minimum Deletions for At Most K Distinct Characters</a></h2><h3>Easy</h3><hr><p>You are given a string <code>s</code> consisting of lowercase English letters, and an integer <code>k</code>.</p>

<p>Your task is to delete some (possibly none) of the characters in the string so that the number of <strong>distinct</strong> characters in the resulting string is <strong>at most</strong> <code>k</code>.</p>

<p>Return the <strong>minimum</strong> number of deletions required to achieve this.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>s</code> has three distinct characters: <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code> and <code>&#39;c&#39;</code>, each with a frequency of 1.</li>
	<li>Since we can have at most <code>k = 2</code> distinct characters, remove all occurrences of any one character from the string.</li>
	<li>For example, removing all occurrences of <code>&#39;c&#39;</code> results in at most <code>k</code> distinct characters. Thus, the answer is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aabb&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>s</code> has two distinct characters (<code>&#39;a&#39;</code> and <code>&#39;b&#39;</code>) with frequencies of 2 and 2, respectively.</li>
	<li>Since we can have at most <code>k = 2</code> distinct characters, no deletions are required. Thus, the answer is 0.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;yyyzz&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>s</code> has two distinct characters (<code>&#39;y&#39;</code> and <code>&#39;z&#39;</code>) with frequencies of 3 and 2, respectively.</li>
	<li>Since we can have at most <code>k = 1</code> distinct character, remove all occurrences of any one character from the string.</li>
	<li>Removing all <code>&#39;z&#39;</code> results in at most <code>k</code> distinct characters. Thus, the answer is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 16</code></li>
	<li><code>1 &lt;= k &lt;= 16</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<p> </p>


## Solution

```java
class Solution {
    static class Pair {
        char ch;
        int count;
        public Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
        @Override
        public String toString() {
            return "(" + ch + " " + count + ")";
        }
    }
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(first.count, second.count);
        }
    }
    public int minDeletion(String s, int k) {
        int n = s.length();
        PriorityQueue<Pair> pq = new PriorityQueue<>(new custom_sort());
        int res = 0;
        int freq[] = new int[26];
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            freq[current - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            pq.offer(new Pair('a', freq[i]));
        }
        while (pq.size() > k) {
            res += pq.poll().count;
        }
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

