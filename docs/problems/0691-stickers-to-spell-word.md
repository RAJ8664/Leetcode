# 691. Stickers To Spell Word

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/stickers-to-spell-word/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0691-stickers-to-spell-word){ .md-button }

---

<h2><a href="https://leetcode.com/problems/stickers-to-spell-word">691. Stickers to Spell Word</a></h2><h3>Hard</h3><hr><p>We are given <code>n</code> different types of <code>stickers</code>. Each sticker has a lowercase English word on it.</p>

<p>You would like to spell out the given string <code>target</code> by cutting individual letters from your collection of stickers and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each sticker.</p>

<p>Return <em>the minimum number of stickers that you need to spell out </em><code>target</code>. If the task is impossible, return <code>-1</code>.</p>

<p><strong>Note:</strong> In all test cases, all words were chosen randomly from the <code>1000</code> most common US English words, and <code>target</code> was chosen as a concatenation of two random words.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stickers = [&quot;with&quot;,&quot;example&quot;,&quot;science&quot;], target = &quot;thehat&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong>
We can use 2 &quot;with&quot; stickers, and 1 &quot;example&quot; sticker.
After cutting and rearrange the letters of those stickers, we can form the target &quot;thehat&quot;.
Also, this is the minimum number of stickers necessary to form the target string.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stickers = [&quot;notice&quot;,&quot;possible&quot;], target = &quot;basicbasic&quot;
<strong>Output:</strong> -1
Explanation:
We cannot form the target &quot;basicbasic&quot; from cutting letters from the given stickers.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == stickers.length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= stickers[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= target.length &lt;= 15</code></li>
	<li><code>stickers[i]</code> and <code>target</code> consist of lowercase English letters.</li>
</ul>


---

## Solution

```java
class Solution {
    private HashMap<String, Integer> dp;
    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        dp = new HashMap<>();
        dp.put("", 0);
        int ans = solve(target, stickers);
        return ans == Integer.MAX_VALUE / 10 ? -1 : ans;
    }

    private int solve(String target, String stickers[]) {
        if (dp.containsKey(target)) 
            return dp.get(target);

        int currFreq[] = new int[26];
        for (int i = 0; i < target.length(); i++)
            currFreq[target.charAt(i) - 'a']++;
        
        int ans = Integer.MAX_VALUE / 10;

        for (int i = 0; i < stickers.length; i++) {
            String current = stickers[i];
            boolean willHelp = false;
            int thisFreq[] = new int[26];
            for (int j = 0; j < current.length(); j++) {
                if (currFreq[current.charAt(j) - 'a'] > 0) 
                    willHelp = true;
                thisFreq[current.charAt(j) - 'a']++;
            }
            if (willHelp == false) 
                continue;
            String newString = "";
            for (int j = 0; j < target.length(); j++) {
                if (thisFreq[target.charAt(j) - 'a'] > 0) 
                    thisFreq[target.charAt(j) - 'a']--;
                else 
                    newString += target.charAt(j);
            }
            ans = Math.min(ans, 1 + solve(newString, stickers));
        }
        dp.put(target, ans);
        return ans;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

