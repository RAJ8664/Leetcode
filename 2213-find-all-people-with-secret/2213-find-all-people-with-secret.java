import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    static class Pair {
        int node, time;
        public Pair(int node, int time) {
            this.node = node;
            this.time = time;
        }
        @Override
        public String toString() {
            return "Pair{" +
                   "node=" + node +
                   ", time=" + time +
                   '}';
        }
    }
    static class customSort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            return Integer.compare(first.time, second.time);
        }
    }
    private ArrayList<ArrayList<Pair >> adj;
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        List<Integer> ans = new ArrayList<>();
        adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());

        for (int edge[] : meetings) {
            int u = edge[0], v = edge[1], t = edge[2];
            adj.get(u).add(new Pair(v, t));
            adj.get(v).add(new Pair(u, t));
        }

        int vis[] = new int[n + 1];
        PriorityQueue<Pair> pq = new PriorityQueue<>(new customSort());
        pq.offer(new Pair(firstPerson, 0));
        pq.offer(new Pair(0, 0));
        vis[0] = 1;
        vis[firstPerson] = 1;
        HashSet<Integer> set = new HashSet<>();
        while (pq.size() > 0) {
            int currNode = pq.peek().node;
            int currTime = pq.peek().time;
            set.add(currNode);
            vis[currNode] = 1;
            pq.poll();
            for (int i = 0; i < adj.get(currNode).size(); i++) {
                int childNode = adj.get(currNode).get(i).node;
                int childTime = adj.get(currNode).get(i).time;
                if (vis[childNode] == 1)
                    continue;
                if (childTime >= currTime)
                    pq.offer(new Pair(childNode, childTime));
            }
        }

        for (int ele : set)
            ans.add(ele);
        return ans;
    }
}