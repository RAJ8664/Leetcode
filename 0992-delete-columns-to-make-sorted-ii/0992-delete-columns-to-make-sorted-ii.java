class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs[0].length();
        int count = 0;
        HashSet<Integer> set = new HashSet<>(); 
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 1; j < strs.length; j++) {
                if (!set.contains(j)) {
                    char pre = strs[j - 1].charAt(i), curr = strs[j].charAt(i);
                    if (pre > curr) {
                        flag = true;
                        count++;
                        break;
                    }
                }
            }
            if (!flag) {
                for (int j = 1; j < strs.length; j++) {
                    if (!set.contains(j)) {
                        char pre = strs[j - 1].charAt(i), curr = strs[j].charAt(i);
                        if (pre < curr) {
                            set.add(j);
                        }
                    }
                }
            }
        }
        return count;
    }
}