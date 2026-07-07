class Solution {
private:
    int solve(int time, int mouse_node, int cat_node, int n,  const vector<vector<int>>& graph, vector<vector<vector<int>>>& dp) {
        if (time >= 6 * n) return 0; 
        if (mouse_node == cat_node) return 2; 
        if (mouse_node == 0) return 1; 

        if (dp[time][mouse_node][cat_node] != -1) 
            return dp[time][mouse_node][cat_node];
            
        if (time % 2 == 0) {
            // It's Mouse's turn
            bool cat_wins_everything = true; 
            for (int child_node : graph[mouse_node]) {
                int next_state = solve(time + 1, child_node, cat_node, n, graph, dp);
                if (next_state == 1) 
                    return dp[time][mouse_node][cat_node] = 1; // Immediate win for mouse
                if (next_state != 2) 
                    cat_wins_everything = false; // Mouse can at least force a draw
            }
            if (cat_wins_everything) 
                return dp[time][mouse_node][cat_node] = 2;
            else 
                return dp[time][mouse_node][cat_node] = 0;
        } 
        else {
            // It's Cat's turn
            bool mouse_wins_everything = true; 
            for (int child_node : graph[cat_node]) {
                if (child_node == 0) continue; // Cat cannot move to the hole
                int next_state = solve(time + 1, mouse_node, child_node, n, graph, dp);
                if (next_state == 2) 
                    return dp[time][mouse_node][cat_node] = 2; // Immediate win for cat
                if (next_state != 1) 
                    mouse_wins_everything = false; // Cat can at least force a draw
            }
            if (mouse_wins_everything) 
                return dp[time][mouse_node][cat_node] = 1;
            else
                return dp[time][mouse_node][cat_node] = 0;
        }
    }

public:
    int catMouseGame(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<vector<vector<int>>> dp(6 * n, vector<vector<int>>(n, vector<int>(n, -1)));
        return solve(0, 1, 2, n, graph, dp);
    }
};