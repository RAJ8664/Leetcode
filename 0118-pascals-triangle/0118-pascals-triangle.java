class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = null;
        for (int i=0; i< numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0;j <= i; j++) {
                if (j == 0 ||j == i){
                    row.add(1);
                }
                else {
                    row.add(temp.get(j - 1) + temp.get(j));
                }
            }
            temp = row;
            ans.add(row);
        }
        return ans;
    }
}