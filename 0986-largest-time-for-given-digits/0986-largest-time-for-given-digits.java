
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public String largestTimeFromDigits(int[] arr) {
        int n = arr.length;
        int freq[] = new int[10];
        for (int ele : arr)
            freq[ele]++;
        ArrayList<String> ans = new ArrayList<>();
        for (int f = 0; f <= 2; f++) {
            for (int s = 0; s <= 9; s++) {
                if (f == 2 && s >= 4)
                    continue;
                for (int t = 0; t <= 5; t++) {
                    for (int u = 0; u <= 9; u++) {
                        int tempFreq[] = new int[10];
                        for (int i = 0; i < 10; i++)
                            tempFreq[i] = freq[i];

                        if (tempFreq[f] == 0)
                            continue;
                        tempFreq[f]--;
                        if (tempFreq[s] == 0)
                            continue;
                        tempFreq[s]--;
                        if (tempFreq[t] == 0)
                            continue;
                        tempFreq[t]--;
                        if (tempFreq[u] == 0)
                            continue;
                        tempFreq[u]--;

                        String res = String.valueOf(f) + String.valueOf(s) + ":" + String.valueOf(t) + String.valueOf(u);
                        if (f == 2 && s == 4)
                            continue;
                        if (f == 2 && s == 4 && t == 0 && u == 0)
                            continue;
                        ans.add(res);
                    }
                }
            }
        }
        ans.add("");
        Collections.sort(ans);
        return ans.get(ans.size() - 1);
    }
}