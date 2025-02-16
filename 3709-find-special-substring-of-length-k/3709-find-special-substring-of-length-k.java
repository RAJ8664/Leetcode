class Solution {
    public boolean hasSpecialSubstring(String s, int k) {
        int n = s.length();
        TreeSet<Character> set = new TreeSet<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            set.add(s.charAt(i));
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int start = 0;
        if (map.size() == 1 && k < n && s.charAt(k) != set.first()) return true;
        if (map.size() == 1 && k >= n) return true; 
        System.out.println(map);
        for (int i = k; i < n; i++) {
            map.put(s.charAt(start), map.getOrDefault(s.charAt(start), 0) -1);
            if (map.getOrDefault(s.charAt(start), 0) == 0) {
                map.remove(s.charAt(start));
                set.remove(s.charAt(start));
            }
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            set.add(s.charAt(i));
            if (map.size() == 1) {
                if (start >= 0 && s.charAt(start) != set.first() && i + 1 < n && set.first() != s.charAt(i + 1)) return true;
                else if (start < 0 && i + 1 < n && s.charAt(i + 1) != set.first()) return true;
                else if (start >= 0 && i + 1 >= n && s.charAt(start) != set.first()) return true;
            }
            System.out.println(map);
            start++;
        }
        return false;
    }
}