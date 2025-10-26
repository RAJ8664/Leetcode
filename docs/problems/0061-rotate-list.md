# 61. Rotate List

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/rotate-list/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0061-rotate-list){ .md-button }

---

<h2><a href="https://leetcode.com/problems/rotate-list">61. Rotate List</a></h2><h3>Medium</h3><hr><p>Given the <code>head</code> of a linked&nbsp;list, rotate the list to the right by <code>k</code> places.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/rotate1.jpg" style="width: 450px; height: 191px;" />
<pre>
<strong>Input:</strong> head = [1,2,3,4,5], k = 2
<strong>Output:</strong> [4,5,1,2,3]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/roate2.jpg" style="width: 305px; height: 350px;" />
<pre>
<strong>Input:</strong> head = [0,1,2], k = 4
<strong>Output:</strong> [2,0,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[0, 500]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
	<li><code>0 &lt;= k &lt;= 2 * 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        ArrayList<Integer> nodes = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            nodes.add(temp.val);
            temp = temp.next;
        }
        k = k % nodes.size();
        reverse(nodes, 0, nodes.size() - k - 1);
        reverse(nodes, nodes.size() - k, nodes.size() - 1);
        reverse(nodes, 0 , nodes.size() - 1);
        ListNode root = null;
        for (int i = 0; i < nodes.size(); i++) {
            int current = nodes.get(i);
            root = insert(root, current);
        }
        return root;
    }
    private ListNode insert(ListNode head, int data) {
        ListNode new_node = new ListNode(data);
        if (head == null) return new ListNode(data);
        ListNode temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = new_node;
        new_node.next = null;
        return head;
    }
    private void reverse(ArrayList<Integer> res, int low, int high) {
        while (low < high) {
            Collections.swap(res, low, high);
            low++; high--;
        }
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

