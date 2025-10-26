# 838. Design Linked List

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/design-linked-list/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0838-design-linked-list){ .md-button }

---

<h2><a href="https://leetcode.com/problems/design-linked-list">838. Design Linked List</a></h2><h3>Medium</h3><hr><p>Design your implementation of the linked list. You can choose to use a singly or doubly linked list.<br />
A node in a singly linked list should have two attributes: <code>val</code> and <code>next</code>. <code>val</code> is the value of the current node, and <code>next</code> is a pointer/reference to the next node.<br />
If you want to use the doubly linked list, you will need one more attribute <code>prev</code> to indicate the previous node in the linked list. Assume all nodes in the linked list are <strong>0-indexed</strong>.</p>

<p>Implement the <code>MyLinkedList</code> class:</p>

<ul>
	<li><code>MyLinkedList()</code> Initializes the <code>MyLinkedList</code> object.</li>
	<li><code>int get(int index)</code> Get the value of the <code>index<sup>th</sup></code> node in the linked list. If the index is invalid, return <code>-1</code>.</li>
	<li><code>void addAtHead(int val)</code> Add a node of value <code>val</code> before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.</li>
	<li><code>void addAtTail(int val)</code> Append a node of value <code>val</code> as the last element of the linked list.</li>
	<li><code>void addAtIndex(int index, int val)</code> Add a node of value <code>val</code> before the <code>index<sup>th</sup></code> node in the linked list. If <code>index</code> equals the length of the linked list, the node will be appended to the end of the linked list. If <code>index</code> is greater than the length, the node <strong>will not be inserted</strong>.</li>
	<li><code>void deleteAtIndex(int index)</code> Delete the <code>index<sup>th</sup></code> node in the linked list, if the index is valid.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MyLinkedList&quot;, &quot;addAtHead&quot;, &quot;addAtTail&quot;, &quot;addAtIndex&quot;, &quot;get&quot;, &quot;deleteAtIndex&quot;, &quot;get&quot;]
[[], [1], [3], [1, 2], [1], [1], [1]]
<strong>Output</strong>
[null, null, null, null, 2, null, 3]

<strong>Explanation</strong>
MyLinkedList myLinkedList = new MyLinkedList();
myLinkedList.addAtHead(1);
myLinkedList.addAtTail(3);
myLinkedList.addAtIndex(1, 2);    // linked list becomes 1-&gt;2-&gt;3
myLinkedList.get(1);              // return 2
myLinkedList.deleteAtIndex(1);    // now the linked list is 1-&gt;3
myLinkedList.get(1);              // return 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= index, val &lt;= 1000</code></li>
	<li>Please do not use the built-in LinkedList library.</li>
	<li>At most <code>2000</code> calls will be made to <code>get</code>, <code>addAtHead</code>, <code>addAtTail</code>, <code>addAtIndex</code> and <code>deleteAtIndex</code>.</li>
</ul>


---

## Solution

```java
class MyLinkedList {
    static class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
            next = null;
        }
    }
    private Node head;
    public MyLinkedList() {
        head = null;
    }
    public int get(int index) {
        if (index >= getSize()) return -1;
        int current_ind = 0;
        Node temp = head;
        while (current_ind != index) {
            temp = temp.next;
            current_ind++;
        }
        return temp.data;
    }
    public void addAtHead(int val) {
        if (head == null) {
            head = new Node(val);
            return;
        }
        Node New_Node = new Node(val);
        New_Node.next = head;
        head = New_Node;
    }
    public void addAtTail(int val) {
        if (head == null) {
            head = new Node(val);
            return;
        }
        Node Temp = head;
        while (Temp.next != null) {
            Temp = Temp.next;
        }
        Node New_Node = new Node(val);
        Temp.next = New_Node;
        New_Node.next = null;
    }
    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == getSize()) {
            addAtTail(val);
            return;
        }
        if (index > getSize()) return;
        int current_ind = 0;
        Node prev = head;
        Node next = head;
        while (current_ind != index) {
            prev = next;
            next = next.next;
            current_ind++;
        }
        Node New_Node = new Node(val);
        prev.next = New_Node;
        New_Node.next = next;
    }
    public void deleteAtIndex(int index) {
        if (index == 0 && head == null) return;
        if (index == 0) {
            head = head.next;
            return;
        }
        if (index >= getSize()) return;
        int current_ind = 0;
        Node prev = head;
        Node next = head;
        while (current_ind != index) {
            prev = next;
            next = next.next;
            current_ind++;
        }
        prev.next = next.next;
    }
    private int getSize() {
        int size = 0;
        Node temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }
}
/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

