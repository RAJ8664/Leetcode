class Solution {
    public int findChampion(int n, int[][] edges) {
        int indegree[] = new int[n];
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            indegree[v]++;
        }
        int count = 0;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                ans = i;
                count++;
            }
        }
        return count == 1 ? ans : -1;
    }
}