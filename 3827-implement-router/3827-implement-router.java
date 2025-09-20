class Router {
    private HashSet<Tuple> packets;
    private ArrayList<Tuple> arr;
    private Deque<Tuple> dq;
    private HashMap<Integer, ArrayList<Tuple >> map;
    private int limit;

    static class Tuple {
        int src, dest, time;
        public Tuple(int src, int dest, int time) {
            this.src = src;
            this.dest = dest;
            this.time = time;
        }
        @Override
        public String toString() {
            return "(" + src + " " + dest + " " + time + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Tuple current = (Tuple)(obj);
            return current.src == src && current.dest == dest && current.time == time;
        }
        @Override
        public int hashCode() {
            return Objects.hash(src, dest, time);
        }
    }

    public Router(int memoryLimit) {
        packets = new HashSet<>();
        dq = new ArrayDeque<>();
        arr = new ArrayList<>();
        map = new HashMap<>();
        limit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        if (packets.contains(new Tuple(source, destination, timestamp)))
            return false;
        if (dq.size() == limit) {
            Tuple oldest = dq.pollFirst();
            if (map.containsKey(oldest.dest)) {
                ArrayList<Tuple> current = map.get(oldest.dest);
                current.remove(oldest);
                map.put(oldest.dest, current);
            }
            packets.remove(oldest);
            arr.remove(0);
        }
        Tuple current = new Tuple(source, destination, timestamp);
        if (packets.contains(current))
            return false;

        packets.add(current);
        dq.addLast(current);
        arr.add(current);
        if (!map.containsKey(current.dest))
            map.put(current.dest, new ArrayList<>());
        map.get(current.dest).add(current);
        return true;
    }

    public int[] forwardPacket() {
        if (dq.size() == 0)
            return new int[] {};
        Tuple toForward = dq.pollFirst();
        packets.remove(toForward);
        if (map.containsKey(toForward.dest)) {
            ArrayList<Tuple> current = map.get(toForward.dest);
            current.remove(toForward);
            map.put(toForward.dest, current);
        }
        int res[] = new int[3];
        res[0] = toForward.src;
        res[1] = toForward.dest;
        res[2] = toForward.time;
        return res;
    }

    public int getCount(int destination, int startTime, int endTime) {
        int left_ind = bs_left_ind(startTime, destination);
        int right_ind = bs_right_ind(endTime, destination);
        if (left_ind == -1 || right_ind == -1)
            return 0;
        return right_ind - left_ind + 1;
    }

    private int bs_left_ind(int start_time, int dest) {
        ArrayList<Tuple> domain = new ArrayList<>();
        if (map.containsKey(dest))
            domain = map.get(dest);
        int low = 0, high = domain.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (domain.get(mid).time >= start_time) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }

    private int bs_right_ind(int end_time, int dest) {
        ArrayList<Tuple> domain = new ArrayList<>();
        if (map.containsKey(dest))
            domain = map.get(dest);
        int low = 0, high = domain.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (domain.get(mid).time <= end_time) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return ans;
    }
}

/**
    Your Router object will be instantiated and called as such:
    Router obj = new Router(memoryLimit);
    boolean param_1 = obj.addPacket(source,destination,timestamp);
    int[] param_2 = obj.forwardPacket();
    int param_3 = obj.getCount(destination,startTime,endTime);
*/