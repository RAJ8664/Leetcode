class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length, m = mana.length;
        long res[] = new long[n + 1];
        for (int j = 0; j < m; ++j) {
            for (int i = 0; i < n; ++i) 
                res[i + 1] = Math.max(res[i + 1], res[i]) + (long) mana[j] * skill[i];
            for (int i = n - 1; i > 0; --i) 
                res[i] = res[i + 1] - (long) mana[j] * skill[i];
        }
        return res[n];
    }
}