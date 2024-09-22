class Solution {
    public boolean reportSpam(String[] message, String[] bannedWords) {
        int n = message.length;
        HashSet<String> set = new HashSet<>();
        for (String x : bannedWords) set.add(x);
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (set.contains(message[i])) count++;
        }
        return count >= 2;
    }
}