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