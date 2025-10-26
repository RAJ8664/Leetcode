# 1262. Online Majority Element In Subarray

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/online-majority-element-in-subarray/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1262-online-majority-element-in-subarray){ .md-button }

---

<h2><a href="https://leetcode.com/problems/online-majority-element-in-subarray">1262. Online Majority Element In Subarray</a></h2><h3>Hard</h3><hr><p>Design a data structure that efficiently finds the <strong>majority element</strong> of a given subarray.</p>

<p>The <strong>majority element</strong> of a subarray is an element that occurs <code>threshold</code> times or more in the subarray.</p>

<p>Implementing the <code>MajorityChecker</code> class:</p>

<ul>
	<li><code>MajorityChecker(int[] arr)</code> Initializes the instance of the class with the given array <code>arr</code>.</li>
	<li><code>int query(int left, int right, int threshold)</code> returns the element in the subarray <code>arr[left...right]</code> that occurs at least <code>threshold</code> times, or <code>-1</code> if no such element exists.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MajorityChecker&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;]
[[[1, 1, 2, 2, 1, 1]], [0, 5, 4], [0, 3, 3], [2, 3, 2]]
<strong>Output</strong>
[null, 1, -1, 2]

<strong>Explanation</strong>
MajorityChecker majorityChecker = new MajorityChecker([1, 1, 2, 2, 1, 1]);
majorityChecker.query(0, 5, 4); // return 1
majorityChecker.query(0, 3, 3); // return -1
majorityChecker.query(2, 3, 2); // return 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= left &lt;= right &lt; arr.length</code></li>
	<li><code>threshold &lt;= right - left + 1</code></li>
	<li><code>2 * threshold &gt; right - left + 1</code></li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>query</code>.</li>
</ul>


---

## Solution

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;

class MajorityChecker {
    private HashMap<Integer, ArrayList<Integer >> map;
    private int nums[];
    public MajorityChecker(int[] arr) {
        int n = arr.length;
        map = new HashMap<>();
        nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = arr[i];
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i]))
                map.put(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }
    }

    public int query(int left, int right, int threshold) {
        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int index = rand.nextInt(right - left + 1) + left;
            int val = nums[index];
            ArrayList<Integer> idx = new ArrayList<>();
            idx = map.get(val);
            if (bsRight(idx, right) - bsLeft(idx, left) + 1 >= threshold)
                return val;
        }
        return -1;
    }

    private int bsLeft(ArrayList<Integer> arr, int left) {
        int n = arr.size();
        int low = 0, high = n - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid) >= left) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }

    private int bsRight(ArrayList<Integer> arr, int right) {
        int n = arr.size();
        int low = 0, high = n - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid) <= right) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return ans;
    }
}

/**
    Your MajorityChecker object will be instantiated and called as such:
    MajorityChecker obj = new MajorityChecker(arr);
    int param_1 = obj.query(left,right,threshold);
*/
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

