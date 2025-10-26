# 2695. Find Score Of An Array After Marking All Elements

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-score-of-an-array-after-marking-all-elements/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2695-find-score-of-an-array-after-marking-all-elements){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-score-of-an-array-after-marking-all-elements">2695. Find Score of an Array After Marking All Elements</a></h2><h3>Medium</h3><hr><p>You are given an array <code>nums</code> consisting of positive integers.</p>

<p>Starting with <code>score = 0</code>, apply the following algorithm:</p>

<ul>
	<li>Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.</li>
	<li>Add the value of the chosen integer to <code>score</code>.</li>
	<li>Mark <strong>the chosen element and its two adjacent elements if they exist</strong>.</li>
	<li>Repeat until all the array elements are marked.</li>
</ul>

<p>Return <em>the score you get after applying the above algorithm</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,3,4,5,2]
<strong>Output:</strong> 7
<strong>Explanation:</strong> We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [<u>2</u>,<u>1</u>,<u>3</u>,4,5,2].
- 2 is the smallest unmarked element, so we mark it and its left adjacent element: [<u>2</u>,<u>1</u>,<u>3</u>,4,<u>5</u>,<u>2</u>].
- 4 is the only remaining unmarked element, so we mark it: [<u>2</u>,<u>1</u>,<u>3</u>,<u>4</u>,<u>5</u>,<u>2</u>].
Our score is 1 + 2 + 4 = 7.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,5,1,3,2]
<strong>Output:</strong> 5
<strong>Explanation:</strong> We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,<u>5</u>,<u>1</u>,<u>3</u>,2].
- 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [<u>2</u>,<u>3</u>,<u>5</u>,<u>1</u>,<u>3</u>,2].
- 2 is the only remaining unmarked element, so we mark it: [<u>2</u>,<u>3</u>,<u>5</u>,<u>1</u>,<u>3</u>,<u>2</u>].
Our score is 1 + 2 + 2 = 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int node;
        int ind;
        public Pair(int node, int ind) {
            this.node = node;
            this.ind = ind;
        }
    }
    static class sorting implements Comparator<Pair> {
        @Override
        public int compare(Pair first , Pair second) {
            int op1 = Integer.compare(first.node, second.node);
            if(op1 != 0) return op1;
            return Integer.compare(first.ind , second.ind);
        }
    }
    public long findScore(int[] nums) {
        int n = nums.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>(new sorting());
        for(int i = 0; i < n; i++) pq.offer(new Pair(nums[i], i));
        HashSet<Integer> visited = new HashSet<>();
        long total = 0;
        while(pq.size() > 0) {
            Pair current = pq.poll();
            if(visited.contains(current.ind)) continue;
            total += current.node;
            visited.add(current.ind);
            if(current.ind -1 >= 0) visited.add(current.ind - 1);
            if(current.ind + 1 < n) visited.add(current.ind + 1);
        }
        return total;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

