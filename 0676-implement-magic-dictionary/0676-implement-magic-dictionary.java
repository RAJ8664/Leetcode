import java.util.ArrayList;

class MagicDictionary {
    private ArrayList<String> res;
    public MagicDictionary() {
        res = new ArrayList<>();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary)
            res.add(word);
    }

    public boolean search(String searchWord) {
        for (String word : res) {
            if (ok(word, searchWord))
                return true;
        }
        return false;
    }

    private boolean ok(String word, String searchWord) {
        int n = word.length();
        int m = searchWord.length();
        if (n != m)
            return false;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (word.charAt(i) != searchWord.charAt(i))
                count++;
        }
        return count == 1;

    }
}

/**
    Your MagicDictionary object will be instantiated and called as such:
    MagicDictionary obj = new MagicDictionary();
    obj.buildDict(dictionary);
    boolean param_2 = obj.search(searchWord);
*/