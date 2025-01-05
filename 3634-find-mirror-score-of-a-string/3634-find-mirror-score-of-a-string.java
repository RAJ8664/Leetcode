class Solution {
    public long calculateScore(String s) {
        int n = s.length();
        long ans = 0;
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for(int i = 0; i < s.length(); ++i){
            int cur = s.charAt(i) - 'a';
            int mirror = 25 - cur;
            if(mp.containsKey(mirror) && mp.get(mirror).size() > 0){
                ans += (long)i - (long)mp.get(mirror).get(mp.get(mirror).size() - 1);
                mp.get(mirror).remove(mp.get(mirror).size() - 1);
            }
            else mp.computeIfAbsent(cur, key -> new ArrayList<>()).add(i);
        }
        return ans;
    }
}