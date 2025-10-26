# 2573. Remove Nodes From Linked List

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/remove-nodes-from-linked-list/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2573-remove-nodes-from-linked-list){ .md-button }

---

<h2><a href="https://leetcode.com/problems/remove-nodes-from-linked-list">2573. Remove Nodes From Linked List</a></h2><h3>Medium</h3><hr><p>You are given the <code>head</code> of a linked list.</p>

<p>Remove every node which has a node with a greater value anywhere to the right side of it.</p>

<p>Return <em>the </em><code>head</code><em> of the modified linked list.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/10/02/drawio.png" style="width: 631px; height: 51px;" />
<pre>
<strong>Input:</strong> head = [5,2,13,3,8]
<strong>Output:</strong> [13,8]
<strong>Explanation:</strong> The nodes that should be removed are 5, 2 and 3.
- Node 13 is to the right of node 5.
- Node 13 is to the right of node 2.
- Node 8 is to the right of node 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = [1,1,1,1]
<strong>Output:</strong> [1,1,1,1]
<strong>Explanation:</strong> Every node has value 1, so no nodes are removed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of the nodes in the given list is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
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
    public ListNode removeNodes(ListNode head) {
        ArrayList<Integer> arr = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            arr.add(temp.val);
            temp = temp.next;
        }
        int next_greater[] = new int[arr.size()];
        Stack<Integer> st = new Stack<>();
        for (int i = arr.size() - 1; i >= 0; i--) {
            while (st.size() > 0 && st.peek() <= arr.get(i)) st.pop();
            if (st.size() > 0) next_greater[i] = st.peek();
            else next_greater[i] = -1;
            st.add(arr.get(i));
        }
        ListNode res = null;
        for (int i = arr.size() - 1; i >= 0; i--) {
            if (next_greater[i] == -1) {
                res = insert(res, arr.get(i));
            }
        }
        return res;
    }

    private ListNode insert(ListNode head, int data) {
        ListNode to_insert = new ListNode(data);
        if (head == null) return new ListNode(data);
        to_insert.next = head;
        head = to_insert;
        return head;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

