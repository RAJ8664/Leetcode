class Solution {
    public int[] decimalRepresentation(int n) {
        ArrayList<Integer> dig = new ArrayList<>();
        int temp = n;
        while (temp > 0) {
            dig.add(0, temp % 10);
            temp /= 10;
        }    
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < dig.size(); i++) {
            int current = dig.get(i);
            if (current == 0) continue;
            int curr = current * (int)(Math.pow(10, dig.size() - i - 1)); 
            ans.add(curr);
        }
        int res[] = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);
        return res;
    }
}