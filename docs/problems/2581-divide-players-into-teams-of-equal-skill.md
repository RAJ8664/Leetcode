# 2581. Divide Players Into Teams Of Equal Skill

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/divide-players-into-teams-of-equal-skill/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2581-divide-players-into-teams-of-equal-skill){ .md-button }

---

<h2><a href="https://leetcode.com/problems/divide-players-into-teams-of-equal-skill">2581. Divide Players Into Teams of Equal Skill</a></h2><h3>Medium</h3><hr><p>You are given a positive integer array <code>skill</code> of <strong>even</strong> length <code>n</code> where <code>skill[i]</code> denotes the skill of the <code>i<sup>th</sup></code> player. Divide the players into <code>n / 2</code> teams of size <code>2</code> such that the total skill of each team is <strong>equal</strong>.</p>

<p>The <strong>chemistry</strong> of a team is equal to the <strong>product</strong> of the skills of the players on that team.</p>

<p>Return <em>the sum of the <strong>chemistry</strong> of all the teams, or return </em><code>-1</code><em> if there is no way to divide the players into teams such that the total skill of each team is equal.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> skill = [3,2,5,1,3,4]
<strong>Output:</strong> 22
<strong>Explanation:</strong> 
Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where each team has a total skill of 6.
The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> skill = [3,4]
<strong>Output:</strong> 12
<strong>Explanation:</strong> 
The two players form a team with a total skill of 7.
The chemistry of the team is 3 * 4 = 12.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> skill = [1,1,2,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> 
There is no way to divide the players into teams such that the total skill of each team is equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= skill.length &lt;= 10<sup>5</sup></code></li>
	<li><code>skill.length</code> is even.</li>
	<li><code>1 &lt;= skill[i] &lt;= 1000</code></li>
</ul>


---

## Solution

```java
class Solution {
    public long dividePlayers(int[] skill) {
        int n = skill.length;
        Arrays.sort(skill);
        int sum = 0, count = 0;
        for (int ele : skill) sum += ele;
        if (sum % (n / 2) != 0) return -1;
        long ans = 0;
        int req = sum / ( n / 2);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : skill) map.put(ele, map.getOrDefault(ele, 0) + 1);
        for (int i = 0; i < n; i++) {
            int current = skill[i];
            if (map.getOrDefault(current, 0) <= 0) continue;
            int current_req = req - current;
            if (current_req < 0) continue;
            if (map.getOrDefault(current_req , 0) > 0) {
                count++;
                map.put(current_req, map.getOrDefault(current_req , 0) - 1);
                map.put(current, map.getOrDefault(current, 0) - 1);
                ans += (current * current_req);
            }
        }
        if (count == n / 2) return ans;
        return -1;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

