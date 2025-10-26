# 480. Sliding Window Median

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/sliding-window-median/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0480-sliding-window-median){ .md-button }

---

<h2><a href="https://leetcode.com/problems/sliding-window-median">480. Sliding Window Median</a></h2><h3>Hard</h3><hr><p>The <strong>median</strong> is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.</p>

<ul>
	<li>For examples, if <code>arr = [2,<u>3</u>,4]</code>, the median is <code>3</code>.</li>
	<li>For examples, if <code>arr = [1,<u>2,3</u>,4]</code>, the median is <code>(2 + 3) / 2 = 2.5</code>.</li>
</ul>

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. There is a sliding window of size <code>k</code> which is moving from the very left of the array to the very right. You can only see the <code>k</code> numbers in the window. Each time the sliding window moves right by one position.</p>

<p>Return <em>the median array for each window in the original array</em>. Answers within <code>10<sup>-5</sup></code> of the actual value will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,-1,-3,5,3,6,7], k = 3
<strong>Output:</strong> [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
<strong>Explanation:</strong> 
Window position                Median
---------------                -----
[<strong>1  3  -1</strong>] -3  5  3  6  7        1
 1 [<strong>3  -1  -3</strong>] 5  3  6  7       -1
 1  3 [<strong>-1  -3  5</strong>] 3  6  7       -1
 1  3  -1 [<strong>-3  5  3</strong>] 6  7        3
 1  3  -1  -3 [<strong>5  3  6</strong>] 7        5
 1  3  -1  -3  5 [<strong>3  6  7</strong>]       6
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,2,3,1,4,2], k = 3
<strong>Output:</strong> [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>


---

## Solution

```java
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Comparator<Integer> comparator = new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                if(nums[a] != nums[b]) return Integer.compare(nums[a), nums[b]);
                else return a - b;
            }
        };
        TreeSet<Integer> maxSet = new TreeSet<>(comparator.reversed());
        TreeSet<Integer> minSet = new TreeSet<>(comparator);
        double res[] = new double[n - k + 1];
        int current_idx = 0;
        for (int i = 0; i < k; i++) {
            maxSet.add(i);
            minSet.add(maxSet.pollFirst());
            if (minSet.size() > maxSet.size()) maxSet.add(minSet.pollFirst());
        }
        res[current_idx++] = getMedian(minSet, maxSet, nums);
        int start = 0;
        for (int i = k; i < n; i++) {
            if (minSet.contains(start)) minSet.remove(start);
            if (maxSet.contains(start)) maxSet.remove(start);
            start++;
            maxSet.add(i);
            minSet.add(maxSet.pollFirst());
            if (minSet.size() > maxSet.size()) maxSet.add(minSet.pollFirst());
            res[current_idx++] = getMedian(minSet, maxSet, nums);
        }
        return res;
    }
    private double getMedian(TreeSet<Integer> minSet, TreeSet<Integer> maxSet, int nums[]) {
        if (minSet.size() == maxSet.size()) {
            double current_res = nums[maxSet.first()] * 1.0 + nums[minSet.first()] * 1.0;
            current_res = current_res / 2 * 1.0;
            return current_res;
        }
        return (double)(nums[maxSet.first()] * 1.0);
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

