class Solution {
    static class Pair {
        int row;
        int col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public int numSpecial(int[][] mat) {
        int row = mat.length, col = mat[0].length; 
        ArrayList<Pair> pos = new ArrayList<>();
        HashMap<Integer,Integer> mapr = new HashMap<>();
        HashMap<Integer,Integer> mapc = new HashMap<>();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(mat[i][j] == 1) {
                    Pair p = new Pair(i,j);
                    pos.add(p);
                }
            }
        }
        for(int i = 0; i < row; i++) {
            int count = 0;
            for(int j = 0; j < col; j++) {
                if(mat[i][j] == 1) 
                    count++;
            }
            mapr.put(i,count);
        }
        for(int j = 0; j < col; j++) {
            int count = 0;
            for(int i = 0; i < row; i++) {
                if(mat[i][j] == 1) 
                    count++;
            }
            mapc.put(j,count);
        }
        int ans = 0;
        for(int i = 0; i < pos.size(); i++) {
            Pair current = pos.get(i);
            int r = current.row, c = current.col, prer = mapr.get(r), prec = mapc.get(c);
            if(prer + prec == 2)  
                ans++;
        }
        return ans;
    }
}