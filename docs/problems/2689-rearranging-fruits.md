# 2689. Rearranging Fruits

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/rearranging-fruits/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2689-rearranging-fruits){ .md-button }

---

<h2><a href="https://leetcode.com/problems/rearranging-fruits">2689. Rearranging Fruits</a></h2><h3>Hard</h3><hr><p>You have two fruit baskets containing <code>n</code> fruits each. You are given two <strong>0-indexed</strong> integer arrays <code>basket1</code> and <code>basket2</code> representing the cost of fruit in each basket. You want to make both baskets <strong>equal</strong>. To do so, you can use the following operation as many times as you want:</p>

<ul>
	<li>Chose two indices <code>i</code> and <code>j</code>, and swap the <code>i<font size="1">th</font>&nbsp;</code>fruit of <code>basket1</code> with the <code>j<font size="1">th</font></code>&nbsp;fruit of <code>basket2</code>.</li>
	<li>The cost of the swap is <code>min(basket1[i],basket2[j])</code>.</li>
</ul>

<p>Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.</p>

<p>Return <em>the minimum cost to make both the baskets equal or </em><code>-1</code><em> if impossible.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> basket1 = [4,2,2,2], basket2 = [1,4,1,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Swap index 1 of basket1 with index 0 of basket2, which has cost 1. Now basket1 = [4,1,2,2] and basket2 = [2,4,1,2]. Rearranging both the arrays makes them equal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> basket1 = [2,3,4,1], basket2 = [3,2,5,1]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be shown that it is impossible to make both the baskets equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>basket1.length == basket2.length</code></li>
	<li><code>1 &lt;= basket1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= basket1[i],basket2[i]&nbsp;&lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    public long minCost(int[] arr1, int[] arr2) {
        int n = arr1.length;
        HashMap<Integer, Integer> mp = new HashMap<>();
        int minVal = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            mp.put(arr1[i], mp.getOrDefault(arr1[i], 0) + 1);
            minVal = Math.min(minVal, arr1[i]);
        }

        for (int i = 0; i < n; i++) {
            mp.put(arr2[i], mp.getOrDefault(arr2[i], 0) - 1);
            minVal = Math.min(minVal, arr2[i]);
        }

        List<Integer> diffList = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            int key = entry.getKey();
            int freq = entry.getValue();
            if (freq % 2 != 0) return -1;
            int count = Math.abs(freq) / 2;
            for (int i = 0; i < count; i++) {
                diffList.add(key);
            }
        }

        Collections.sort(diffList);
        int size = diffList.size() / 2;
        long cost = 0;

        for (int i = 0; i < size; i++) {
            cost += Math.min(2 * minVal, diffList.get(i));
        }

        return cost;
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

