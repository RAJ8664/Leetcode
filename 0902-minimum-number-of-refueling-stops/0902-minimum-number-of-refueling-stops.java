class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        if (startFuel >= target) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int currIdx = 0, totalStops = 0, currFuel = startFuel;
        while (currFuel < target) {
            while (currIdx < n && stations[currIdx][0] <= currFuel) {
                pq.offer(stations[currIdx][1]);
                currIdx++;
            }
            if (pq.size() == 0) return -1;
            currFuel += pq.poll();
            totalStops++; 
        }
        return totalStops;
    }
}