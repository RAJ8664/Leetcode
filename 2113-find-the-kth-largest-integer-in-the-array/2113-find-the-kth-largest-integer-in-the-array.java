import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Solution {
    static class customSort implements Comparator<String> {
        @Override
        public int compare(String first, String second) {
            if (first.length() != second.length()) {
                if (first.length() < second.length())
                    return 1;
                else
                    return -1;
            } else {
                for (int i = 0; i < first.length(); i++) {
                    int f = first.charAt(i) - '0';
                    int s = second.charAt(i) - '0';
                    if (f == s)
                        continue;
                    if (f < s)
                        return 1;
                    if (s < f)
                        return -1;
                }
                return 0;
            }
        }
    }
    public String kthLargestNumber(String[] nums, int k) {
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)
            res.add(nums[i]);
        Collections.sort(res, new customSort());
        return res.get(k - 1);
    }
}