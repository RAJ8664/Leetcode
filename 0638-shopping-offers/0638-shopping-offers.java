class Solution {
    private HashMap<String, Integer> dp;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();
        int freq[] = new int[6];
        dp = new HashMap<>();
        String temp = "", base = "";
        for (int i = 0; i < 6; i++) 
            base += 0 + ":";
        dp.put(base, 0);
        for (int i = 0; i < needs.size(); i++) 
            freq[i] = needs.get(i);
        for (int i = 0; i < 6; i++) 
            temp += freq[i] + ":";
        return solve(temp, freq, special, price);
    }
    private int solve(String current, int freq[], List<List<Integer>> special, List<Integer> price) {
        if (dp.containsKey(current))
            return dp.get(current);

        String currentKey = "";
        for (int i = 0; i < 6; i++) {
            currentKey += freq[i] + ":";
        }
        
        int ans = getAns(freq, price);
        for (int i = 0; i < special.size(); i++) {
            int newFreq[] = new int[6];
            for (int j = 0; j < 6; j++) newFreq[j] = freq[j];
            for (int j = 0; j < special.get(i).size() - 1; j++) {
                newFreq[j] = newFreq[j] - special.get(i).get(j);
            }
            boolean isGood = true;
            for (int j = 0; j < 6; j++) {
                if (newFreq[j] < 0) isGood = false;
            }
            if (isGood == false) continue;
            String newCurrent = "";
            for (int j = 0; j < 6; j++) {
                newCurrent += newFreq[j] + ":";
            }
            ans = Math.min(ans, special.get(i).get(special.get(i).size() - 1) + solve(newCurrent, newFreq, special, price));
        }
        dp.put(currentKey, ans);
        return ans;
    }
    private int getAns(int freq[], List<Integer> price) {
        int ans = 0;
        for (int i = 0; i < price.size(); i++) {
            ans += price.get(i) * freq[i];
        }
        return ans;
    }
}