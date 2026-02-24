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
    public int sumRootToLeaf(TreeNode root) {
        ArrayList<String> res = new ArrayList<>();
        solve(root, res, "");
        int sum = 0;
        for(int i = 0; i < res.size(); i++) {
            String current = res.get(i);
            int cal = Integer.parseInt(current, 2);
            sum += cal;
        }
        return sum;
    }

    public static void solve(TreeNode root, ArrayList<String> res, String current) {
        if(root == null) 
            return;
        if(root.left == null && root.right == null) {
            current += root.val;
            res.add(current);
            current = current.substring(0, current.length() - 1);
            return;
        }
        current += root.val;
        solve(root.left, res,current);
        solve(root.right, res,current);
    }
}