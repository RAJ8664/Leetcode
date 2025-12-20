class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int count = 0;
        for (int j = 0; j < strs[0].length(); j++) {
            char prev = strs[0].charAt(j);
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (strs[i].charAt(j) < prev) {
                    flag = false;
                    break;
                }
                prev = strs[i].charAt(j);
            }
            if (flag == false)
                count++;
        }
        return count;
    }
}