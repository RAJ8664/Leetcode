class Solution {
    static class Pair {
        int node, cost;
        public Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        @Override
        public String toString() {
            return "(" + node + " " + cost + ")";
        }
    }

    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int op1 = Integer.compare(first.cost, second.cost);
            return op1;
        }
    }
    
    private ArrayList<ArrayList<Pair>> adj;
   
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        if (source == target)
            return 0;

        adj = new ArrayList<>();
        for (int i = 0; i <= routes.length; i++)
            adj.add(new ArrayList<>());

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            for (int ele : routes[i - 1]) {
                if (!map.containsKey(ele)) 
                    map.put(ele, new ArrayList<>());
                map.get(ele).add(i);
            }
        }

        int dist[] = new int[(int)(n + 1)];
        Arrays.fill(dist, (int)(1e9));
        
        PriorityQueue<Pair> pq = new PriorityQueue<>(new customSort());
        for (Map.Entry<Integer, ArrayList<Integer>> curr : map.entrySet()) {
            ArrayList<Integer> val = new ArrayList<>();
            val = curr.getValue();
            for (int i = 0; i < val.size() - 1; i++) {
                int u = val.get(i), v = val.get(i + 1);
                if (u == v) continue;
                adj.get(u).add(new Pair(v, 1));
                adj.get(v).add(new Pair(u, 1));
            }
            if (val.size() > 1) {
                int u = val.get(0), v = val.get(val.size() - 1);
                if (u == v) continue;
                adj.get(u).add(new Pair(v, 1));
                adj.get(v).add(new Pair(u, 1));
            }
            
            if (curr.getKey() == source) {
                for (int ele : val) {
                    dist[ele] = 0;
                    pq.offer(new Pair(ele, 0));
                }
            }
        }

        while (pq.size() > 0){
            int currNode = pq.peek().node, currCost = pq.peek().cost;
            pq.poll();
            for (int i = 0; i < adj.get(currNode).size(); i++) {
                int childNode = adj.get(currNode).get(i).node;
                int childDist = adj.get(currNode).get(i).cost;
                if (dist[childNode] > childDist + currCost) {
                    dist[childNode] = childDist + currCost;
                    pq.offer(new Pair(childNode, dist[childNode]));
                } 
            }
        }

        int mini = Integer.MAX_VALUE;
        boolean flag = false;
        for (Map.Entry<Integer, ArrayList<Integer>> curr : map.entrySet()) {
            ArrayList<Integer> val = new ArrayList<>();
            val = curr.getValue();
            if (curr.getKey() == target) {
                for (int ele : val) {
                    if (dist[ele] != (int)(1e9)) flag = true;
                    mini = Math.min(mini, dist[ele]);
                }
            }
        }
        if (flag == false) return -1;
        return mini + 1;
    }
}