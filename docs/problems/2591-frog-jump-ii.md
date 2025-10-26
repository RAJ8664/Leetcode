# 2591. Frog Jump Ii

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/frog-jump-ii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2591-frog-jump-ii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/frog-jump-ii">2591. Frog Jump II</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> integer array <code>stones</code> sorted in <strong>strictly increasing order</strong> representing the positions of stones in a river.</p>

<p>A frog, initially on the first stone, wants to travel to the last stone and then return to the first stone. However, it can jump to any stone <strong>at most once</strong>.</p>

<p>The <strong>length</strong> of a jump is the absolute difference between the position of the stone the frog is currently on and the position of the stone to which the frog jumps.</p>

<ul>
	<li>More formally, if the frog is at <code>stones[i]</code> and is jumping to <code>stones[j]</code>, the length of the jump is <code>|stones[i] - stones[j]|</code>.</li>
</ul>

<p>The <strong>cost</strong> of a path is the <strong>maximum length of a jump</strong> among all jumps in the path.</p>

<p>Return <em>the <strong>minimum</strong> cost of a path for the frog</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/11/14/example-1.png" style="width: 600px; height: 219px;" />
<pre>
<strong>Input:</strong> stones = [0,2,5,6,7]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The above figure represents one of the optimal paths the frog can take.
The cost of this path is 5, which is the maximum length of a jump.
Since it is not possible to achieve a cost of less than 5, we return it.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/11/14/example-2.png" style="width: 500px; height: 171px;" />
<pre>
<strong>Input:</strong> stones = [0,3,9]
<strong>Output:</strong> 9
<strong>Explanation:</strong> 
The frog can jump directly to the last stone and come back to the first stone. 
In this case, the length of each jump will be 9. The cost for the path will be max(9, 9) = 9.
It can be shown that this is the minimum achievable cost.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= stones.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= stones[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>stones[0] == 0</code></li>
	<li><code>stones</code> is sorted in a strictly increasing order.</li>
</ul>


---

## Solution

```java
class Solution {
    public int maxJump(int[] stones) {
        int n = stones.length;
        int low = 0, high = (int)(1e9 + 10), ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, stones)) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }

    private boolean ok(int target, int arr[]) {
        int n = arr.length;
        int currIdx = 0;
        int vis[] = new int[n + 1];
        while (currIdx < n) {
            if (currIdx == n - 1)
                break;
            int low = currIdx + 1, high = n - 1, ans = -1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (Math.abs(arr[mid] - arr[currIdx]) <= target) {
                    ans = mid;
                    low = mid + 1;
                } else
                    high = mid - 1;
            }
            if (ans == -1)
                return false;
            currIdx = ans;
            vis[ans] = 1;
        }

        currIdx = n - 1;
        while (currIdx >= 0) {
            if (currIdx == 0)
                return true;
            int low = 0, high = currIdx - 1, ans = -1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (Math.abs(arr[mid] - arr[currIdx]) > target)
                    low = mid + 1;
                else if (Math.abs(arr[mid] - arr[currIdx]) <= target && vis[mid] == 0) {
                    ans = mid;
                    high = mid - 1;
                } else
                    low = mid + 1;
            }
            if (ans == -1)
                return false;
            currIdx = ans;
            vis[ans] = 1;
        }
        return true;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

