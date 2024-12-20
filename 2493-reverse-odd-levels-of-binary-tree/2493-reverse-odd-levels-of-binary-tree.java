/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (q.size() > 0) {
            int len = q.size();
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                if (q.peek().left != null) q.offer(q.peek().left);
                if (q.peek().right != null) q.offer(q.peek().right);
                temp.add(q.poll().val);
            }
            res.add(new ArrayList<>(temp));
        }
        for (int i = 0; i < res.size(); i++) if (i % 2 == 1) Collections.reverse(res.get(i));
        ArrayList<Integer> nodes = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            for (int ele : res.get(i)) nodes.add(ele);
        }
        return BuildTree(nodes);
    }
    private TreeNode BuildTree(ArrayList<Integer> nodes) {
        int n = nodes.size();
        if (n == 0) return null;
        TreeNode root = new TreeNode(nodes.get(0));
        int current_index = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (q.size() > 0) {
            TreeNode current_node = q.poll();
            int left = -1, right = -1;
            if (current_index < n) left = nodes.get(current_index++);
            if (current_index < n) right = nodes.get(current_index++);
            if (left == -1) current_node.left = null;
            if (right == -1) current_node.right = null;
            if (left != -1) {
                current_node.left = new TreeNode(left);
                q.offer(current_node.left);
            }
            if (right != -1) {
                current_node.right = new TreeNode(right);
                q.offer(current_node.right);
            }
        }
        return root;
    }
}