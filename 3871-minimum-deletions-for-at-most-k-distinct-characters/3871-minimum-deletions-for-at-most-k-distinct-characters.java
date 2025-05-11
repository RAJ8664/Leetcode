class Solution {
    static class Pair {
        char ch;
        int count;
        public Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
        @Override
        public String toString() {
            return "(" + ch + " " + count + ")";
        }
    }
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(first.count, second.count);
        }
    }
    public int minDeletion(String s, int k) {
        int n = s.length();
        PriorityQueue<Pair> pq = new PriorityQueue<>(new custom_sort());
        int res = 0;
        int freq[] = new int[26];
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            freq[current - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            pq.offer(new Pair('a', freq[i]));
        }
        while (pq.size() > k) {
            res += pq.poll().count;
        }
        return res;
    }
}