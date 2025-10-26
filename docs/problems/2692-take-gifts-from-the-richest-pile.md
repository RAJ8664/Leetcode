# 2692. Take Gifts From The Richest Pile

!!! success "Difficulty: Easy"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/take-gifts-from-the-richest-pile/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2692-take-gifts-from-the-richest-pile){ .md-button }

---

<h2><a href="https://leetcode.com/problems/take-gifts-from-the-richest-pile">2692. Take Gifts From the Richest Pile</a></h2><h3>Easy</h3><hr><p>You are given an integer array <code>gifts</code> denoting the number of gifts in various piles. Every second, you do the following:</p>

<ul>
	<li>Choose the pile with the maximum number of gifts.</li>
	<li>If there is more than one pile with the maximum number of gifts, choose any.</li>
	<li>Leave behind the floor of the square root of the number of gifts in the pile. Take the rest of the gifts.</li>
</ul>

<p>Return <em>the number of gifts remaining after </em><code>k</code><em> seconds.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> gifts = [25,64,9,4,100], k = 4
<strong>Output:</strong> 29
<strong>Explanation:</strong> 
The gifts are taken in the following way:
- In the first second, the last pile is chosen and 10 gifts are left behind.
- Then the second pile is chosen and 8 gifts are left behind.
- After that the first pile is chosen and 5 gifts are left behind.
- Finally, the last pile is chosen again and 3 gifts are left behind.
The final remaining gifts are [5,8,9,4,3], so the total number of gifts remaining is 29.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> gifts = [1,1,1,1], k = 4
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
In this case, regardless which pile you choose, you have to leave behind 1 gift in each pile. 
That is, you can&#39;t take any pile with you. 
So, the total gifts remaining are 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= gifts.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= gifts[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>3</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        long ans = 0;
        for (int i : gifts) {
            q.offer(i);
            ans += i;
        }
        while (k-- >0) {
            int v = q.poll();
            ans -= (v - (int) Math.sqrt(v));
            q.offer((int) Math.sqrt(v));
        }
        return ans;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

