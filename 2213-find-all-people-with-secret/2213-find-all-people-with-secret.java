class Solution {
    static class Pair {
        int node, time;
        Pair(int n, int t) {
            node = n;
            time = t;
        }
    }
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] m : meetings) {
            adj.get(m[0]).add(new Pair(m[1], m[2]));
            adj.get(m[1]).add(new Pair(m[0], m[2]));
        }
        int[] timeKnown = new int[n];
        Arrays.fill(timeKnown, Integer.MAX_VALUE);
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        timeKnown[0] = 0;
        timeKnown[firstPerson] = 0;
        pq.offer(new Pair(0, 0));
        pq.offer(new Pair(firstPerson, 0));
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            if (cur.time > timeKnown[cur.node]) 
                continue;
            for (Pair next : adj.get(cur.node)) {
                if (next.time >= cur.time &&
                    next.time < timeKnown[next.node]) {
                    timeKnown[next.node] = next.time;
                    pq.offer(new Pair(next.node, next.time));
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (timeKnown[i] != Integer.MAX_VALUE)
                ans.add(i);
        }
        return ans;
    }
}
