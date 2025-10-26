# 1483. Rank Teams By Votes

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/rank-teams-by-votes/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1483-rank-teams-by-votes){ .md-button }

---

<h2><a href="https://leetcode.com/problems/rank-teams-by-votes">1483. Rank Teams by Votes</a></h2><h3>Medium</h3><hr><p>In a special ranking system, each voter gives a rank from highest to lowest to all teams participating in the competition.</p>

<p>The ordering of teams is decided by who received the most position-one votes. If two or more teams tie in the first position, we consider the second position to resolve the conflict, if they tie again, we continue this process until the ties are resolved. If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.</p>

<p>You are given an array of strings <code>votes</code> which is the votes of all voters in the ranking systems. Sort all teams according to the ranking system described above.</p>

<p>Return <em>a string of all teams <strong>sorted</strong> by the ranking system</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;ABC&quot;,&quot;ACB&quot;,&quot;ABC&quot;,&quot;ACB&quot;,&quot;ACB&quot;]
<strong>Output:</strong> &quot;ACB&quot;
<strong>Explanation:</strong> 
Team A was ranked first place by 5 voters. No other team was voted as first place, so team A is the first team.
Team B was ranked second by 2 voters and ranked third by 3 voters.
Team C was ranked second by 3 voters and ranked third by 2 voters.
As most of the voters ranked C second, team C is the second team, and team B is the third.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;WXYZ&quot;,&quot;XYZW&quot;]
<strong>Output:</strong> &quot;XWYZ&quot;
<strong>Explanation:</strong>
X is the winner due to the tie-breaking rule. X has the same votes as W for the first position, but X has one vote in the second position, while W does not have any votes in the second position. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;ZMNAGUEDSJYLBOPHRQICWFXTVK&quot;]
<strong>Output:</strong> &quot;ZMNAGUEDSJYLBOPHRQICWFXTVK&quot;
<strong>Explanation:</strong> Only one voter, so their votes are used for the ranking.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= votes.length &lt;= 1000</code></li>
	<li><code>1 &lt;= votes[i].length &lt;= 26</code></li>
	<li><code>votes[i].length == votes[j].length</code> for <code>0 &lt;= i, j &lt; votes.length</code>.</li>
	<li><code>votes[i][j]</code> is an English <strong>uppercase</strong> letter.</li>
	<li>All characters of <code>votes[i]</code> are unique.</li>
	<li>All the characters that occur in <code>votes[0]</code> <strong>also occur</strong> in <code>votes[j]</code> where <code>1 &lt;= j &lt; votes.length</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        char ch;
        int votes;
        int freq[] = new int[30];
        public Pair(char ch, int votes, int freq[]) {
            this.ch = ch;
            this.votes = votes;
            this.freq = freq;
        }
    }
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int temp1[] = new int[30];
            int temp2[] = new int[30];
            temp1 = first.freq;
            temp2 = second.freq;
            for (int i = 0; i < 30; i++) {
                if (temp1[i] == temp2[i]) continue;
                if (temp1[i] > temp2[i]) return -1;
                return 1;
            }
            return Integer.compare(first.ch, second.ch);
        }
    }
    public String rankTeams(String[] votes) {
        int n = votes.length;
        HashSet<Character> set = new HashSet<>();
        int score[] = new int[30];
        int freq[][] = new int[30][30];
        for (int i = 0; i < n; i++) {
            String current = votes[i];
            int it = 1;
            for (int j = current.length() - 1; j >= 0; j--) {
                freq[current.charAt(j) - 'A'][j + 1]++;
                score[current.charAt(j) - 'A'] += it;
                it++;
                set.add(current.charAt(j));
            }
        }
        ArrayList<Pair> res = new ArrayList<>();
        for (Character x : set) res.add(new Pair(x, score[x - 'A'], freq[x - 'A']));
        Collections.sort(res, new custom_sort());
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < res.size(); i++) ans.append(res.get(i).ch);
        return ans.toString();
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

