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
    public ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode temp = head;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> tempNodes = new ArrayList<>();
        int req = 1;
        while (temp != null) {
            if (tempNodes.size() == req) {
                req++;
                res.add(new ArrayList<>(tempNodes));
                tempNodes.clear();
            }
            tempNodes.add(temp.val);
            temp = temp.next;
        }
        res.add(new ArrayList<>(tempNodes)); 
        for (ArrayList<Integer> curr : res) {
            if (curr.size() % 2 == 0) 
                Collections.reverse(curr);
        }
        ListNode ans = null;
        for (int i = res.size() - 1; i >= 0; i--) {
            ArrayList<Integer> current = res.get(i);
            for (int j = current.size() - 1; j >= 0; j--) {
                ans = addNode(ans, res.get(i).get(j));
            }
        }    
        return ans;
    }
    private ListNode addNode(ListNode head, int val) {
        ListNode current = new ListNode(val);
        if (head == null) {
            return current;
        }
        else {
            current.next = head;
            head = current;
            return head;
        } 
    }
}