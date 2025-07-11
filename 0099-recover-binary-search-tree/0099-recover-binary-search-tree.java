import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
    Definition for a binary tree node.
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
    }
*/
class Solution {
    private ArrayList<Integer> inorderList;
    public void recoverTree(TreeNode root) {
        inorderList = new ArrayList<>();
        inorder(root);
        int a = -1, b = -1;
        ArrayList<Integer> sortedList = new ArrayList<>();
        for (int i = 0; i < inorderList.size(); i++)
            sortedList.add(inorderList.get(i));
        Collections.sort(sortedList);
        for (int i = 0; i < sortedList.size(); i++) {
            if (sortedList.get(i) != inorderList.get(i)) {
                if (a == -1)
                    a = sortedList.get(i);
                else
                    b = sortedList.get(i);
            }
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (q.size() > 0) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                if (q.peek().left != null)
                    q.offer(q.peek().left);
                if (q.peek().right != null)
                    q.offer(q.peek().right);

                if (q.peek().val == a)
                    q.peek().val = b;
                else if (q.peek().val == b)
                    q.peek().val = a;
                q.poll();
            }
        }
    }
    private void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        inorderList.add(root.val);
        inorder(root.right);
    }
}