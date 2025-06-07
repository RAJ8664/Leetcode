class Solution {
    private int mod = (int)(1e9 + 7);
    private ArrayList<ArrayList<Integer>> adj;
    private int dp[];
    private HashMap<Integer, ArrayList<Integer>> map;
    private int memo[][];
    public int goodSubtreeSum(int[] vals, int[] par) {
        int n = vals.length;
        map = new HashMap<>();
        for (int i = 0; i <= n + 1; i++) map.put(i, new ArrayList<>());
        dp = new int[n + 1];
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++) adj.add(new ArrayList<>());
        for (int i = 1; i < n; i++) {
            adj.get(i).add(par[i]);
            adj.get(par[i]).add(i);
        }
        long res = 0;
        dfs(0, -1, vals);
        for (int ele : dp) res = (res + ele) % mod;
        return (int)(res);
    }
    private void dfs(int u, int par, int val[]) {
        if (adj.get(u).size() == 1 && u != 0) {
            if (check(val[u]) == false) {
                dp[u] = 0;
                if (!map.containsKey(u)) map.put(u, new ArrayList<>());
                map.get(u).add(val[u]);
            }
            else {
                dp[u] = val[u];
                if (!map.containsKey(u)) map.put(u, new ArrayList<>());
                map.get(u).add(val[u]);
            }
            return;
        }
        ArrayList<Integer> current = new ArrayList<>();
        current.add(val[u]);
        for (int v : adj.get(u)) {
            if (v != par) {
                dfs(v, u, val);
                ArrayList<Integer> child = new ArrayList<>();
                child = map.get(v);
                for (int ele : child) current.add(ele);
            }
        }
        map.put(u, current);
        dp[u] = GetAnswer(current);    
    }
    private int GetAnswer(ArrayList<Integer> current) {
        int n = current.size();
        memo = new int[n + 1][1 << 10];
        for (int temp[] : memo) Arrays.fill(temp, -1);
        int res = foo(0, 0, current);
        return res;
    }
    private int foo(int ind, int mask, ArrayList<Integer> arr) {
        if (ind >= arr.size()) return 0;
        if (memo[ind][mask] != -1) return memo[ind][mask];
        int current_mask = mask, temp = arr.get(ind);
        boolean flag = true;
        while (temp > 0) {
            int d = temp % 10;
            if ((current_mask & (1 << d)) != 0) {
                flag = false;
                break;
            }
            current_mask |= (1 << d);
            temp /= 10;
        }
        if (mask == 0) {
            int op1 = 0, op2 = 0;
            op1 = foo(ind + 1, mask, arr);
            if (flag == true) {
                op2 = arr.get(ind) + foo(ind + 1, current_mask, arr);
            }
            int res = Math.max(op1, op2);
            return memo[ind][mask] = res;
        }
        else {
            int op1 = 0, op2 = 0;
            op1 = foo(ind + 1, mask, arr);
            if (flag == true) {
                op2 = arr.get(ind) + foo(ind + 1, current_mask, arr);
            }
            int res = Math.max(op1, op2);
            return memo[ind][mask] = res;
        }
    }
    private boolean check_string(String s) {
        int n = s.length();
        int freq[] = new int[10];
        for (int i = 0; i < n; i++) {
            if (freq[s.charAt(i) - '0'] > 0) return false;
            freq[s.charAt(i) - '0']++;
        }
        return true;
    }
    private boolean check(int n) {
        int temp = n;
        boolean flag = true;
        int freq[] = new int[10];
        while (temp > 0) {
            if (freq[temp % 10] > 0) flag = false;
            freq[temp % 10]++;
            temp /= 10;
        }
        return flag;
    }
}