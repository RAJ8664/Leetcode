class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            adj.add(new ArrayList<>());
        
        int[] indegree = new int[n + 1];
        for (int[] edge : relations) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();
        int[] finishTime = new int[n + 1]; 

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                finishTime[i] = time[i - 1]; 
            }
        }

        int res = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            res = Math.max(res, finishTime[u]);
            for (int v : adj.get(u)) {
                finishTime[v] = Math.max(finishTime[v], finishTime[u] + time[v - 1]);
                if (--indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }
        return res;
    }
}
