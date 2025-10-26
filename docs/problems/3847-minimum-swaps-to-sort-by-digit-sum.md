# 3847. Minimum Swaps To Sort By Digit Sum


---

<h2><a href="https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum">3847. Minimum Swaps to Sort by Digit Sum</a></h2><h3>Medium</h3><hr><p>You are given an array <code>nums</code> of <strong>distinct</strong> positive integers. You need to sort the array in <strong>increasing</strong> order based on the sum of the digits of each number. If two numbers have the same digit sum, the <strong>smaller</strong> number appears first in the sorted order.</p>

<p>Return the <strong>minimum</strong> number of swaps required to rearrange <code>nums</code> into this sorted order.</p>

<p>A <strong>swap</strong> is defined as exchanging the values at two distinct positions in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [37,100]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Compute the digit sum for each integer: <code>[3 + 7 = 10, 1 + 0 + 0 = 1] &rarr; [10, 1]</code></li>
	<li>Sort the integers based on digit sum: <code>[100, 37]</code>. Swap <code>37</code> with <code>100</code> to obtain the sorted order.</li>
	<li>Thus, the minimum number of swaps required to rearrange <code>nums</code> is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [22,14,33,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Compute the digit sum for each integer: <code>[2 + 2 = 4, 1 + 4 = 5, 3 + 3 = 6, 7 = 7] &rarr; [4, 5, 6, 7]</code></li>
	<li>Sort the integers based on digit sum: <code>[22, 14, 33, 7]</code>. The array is already sorted.</li>
	<li>Thus, the minimum number of swaps required to rearrange <code>nums</code> is 0.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [18,43,34,16]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Compute the digit sum for each integer: <code>[1 + 8 = 9, 4 + 3 = 7, 3 + 4 = 7, 1 + 6 = 7] &rarr; [9, 7, 7, 7]</code></li>
	<li>Sort the integers based on digit sum: <code>[16, 34, 43, 18]</code>. Swap <code>18</code> with <code>16</code>, and swap <code>43</code> with <code>34</code> to obtain the sorted order.</li>
	<li>Thus, the minimum number of swaps required to rearrange <code>nums</code> is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code> consists of <strong>distinct</strong> positive integers.</li>
</ul>


## Solution

```java
class Solution {
    static class Tuple {
        int ele, sum, idx;
        public Tuple(int ele, int sum, int idx) {
            this.ele = ele;
            this.sum = sum;
            this.idx = idx;
        }
        @Override
        public String toString() {
            return "(" + ele + " " + sum + " " + idx + ")";
        }
    }
    static class custom_sort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            int op1 = Integer.compare(first.sum, second.sum);
            if (op1 != 0) return op1;
            return Integer.compare(first.ele, second.ele);
        }
    }
    public int minSwaps(int[] nums) {
        int n = nums.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new custom_sort());
        for (int i = 0; i < n; i++) {
            pq.offer(new Tuple(nums[i], compute_sum(nums[i]), i));
        }
        int count = 0, index = 0;
        int arr[] = new int[n];
        int loc[] = new int[n];
        while (pq.size() > 0) {
            int curr_ele = pq.peek().ele, curr_sum = pq.peek().sum , curr_idx = pq.peek().idx;
            arr[index++] = curr_idx;
            pq.poll();
        }
        int req[] = new int[n];
        for (int i = 0; i < n; i++) req[i] = arr[i];
        Arrays.sort(req);
        for (int i = 0; i < n; i++) loc[arr[i]] = i;
        for (int i = 0; i < n; i++) {
            if (arr[i] != req[i]) {
                count++;
                int idxx = loc[req[i]];
                loc[arr[i]] = idxx;
                int temp = arr[i];
                arr[i] = arr[idxx];
                arr[idxx] = temp;
            }
        }
        return count;
    }
    private int compute_sum(int n) {
        int res = 0;
        int temp = n;
        while (temp > 0) {
            res += temp % 10;
            temp /= 10;
        }
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

