# 2078. Maximum Compatibility Score Sum

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/maximum-compatibility-score-sum/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2078-maximum-compatibility-score-sum){ .md-button }

---

<h2><a href="https://leetcode.com/problems/maximum-compatibility-score-sum">2078. Maximum Compatibility Score Sum</a></h2><h3>Medium</h3><hr><p>There is a survey that consists of <code>n</code> questions where each question&#39;s answer is either <code>0</code> (no) or <code>1</code> (yes).</p>

<p>The survey was given to <code>m</code> students numbered from <code>0</code> to <code>m - 1</code> and <code>m</code> mentors numbered from <code>0</code> to <code>m - 1</code>. The answers of the students are represented by a 2D integer array <code>students</code> where <code>students[i]</code> is an integer array that contains the answers of the <code>i<sup>th</sup></code> student (<strong>0-indexed</strong>). The answers of the mentors are represented by a 2D integer array <code>mentors</code> where <code>mentors[j]</code> is an integer array that contains the answers of the <code>j<sup>th</sup></code> mentor (<strong>0-indexed</strong>).</p>

<p>Each student will be assigned to <strong>one</strong> mentor, and each mentor will have <strong>one</strong> student assigned to them. The <strong>compatibility score</strong> of a student-mentor pair is the number of answers that are the same for both the student and the mentor.</p>

<ul>
	<li>For example, if the student&#39;s answers were <code>[1, <u>0</u>, <u>1</u>]</code> and the mentor&#39;s answers were <code>[0, <u>0</u>, <u>1</u>]</code>, then their compatibility score is 2 because only the second and the third answers are the same.</li>
</ul>

<p>You are tasked with finding the optimal student-mentor pairings to <strong>maximize</strong> the<strong> sum of the compatibility scores</strong>.</p>

<p>Given <code>students</code> and <code>mentors</code>, return <em>the <strong>maximum compatibility score sum</strong> that can be achieved.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> students = [[1,1,0],[1,0,1],[0,0,1]], mentors = [[1,0,0],[0,0,1],[1,1,0]]
<strong>Output:</strong> 8
<strong>Explanation:</strong>&nbsp;We assign students to mentors in the following way:
- student 0 to mentor 2 with a compatibility score of 3.
- student 1 to mentor 0 with a compatibility score of 2.
- student 2 to mentor 1 with a compatibility score of 3.
The compatibility score sum is 3 + 2 + 3 = 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> students = [[0,0],[0,0],[0,0]], mentors = [[1,1],[1,1],[1,1]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The compatibility score of any student-mentor pair is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == students.length == mentors.length</code></li>
	<li><code>n == students[i].length == mentors[j].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 8</code></li>
	<li><code>students[i][k]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>mentors[j][k]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>


---

## Solution

```java
import java.util.ArrayList;

class Solution {
    private ArrayList<ArrayList<Integer >> permutations;
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int n = students.length;
        permutations = new ArrayList<>();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = i;

        getPermutations(arr, 0);

        int maxi = 0;
        for (ArrayList<Integer> curr : permutations) {
            int idx = 0;
            int currentScore = 0;
            for (int i = 0; i < curr.size(); i++) {
                for (int j = 0; j < students[idx].length; j++) {
                    if (students[idx][j] == mentors[curr.get(i)][j])
                        currentScore++;
                }
                idx++;
            }
            maxi = Math.max(maxi, currentScore);
        }
        return maxi;
    }

    private void getPermutations(int arr[], int index) {
        if (index >= arr.length) {
            ArrayList<Integer> current = new ArrayList<>();
            for (int ele : arr)
                current.add(ele);
            permutations.add(new ArrayList<>(current));
            return;
        }

        for (int i =  index; i < arr.length; i++) {
            swap(arr, index, i);
            getPermutations(arr, index + 1);
            swap(arr, index, i);
        }
    }

    private void swap(int arr[], int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

