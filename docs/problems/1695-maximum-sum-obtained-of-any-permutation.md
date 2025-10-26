# 1695. Maximum Sum Obtained Of Any Permutation

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-sum-obtained-of-any-permutation/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1695-maximum-sum-obtained-of-any-permutation){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-sum-obtained-of-any-permutation">1695. Maximum Sum Obtained of Any Permutation</a></h2><h3>Medium</h3><hr><p>We have an array of integers, <code>nums</code>, and an array of <code>requests</code> where <code>requests[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>. The <code>i<sup>th</sup></code> request asks for the sum of <code>nums[start<sub>i</sub>] + nums[start<sub>i</sub> + 1] + ... + nums[end<sub>i</sub> - 1] + nums[end<sub>i</sub>]</code>. Both <code>start<sub>i</sub></code> and <code>end<sub>i</sub></code> are <em>0-indexed</em>.</p>

<p>Return <em>the maximum total sum of all requests <strong>among all permutations</strong> of</em> <code>nums</code>.</p>

<p>Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5], requests = [[1,3],[0,1]]
<strong>Output:</strong> 19
<strong>Explanation:</strong> One permutation of nums is [2,1,3,4,5] with the following result: 
requests[0] -&gt; nums[1] + nums[2] + nums[3] = 1 + 3 + 4 = 8
requests[1] -&gt; nums[0] + nums[1] = 2 + 1 = 3
Total sum: 8 + 3 = 11.
A permutation with a higher total sum is [3,5,4,2,1] with the following result:
requests[0] -&gt; nums[1] + nums[2] + nums[3] = 5 + 4 + 2 = 11
requests[1] -&gt; nums[0] + nums[1] = 3 + 5  = 8
Total sum: 11 + 8 = 19, which is the best that you can do.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6], requests = [[0,1]]
<strong>Output:</strong> 11
<strong>Explanation:</strong> A permutation with the max total sum is [6,5,4,3,2,1] with request sums [11].</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,10], requests = [[0,2],[1,3],[1,1]]
<strong>Output:</strong> 47
<strong>Explanation:</strong> A permutation with the max total sum is [4,10,5,3,2,1] with request sums [19,18,10].</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i]&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= requests.length &lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>requests[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub>&nbsp;&lt;= end<sub>i</sub>&nbsp;&lt;&nbsp;n</code></li>
</ul>


---

## Solution

```java
class Solution {
    private final int mod = (int)(1e9 + 7);
    private int freq[];
    static class Pair {
        int node, idx;
        public Pair(int node, int idx) {
            this.node = node;
            this.idx = idx;
        }
        @Override
        public String toString() {
            return "(" + node + " " + idx + ")";
        }
    }
    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(second.node, first.node);
        }
    }
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length;
        freq = new int[n];

        for (int query[] : requests) {
            int l = query[0], r = query[1];
            freq[l]++; if (r + 1 < n) freq[r + 1]--;
        }

        for (int i = 1; i < n; i++) 
            freq[i] += freq[i - 1];

        ArrayList<Pair> maxFreq = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            maxFreq.add(new Pair(freq[i], i));
        }
        Collections.sort(maxFreq, new customSort()); 

        ArrayList<Integer> values = new ArrayList<>();
        for (int ele : nums)
            values.add(ele);
        Collections.sort(values);
        int idx = values.size() - 1;

        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[maxFreq.get(i).idx] = values.get(idx--);

        int pref[] = new int[n];
        pref[0] = arr[0];
        for (int i = 1; i < n; i++) 
            pref[i] += pref[i - 1] + arr[i];

        long res = 0;
        for (int query[] : requests) {
            int l = query[0], r = query[1];
            long total = pref[r] * 1L;
            if (l - 1 >= 0) {
                total -= pref[l - 1] * 1L;
            }
            res = (res + total) % mod;
        }
        return (int)(res);    
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

