# 2162. Partition Array Into Two Arrays To Minimize Sum Difference

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2162-partition-array-into-two-arrays-to-minimize-sum-difference){ .md-button }

---

<h2><a href="https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference">2162. Partition Array Into Two Arrays to Minimize Sum Difference</a></h2><h3>Hard</h3><hr><p>You are given an integer array <code>nums</code> of <code>2 * n</code> integers. You need to partition <code>nums</code> into <strong>two</strong> arrays of length <code>n</code> to <strong>minimize the absolute difference</strong> of the <strong>sums</strong> of the arrays. To partition <code>nums</code>, put each element of <code>nums</code> into <strong>one</strong> of the two arrays.</p>

<p>Return <em>the <strong>minimum</strong> possible absolute difference</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="example-1" src="https://assets.leetcode.com/uploads/2021/10/02/ex1.png" style="width: 240px; height: 106px;" />
<pre>
<strong>Input:</strong> nums = [3,9,7,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> One optimal partition is: [3,9] and [7,3].
The absolute difference between the sums of the arrays is abs((3 + 9) - (7 + 3)) = 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-36,36]
<strong>Output:</strong> 72
<strong>Explanation:</strong> One optimal partition is: [-36] and [36].
The absolute difference between the sums of the arrays is abs((-36) - (36)) = 72.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="example-3" src="https://assets.leetcode.com/uploads/2021/10/02/ex3.png" style="width: 316px; height: 106px;" />
<pre>
<strong>Input:</strong> nums = [2,-1,0,4,-2,-9]
<strong>Output:</strong> 0
<strong>Explanation:</strong> One optimal partition is: [2,4,-9] and [-1,0,-2].
The absolute difference between the sums of the arrays is abs((2 + 4 + -9) - (-1 + 0 + -2)) = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 15</code></li>
	<li><code>nums.length == 2 * n</code></li>
	<li><code>-10<sup>7</sup> &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    private HashMap<Integer, ArrayList<Integer>> firstMap;
    private HashMap<Integer, ArrayList<Integer>> secondMap;

    public int minimumDifference(int[] nums) {
        int n = nums.length;
        firstMap = new HashMap<>();
        secondMap = new HashMap<>();

        int[] first = Arrays.copyOfRange(nums, 0, n / 2);
        int[] second = Arrays.copyOfRange(nums, n / 2, n);

        build(0, first, 0, 0, 1);
        build(0, second, 0, 0, 2);

        int total = Arrays.stream(nums).sum();
        int mini = Integer.MAX_VALUE;

        for (int i = 0; i <= n / 2; i++) {
            ArrayList<Integer> leftSums = firstMap.getOrDefault(i, new ArrayList<>());
            ArrayList<Integer> rightSums = secondMap.getOrDefault(n / 2 - i, new ArrayList<>());

            Collections.sort(rightSums);

            for (int ele1 : leftSums) {
                int target = total / 2 - ele1;
                int idx = Collections.binarySearch(rightSums, target);

                if (idx < 0) idx = -idx - 1;

                if (idx < rightSums.size()) {
                    int currentSum = ele1 + rightSums.get(idx);
                    int rem = total - currentSum;
                    mini = Math.min(mini, Math.abs(currentSum - rem));
                }
                if (idx > 0) {
                    int currentSum = ele1 + rightSums.get(idx - 1);
                    int rem = total - currentSum;
                    mini = Math.min(mini, Math.abs(currentSum - rem));
                }
            }
        }

        return mini;
    }

    private void build(int ind, int[] arr, int count, int sum, int id) {
        if (ind == arr.length) {
            if (id == 1)
                firstMap.computeIfAbsent(count, k -> new ArrayList<>()).add(sum);
            else
                secondMap.computeIfAbsent(count, k -> new ArrayList<>()).add(sum);
            return;
        }
        build(ind + 1, arr, count + 1, sum + arr[ind], id);
        build(ind + 1, arr, count, sum, id);                
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

