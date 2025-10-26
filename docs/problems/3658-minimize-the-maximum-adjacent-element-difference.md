# 3658. Minimize The Maximum Adjacent Element Difference


---

<h2><a href="https://leetcode.com/problems/minimize-the-maximum-adjacent-element-difference">3658. Minimize the Maximum Adjacent Element Difference</a></h2><h3>Hard</h3><hr><p>You are given an array of integers <code>nums</code>. Some values in <code>nums</code> are <strong>missing</strong> and are denoted by -1.</p>

<p>You can choose a pair of <strong>positive</strong> integers <code>(x, y)</code> <strong>exactly once</strong> and replace each&nbsp;<strong>missing</strong> element with <em>either</em> <code>x</code> or <code>y</code>.</p>

<p>You need to <strong>minimize</strong><strong> </strong>the<strong> maximum</strong> <strong>absolute difference</strong> between <em>adjacent</em> elements of <code>nums</code> after replacements.</p>

<p>Return the <strong>minimum</strong> possible difference.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,-1,10,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>By choosing the pair as <code>(6, 7)</code>, nums can be changed to <code>[1, 2, 6, 10, 8]</code>.</p>

<p>The absolute differences between adjacent elements are:</p>

<ul>
	<li><code>|1 - 2| == 1</code></li>
	<li><code>|2 - 6| == 4</code></li>
	<li><code>|6 - 10| == 4</code></li>
	<li><code>|10 - 8| == 2</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,-1,-1]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>By choosing the pair as <code>(4, 4)</code>, nums can be changed to <code>[4, 4, 4]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,10,-1,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>By choosing the pair as <code>(11, 9)</code>, nums can be changed to <code>[11, 10, 9, 8]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either -1 or in the range <code>[1, 10<sup>9</sup>]</code>.</li>
</ul>


## Solution

```java
class Solution {
    public int minDifference(int[] arr) {
        int n = arr.length;
        List<Integer> nums = new ArrayList<>();
        int maxi = Integer.MIN_VALUE, mini = Integer.MAX_VALUE;
        int count = 0;
        for (int ele : arr) {
            maxi = Math.max(maxi, ele);
            if (ele != -1) mini = Math.min(mini, ele);
            if (ele == -1) count++;
            nums.add(ele);
        }
        if (count == n) return 0;
        if (count == 0) {
            maxi = 0;
            for (int i = 0; i < n - 1; i++) maxi = Math.max(maxi, Math.abs(arr[i] - arr[i + 1]));
            return maxi;
        }
        int low = 0, high = (int)(1000000005), ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, new ArrayList<>(nums))) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return ans;
    }
    private boolean ok(int mid, ArrayList<Integer> nums) {
        int n = nums.size();
        int mini = Integer.MAX_VALUE, maxi = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) != -1 && 
                ((i > 0 && nums.get(i - 1) == -1) || (i < nums.size() - 1 && nums.get(i + 1) == -1))) {
                mini = Math.min(mini, nums.get(i) - mid);
                maxi = Math.max(maxi, nums.get(i) + mid);
            }
        }
        if (maxi == Integer.MIN_VALUE || mini == Integer.MAX_VALUE) return true;
        mini += 2 * mid;
        maxi -= 2 * mid;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == -1) {
                boolean current = 
                    (i == 0 || Math.abs(nums.get(i - 1) - mini) <= mid) &&
                    (i == nums.size() - 1 || nums.get(i + 1) == -1 || Math.abs(nums.get(i + 1) - mini) <= mid);
                if (current == true) nums.set(i, mini);
                else nums.set(i, maxi);
            }
        }
        for (int i = 0; i < nums.size() - 1; i++) {
            if (Math.abs(nums.get(i) - nums.get(i + 1)) > mid) return false;
        }
        return true;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

