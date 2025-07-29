class Solution {
    public int[] longestCommonPrefix(String[] words) {
        int n = words.length;

        int prefMax[] = new int[n];
        int suffMax[] = new int[n];

        for (int i = 1; i < n; i++)
            prefMax[i] = Math.max(prefMax[i - 1], getLen(words[i - 1], words[i]));

        for (int i = n - 2; i >= 0; i--)
            suffMax[i] = Math.max(suffMax[i + 1], getLen(words[i], words[i + 1]));

        int res[] = new int[n];
        for (int i = 0; i < n; i++) {
            if (i - 1 >= 0)
                res[i] = Math.max(res[i], prefMax[i - 1]);
            if (i + 1 < n)
                res[i] = Math.max(res[i], suffMax[i + 1]);
            if (i - 1 >= 0 && i + 1 < n)
                res[i] = Math.max(res[i], getLen(words[i - 1], words[i + 1]));
        }
        return res;
    }

    private int getLen(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0, count = 0;
        while (i < n && j < m && s.charAt(i) == t.charAt(j)) {
            count++;
            i++;
            j++;
        }
        return count;
    }
}