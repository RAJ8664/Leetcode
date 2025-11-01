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
    public ListNode modifiedList(int[] nums, ListNode head) {
        HashSet<Integer> set = new HashSet<>();
        for (int ele : nums)
            set.add(ele);
        ListNode temp = head;
        ArrayList<Integer> nodes = new ArrayList<>();
        while (temp != null) {
            nodes.add(temp.val);
            temp = temp.next;
        } 
        ListNode ans = null;
        for (int i = nodes.size() - 1; i >= 0; i--) {
            int currNode = nodes.get(i);
            if (!set.contains(currNode)) 
                ans = addNode(ans, currNode);
        }
        return ans;
    }
    private ListNode addNode(ListNode head, int val) {
        ListNode newNode = new ListNode(val);
        if (head == null)
            return newNode;
        newNode.next = head;
        head = newNode;
        return head;
    }
}