class Solution {
    private long base = 911;
    private long mod = 1000000007L;
    private ArrayList<ArrayList<Integer>> adj;
    private long forwardHash[];
    private long reverseHash[];
    private int len[];
    private long pow[];
    public boolean[] findAnswer(int[] parent, String s) {
        int n = parent.length;
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        for(int i = 0; i < n; i++) {
            if(parent[i] != -1){
                adj.get(parent[i]).add(i);
            }
        }
        for (ArrayList<Integer> curr : adj) Collections.sort(curr);
        
        pow = new long[n + 1];
        forwardHash = new long[n];
        reverseHash = new long[n];
        len = new int[n];

        pow[0] = 1;
        for(int i = 1; i <= n; i++) pow[i] = (pow[i - 1] * base) % mod;
        
        dfs(0, s);
        
        boolean[] answer = new boolean[n];
        for(int i = 0; i < n; i++) {
            if (forwardHash[i] == reverseHash[i]) answer[i] = true;
            else answer[i] = false;
        }
        return answer;
    }
   
    public void dfs(int u, String s) {
        len[u] = 1;
        forwardHash[u] = 0;
        for(int v : adj.get(u)) {
            dfs(v, s);
            forwardHash[u] = (forwardHash[u] * pow[len[v]] + forwardHash[v]) % mod;
            len[u] += len[v];
        }
       
        forwardHash[u] = (forwardHash[u] * base + (s.charAt(u) - 'a' + 1)) % mod;
        reverseHash[u] = (s.charAt(u) - 'a' + 1);
        
        for (int i = adj.get(u).size() - 1; i >= 0; i--) {
            int v = adj.get(u).get(i);
            reverseHash[u] = (reverseHash[u] * pow[len[v]] + reverseHash[v]) % mod;
        }
    }
}