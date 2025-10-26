# 4000. Count Bowl Subarrays


---

<h2><a href="https://leetcode.com/problems/count-bowl-subarrays">4000. Count Bowl Subarrays</a></h2><h3>Medium</h3><hr><p>You are given an integer array <code>nums</code> with <strong>distinct</strong> elements.</p>

<p>A <span data-keyword="subarray">subarray</span> <code>nums[l...r]</code> of <code>nums</code> is called a <strong>bowl</strong> if:</p>

<ul>
	<li>The subarray has length at least 3. That is, <code>r - l + 1 &gt;= 3</code>.</li>
	<li>The <strong>minimum</strong> of its two ends is <strong>strictly greater</strong> than the <strong>maximum</strong> of all elements in between. That is, <code>min(nums[l], nums[r]) &gt; max(nums[l + 1], ..., nums[r - 1])</code>.</li>
</ul>

<p>Return the number of <strong>bowl</strong> subarrays in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,5,3,1,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The bowl subarrays are <code>[3, 1, 4]</code> and <code>[5, 3, 1, 4]</code>.</p>

<ul>
	<li><code>[3, 1, 4]</code> is a bowl because <code>min(3, 4) = 3 &gt; max(1) = 1</code>.</li>
	<li><code>[5, 3, 1, 4]</code> is a bowl because <code>min(5, 4) = 4 &gt; max(3, 1) = 3</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The bowl subarrays are <code>[5, 1, 2]</code>, <code>[5, 1, 2, 3]</code> and <code>[5, 1, 2, 3, 4]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = </span>[1000000000,999999999,999999998]</p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>No subarray is a bowl.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code> consists of distinct elements.</li>
</ul>


## Solution

```java
class Solution {
    private int nextGreater[];
    private int prevGreater[];
    static class Pair {
        int node, idx;
        public Pair(int node, int idx) {
            this.node = node;
            this.idx = idx;
        }
    }
    public long bowlSubarrays(int[] nums) {
        int n = nums.length;

        buildNextGreater(nums);
        buildPrevGreater(nums);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (nextGreater[i] != -1 && prevGreater[i] != -1) 
                ans++;
        } 
        return ans;
    }

    private void buildNextGreater(int arr[]) {
        int n = arr.length;
        nextGreater = new int[n];
        Stack<Pair> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            int current = arr[i];
            while (st.size() > 0 && st.peek().node <= current) {
                st.pop();
            }
            if (st.size() == 0)
                nextGreater[i] = -1;
            else 
                nextGreater[i] = st.peek().idx;
            st.add(new Pair(current, i));
        }
    }
    
    private void buildPrevGreater(int arr[]) {
        int n = arr.length;
        prevGreater = new int[n];
        Stack<Pair> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            int current = arr[i];
            while (st.size() > 0 && st.peek().node <= current) 
                st.pop();
            if (st.size() == 0)
                prevGreater[i] = -1;
            else 
                prevGreater[i] = st.peek().idx;
            st.add(new Pair(current, i));
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

