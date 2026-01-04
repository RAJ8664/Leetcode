class Solution {
    public String largestEven(String s) {
        int n = s.length();
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++)
            temp.add(s.charAt(i) - '0');
        int lastIdx = n;
        for (int i = temp.size() - 1; i >= 0; i--) {
            if (temp.get(i) % 2 == 0)
                break;
            else
                lastIdx = i;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < lastIdx; i++)
            res.append(temp.get(i) + "");
        return res.toString();
    }
}