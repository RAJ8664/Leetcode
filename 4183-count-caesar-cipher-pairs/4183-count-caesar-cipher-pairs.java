class Solution {
    private HashMap<String, Integer> freq;
    public long countPairs(String[] words) {
        int n = words.length;
        freq = new HashMap<>();
        long count = 0;
        for (int i = 0; i < n; i++) {
            String current = words[i];
            HashSet<String> gen = new HashSet<>();
            gen = generate(current);
            for (String s : gen)
                count = (count + 1L * freq.getOrDefault(s, 0));
            for (String s : gen)
                freq.put(s, freq.getOrDefault(s, 0) + 1);
        }
        return count / 26;
    }

    private HashSet<String> generate(String current) {
        HashSet<String> set = new HashSet<>();
        StringBuilder next = new StringBuilder(current);
        set.add(next.toString());
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < current.length(); j++)
                next.setCharAt(j, (char)((next.charAt(j) - 'a' + 1) % 26 + 'a'));
            set.add(next.toString());
        }
        return set;
    }
}