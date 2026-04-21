class Solution {
    private int time[][], vis[][], color[][];
    
    static class Tuple {
        int row, col, time;
        public Tuple(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
        @Override
        public String toString() {
            return "(" + row + " " + col + ")";
        }
    }

    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            return Integer.compare(first.time, second.time);
        }
    }

    public int[][] colorGrid(int n, int m, int[][] sources) {
        time = new int[n][m];
        vis = new int[n][m];
        color = new int[n][m];

        for (int x[]: time)
            Arrays.fill(x, (int)(1e9));

        PriorityQueue<Tuple> pq = new PriorityQueue<>(new customSort());

        for (int curr[] : sources) {
            int currRow = curr[0], currCol = curr[1], currColor = curr[2];
            pq.offer(new Tuple(currRow, currCol, 0));
            vis[currRow][currCol] = 1;
            time[currRow][currCol] = 0;
            color[currRow][currCol] = currColor;
        }
        
        int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (pq.size() > 0) {
            int currRow = pq.peek().row, currCol = pq.peek().col, currTime = pq.peek().time;
            pq.poll();
            for (int dire[]: dir) {
                int newRow = currRow + dire[0], newCol = currCol + dire[1];
                if (newRow < n && newCol < m && newRow >= 0 && newCol >= 0) {
                    if (vis[newRow][newCol] == 1) {
                        if (time[newRow][newCol] == currTime + 1) {
                            if (color[newRow][newCol] < color[currRow][currCol]) {
                                color[newRow][newCol] = color[currRow][currCol];
                                pq.offer(new Tuple(newRow, newCol, currTime + 1));
                            }
                        }
                    } else {
                        vis[newRow][newCol] = 1;
                        color[newRow][newCol] = color[currRow][currCol];
                        time[newRow][newCol] = currTime + 1;
                        pq.offer(new Tuple(newRow, newCol, currTime + 1)); 
                    
                    }
                }
            }
        }

        return color;
    }
}