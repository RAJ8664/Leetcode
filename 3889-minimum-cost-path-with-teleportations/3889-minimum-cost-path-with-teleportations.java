class Solution {
    static class State {
        int row, col, tused, cost;
        public State(int row, int col, int tused, int cost) {
            this.row = row;
            this.col = col;
            this.tused = tused;
            this.cost = cost;
        } 
    }
    
    static class Tuple {
        int row, col, cost;
        public Tuple(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }

    static class customSort implements Comparator<State> {
        @Override
        public int compare(State first, State second) {
            return Integer.compare(first.cost, second.cost);
        }
    }

    static class customSort1 implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            return Integer.compare(first.cost, second.cost);
        }
    }

    public int minCost(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;
        int dist[][][] = new int[n][m][k + 1];
        for (int[][] current : dist) {
            for (int[] current1 : current) 
                Arrays.fill(current1, (int)(1e9));
        }
        
        ArrayList<Tuple> cells = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cells.add(new Tuple(i, j, grid[i][j]));
            }
        }
        Collections.sort(cells, new customSort1());

        int dir[][] = {{0, 1}, {1, 0}};
        dist[0][0][0] = 0;
        PriorityQueue<State> pq = new PriorityQueue<>(new customSort());
        pq.offer(new State(0, 0, 0, 0));

        int[] idxPerLayer = new int[k + 1];
        Arrays.fill(idxPerLayer, -1);

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int currRow = cur.row, currCol = cur.col, currTused = cur.tused, currCost = cur.cost;
           
            if (currRow == n - 1 && currCol == m - 1) 
                return currCost;

            /* Move 1 */
            for (int[] dire : dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < m) {
                    if (dist[newRow][newCol][currTused] > currCost + grid[newRow][newCol]) {
                        dist[newRow][newCol][currTused] = currCost + grid[newRow][newCol];
                        pq.offer(new State(newRow, newCol, currTused, dist[newRow][newCol][currTused]));
                    }
                }
            }

            /* Move 2 */
            if (currTused + 1 <= k) {
                while (idxPerLayer[currTused] + 1 < cells.size() && cells.get(idxPerLayer[currTused] + 1).cost <= grid[currRow][currCol]) {
                    idxPerLayer[currTused]++;
                    Tuple current = cells.get(idxPerLayer[currTused]);
                    if (dist[current.row][current.col][currTused + 1] > currCost) {
                        dist[current.row][current.col][currTused + 1] = currCost;
                        pq.offer(new State(current.row, current.col, currTused + 1, dist[current.row][current.col][currTused + 1]));
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            res = Math.min(res, dist[n - 1][m - 1][i]);
        }
        return res;
    }
}
