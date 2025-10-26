# 2665. Minimum Time To Repair Cars

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/minimum-time-to-repair-cars/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2665-minimum-time-to-repair-cars){ .md-button }

---

<h2><a href="https://leetcode.com/problems/minimum-time-to-repair-cars">2665. Minimum Time to Repair Cars</a></h2><h3>Medium</h3><hr><p>You are given an integer array <code>ranks</code> representing the <strong>ranks</strong> of some mechanics. <font face="monospace">ranks<sub>i</sub></font> is the rank of the <font face="monospace">i<sup>th</sup></font> mechanic<font face="monospace">.</font> A mechanic with a rank <code>r</code> can repair <font face="monospace">n</font> cars in <code>r * n<sup>2</sup></code> minutes.</p>

<p>You are also given an integer <code>cars</code> representing the total number of cars waiting in the garage to be repaired.</p>

<p>Return <em>the <strong>minimum</strong> time taken to repair all the cars.</em></p>

<p><strong>Note:</strong> All the mechanics can repair the cars simultaneously.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> ranks = [4,2,3,1], cars = 10
<strong>Output:</strong> 16
<strong>Explanation:</strong> 
- The first mechanic will repair two cars. The time required is 4 * 2 * 2 = 16 minutes.
- The second mechanic will repair two cars. The time required is 2 * 2 * 2 = 8 minutes.
- The third mechanic will repair two cars. The time required is 3 * 2 * 2 = 12 minutes.
- The fourth mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> ranks = [5,1,8], cars = 6
<strong>Output:</strong> 16
<strong>Explanation:</strong> 
- The first mechanic will repair one car. The time required is 5 * 1 * 1 = 5 minutes.
- The second mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
- The third mechanic will repair one car. The time required is 8 * 1 * 1 = 8 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= ranks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= ranks[i] &lt;= 100</code></li>
	<li><code>1 &lt;= cars &lt;= 10<sup>6</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public long repairCars(int[] ranks, int cars) {
        int n = ranks.length;
        long ans = -1, low = 1, high = (long)(1e15);
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (ok(mid, ranks, cars)) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return ans;
    }
    private boolean ok(long mid, int ranks[] , int cars) {
        int n = ranks.length;
        long count = 1, total = 0;
        for (int i = 0; i < n; i++) {
            long current = ranks[i];
            total += (long)(Math.sqrt(mid / current));
        }
        return total >= cars;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

