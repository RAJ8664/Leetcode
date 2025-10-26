# 3657. Check If Grid Can Be Cut Into Sections

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3657-check-if-grid-can-be-cut-into-sections){ .md-button }

---

<h2><a href="https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections">3657. Check if Grid can be Cut into Sections</a></h2><h3>Medium</h3><hr><p>You are given an integer <code>n</code> representing the dimensions of an <code>n x n</code><!-- notionvc: fa9fe4ed-dff8-4410-8196-346f2d430795 --> grid, with the origin at the bottom-left corner of the grid. You are also given a 2D array of coordinates <code>rectangles</code>, where <code>rectangles[i]</code> is in the form <code>[start<sub>x</sub>, start<sub>y</sub>, end<sub>x</sub>, end<sub>y</sub>]</code>, representing a rectangle on the grid. Each rectangle is defined as follows:</p>

<ul>
	<li><code>(start<sub>x</sub>, start<sub>y</sub>)</code>: The bottom-left corner of the rectangle.</li>
	<li><code>(end<sub>x</sub>, end<sub>y</sub>)</code>: The top-right corner of the rectangle.</li>
</ul>

<p><strong>Note </strong>that the rectangles do not overlap. Your task is to determine if it is possible to make <strong>either two horizontal or two vertical cuts</strong> on the grid such that:</p>

<ul>
	<li>Each of the three resulting sections formed by the cuts contains <strong>at least</strong> one rectangle.</li>
	<li>Every rectangle belongs to <strong>exactly</strong> one section.</li>
</ul>

<p>Return <code>true</code> if such cuts can be made; otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, rectangles = [[1,0,5,2],[0,2,2,4],[3,2,5,3],[0,4,4,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/10/23/tt1drawio.png" style="width: 285px; height: 280px;" /></p>

<p>The grid is shown in the diagram. We can make horizontal cuts at <code>y = 2</code> and <code>y = 4</code>. Hence, output is true.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, rectangles = [[0,0,1,1],[2,0,3,4],[0,2,2,3],[3,0,4,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/10/23/tc2drawio.png" style="width: 240px; height: 240px;" /></p>

<p>We can make vertical cuts at <code>x = 2</code> and <code>x = 3</code>. Hence, output is true.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, rectangles = [[0,2,2,4],[1,0,3,2],[2,2,3,4],[3,0,4,2],[3,2,4,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>We cannot make two horizontal or two vertical cuts that satisfy the conditions. Hence, output is false.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>3 &lt;= rectangles.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= rectangles[i][0] &lt; rectangles[i][2] &lt;= n</code></li>
	<li><code>0 &lt;= rectangles[i][1] &lt; rectangles[i][3] &lt;= n</code></li>
	<li>No two rectangles overlap.</li>
</ul>


---

## Solution

```java
class Solution {
    private ArrayList<Pair> vertical_merged;
    private ArrayList<Pair> horizontal_merged;
    static class Pair {
        int start, end;
        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public String toString() {
            return "(" + start + " " + end + ")";
        }
    } 
    static class custom_sort implements Comparator<int[]> {
        @Override
        public int compare(int[] first, int[] second) {
            return Integer.compare(first[0], second[0]);
        }
    }
    public boolean checkValidCuts(int n, int[][] arr) {
        int len = arr.length;
        int varr[][] = new int[len][2];
        int harr[][] = new int[len][2];
        for (int i = 0; i < len; i++) {
            int vl = arr[i][0], vr = arr[i][2];
            int hl = arr[i][1], hr = arr[i][3];
            varr[i][0] = vl; varr[i][1] = Math.max(varr[i][1], vr);
            harr[i][0] = hl; harr[i][1] = Math.max(harr[i][1], hr);
        }
        
        Arrays.sort(varr, new custom_sort());
        Arrays.sort(harr, new custom_sort());

        vertical_merged = new ArrayList<>();
        horizontal_merged = new ArrayList<>();
        merge(varr, vertical_merged);
        merge(harr, horizontal_merged);

        if (vertical_merged.size() >= 3 || horizontal_merged.size() >= 3) return true;
        return false;
    }
    private void merge(int arr[][], ArrayList<Pair> res) {
        int n = arr.length;
        int left = 0, right = 0;
        while (left < n) {
            int mini = arr[left][0], maxi = arr[left][1];
            while (right < n && arr[right][0] < maxi) {
                maxi = Math.max(maxi, arr[right][1]);
                right++;
            }
            res.add(new Pair(mini, maxi));
            left = right;
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

