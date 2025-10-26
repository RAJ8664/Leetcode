# 901. Advantage Shuffle

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/advantage-shuffle/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0901-advantage-shuffle){ .md-button }

---

<h2><a href="https://leetcode.com/problems/advantage-shuffle">901. Advantage Shuffle</a></h2><h3>Medium</h3><hr><p>You are given two integer arrays <code>nums1</code> and <code>nums2</code> both of the same length. The <strong>advantage</strong> of <code>nums1</code> with respect to <code>nums2</code> is the number of indices <code>i</code> for which <code>nums1[i] &gt; nums2[i]</code>.</p>

<p>Return <em>any permutation of </em><code>nums1</code><em> that maximizes its <strong>advantage</strong> with respect to </em><code>nums2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums1 = [2,7,11,15], nums2 = [1,10,4,11]
<strong>Output:</strong> [2,11,7,15]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums1 = [12,24,8,32], nums2 = [13,25,32,11]
<strong>Output:</strong> [24,32,8,12]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums2.length == nums1.length</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int ans[] = new int[n];
        TreeSet<Integer> set = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums1) {
            set.add(ele);
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int current_ele = nums2[i];
            Integer next = set.higher(current_ele);
            if (next != null) {
                ans[i] = next;
                map.put(next, map.getOrDefault(next, 0) -1);
                if (map.getOrDefault(next, 0) == 0) set.remove(next);
            }
            else {
                ans[i] = set.first();
                map.put(set.first(), map.getOrDefault(set.first(), 0) -1);
                if (map.getOrDefault(set.first(), 0) == 0) set.remove(set.first());
            }
        }
        return ans;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

