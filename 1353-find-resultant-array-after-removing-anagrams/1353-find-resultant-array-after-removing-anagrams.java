class Solution {
    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> res = new ArrayList<>();
        Stack<String> st = new Stack<>();
        for (int i = 0; i < words.length; i++) {
            String current = words[i];
            if (st.size() == 0)
                st.add(current);
            else {
                if (isAnagram(st.peek(), current))
                    continue;
                else 
                    st.add(current);
            }
        }
        while (st.size() > 0)
            res.add(st.pop());
        Collections.reverse(res);
        return res;
    }

    private boolean isAnagram(String s, String t) {
        int n = s.length(), m = t.length();
        int freq1[] = new int[26];
        int freq2[] = new int[26];
        for (int i = 0; i < n; i++)
            freq1[s.charAt(i) - 'a']++;
        for (int i = 0; i < m; i++) 
            freq2[t.charAt(i) - 'a']++;
        for (int i = 0; i < 26; i++)
            if (freq1[i] != freq2[i]) 
                return false;
        return true;
    } 
}