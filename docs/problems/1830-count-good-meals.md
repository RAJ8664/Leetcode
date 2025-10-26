# 1830. Count Good Meals

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/count-good-meals/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1830-count-good-meals){ .md-button }

---

<h2><a href="https://leetcode.com/problems/count-good-meals">1830. Count Good Meals</a></h2><h3>Medium</h3><hr><p>A <strong>good meal</strong> is a meal that contains <strong>exactly two different food items</strong> with a sum of deliciousness equal to a power of two.</p>

<p>You can pick <strong>any</strong> two different foods to make a good meal.</p>

<p>Given an array of integers <code>deliciousness</code> where <code>deliciousness[i]</code> is the deliciousness of the <code>i<sup>​​​​​​th</sup>​​​​</code>​​​​ item of food, return <em>the number of different <strong>good meals</strong> you can make from this list modulo</em> <code>10<sup>9</sup> + 7</code>.</p>

<p>Note that items with different indices are considered different even if they have the same deliciousness value.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> deliciousness = [1,3,5,7,9]
<strong>Output:</strong> 4
<strong>Explanation: </strong>The good meals are (1,3), (1,7), (3,5) and, (7,9).
Their respective sums are 4, 8, 8, and 16, all of which are powers of 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> deliciousness = [1,1,1,3,3,3,7]
<strong>Output:</strong> 15
<strong>Explanation: </strong>The good meals are (1,1) with 3 ways, (1,3) with 9 ways, and (1,7) with 3 ways.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= deliciousness.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= deliciousness[i] &lt;= 2<sup>20</sup></code></li>
</ul>


---

## Solution

```java
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    private int mod = 1000000007;
    public int countPairs(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0, pow2Count = 0;
        for (int i = 0; i < n; i++) {
            int current = arr[i];
            if (arr[i] == 0) {
                count = (count + pow2Count) % mod;
                map.put(0, map.getOrDefault(0, 0) + 1);
                continue;
            }
            if (current > 0 && (current & (current - 1)) == 0) {
                pow2Count++;
                count = (count + map.getOrDefault(current, 0)) % mod;
                count = (count + map.getOrDefault(0, 0)) % mod;
            } else {
                /* Find the first number which is power of 2 and is greater than current */
                int mask = 1;
                while (mask < current)
                    mask = mask << 1;
                int req = mask - current;
                count = (count + map.getOrDefault(req, 0)) % mod;
            }
            map.put(current, map.getOrDefault(current, 0) + 1);
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

