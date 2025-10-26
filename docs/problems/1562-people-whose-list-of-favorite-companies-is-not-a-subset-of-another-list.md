# 1562. People Whose List Of Favorite Companies Is Not A Subset Of Another List

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1562-people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list){ .md-button }

---

<h2><a href="https://leetcode.com/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list">1562. People Whose List of Favorite Companies Is Not a Subset of Another List</a></h2><h3>Medium</h3><hr><p>Given the array <code>favoriteCompanies</code> where <code>favoriteCompanies[i]</code> is the list of favorites companies for the <code>ith</code> person (<strong>indexed from 0</strong>).</p>

<p><em>Return the indices of people whose list of favorite companies is not a <strong>subset</strong> of any other list of favorites companies</em>. You must return the indices in increasing order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> favoriteCompanies = [[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;],[&quot;google&quot;,&quot;microsoft&quot;],[&quot;google&quot;,&quot;facebook&quot;],[&quot;google&quot;],[&quot;amazon&quot;]]
<strong>Output:</strong> [0,1,4] 
<strong>Explanation:</strong> 
Person with index=2 has favoriteCompanies[2]=[&quot;google&quot;,&quot;facebook&quot;] which is a subset of favoriteCompanies[0]=[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;] corresponding to the person with index 0. 
Person with index=3 has favoriteCompanies[3]=[&quot;google&quot;] which is a subset of favoriteCompanies[0]=[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;] and favoriteCompanies[1]=[&quot;google&quot;,&quot;microsoft&quot;]. 
Other lists of favorite companies are not a subset of another list, therefore, the answer is [0,1,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> favoriteCompanies = [[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;],[&quot;leetcode&quot;,&quot;amazon&quot;],[&quot;facebook&quot;,&quot;google&quot;]]
<strong>Output:</strong> [0,1] 
<strong>Explanation:</strong> In this case favoriteCompanies[2]=[&quot;facebook&quot;,&quot;google&quot;] is a subset of favoriteCompanies[0]=[&quot;leetcode&quot;,&quot;google&quot;,&quot;facebook&quot;], therefore, the answer is [0,1].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> favoriteCompanies = [[&quot;leetcode&quot;],[&quot;google&quot;],[&quot;facebook&quot;],[&quot;amazon&quot;]]
<strong>Output:</strong> [0,1,2,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= favoriteCompanies.length &lt;= 100</code></li>
	<li><code>1 &lt;= favoriteCompanies[i].length &lt;= 500</code></li>
	<li><code>1 &lt;= favoriteCompanies[i][j].length &lt;= 20</code></li>
	<li>All strings in <code>favoriteCompanies[i]</code> are <strong>distinct</strong>.</li>
	<li>All lists of favorite companies are <strong>distinct</strong>, that is, If we sort alphabetically each list then <code>favoriteCompanies[i] != favoriteCompanies[j].</code></li>
	<li>All strings consist of lowercase English letters only.</li>
</ul>


---

## Solution

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> peopleIndexes(List<List<String >> favoriteCompanies) {
        int n = favoriteCompanies.size();
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, ArrayList<String >> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (String s : favoriteCompanies.get(i)) {
                if (!map.containsKey(i))
                    map.put(i, new ArrayList<>());
                map.get(i).add(s);
            }
        }
        for (Map.Entry<Integer, ArrayList<String >> entry : map.entrySet()) {
            int key = entry.getKey();
            ArrayList<String> currentCmp = entry.getValue();
            boolean flag = true;
            for (Map.Entry<Integer, ArrayList<String >> otherEntry : map.entrySet()) {
                if (key == otherEntry.getKey())
                    continue;
                ArrayList<String> otherCmp = otherEntry.getValue();
                HashSet<String> set = new HashSet<>(otherCmp);
                boolean containsAll = true;
                for (String x : currentCmp) {
                    if (!set.contains(x)) {
                        containsAll = false;
                        break;
                    }
                }
                if (containsAll) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                res.add(key);
            }
        }
        Collections.sort(res);
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

