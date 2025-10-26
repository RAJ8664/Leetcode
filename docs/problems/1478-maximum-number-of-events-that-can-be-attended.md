# 1478. Maximum Number Of Events That Can Be Attended

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/1478-maximum-number-of-events-that-can-be-attended){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended">1478. Maximum Number of Events That Can Be Attended</a></h2><h3>Medium</h3><hr><p>You are given an array of <code>events</code> where <code>events[i] = [startDay<sub>i</sub>, endDay<sub>i</sub>]</code>. Every event <code>i</code> starts at <code>startDay<sub>i</sub></code><sub> </sub>and ends at <code>endDay<sub>i</sub></code>.</p>

<p>You can attend an event <code>i</code> at any day <code>d</code> where <code>startTime<sub>i</sub> &lt;= d &lt;= endTime<sub>i</sub></code>. You can only attend one event at any time <code>d</code>.</p>

<p>Return <em>the maximum number of events you can attend</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/02/05/e1.png" style="width: 400px; height: 267px;" />
<pre>
<strong>Input:</strong> events = [[1,2],[2,3],[3,4]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> events= [[1,2],[2,3],[3,4],[1,2]]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= events.length &lt;= 10<sup>5</sup></code></li>
	<li><code>events[i].length == 2</code></li>
	<li><code>1 &lt;= startDay<sub>i</sub> &lt;= endDay<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>


---

## Solution

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    static class Pair {
        int start, end;
        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public String toString() {
            return "Pair{" +
                   "start=" + start +
                   ", end=" + end +
                   '}';
        }
    }
    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int op1 = Integer.compare(first.start, second.start);
            if (op1 != 0)
                return op1;
            return Integer.compare(first.end, second.end);
        }
    }
    public int maxEvents(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        ArrayList<Pair> events = new ArrayList<>();
        for (int current[] : arr)
            events.add(new Pair(current[0], current[1]));
        Collections.sort(events, new customSort());

        int currentIdx = 0, count = 0, currentDay = 1, lastDay = 0;
        for (Pair e : events)
            lastDay = Math.max(lastDay, e.end);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (currentDay <= lastDay) {
            while (currentIdx < n && events.get(currentIdx).start <= currentDay) {
                pq.offer(events.get(currentIdx).end);
                currentIdx++;
            }
            while (pq.size() > 0 && pq.peek() < currentDay)
                pq.poll();
            if (pq.size() > 0) {
                count++;
                pq.poll();
            }
            currentDay++;
        }
        return count;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

