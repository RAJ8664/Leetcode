class Solution {
    public String majorityFrequencyGroup(String s) {
        int n = s.length();
        int freq[] = new int[26];
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']++;
        }    
        
        HashMap<Integer, ArrayList<Character>> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            int currFreq = freq[i];
            if (currFreq == 0) 
                continue;
            if (!map.containsKey(currFreq))
                map.put(currFreq, new ArrayList<>()); 
            map.get(currFreq).add((char)(i + 'a')); 
        }

        int maxGroupSize = 0, maxFreq = 0;
        ArrayList<Character> res = new ArrayList<>();
        for (Map.Entry<Integer, ArrayList<Character>> curr : map.entrySet()) {
            maxGroupSize = Math.max(maxGroupSize, curr.getValue().size());
        }

        for (Map.Entry<Integer, ArrayList<Character>> curr : map.entrySet()) {
            if (curr.getValue().size() == maxGroupSize) {
                if (curr.getKey() > maxFreq) {
                    maxFreq = curr.getKey();
                    res = curr.getValue();
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        for (char x : res) 
            ans.append(x);
        return ans.toString();
    }
}