import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
    static class Pair {
        String first, second;
        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }
        @Override
        public String toString() {
            return first + ", " + second;
        }
    }
    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            int op1 = o1.second.compareTo(o2.second);
            if (op1 != 0)
                return op1;
            return o1.first.compareTo(o2.first);
        }
    }
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        int n = code.length;
        List<Pair> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (check(code[i], businessLine[i], isActive[i]))
                res.add(new Pair(code[i], businessLine[i]));
        }
        Collections.sort(res, new customSort());
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < res.size(); i++)
            ans.add(res.get(i).first);
        return ans;
    }
    private boolean check(String first, String second, boolean third) {
        if (third == false)
            return false;
        if (!second.equals("electronics") && !second.equals("grocery") && !second.equals("pharmacy") && !second.equals("restaurant"))
            return false;
        if (first.length() == 0)
            return false;
        for (int i = 0; i < first.length(); i++) {
            char current = first.charAt(i);
            if (!Character.isDigit(current) && !Character.isLetter(current) && current != '_')
                return false;
        }
        return true;
    }
}