class Solution {
    private int maxi;
    public int maximumRows(int[][] matrix, int numSelect) {
        int n = matrix.length, m = matrix[0].length;
        maxi = 0;
        solve(0, matrix, numSelect, new HashSet<>());
        return maxi;
    }
    private void solve(int col, int matrix[][], int numSelect, HashSet<Integer> temp) {
        if (col == matrix[0].length) {
            int total = 0;
            if (temp.size() == numSelect) {
                int count = 0;
                for (int i = 0; i < matrix.length; i++) {
                    boolean flag = true;
                    for (int j = 0; j < matrix[0].length; j++) {
                        if (matrix[i][j] == 1) {
                            if (!temp.contains(j)) {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag)
                        total++;
                }
                maxi = Math.max(maxi, total);
            }
            return;
        }

        temp.add(col);
        solve(col + 1, matrix, numSelect, temp);
        temp.remove(col);
        solve(col + 1, matrix, numSelect, temp);
    }
}