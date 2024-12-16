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
    public ListNode reverseKGroup(ListNode head, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            res.add(temp.val);
            temp = temp.next;
        }
        ArrayList<Integer> nodes = new ArrayList<>();
        int left = 0, right = 0;
        while (left < res.size()) {
            int current_pointer = k;
            ArrayList<Integer> temp_nodes = new ArrayList<>();
            while (right < res.size() && current_pointer > 0) {
                current_pointer--;
                temp_nodes.add(res.get(right++));
            }
            if (temp_nodes.size() == k) Collections.reverse(temp_nodes);
            for (int ele : temp_nodes) nodes.add(ele);
            left = right;
        }
        Collections.reverse(nodes);
        ListNode res_node = new ListNode(nodes.get(0));
        for (int i = 1; i < nodes.size(); i++) {
            res_node = insert(res_node, nodes.get(i));
        } 
        return res_node;
    }
    private ListNode insert(ListNode head, int val) {
        ListNode to_insert = new ListNode(val);
        to_insert.next = head;
        head = to_insert;
        return head;
    }
}
