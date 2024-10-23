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
    static class Pair {
        TreeNode current;
        int level;
        int val;
        TreeNode parent;
        int child;
        public Pair (TreeNode current, int level , int val, TreeNode parent, int child) {
            this.current = current;
            this.level = level;
            this.val = val;
            this.parent = parent;
            this.child = child;
        }
    }
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0, root.val, null, -1));
        ArrayList<Pair> res = new ArrayList<>();
        ArrayList<Integer> level_sum = new ArrayList<>();
        HashMap<TreeNode, Integer> map = new HashMap<>();
        res.add(new Pair(root, 0 , root.val, null, -1));
        while (q.size() > 0) {
            int len = q.size();
            int sum = 0;
            for (int i = 0; i < len; i++) {
                TreeNode curr = q.peek().current;
                if (q.peek().current.left != null) {
                    q.offer(new Pair(q.peek().current.left, q.peek().level + 1, q.peek().current.left.val, q.peek().current, 0));
                    res.add(new Pair(q.peek().current.left, q.peek().level + 1, q.peek().current.left.val, q.peek().current, 0));
                    if (q.peek().current.right != null) map.put(q.peek().current.left, q.peek().current.right.val);
                    else map.put(q.peek().current.left, 0);
                }
                if (q.peek().current.right != null) {
                    q.offer(new Pair(q.peek().current.right, q.peek().level + 1, q.peek().current.right.val, q.peek().current, 1));
                    res.add(new Pair(q.peek().current.right, q.peek().level + 1, q.peek().current.right.val, q.peek().current, 1));
                    if (q.peek().current.left != null) map.put(q.peek().current.right, q.peek().current.left.val);
                    else map.put(q.peek().current.right, 0);
                }
                sum += q.peek().current.val;
                q.poll();
            }
            level_sum.add(sum);
        }     
        int current_level = 0;
        Queue<TreeNode> new_q = new LinkedList<>();
        new_q.offer(root);
        while (new_q.size() > 0) {
            int len = new_q.size();
            for (int i = 0; i < len; i++) {
                if (new_q.peek().left != null) {
                    new_q.offer(new_q.peek().left);
                }
                if (new_q.peek().right != null) {
                    new_q.offer(new_q.peek().right);
                }
                if (current_level == 0) {
                    new_q.peek().val = 0;
                    new_q.poll();
                    continue;
                }
                if (current_level != 0) {
                    int sum = 0;
                    sum += map.get(new_q.peek());
                    sum += new_q.peek().val;
                    int current_level_sum = level_sum.get(current_level);
                    new_q.peek().val = current_level_sum - sum;
                    new_q.poll();
                }
            }
            current_level++;
        }
        return root;
    }


    static Debug dbg = new Debug();
    static class Debug {
        public static boolean LOCAL = true;
        public static boolean getLocal() {
            try {
                return System.getProperty("LOCAL") == null;
            }catch(SecurityException e) {
                return false;
            }
        }
        public static <T> String ts(T t) {
            if(t==null) {
                return "null";
            }
            if(t instanceof Iterable) {
                return ts((Iterable<?>) t);
            }else if(t instanceof int[]) {
                String s = Arrays.toString((int[]) t);
                return "{"+s.substring(1, s.length()-1)+"}";
            }else if(t instanceof long[]) {
                String s = Arrays.toString((long[]) t);
                return "{"+s.substring(1, s.length()-1)+"}";
            }else if(t instanceof char[]) {
                String s = Arrays.toString((char[]) t);
                return "{"+s.substring(1, s.length()-1)+"}";
            }else if(t instanceof double[]) {
                String s = Arrays.toString((double[]) t);
                return "{"+s.substring(1, s.length()-1)+"}";
            }else if(t instanceof boolean[]) {
                String s = Arrays.toString((boolean[]) t);
                return "{"+s.substring(1, s.length()-1)+"}";
            }else if(t instanceof Object[]) {
                return ts((Object[]) t);
            }
            return t.toString();
        }
        private static <T> String ts(T[] arr) {
            StringBuilder ret = new StringBuilder();
            ret.append("{");
            boolean first = true;
            for(T t: arr) {
                if(!first) ret.append(", ");
                first = false;
                ret.append(ts(t));
            }
            ret.append("}");
            return ret.toString();
        }
        private static <T> String ts(Iterable<T> iter) {
            StringBuilder ret = new StringBuilder();
            ret.append("{");
            boolean first = true;
            for(T t: iter) {
                if(!first) ret.append(", ");
                first = false;
                ret.append(ts(t));
            }
            ret.append("}");
            return ret.toString();
        }
        public static void print(Object... o) {
            if(LOCAL) {
                System.out.print("Line #"+Thread.currentThread().getStackTrace()[2].getLineNumber()+": [");
                for(int i = 0; i<o.length; i++) {
                    if(i!=0) System.out.print(", ");
                    System.out.print(ts(o[i]));
                }
                System.out.println("]");
            }
        }
    }
}