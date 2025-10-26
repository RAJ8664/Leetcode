# 1055. Pairs Of Songs With Total Durations Divisible By 60

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1055-pairs-of-songs-with-total-durations-divisible-by-60){ .md-button }

---

<h2><a href="https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/?envType=problem-list-v2&envId=counting">1055. Pairs of Songs With Total Durations Divisible by 60</a></h2><h3>Medium</h3><hr><p>You are given a list of songs where the <code>i<sup>th</sup></code> song has a duration of <code>time[i]</code> seconds.</p>

<p>Return <em>the number of pairs of songs for which their total duration in seconds is divisible by</em> <code>60</code>. Formally, we want the number of indices <code>i</code>, <code>j</code> such that <code>i &lt; j</code> with <code>(time[i] + time[j]) % 60 == 0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> time = [30,20,150,100,40]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> time = [60,60,60]
<strong>Output:</strong> 3
<strong>Explanation:</strong> All three pairs have a total duration of 120, which is divisible by 60.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= time.length &lt;= 6 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= time[i] &lt;= 500</code></li>
</ul>


---

## Solution

```java
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int n = time.length;
        int freq[] = new int[1001];
        int count = 0;
        for (int i = 0; i < n; i++) {
            int current = time[i];
            for (int j = 60; j <= 1000; j += 60) if (j - current > 0) count += freq[j - current];
            freq[current]++;
        }
        return count;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

