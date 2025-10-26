# 852. Friends Of Appropriate Ages

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/friends-of-appropriate-ages/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0852-friends-of-appropriate-ages){ .md-button }

---

<h2><a href="https://leetcode.com/problems/friends-of-appropriate-ages">852. Friends Of Appropriate Ages</a></h2><h3>Medium</h3><hr><p>There are <code>n</code> persons on a social media website. You are given an integer array <code>ages</code> where <code>ages[i]</code> is the age of the <code>i<sup>th</sup></code> person.</p>

<p>A Person <code>x</code> will not send a friend request to a person <code>y</code> (<code>x != y</code>) if any of the following conditions is true:</p>

<ul>
	<li><code>age[y] &lt;= 0.5 * age[x] + 7</code></li>
	<li><code>age[y] &gt; age[x]</code></li>
	<li><code>age[y] &gt; 100 &amp;&amp; age[x] &lt; 100</code></li>
</ul>

<p>Otherwise, <code>x</code> will send a friend request to <code>y</code>.</p>

<p>Note that if <code>x</code> sends a request to <code>y</code>, <code>y</code> will not necessarily send a request to <code>x</code>. Also, a person will not send a friend request to themself.</p>

<p>Return <em>the total number of friend requests made</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> ages = [16,16]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 2 people friend request each other.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> ages = [16,17,18]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Friend requests are made 17 -&gt; 16, 18 -&gt; 17.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> ages = [20,30,100,110,120]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Friend requests are made 110 -&gt; 100, 120 -&gt; 110, 120 -&gt; 100.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == ages.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= ages[i] &lt;= 120</code></li>
</ul>


---

## Solution

```java
class Solution {
    public int numFriendRequests(int[] ages) {
        int n = ages.length, count = 0;
        Arrays.sort(ages);
        for (int i = 0; i < n / 2; i++) {
            int temp = ages[i];
            ages[i] = ages[n - 1 - i];
            ages[n - 1 - i] = temp;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int current = ages[i];
            int res = binarySearch(i, current, ages);
            if (!map.containsKey(current)) map.put(current, res);
        }
        for (int i = 0; i < n; i++) count += map.getOrDefault(ages[i], 0);
        return count;
    }
    private int binarySearch(int current_ind, int current, int ages[]) {
        int n = ages.length;
        int low = current_ind + 1, high = n - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ages[mid] <= current && ages[mid] > 0.5 * current + 7 && !(ages[mid] > 100 && current < 100)) {
                ans = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        if (ans == -1) return 0;
        return ans - current_ind;
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

