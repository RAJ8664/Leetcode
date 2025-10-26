# 3827. Implement Router

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/implement-router/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3827-implement-router){ .md-button }

---

<h2><a href="https://leetcode.com/problems/implement-router">3827. Implement Router</a></h2><h3>Medium</h3><hr><p>Design a data structure that can efficiently manage data packets in a network router. Each data packet consists of the following attributes:</p>

<ul>
	<li><code>source</code>: A unique identifier for the machine that generated the packet.</li>
	<li><code>destination</code>: A unique identifier for the target machine.</li>
	<li><code>timestamp</code>: The time at which the packet arrived at the router.</li>
</ul>

<p>Implement the <code>Router</code> class:</p>

<p><code>Router(int memoryLimit)</code>: Initializes the Router object with a fixed memory limit.</p>

<ul>
	<li><code>memoryLimit</code> is the <strong>maximum</strong> number of packets the router can store at any given time.</li>
	<li>If adding a new packet would exceed this limit, the <strong>oldest</strong> packet must be removed to free up space.</li>
</ul>

<p><code>bool addPacket(int source, int destination, int timestamp)</code>: Adds a packet with the given attributes to the router.</p>

<ul>
	<li>A packet is considered a duplicate if another packet with the same <code>source</code>, <code>destination</code>, and <code>timestamp</code> already exists in the router.</li>
	<li>Return <code>true</code> if the packet is successfully added (i.e., it is not a duplicate); otherwise return <code>false</code>.</li>
</ul>

<p><code>int[] forwardPacket()</code>: Forwards the next packet in FIFO (First In First Out) order.</p>

<ul>
	<li>Remove the packet from storage.</li>
	<li>Return the packet as an array <code>[source, destination, timestamp]</code>.</li>
	<li>If there are no packets to forward, return an empty array.</li>
</ul>

<p><code>int getCount(int destination, int startTime, int endTime)</code>:</p>

<ul>
	<li>Returns the number of packets currently stored in the router (i.e., not yet forwarded) that have the specified destination and have timestamps in the inclusive range <code>[startTime, endTime]</code>.</li>
</ul>

<p><strong>Note</strong> that queries for <code>addPacket</code> will be made in increasing order of <code>timestamp</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;Router&quot;, &quot;addPacket&quot;, &quot;addPacket&quot;, &quot;addPacket&quot;, &quot;addPacket&quot;, &quot;addPacket&quot;, &quot;forwardPacket&quot;, &quot;addPacket&quot;, &quot;getCount&quot;]<br />
[[3], [1, 4, 90], [2, 5, 90], [1, 4, 90], [3, 5, 95], [4, 5, 105], [], [5, 2, 110], [5, 100, 110]]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, true, true, false, true, true, [2, 5, 90], true, 1] </span></p>

<p><strong>Explanation</strong></p>
Router router = new Router(3); // Initialize Router with memoryLimit of 3.<br />
router.addPacket(1, 4, 90); // Packet is added. Return True.<br />
router.addPacket(2, 5, 90); // Packet is added. Return True.<br />
router.addPacket(1, 4, 90); // This is a duplicate packet. Return False.<br />
router.addPacket(3, 5, 95); // Packet is added. Return True<br />
router.addPacket(4, 5, 105); // Packet is added, <code>[1, 4, 90]</code> is removed as number of packets exceeds memoryLimit. Return True.<br />
router.forwardPacket(); // Return <code>[2, 5, 90]</code> and remove it from router.<br />
router.addPacket(5, 2, 110); // Packet is added. Return True.<br />
router.getCount(5, 100, 110); // The only packet with destination 5 and timestamp in the inclusive range <code>[100, 110]</code> is <code>[4, 5, 105]</code>. Return 1.</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;Router&quot;, &quot;addPacket&quot;, &quot;forwardPacket&quot;, &quot;forwardPacket&quot;]<br />
[[2], [7, 4, 90], [], []]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, true, [7, 4, 90], []] </span></p>

<p><strong>Explanation</strong></p>
Router router = new Router(2); // Initialize <code>Router</code> with <code>memoryLimit</code> of 2.<br />
router.addPacket(7, 4, 90); // Return True.<br />
router.forwardPacket(); // Return <code>[7, 4, 90]</code>.<br />
router.forwardPacket(); // There are no packets left, return <code>[]</code>.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= memoryLimit &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= source, destination &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= timestamp &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= startTime &lt;= endTime &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls will be made to <code>addPacket</code>, <code>forwardPacket</code>, and <code>getCount</code> methods altogether.</li>
	<li>queries for <code>addPacket</code> will be made in increasing order of <code>timestamp</code>.</li>
</ul>


---

## Solution

```java
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

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

