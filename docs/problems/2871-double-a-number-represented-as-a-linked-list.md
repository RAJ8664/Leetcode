# 2871. Double A Number Represented As A Linked List

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2871-double-a-number-represented-as-a-linked-list){ .md-button }

---

<h2><a href="https://leetcode.com/problems/double-a-number-represented-as-a-linked-list">2871. Double a Number Represented as a Linked List</a></h2><h3>Medium</h3><hr><p>You are given the <code>head</code> of a <strong>non-empty</strong> linked list representing a non-negative integer without leading zeroes.</p>

<p>Return <em>the </em><code>head</code><em> of the linked list after <strong>doubling</strong> it</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/05/28/example.png" style="width: 401px; height: 81px;" />
<pre>
<strong>Input:</strong> head = [1,8,9]
<strong>Output:</strong> [3,7,8]
<strong>Explanation:</strong> The figure above corresponds to the given linked list which represents the number 189. Hence, the returned linked list represents the number 189 * 2 = 378.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/05/28/example2.png" style="width: 401px; height: 81px;" />
<pre>
<strong>Input:</strong> head = [9,9,9]
<strong>Output:</strong> [1,9,9,8]
<strong>Explanation:</strong> The figure above corresponds to the given linked list which represents the number 999. Hence, the returned linked list reprersents the number 999 * 2 = 1998. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[1, 10<sup>4</sup>]</code></li>
	<li><font face="monospace"><code>0 &lt;= Node.val &lt;= 9</code></font></li>
	<li>The input is generated such that the list represents a number that does not have leading zeros, except the number <code>0</code> itself.</li>
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
    public ListNode doubleIt(ListNode head) {
        String current = "";
        ListNode temp = head;
        while (temp != null) {
            current += temp.val;
            temp = temp.next;
        }
        String dummy = current, res = "";
        int carry = 0;
        for (int i = current.length() - 1; i >= 0; i--) {
            int first = current.charAt(i) - '0';
            int second = dummy.charAt(i) - '0';
            int dig = first + second + carry;
            if (i == 0) {
                res = dig + (res);
                continue;
            }
            if (dig >= 10) {
                res = (dig % 10) + res;
                carry = dig / 10;
            }
            else {
                res = dig + res;
                carry = 0;
            }
        }

        ListNode answer_node = null;
        for (int i = res.length() - 1; i >= 0; i--) {
            int current_data = res.charAt(i) - '0';
            answer_node = insert(answer_node, current_data);
        }
        return answer_node;
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

