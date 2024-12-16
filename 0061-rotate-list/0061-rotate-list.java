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
