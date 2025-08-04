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
    public ListNode removeElements(ListNode head, int val) {
        ListNode res = null;
        while (head != null) {
            if (head.val != val) {
                res = push(res, head.val);
            }
            head = head.next;
        }
        res = reverse(res);
        return res;
    }
    private ListNode push(ListNode head, int val) {
        if (head == null) return new ListNode(val);
        ListNode newHead = new ListNode(val);
        newHead.next = head;
        head = newHead;
        return head;
    }
    private ListNode reverse(ListNode head) {
        ListNode current = head;
        ListNode prev = null, next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}