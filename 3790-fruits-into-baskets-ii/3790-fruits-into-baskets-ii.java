class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        int total = 0, count = 0;
        for (int ele : fruits) total += ele;
        int vis[] = new int[n];
        for (int i = 0; i < n; i++) {
            boolean found = false;
            for (int j = 0; j < n; j++) {
                if (vis[j] == 1) continue;
                if (baskets[j] >= fruits[i]) {
                    total -= fruits[i];
                    vis[j] = 1;
                    found = true;
                    break;
                }
            }
            if (found == false) count++;
        }
        return count;
    }
}