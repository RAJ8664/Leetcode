# 1350. Remove Sub Folders From The Filesystem

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1350-remove-sub-folders-from-the-filesystem){ .md-button }

---

<h2><a href="https://leetcode.com/problems/remove-sub-folders-from-the-filesystem">1350. Remove Sub-Folders from the Filesystem</a></h2><h3>Medium</h3><hr><p>Given a list of folders <code>folder</code>, return <em>the folders after removing all <strong>sub-folders</strong> in those folders</em>. You may return the answer in <strong>any order</strong>.</p>

<p>If a <code>folder[i]</code> is located within another <code>folder[j]</code>, it is called a <strong>sub-folder</strong> of it. A sub-folder of <code>folder[j]</code> must start with <code>folder[j]</code>, followed by a <code>&quot;/&quot;</code>. For example, <code>&quot;/a/b&quot;</code> is a sub-folder of <code>&quot;/a&quot;</code>, but <code>&quot;/b&quot;</code> is not a sub-folder of <code>&quot;/a/b/c&quot;</code>.</p>

<p>The format of a path is one or more concatenated strings of the form: <code>&#39;/&#39;</code> followed by one or more lowercase English letters.</p>

<ul>
	<li>For example, <code>&quot;/leetcode&quot;</code> and <code>&quot;/leetcode/problems&quot;</code> are valid paths while an empty string and <code>&quot;/&quot;</code> are not.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> folder = [&quot;/a&quot;,&quot;/a/b&quot;,&quot;/c/d&quot;,&quot;/c/d/e&quot;,&quot;/c/f&quot;]
<strong>Output:</strong> [&quot;/a&quot;,&quot;/c/d&quot;,&quot;/c/f&quot;]
<strong>Explanation:</strong> Folders &quot;/a/b&quot; is a subfolder of &quot;/a&quot; and &quot;/c/d/e&quot; is inside of folder &quot;/c/d&quot; in our filesystem.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> folder = [&quot;/a&quot;,&quot;/a/b/c&quot;,&quot;/a/b/d&quot;]
<strong>Output:</strong> [&quot;/a&quot;]
<strong>Explanation:</strong> Folders &quot;/a/b/c&quot; and &quot;/a/b/d&quot; will be removed because they are subfolders of &quot;/a&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> folder = [&quot;/a/b/c&quot;,&quot;/a/b/ca&quot;,&quot;/a/b/d&quot;]
<strong>Output:</strong> [&quot;/a/b/c&quot;,&quot;/a/b/ca&quot;,&quot;/a/b/d&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= folder.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= folder[i].length &lt;= 100</code></li>
	<li><code>folder[i]</code> contains only lowercase letters and <code>&#39;/&#39;</code>.</li>
	<li><code>folder[i]</code> always starts with the character <code>&#39;/&#39;</code>.</li>
	<li>Each folder name is <strong>unique</strong>.</li>
</ul>


---

## Solution

```java
class Solution {
    static class custom_sort implements Comparator<String> {
        @Override
        public int compare(String first , String second) {
            return Integer.compare(first.length() , second.length());
        }
    }
    public List<String> removeSubfolders(String[] folder) {
        int n = folder.length;
        List<String> res = new ArrayList<>();
        Arrays.sort(folder, new custom_sort());
        int vis[] = new int[n];
        for (int i = 0; i < n; i++) {
            if (vis[i] == 1) 
                continue;
            res.add(folder[i]);
            vis[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (check(folder[i] , folder[j])) 
                    vis[j] = 1;
            }
        }
        return res;
    }
    private boolean check(String first, String second) {
        int n = first.length();
        int m = second.length();
        if (n == m) {
            if (second.startsWith(first)) 
                return true;
        }
        for (int i = 0; i < Math.min(n , m); i++) {
            if (first.charAt(i) != second.charAt(i)) 
                return false;
        }
        if (second.charAt(n) == '/') 
            return true;
        return false;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

