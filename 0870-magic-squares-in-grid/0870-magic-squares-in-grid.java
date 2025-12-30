class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x1 = i;
                int y1 = j;
                
                int x2 = x1;
                int y2 = y1 + 2;

                int x3 = x1 + 2;
                int y3 = y1;

                int x4 = x3;
                int y4 = y2;

                if (x1 + 2 < n && y1 + 2 < m) {
                    /*
                        x1 y1  .   x2 y2
                        .
                        x3 y3   .  x4 y4
                    
                    */
                    //check sum of each row;
                    HashSet<Integer> set = new HashSet<>();
                    for (int row = x1; row <= x3; row++) {
                        int sum = 0;
                        for (int col = y1; col <= y2; col++) {
                            sum += grid[row][col];
                        }
                        set.add(sum);
                    }
                    int req = -1;
                    for (int ele : set) req = ele;
                    if (set.size() > 1) continue;

                    set.clear();
                    //check sum of each col;
                    for (int col = y1; col <= y2; col++) {
                        int sum = 0;
                        for (int row = x1; row <= x3; row++) {
                            sum += grid[row][col];
                        }
                        set.add(sum);
                    }
                    if (set.size() > 1) continue;

                    //check for both diagonal;
                    set.clear();
                    int cr = x1;
                    int cc = y1;
                    int sum = 0;
                    while (cr != x4 && cc != y4) {
                        sum += grid[cr][cc];
                        cr++;
                        cc++;
                    }
                    sum += grid[x4][y4];

                    if (sum != req) continue;

                    cr = x2;
                    cc = y2;

                    sum = 0;
                    while (cr != x3 && cc != y3) {
                        sum += grid[cr][cc];
                        cr++;
                        cc--;
                    } 
                    sum += grid[x3][y3];
                    if (sum != req) continue;

                    if (sum != 15) continue;

                    ArrayList<Integer> res = new ArrayList<>();
                    for (int row = x1; row <= x3; row++) {
                        for (int col = y1; col <= y2; col++) {
                            res.add(grid[row][col]);
                        }
                    }

                    if (res.size() != 9) continue;
                    sum = 0;
                    boolean flag = true;
                    for (int num = 1; num <= 9; num++) {
                        if (!res.contains(num)) flag = false;;
                    }
                    if(flag == true) count++;
                }
            }
        }
        return count;
    }
}