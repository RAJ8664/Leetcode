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
class CBTInserter {
    private TreeNode root;
    public CBTInserter(TreeNode head) {
        this.root = head;
    }
    
    public int insert(int val) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int res = -1;
        while (q.size() > 0) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                if (q.peek().left == null) {
                    q.peek().left = new TreeNode(val);
                    res = q.peek().val;
                    return res;
                }
                if (q.peek().right == null) {
                    q.peek().right = new TreeNode(val);
                    res = q.peek().val;
                    return res;
                }
                if (q.peek().left != null) {
                    q.offer(q.peek().left);
                }
                if (q.peek().right != null) {
                    q.offer(q.peek().right);
                }
                q.poll();
            }
        }
        return res;
    }
    
    public TreeNode get_root() {
        return root;   
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */