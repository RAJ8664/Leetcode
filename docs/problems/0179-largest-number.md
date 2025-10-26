# 179. Largest Number

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/largest-number/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0179-largest-number){ .md-button }

---

<h2><a href="https://leetcode.com/problems/largest-number">179. Largest Number</a></h2><h3>Medium</h3><hr><p>Given a list of non-negative integers <code>nums</code>, arrange them such that they form the largest number and return it.</p>

<p>Since the result may be very large, so you need to return a string instead of an integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,2]
<strong>Output:</strong> &quot;210&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,30,34,5,9]
<strong>Output:</strong> &quot;9534330&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    static class custom_sort implements Comparator<String> {
        @Override
        public int compare(String first, String second) {
            String a = first + second;
            String b = second + first;
            return b.compareTo(a);
        }
    }
    public String largestNumber(int[] nums) {
        int n = nums.length;
        ArrayList<String> res = new ArrayList<>();
        for (int ele : nums) res.add(String.valueOf(ele));
        Collections.sort(res, new custom_sort());
        if (res.size() > 0 && res.get(0).equals("0")) return "0";
        String ans = "";
        for (String temp : res) ans += temp;
        return ans;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

