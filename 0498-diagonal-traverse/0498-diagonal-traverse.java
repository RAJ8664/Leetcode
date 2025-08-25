class Solution {
    private ArrayList<Integer> res;
    private int count;
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        res = new ArrayList<>();
        count = 0;
        for (int j = 0; j < m; j++) {
            fill(0, j, mat);
            count++;
        }
        for (int i = 1; i < n; i++) {
            fill(i, m - 1, mat);
            count++;
        }
        int ans[] = new int[res.size()];
        for (int i = 0; i < res.size(); i++) 
            ans[i] = res.get(i);
        return ans; 
    }
    private void fill(int startRow, int startCol, int arr[][]) {
        int n = arr.length, m = arr[0].length;
        ArrayList<Integer> temp = new ArrayList<>();
        while (startRow < n && startCol >= 0) {
            temp.add(arr[startRow][startCol]);
            startRow++;
            startCol--;
        }
        if (count % 2 == 0) {
            Collections.reverse(temp);
        }
        for (int ele : temp) 
            res.add(ele);
    }
}