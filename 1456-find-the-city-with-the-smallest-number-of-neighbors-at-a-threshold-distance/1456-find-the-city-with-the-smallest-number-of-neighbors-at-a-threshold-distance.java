class Solution {
    private int dist[][];
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        dist = new int[n][n];
        for (int current[] : dist)
            Arrays.fill(current, (int)(1e9));
        for (int i = 0; i < n; i++)
            dist[i][i] = 0;
        for (int edge[] : edges) {
            dist[edge[0]][edge[1]] = edge[2];
            dist[edge[1]][edge[0]] = edge[2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] == (int)(1e9) || dist[k][j] == (int)(1e9))
                        continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int ansNode = -1, minCount = n + 1;
        for (int i = 0; i < n; i++) {
            int currCount = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) 
                    continue;
                if (dist[i][j] <= distanceThreshold) {
                    currCount++;
                }
            }
            if (currCount <= minCount) {
                ansNode = i;
                minCount = currCount;
            }
        }
        return ansNode;
    }
}