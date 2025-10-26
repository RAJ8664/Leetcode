# 2338. Minimum Consecutive Cards To Pick Up

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2338-minimum-consecutive-cards-to-pick-up){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up">2338. Minimum Consecutive Cards to Pick Up</a></h2><h3>Medium</h3><hr><p>You are given an integer array <code>cards</code> where <code>cards[i]</code> represents the <strong>value</strong> of the <code>i<sup>th</sup></code> card. A pair of cards are <strong>matching</strong> if the cards have the <strong>same</strong> value.</p>

<p>Return<em> the <strong>minimum</strong> number of <strong>consecutive</strong> cards you have to pick up to have a pair of <strong>matching</strong> cards among the picked cards.</em> If it is impossible to have matching cards, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> cards = [3,4,2,3,4,7]
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can pick up the cards [3,4,2,3] which contain a matching pair of cards with value 3. Note that picking up the cards [4,2,3,4] is also optimal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> cards = [1,0,5,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no way to pick up a set of consecutive cards that contain a pair of matching cards.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= cards.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= cards[i] &lt;= 10<sup>6</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int minimumCardPickup(int[] cards) {
        int n = cards.length;
        int mini = Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int current = cards[i];
            if (map.containsKey(current)) {
                mini = Math.min(mini, i - map.get(current) + 1);
                map.put(current, i);
            }
            else {
                map.put(current, i);
            }
        }
        return mini == Integer.MAX_VALUE ? -1 : mini; 
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

