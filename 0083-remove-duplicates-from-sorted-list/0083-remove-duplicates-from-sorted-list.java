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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode res = new ListNode(head.val);
        head = head.next;
        ListNode current = res;
        while (head != null) {
            if (head.val == current.val) {
                head = head.next;
            }
            else {
                current.next = new ListNode(head.val);
                current = current.next;
                head = head.next;
            }
        }
        return res;
    }
}