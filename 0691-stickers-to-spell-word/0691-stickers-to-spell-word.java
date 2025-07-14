class Solution {
    private HashMap<String, Integer> dp;
    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        dp = new HashMap<>();
        dp.put("", 0);
        int ans = solve(target, stickers);
        return ans == Integer.MAX_VALUE / 10 ? -1 : ans;
    }

    private int solve(String target, String stickers[]) {
        if (dp.containsKey(target)) 
            return dp.get(target);

        int currFreq[] = new int[26];
        for (int i = 0; i < target.length(); i++)
            currFreq[target.charAt(i) - 'a']++;
        
        int ans = Integer.MAX_VALUE / 10;

        for (int i = 0; i < stickers.length; i++) {
            String current = stickers[i];
            boolean willHelp = false;
            int thisFreq[] = new int[26];
            for (int j = 0; j < current.length(); j++) {
                if (currFreq[current.charAt(j) - 'a'] > 0) 
                    willHelp = true;
                thisFreq[current.charAt(j) - 'a']++;
            }
            if (willHelp == false) 
                continue;
            String newString = "";
            for (int j = 0; j < target.length(); j++) {
                if (thisFreq[target.charAt(j) - 'a'] > 0) 
                    thisFreq[target.charAt(j) - 'a']--;
                else 
                    newString += target.charAt(j);
            }
            ans = Math.min(ans, 1 + solve(newString, stickers));
        }
        dp.put(target, ans);
        return ans;
    }
}