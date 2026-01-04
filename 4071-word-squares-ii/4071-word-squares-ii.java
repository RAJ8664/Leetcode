class Solution {
    private List<List<String >> res;
    public List<List<String >> wordSquares(String[] words) {
        int n = words.length;
        res = new ArrayList<>();
        HashSet<List<String >> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i)
                    continue;
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j)
                        continue;
                    for (int l = 0; l < n; l++) {
                        if (l == i || l == j || l == k)
                            continue;
                        List<String> temp = new ArrayList<>();
                        String top = words[i], left = words[j], right = words[k], bottom = words[l];
                        if (check(top, left, right, bottom)) {
                            temp.add(words[i]);
                            temp.add(words[j]);
                            temp.add(words[k]);
                            temp.add(words[l]);
                            set.add(temp);
                        }
                    }
                }
            }
        }
        for (List<String> x : set)
            res.add(new ArrayList<>(x));
        Collections.sort(res, new customSort());
        return res;
    }

    private boolean check(String top, String left, String right, String bottom) {
        return top.charAt(0) == left.charAt(0) && top.charAt(3) == right.charAt(0) && bottom.charAt(0) == left.charAt(3) && bottom.charAt(3) == right.charAt(3);
    }

    static class customSort implements Comparator<List<String >> {
        @Override
        public int compare(List<String> first, List<String> second) {
            int op1 = first.get(0).compareTo(second.get(0));
            if (op1 != 0)
                return op1;
            int op2 = first.get(1).compareTo(second.get(1));
            if (op2 != 0)
                return op2;
            int op3 = first.get(2).compareTo(second.get(2));
            if (op3 != 0)
                return op3;
            return first.get(3).compareTo(second.get(3));
        }
    }
}