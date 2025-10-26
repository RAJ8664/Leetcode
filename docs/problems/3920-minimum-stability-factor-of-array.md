# 3920. Minimum Stability Factor Of Array


---

<h2><a href="https://leetcode.com/problems/minimum-stability-factor-of-array">3920. Minimum Stability Factor of Array</a></h2><h3>Hard</h3><hr><p>You are given an integer array <code>nums</code> and an integer <code>maxC</code>.</p>

<p>A <strong><span data-keyword="subarray">subarray</span></strong> is called <strong>stable</strong> if the <em>highest common factor (HCF)</em> of all its elements is <strong>greater than or equal to</strong> 2.</p>

<p>The <strong>stability factor</strong> of an array is defined as the length of its <strong>longest</strong> stable subarray.</p>

<p>You may modify <strong>at most</strong> <code>maxC</code> elements of the array to any integer.</p>

<p>Return the <strong>minimum</strong> possible stability factor of the array after at most <code>maxC</code> modifications. If no stable subarray remains, return 0.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>The <strong>highest common factor (HCF)</strong> of an array is the largest integer that evenly divides all the array elements.</li>
	<li>A <strong>subarray</strong> of length 1 is stable if its only element is greater than or equal to 2, since <code>HCF([x]) = x</code>.</li>
</ul>

<div class="notranslate" style="all: initial;"> </div>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,5,10], maxC = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The stable subarray <code>[5, 10]</code> has <code>HCF = 5</code>, which has a stability factor of 2.</li>
	<li>Since <code>maxC = 1</code>, one optimal strategy is to change <code>nums[1]</code> to <code>7</code>, resulting in <code>nums = [3, 7, 10]</code>.</li>
	<li>Now, no subarray of length greater than 1 has <code>HCF &gt;= 2</code>. Thus, the minimum possible stability factor is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,6,8], maxC = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The subarray <code>[2, 6, 8]</code> has <code>HCF = 2</code>, which has a stability factor of 3.</li>
	<li>Since <code>maxC = 2</code>, one optimal strategy is to change <code>nums[1]</code> to 3 and <code>nums[2]</code> to 5, resulting in <code>nums = [2, 3, 5]</code>.</li>
	<li>Now, no subarray of length greater than 1 has <code>HCF &gt;= 2</code>. Thus, the minimum possible stability factor is 1.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,9,6], maxC = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The stable subarrays are:
	<ul>
		<li><code>[2, 4]</code> with <code>HCF = 2</code> and stability factor of 2.</li>
		<li><code>[9, 6]</code> with <code>HCF = 3</code> and stability factor of 2.</li>
	</ul>
	</li>
	<li>Since <code>maxC = 1</code>, the stability factor of 2 cannot be reduced due to two separate stable subarrays. Thus, the minimum possible stability factor is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= maxC &lt;= n</code></li>
</ul>


## Solution

```java
class Solution {
    public int minStable(int[] nums, int maxC) {
        int n = nums.length;
        if (maxC == 0)
            return find(nums);
        int low = 1, high = n, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid, maxC, nums)) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        if (ans == -1)
            return 1;
        return ans - 1;
    }
    private boolean ok(int mid, int maxC, int arr[]) {
        int n = arr.length;
        int count = 0, i = 0;
        SparseGcd gcd = new SparseGcd(arr);
        while (i < n) {
            if (i + mid - 1 >= n)
                break;
            if (gcd.query(i, i + mid - 1) >= 2) {
                count++;
                i = i + mid;
            } else
                i++;
        }
        return count <= maxC;
    }
    private int find(int arr[]) {
        int n = arr.length;
        int count1 = 0;
        for (int ele : arr)
            if (ele == 1)
                count1++;
        if (count1 == n)
            return 0;
        int low = 1, high = n, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(mid, arr)) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return ans;
    }
    private boolean check(int mid, int arr[]) {
        SparseGcd gcd = new SparseGcd(arr);
        for (int i = 0; i < arr.length; i++) {
            if (i + mid - 1 < arr.length) {
                if (gcd.query(i, i + mid - 1) >= 2)
                    return true;
            }
        }
        return false;
    }
    static class SparseGcd {
        private int[][] sparseTable;
        private int n;

        private int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        public SparseGcd(int[] arr) {
            this.n = arr.length;
            int maxLog = (int)(Math.log(n) / Math.log(2)) + 1;
            this.sparseTable = new int[n][maxLog];

            for (int i = 0; i < n; i++)
                sparseTable[i][0] = arr[i];

            for (int j = 1; (1 << j) <= n; j++) {
                for (int i = 0; (i + (1 << j)) <= n; i++)
                    sparseTable[i][j] = gcd(sparseTable[i][j - 1], sparseTable[i + (1 << (j - 1))][j - 1]);
            }
        }

        public int query(int left, int right) {
            if (left < 0 || right >= n || left > right)
                throw new IllegalArgumentException("Invalid range");
            int j = (int)(Math.log(right - left + 1) / Math.log(2));
            return gcd(sparseTable[left][j], sparseTable[right - (1 << j) + 1][j]);
        }
    }


}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

