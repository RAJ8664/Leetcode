class LFUCache {
    private TreeSet<Node> set;
    private HashMap<Integer, Integer> map;
    private int currTime;
    private HashMap<Integer, Integer> freq;
    private HashMap<Integer, Integer> time;
    private int totalCap, currCap;

    static class Node {
        int key, value, count, currTime;
        public Node(int key, int value, int count, int currTime) {
            this.key = key;
            this.value = value;
            this.count = count;
            this.currTime = currTime;
        }
        @Override
        public String toString() {
            return "(" + key + " " + value + " " + count + " " + currTime + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Node current = (Node)(obj);
            return current.key == key && current.value == value && current.count == count && current.currTime == currTime;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value, count, currTime);
        }
    }

    static class customSort implements Comparator<Node> {
        @Override
        public int compare(Node first, Node second) {
            int op1 = Integer.compare(second.count, first.count);
            if (op1 != 0)
                return op1;
            return Integer.compare(second.currTime, first.currTime);
        }
    }

    public LFUCache(int capacity) {
        set = new TreeSet<>(new customSort()); 
        map = new HashMap<>();
        currTime = 0;
        freq = new HashMap<>();
        time = new HashMap<>();
        currCap = 0;
        totalCap = capacity;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Node prev = new Node(key, map.get(key), freq.get(key), time.get(key));
            int res = map.get(key);
            set.remove(prev);
            time.put(key, currTime);
            set.add(new Node(key, map.get(key), freq.get(key) + 1, currTime));
            freq.put(key, freq.get(key) + 1);
            currTime++;
            return res;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node prev = new Node(key, map.get(key), freq.get(key), time.get(key));
            set.remove(prev);
            map.put(key, value);
            freq.put(key, freq.get(key) + 1);
            time.put(key, currTime);
            set.add(new Node(key, value, freq.get(key), currTime));
            currTime++;
        } else {
            if (currCap < totalCap) {
                map.put(key, value);
                freq.put(key, 1);
                time.put(key, currTime);
                set.add(new Node(key, value, 1, currTime));
                currTime++;
                currCap++; 
            } else {
                Node last = set.pollLast();
                map.remove(last.key);
                map.put(key, value);
                freq.put(key, 1);
                time.put(key, currTime);
                set.add(new Node(key, value, 1, currTime));
                currTime++;
                currCap++; 
            }
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */