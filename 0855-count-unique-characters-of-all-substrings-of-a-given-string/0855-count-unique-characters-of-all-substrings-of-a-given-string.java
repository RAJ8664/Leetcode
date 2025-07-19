class Solution {
    private HashMap<Character, TreeSet<Integer>> map;
    public int uniqueLetterString(String s) {
        int n = s.length();
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (!map.containsKey(current))
                map.put(current, new TreeSet<>());
            map.get(current).add(i);
        }
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            TreeSet<Integer> currentSet = map.get(current);
            Integer leftIdx = currentSet.lower(i);
            Integer rightIdx = currentSet.higher(i);
            
            
            int leftCount = 0, rightCount = 0, totalCount = 0;
            if (leftIdx != null) 
                leftCount = i - leftIdx - 1;
            else 
                leftCount += i;
            if (rightIdx != null) 
                rightCount += rightIdx - i - 1;
            else 
                rightCount += n - i - 1;
           
            ans += (leftCount * (rightCount + 1));
            ans += (rightCount);
            ans++;
        }
        return ans;
    }
}