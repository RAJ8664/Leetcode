class Solution {
    private HashMap<String, Integer> map;
    private List<List<String >> res;
    static class Pair {
        String word;
        int step;
        public Pair(String word, int step) {
            this.word = word;
            this.step = step;
        }
        @Override
        public String toString() {
            return "(" + word + ", " + step + ")";
        }
    }
    public List<List<String >> findLadders(String beginWord, String endWord, List<String> wordList) {
        int n = wordList.size();
        map = new HashMap<>();
        res = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (String curr : wordList)
            set.add(curr);
        int minStep = Integer.MAX_VALUE;
        if (set.contains(beginWord))
            set.remove(beginWord);
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(beginWord, 0));
        while (q.size() > 0) {
            String currWord = q.peek().word;
            int currStep = q.peek().step;
            q.poll();
            if (!map.containsKey(currWord))
                map.put(currWord, currStep);
            if (currWord.equals(endWord)) {
                minStep = currStep;
                break;
            }
            for (int i = 0; i < currWord.length(); i++) {
                char current[] = currWord.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    current[i] = j;
                    if (set.contains(new String(current))) {
                        set.remove(new String(current));
                        q.offer(new Pair(new String(current), currStep + 1));
                    }
                }
            }
        }
        if (minStep == Integer.MAX_VALUE)
            return new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        temp.add(endWord);
        dfs(beginWord, endWord, temp);
        return res;
    }
    private void dfs(String beginWord, String curr, ArrayList<String> temp) {
        if (curr.equals(beginWord)) {
            Collections.reverse(temp);
            res.add(new ArrayList<>(temp));
            Collections.reverse(temp);
            return;
        }
        for (int i = 0; i < curr.length(); i++) {
            char current[] = curr.toCharArray();
            for (char j = 'a'; j <= 'z'; j++) {
                current[i] = j;
                if (map.containsKey(new String(current))) {
                    if (map.get(new String(current)) == map.get(curr) - 1) {
                        temp.add(new String(current));
                        dfs(beginWord, new String(current), temp);
                        temp.remove(temp.size() - 1);
                    }
                }
            }
        }
    }
}