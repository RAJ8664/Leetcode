class Solution {
    static class Tuple {
        int u, v, weight;
        public Tuple(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return "(" + u + " " + v + " " + weight + ")";
        }
    }

    static class custom_sort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            return Integer.compare(second.weight, first.weight);
        }
    }
    public int maxStability(int n, int[][] edges, int k) {
        ArrayList<Tuple> upgradeEdge = new ArrayList<>();
        int count = 0;
        int min = Integer.MAX_VALUE;
        
        DSU dsu = new DSU(n + 1);
        
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][3] == 0) 
                upgradeEdge.add(new Tuple(edges[i][0], edges[i][1], edges[i][2]));
            else {
                if (dsu.Leader(edges[i][0]) != dsu.Leader(edges[i][1])) {
                    dsu.unite(edges[i][0], edges[i][1]);
                    min = Math.min(min, edges[i][2]);
                    count++;
                }
                else 
                    return -1;
            }
        }

        Collections.sort(upgradeEdge, new custom_sort());
        ArrayList<Integer> tempWeight = new ArrayList<>();
        
        for (int i = 0; i < upgradeEdge.size(); i++) {
            int u = upgradeEdge.get(i).u, v = upgradeEdge.get(i).v, wt = upgradeEdge.get(i).weight;
            if (count == n - 1) break;
            if (dsu.Leader(u) != dsu.Leader(v)) {
                dsu.unite(u, v);
                count++;
                tempWeight.add(wt);
            }
        }
        if (count < n - 1) 
            return -1;
        
        Collections.reverse(tempWeight);
        for (int i = 0; i < tempWeight.size(); i++) {
            if (k > 0) {
                k--;
                min = Math.min(min, 2 * tempWeight.get(i));
            }
            else 
                min = Math.min(min, tempWeight.get(i));
        }
        return min;
    }
    static class DSU {
        int[] Parent, Group_Size;
        int Number_of_Nodes, Number_of_Groups, Max_Group;

        public DSU(int Number_of_Nodes) {
            this.Number_of_Nodes = Number_of_Nodes;
            Parent = new int[Number_of_Nodes + 1];
            Group_Size = new int[Number_of_Nodes + 1];
            Number_of_Groups = Number_of_Nodes;
            Max_Group = 1;
            for (int i = 1; i <= Number_of_Nodes; i++) {
                Parent[i] = i;
                Group_Size[i] = 1;
            }
        }

        public int Leader(int x) {
            return Parent[x] = (Parent[x] == x ? x : Leader(Parent[x]));
        }

        public boolean Is_same_Group(int x, int y) {
            return Leader(x) == Leader(y);
        }

        public void unite(int x, int y) {
            int leader1 = Leader(x);
            int leader2 = Leader(y);
            if (leader1 != leader2) {
                Number_of_Groups--;
                if (Group_Size[leader1] < Group_Size[leader2]) {
                    int temp = leader1;
                    leader1 = leader2;
                    leader2 = temp;
                }
                Parent[leader2] = leader1;
                Group_Size[leader1] += Group_Size[leader2];
                Max_Group = Math.max(Max_Group, Group_Size[leader1]);
            }
        }

        public int getSize(int x) {
            return Group_Size[Leader(x)];
        }
    }
}