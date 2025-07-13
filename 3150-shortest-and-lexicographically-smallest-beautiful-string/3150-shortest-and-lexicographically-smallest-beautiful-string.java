import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int pref[] = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1')
                count++;
            pref[i] = count;
        }
        ArrayList<String> res = new ArrayList<>();
        for (int len = k; len <= n; len++) {
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                if (i + len - 1 < n) {
                    int total = 0;
                    total += pref[i + len - 1];
                    if (i - 1 >= 0)
                        total -= pref[i - 1];
                    if (total == k)
                        flag = true;
                }
            }
            if (flag == true) {
                for (int i = 0; i < n; i++) {
                    if (i + len - 1 < n) {
                        int total = 0;
                        total += pref[i + len - 1];
                        if (i - 1 >= 0)
                            total -= pref[i - 1];
                        if (total == k)
                            res.add(s.substring(i, i + len));
                    }
                }
                break;
            }
        }
        Collections.sort(res);
        if (res.size() == 0)
            return "";
        return res.get(0);
    }
}