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
    public ListNode deleteMiddle(ListNode head) {
        if(head.next == null) return null;
        delete(head);
        return head;
    }

    static void delete(ListNode head) {
        if(head == null) return;
        ListNode temp = head;
        int count = 0;
        while(temp != null) {
            count++;
            temp = temp.next;
        }
        int req = count / 2;
        ListNode prev = null;
        prev = head;
        while(req > 0) {
            prev = head;
            head = head.next;
            req--;
        }
        prev.next = head.next;
    }
}