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
    static int mod = (int)(1e9 + 7);
    public int maxProduct(TreeNode root) {
        long total_sum = 0;
        HashMap<TreeNode, Integer> id = new HashMap<>(); 
        long cost[] = new long[(int)(1e5 + 1)];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= (int)(1e5 + 1); i++) adj.add(new ArrayList<>());
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int current_id = 1;
        while(q.size() > 0) {
            int len = q.size();
            for(int i = 0; i < len; i++) {
                int u = q.peek().val;
                if(!id.containsKey(q.peek())) id.put(q.peek(), current_id);
                cost[current_id] = q.peek().val;
                current_id++;
                total_sum += u;
                if(q.peek().left != null) {
                    int v = q.peek().left.val;
                    if(!id.containsKey(q.peek().left)) id.put(q.peek().left, current_id);
                    cost[current_id] = q.peek().left.val;
                    current_id++;
                    adj.get(id.get(q.peek())).add(id.get(q.peek().left));
                    adj.get(id.get(q.peek().left)).add(id.get(q.peek()));
                    q.offer(q.peek().left);
                }
                if(q.peek().right != null) {
                    int v = q.peek().right.val;
                    if(!id.containsKey(q.peek().right)) id.put(q.peek().right, current_id);
                    cost[current_id] = q.peek().right.val;
                    current_id++;
                    adj.get(id.get(q.peek())).add(id.get(q.peek().right));
                    adj.get(id.get(q.peek().right)).add(id.get(q.peek()));
                    q.offer(q.peek().right);
                }
                q.poll();
            }
        }
        long subtree_sum[] = new long[(int)(1e5)];
        
        dfs(id.get(root), id.get(root), -1, adj, subtree_sum, cost);

        long maxi = Long.MIN_VALUE;
        long total = subtree_sum[id.get(root)];
        q.clear();
        q.offer(root);
        while(q.size() > 0) {
            int len = q.size();
            for(int i = 0; i < len; i++) {
                TreeNode u = q.peek();
                if(q.peek().left != null) {
                    TreeNode v = q.peek().left;
                    long down_sum = subtree_sum[id.get(v)];
                    long up_sum = total - down_sum;
                    long current_sum = down_sum * 1L * up_sum;
                    maxi = Math.max(maxi, current_sum);
                    q.offer(q.peek().left);
                }
                if(q.peek().right != null) {
                    TreeNode v = q.peek().right;
                    long down_sum = subtree_sum[id.get(v)];
                    long up_sum = total - down_sum;
                    long current_sum = down_sum * 1L * up_sum;
                    maxi = Math.max(maxi, current_sum);
                    q.offer(q.peek().right);
                }
                q.poll();
            }
        }
        return (int)(maxi % mod);
    }

    static void dfs(int root, int u , int par, ArrayList<ArrayList<Integer>> adj , long subtree_sum[], long cost[]) {
        if(adj.get(u).size() == 1 && u != root) {
            subtree_sum[u] = cost[u];
            return;
        }
        for(int v : adj.get(u)) {
            if(v != par) {
                dfs(root, v, u , adj, subtree_sum, cost);
            }
        }
        for(int v : adj.get(u)) {
            if(v != par) {
                subtree_sum[u] += subtree_sum[v];
            }
        }
        subtree_sum[u] += cost[u];
    }
}