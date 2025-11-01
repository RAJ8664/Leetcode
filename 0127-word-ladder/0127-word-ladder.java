class Solution {
    static class Pair {
        String word;
        int step;
        public Pair(String word, int step) {
            this.word = word;
            this.step = step;
        }
        @Override
        public String toString() {
            return "(" + word + " " + step + ")";
        }
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        for (String curr : wordList)
            set.add(curr);
        if (set.contains(beginWord))
            set.remove(beginWord);
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(beginWord, 1));
        while (q.size() > 0) {
            String currWord = q.peek().word;
            int currStep = q.peek().step;
            q.poll();
            if (currWord.equals(endWord))
                return currStep;
            for (int i = 0; i < currWord.length(); i++) {
                char current[] = currWord.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    current[i] = j;
                    String newString = new String(current);
                    if (set.contains(newString)) {
                        set.remove(newString);
                        q.offer(new Pair(newString, currStep + 1));
                    }
                }
            }
        }
        return 0;
    }
}