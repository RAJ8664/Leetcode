# 3592. Find X Sum Of All K Long Subarrays Ii

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-ii/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3592-find-x-sum-of-all-k-long-subarrays-ii){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-ii">3592. Find X-Sum of All K-Long Subarrays II</a></h2><h3>Hard</h3><hr><p>You are given an array <code>nums</code> of <code>n</code> integers and two integers <code>k</code> and <code>x</code>.</p>

<p>The <strong>x-sum</strong> of an array is calculated by the following procedure:</p>

<ul>
	<li>Count the occurrences of all elements in the array.</li>
	<li>Keep only the occurrences of the top <code>x</code> most frequent elements. If two elements have the same number of occurrences, the element with the <strong>bigger</strong> value is considered more frequent.</li>
	<li>Calculate the sum of the resulting array.</li>
</ul>

<p><strong>Note</strong> that if an array has less than <code>x</code> distinct elements, its <strong>x-sum</strong> is the sum of the array.</p>

<p>Return an integer array <code>answer</code> of length <code>n - k + 1</code> where <code>answer[i]</code> is the <strong>x-sum</strong> of the <span data-keyword="subarray-nonempty">subarray</span> <code>nums[i..i + k - 1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,2,3,4,2,3], k = 6, x = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[6,10,12]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For subarray <code>[1, 1, 2, 2, 3, 4]</code>, only elements 1 and 2 will be kept in the resulting array. Hence, <code>answer[0] = 1 + 1 + 2 + 2</code>.</li>
	<li>For subarray <code>[1, 2, 2, 3, 4, 2]</code>, only elements 2 and 4 will be kept in the resulting array. Hence, <code>answer[1] = 2 + 2 + 2 + 4</code>. Note that 4 is kept in the array since it is bigger than 3 and 1 which occur the same number of times.</li>
	<li>For subarray <code>[2, 2, 3, 4, 2, 3]</code>, only elements 2 and 3 are kept in the resulting array. Hence, <code>answer[2] = 2 + 2 + 2 + 3 + 3</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,8,7,8,7,5], k = 2, x = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[11,15,15,15,12]</span></p>

<p><strong>Explanation:</strong></p>

<p>Since <code>k == x</code>, <code>answer[i]</code> is equal to the sum of the subarray <code>nums[i..i + k - 1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= x &lt;= k &lt;= nums.length</code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int node, freq;
        public Pair(int node, int freq) {
            this.node = node;
            this.freq = freq;
        }
        @Override
        public String toString() {
            return "(" + node + " " + freq + ")";
        }
        @Override
        public int hashCode() {
            return Objects.hash(node, freq);
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair current = (Pair)(obj);
            return current.node == node && current.freq == freq;
        }
    }
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int op1 = Integer.compare(second.freq, first.freq);
            if (op1 != 0) return op1;
            return Integer.compare(second.node, first.node);
        }
    }
    public static long[] findXSum(int[] arr, int k, int x) {
        int n = arr.length, p = 0;
        long res[] = new long[n - k + 1]; 
        TreeSet<Pair> set = new TreeSet<>(new custom_sort());
        HashMap<Integer, Integer> map = new HashMap<>(); 
        TreeSet<Pair> removed = new TreeSet<>(new custom_sort());
        long sum = 0;
        for (int i = 0; i < k; i++) {
            int current = arr[i];
            if (map.containsKey(current)) {
                if(set.contains(new Pair(current, map.getOrDefault(current, 0)))) sum -= current * 1L * map.getOrDefault(current, 0);
                if (removed.contains(new Pair(current, map.getOrDefault(current, 0)))) removed.remove(new Pair(current, map.getOrDefault(current, 0)));
                set.remove(new Pair(current, map.getOrDefault(current, 0)));
                
                map.put(current, map.getOrDefault(current, 0) + 1);
                sum += current * 1L * map.getOrDefault(current, 0);
                set.add(new Pair(current, map.getOrDefault(current, 0)));
                if (set.size() > x) {
                    Pair last = set.pollLast();
                    sum -= last.node * 1L * last.freq;
                    removed.add(new Pair(last.node, map.getOrDefault(last.node, 0)));
                }
            }
            else {
                map.put(current, 1);
                set.add(new Pair(current, 1));
                sum += current;
                if (set.size() > x) {
                    Pair last = set.pollLast();
                    sum -= last.node * 1L * last.freq;
                    removed.add(new Pair(last.node, map.getOrDefault(last.node, 0)));
                }
            }
            while (removed.size() > 0) {
                Pair second = removed.first();
                Pair first = set.last();
                if (second.freq > first.freq || (second.freq == first.freq && second.node > first.node)) {
                    sum += second.node * 1L *  second.freq;
                    set.add(removed.pollFirst());
                    if (set.size() > x) {
                        Pair r = set.last();
                        removed.add(set.pollLast());
                        sum -= r.node * 1L * r.freq;
                    }
                }
                else break;
            }
        }
        res[p++] = sum;
        int start = 0;
        for (int i = k; i < n; i++) {
            int prev = arr[start++];
            if(set.contains(new Pair(prev, map.getOrDefault(prev, 0)))) sum -= prev * 1L * map.getOrDefault(prev, 0);
            if (removed.contains(new Pair(prev, map.getOrDefault(prev, 0)))) removed.remove(new Pair(prev, map.getOrDefault(prev, 0)));
            set.remove(new Pair(prev, map.getOrDefault(prev, 0)));

            map.put(prev, map.getOrDefault(prev, 0) -1);
            sum += prev * 1L * map.getOrDefault(prev, 0);
            set.add(new Pair(prev, map.getOrDefault(prev, 0)));

            if (set.size() > x) {
                Pair last = set.pollLast();
                sum -= last.node * 1L * last.freq;
                removed.add(new Pair(last.node, map.getOrDefault(last.node, 0)));
            }
            int now = arr[i];
            if(set.contains(new Pair(now, map.getOrDefault(now, 0)))) sum -= now * 1L * map.getOrDefault(now, 0);
            if (removed.contains(new Pair(now, map.getOrDefault(now, 0)))) removed.remove(new Pair(now, map.getOrDefault(now, 0)));
            set.remove(new Pair(now, map.getOrDefault(now, 0)));
            
            map.put(now, map.getOrDefault(now, 0) + 1);
            sum += now * 1L * map.getOrDefault(now, 0);
            set.add(new Pair(now, map.getOrDefault(now, 0)));

            if (set.size() > x) {
                Pair last = set.pollLast();
                sum -= last.node * 1L * last.freq;
                removed.add(new Pair(last.node, map.getOrDefault(last.node, 0)));
            }
            while (removed.size() > 0) {
                Pair second = removed.first();
                Pair first = set.last();
                if (second.freq > first.freq || (second.freq == first.freq && second.node > first.node)) {
                    sum += second.node * 1L * map.getOrDefault(second.node, 0);
                    set.add(removed.pollFirst());
                    if (set.size() > x) {
                        Pair r = set.last();
                        removed.add(set.pollLast());
                        sum -= r.node * 1L * r.freq;
                    }
                }
                else break;
            }
            res[p++] = sum;
        }
        return res;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

