# 905. Length Of Longest Fibonacci Subsequence

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0905-length-of-longest-fibonacci-subsequence){ .md-button }

---

<h2><a href="https://leetcode.com/problems/length-of-longest-fibonacci-subsequence">905. Length of Longest Fibonacci Subsequence</a></h2><h3>Medium</h3><hr><p>A sequence <code>x<sub>1</sub>, x<sub>2</sub>, ..., x<sub>n</sub></code> is <em>Fibonacci-like</em> if:</p>

<ul>
	<li><code>n &gt;= 3</code></li>
	<li><code>x<sub>i</sub> + x<sub>i+1</sub> == x<sub>i+2</sub></code> for all <code>i + 2 &lt;= n</code></li>
</ul>

<p>Given a <b>strictly increasing</b> array <code>arr</code> of positive integers forming a sequence, return <em>the <strong>length</strong> of the longest Fibonacci-like subsequence of</em> <code>arr</code>. If one does not exist, return <code>0</code>.</p>

<p>A <strong>subsequence</strong> is derived from another sequence <code>arr</code> by deleting any number of elements (including none) from <code>arr</code>, without changing the order of the remaining elements. For example, <code>[3, 5, 8]</code> is a subsequence of <code>[3, 4, 5, 6, 7, 8]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,4,5,6,7,8]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The longest subsequence that is fibonacci-like: [1,2,3,5,8].</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,3,7,11,12,14,18]
<strong>Output:</strong> 3
<strong>Explanation</strong>:<strong> </strong>The longest subsequence that is fibonacci-like: [1,11,12], [3,11,14] or [7,11,18].</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt; arr[i + 1] &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    private HashMap<Integer, TreeSet<Integer>> map;
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int maxi = 0;
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                TreeSet<Integer> temp = new TreeSet<>();
                temp = map.get(arr[i]);
                temp.add(i);
                map.put(arr[i] , new TreeSet<>(temp));
            }
            else {
                map.put(arr[i] , new TreeSet<Integer>());
                TreeSet<Integer> temp = new TreeSet<>();
                temp.add(i);
                map.put(arr[i] , new TreeSet<>(temp)); 
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = arr[i] , b = arr[j];
                int count = 2, prev_ind = j;
                while (true) {
                    //check find the first index greater than prev_ind which is equal to a + b;
                    int res = check(a + b , prev_ind, n - 1);
                    if (res == -1) break;
                    else {
                        prev_ind = res;
                        int search = a + b;
                        a = b;
                        b = search;
                        count++;
                    }
                }
                if (count >= 3) maxi = Math.max(maxi, count);
            }
        }
        return maxi;
    }
    private int check(int target, int low, int high) {
        if (map.containsKey(target)) {
            TreeSet<Integer> current = map.get(target);
            if (current.higher(low) != null) return current.higher(low);
            return -1;
        }
        return -1;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

