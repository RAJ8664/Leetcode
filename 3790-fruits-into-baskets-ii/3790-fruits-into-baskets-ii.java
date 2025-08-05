class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        int vis[] = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if (vis[j] == 0 && baskets[j] >= fruits[i]) {
                    vis[j] = 1;
                    flag = true;
                    break;
                }
            }
            if (flag == false) count++;
        }
        return count;
    }
}