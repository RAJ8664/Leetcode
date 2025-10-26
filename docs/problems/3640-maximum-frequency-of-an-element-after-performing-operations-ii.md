# 3640. Maximum Frequency Of An Element After Performing Operations Ii

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-ii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3640-maximum-frequency-of-an-element-after-performing-operations-ii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-frequency-of-an-element-after-performing-operations-ii">3640. Maximum Frequency of an Element After Performing Operations II</a></h2><h3>Hard</h3><hr><p>You are given an integer array <code>nums</code> and two integers <code>k</code> and <code>numOperations</code>.</p>

<p>You must perform an <strong>operation</strong> <code>numOperations</code> times on <code>nums</code>, where in each operation you:</p>

<ul>
	<li>Select an index <code>i</code> that was <strong>not</strong> selected in any previous operations.</li>
	<li>Add an integer in the range <code>[-k, k]</code> to <code>nums[i]</code>.</li>
</ul>

<p>Return the <strong>maximum</strong> possible <span data-keyword="frequency-array">frequency</span> of any element in <code>nums</code> after performing the <strong>operations</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,5], k = 1, numOperations = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>We can achieve a maximum frequency of two by:</p>

<ul>
	<li>Adding 0 to <code>nums[1]</code>, after which <code>nums</code> becomes <code>[1, 4, 5]</code>.</li>
	<li>Adding -1 to <code>nums[2]</code>, after which <code>nums</code> becomes <code>[1, 4, 4]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,11,20,20], k = 5, numOperations = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>We can achieve a maximum frequency of two by:</p>

<ul>
	<li>Adding 0 to <code>nums[1]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= numOperations &lt;= nums.length</code></li>
</ul>


---

## Solution

```java
class Solution {
    
    static class Pair {
        int first, second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
        @Override
        public String toString() {
            return "(" + first + " " + second + ")";
        }
    }
   
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(first.first, second.first);
        }
    }

    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        HashMap<Integer, Integer> freq = new HashMap<>();
        ArrayList<Pair> res = new ArrayList<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
            res.add(new Pair(num - k, 1)); res.add(new Pair(num + 1 + k , -1));
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (Pair x : res) 
            set.add(x.first);
        for (int num : freq.keySet()) 
            set.add(num);
        Collections.sort(res, new custom_sort());
        int idx = 0, temp = 0, maxi = 0;
        for (int ele : set) {
            while (idx < res.size() && res.get(idx).first <= ele) {
                temp += res.get(idx).second;
                idx++;
            }
            int cnt = freq.getOrDefault(ele, 0);
            int curr = cnt + Math.min(numOperations, temp - cnt);
            maxi = Math.max(maxi, curr);
        }
        return maxi;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

