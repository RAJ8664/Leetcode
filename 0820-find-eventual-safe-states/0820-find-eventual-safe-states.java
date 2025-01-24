class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int m = graph[0].length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= n + 1; i++) adj.add(new  ArrayList<>());
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph[i].length; j++) {
               adj.get(graph[i][j]).add(i);
            }
        }
        int indegree[] = new int[n + 1];
        for(ArrayList<Integer> current : adj) {
            for(int ele : current) indegree[ele]++;
        }
        List<Integer> ans = new ArrayList<>();
        ans = solve(n,adj,indegree);
        Collections.sort(ans);
        return ans;
    }
    private static List<Integer> solve(int n , ArrayList<ArrayList<Integer>> adj , int indegree[]) {
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(indegree[i] == 0) q.offer(i);
        }
        List<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()) {
            int current = q.peek();
            q.poll();
            ans.add(current);
            for(int child : adj.get(current)) {
                indegree[child]--;
                if(indegree[child] == 0) q.offer(child);
            }
        }
        return ans;
    }
}