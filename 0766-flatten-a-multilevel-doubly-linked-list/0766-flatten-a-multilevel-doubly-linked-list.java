/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    private ArrayList<Integer> res;
    public Node flatten(Node head) {
        res = new ArrayList<>();
        dfs(head);
        if (res.size() == 0)
            return null;
        Node newHead = new Node(res.get(res.size() - 1));
        for (int i = res.size() - 2; i >= 0; i--) 
            newHead = addHead(res.get(i), newHead);
        return newHead;
    }
    private Node addHead(int val, Node currHead) {
        Node newNode = new Node(val);
        newNode.next = currHead;
        currHead.prev = newNode;
        currHead = newNode;
        return currHead;
    }
    private void dfs(Node currHead) {
        if (currHead == null) return;
        res.add(currHead.val);

        dfs(currHead.child);
        dfs(currHead.next);

    }
}