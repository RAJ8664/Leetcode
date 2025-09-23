class Solution {
    public int compareVersion(String version1, String version2) {
        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();
        int val = 0;
    
        for (int i = 0; i < version1.length(); i++) {
            char current = version1.charAt(i);
            if (current == '.' || i == version1.length() - 1) {
                if (i == version1.length() - 1 && current != '.')
                    val = val * 10 + (current - '0');
                first.add(val);
                val = 0;
            } else {
                val = val * 10 + (current - '0');
            }
        }
    
        val = 0;
        for (int i = 0; i < version2.length(); i++) {
            char current = version2.charAt(i);
            if (current == '.' || i == version2.length() - 1) {
                if (i == version2.length() - 1 && current != '.')
                    val = val * 10 + (current - '0');
                second.add(val);
                val = 0;
            } else {
                val = val * 10 + (current - '0');
            }
        }
    
        int n = Math.max(first.size(), second.size());
        for (int i = 0; i < n; i++) {
            int a = (i < first.size() ? first.get(i) : 0);
            int b = (i < second.size() ? second.get(i) : 0);
            if (a < b) return -1;
            if (a > b) return 1;
        }
        return 0;
    }
}