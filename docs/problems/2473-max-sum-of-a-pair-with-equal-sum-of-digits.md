# 2473. Max Sum Of A Pair With Equal Sum Of Digits

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2473-max-sum-of-a-pair-with-equal-sum-of-digits){ .md-button }

---

<h2><a href="https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits">2473. Max Sum of a Pair With Equal Sum of Digits</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> array <code>nums</code> consisting of <strong>positive</strong> integers. You can choose two indices <code>i</code> and <code>j</code>, such that <code>i != j</code>, and the sum of digits of the number <code>nums[i]</code> is equal to that of <code>nums[j]</code>.</p>

<p>Return <em>the <strong>maximum</strong> value of </em><code>nums[i] + nums[j]</code><em> that you can obtain over all possible indices </em><code>i</code><em> and </em><code>j</code><em> that satisfy the conditions.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [18,43,36,13,7]
<strong>Output:</strong> 54
<strong>Explanation:</strong> The pairs (i, j) that satisfy the conditions are:
- (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
- (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
So the maximum sum that we can obtain is 54.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,12,19,14]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are no two numbers that satisfy the conditions, so we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public int maximumSum(int[] nums) {
        int n = nums.length;
        HashMap<Integer, MultiSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int current = nums[i], sum = 0;
            while (current > 0) {
                sum += current % 10;
                current /= 10;
            }
            if (!map.containsKey(sum)) map.put(sum, new MultiSet<>());
            map.get(sum).add(nums[i]);
        }
        int maxi = 0;
        for (Map.Entry<Integer, MultiSet<Integer>> x : map.entrySet()) {
            MultiSet<Integer> temp = x.getValue();
            if (temp.size <= 1) continue;
            int current_sum = temp.last();
            temp.remove(temp.last());
            current_sum += temp.last();
            maxi = Math.max(maxi, current_sum);
        } 
        if (maxi == 0) return -1;
        return maxi;
    }
    static class MultiSet<T> {
        TreeMap<T, Integer> frequency;
        TreeSet<T> set;
        int size;
        public MultiSet() {
            set = new TreeSet<>();
            frequency = new TreeMap<>();
            size = 0;
        }
        public void add(T elem) {
            if (frequency.get(elem) == null || frequency.get(elem) == 0) {
                frequency.put(elem, 0);
                set.add(elem);
            }
            frequency.put(elem, frequency.get(elem) + 1);
            size++;
        }
        public void remove(T elem) {
            if (!set.contains(elem)) return;
            frequency.put(elem, frequency.get(elem) - 1);
            if (frequency.get(elem) == 0) {
                set.remove(elem);
                frequency.remove(elem);
            }
            size--;
        }
        public T last() { return set.last(); }
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

