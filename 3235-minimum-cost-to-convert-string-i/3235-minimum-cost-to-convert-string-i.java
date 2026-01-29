class Solution {
    static class Pair {
        int u;
        int v;
        int wt;
        public Pair(int u , int v, int wt) {
            this.u = u;
            this.v = v;
            this.wt = wt;
        }
        @Override
        public String toString() {
            return "(" + u + " " + v + " " + wt + ")";
        }
    }

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        ArrayList<Pair> edges = new ArrayList<>();
        for(int i = 0; i < original.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            int wt = cost[i];
            edges.add(new Pair(u , v , wt));
        }

        long res = 0;
        long matrix[][] = new long[30][30];
        for(long current[] : matrix) Arrays.fill(current, (Integer.MAX_VALUE));

        for(Pair x : edges) {
            int u = x.u;
            int v = x.v;
            long wt = (long)x.wt;
            matrix[u][v] = Math.min(matrix[u][v] , wt);
        }

        shortest_distance(matrix);

        for(int i = 0; i < source.length(); i++) {
            if(source.charAt(i) == target.charAt(i)) continue;
            else {
                int u = source.charAt(i) - 'a';
                int v = target.charAt(i) - 'a';
                if(matrix[u][v] != -1) res += matrix[u][v];
                else return -1;
            }
        }
        return res;
    }

    public void shortest_distance(long[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == Integer.MAX_VALUE) {
                    matrix[i][j] = (long)(Long.MAX_VALUE / 10);
                }
                if (i == j) matrix[i][j] = 0;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j],
                                            matrix[i][k] + matrix[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == (long)(Long.MAX_VALUE / 10)) {
                    matrix[i][j] = -1;
                }
            }
        }
    }
}