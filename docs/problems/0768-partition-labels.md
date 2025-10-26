# 768. Partition Labels

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/partition-labels/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0768-partition-labels){ .md-button }

---

<h2><a href="https://leetcode.com/problems/partition-labels">768. Partition Labels</a></h2><h3>Medium</h3><hr><p>You are given a string <code>s</code>. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string <code>&quot;ababcc&quot;</code> can be partitioned into <code>[&quot;abab&quot;, &quot;cc&quot;]</code>, but partitions such as <code>[&quot;aba&quot;, &quot;bcc&quot;]</code> or <code>[&quot;ab&quot;, &quot;ab&quot;, &quot;cc&quot;]</code> are invalid.</p>

<p>Note that the partition is done so that after concatenating all the parts in order, the resultant string should be <code>s</code>.</p>

<p>Return <em>a list of integers representing the size of these parts</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ababcbacadefegdehijhklij&quot;
<strong>Output:</strong> [9,7,8]
<strong>Explanation:</strong>
The partition is &quot;ababcbaca&quot;, &quot;defegde&quot;, &quot;hijhklij&quot;.
This is a partition so that each letter appears in at most one part.
A partition like &quot;ababcbacadefegde&quot;, &quot;hijhklij&quot; is incorrect, because it splits s into less parts.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;eccbbbbdec&quot;
<strong>Output:</strong> [10]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>


---

## Solution

```java
class Solution {
    public  List<Integer> partitionLabels(String s) {
        int n = s.length();
        List<Integer> res = new ArrayList<>();
        int first[] = new int[30];
        int last[] = new int[30];
        Arrays.fill(first, -1); Arrays.fill(last, -1);
        for(int i = 0; i < n; i++) {
            if(first[s.charAt(i) - 'a'] == -1) first[s.charAt(i) - 'a'] = i;
            else last[s.charAt(i) - 'a'] = i;
        }
        for(int i = n - 1; i >= 0; i--) {
            if(last[s.charAt(i) - 'a'] == -1) last[s.charAt(i) - 'a'] = i;
        }
        int start = 0;
        while(true) {
            int j = last[s.charAt(start) - 'a'];
            int lastj = start;
            while(start < j) {
                start++;
                j = Math.max(j , last[s.charAt(start) - 'a']);
            }
            res.add(j - lastj + 1);
            start = j + 1;
            if(start >= n) break;
        }
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

