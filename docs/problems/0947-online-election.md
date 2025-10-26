# 947. Online Election

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/online-election/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0947-online-election){ .md-button }

---

<h2><a href="https://leetcode.com/problems/online-election">947. Online Election</a></h2><h3>Medium</h3><hr><p>You are given two integer arrays <code>persons</code> and <code>times</code>. In an election, the <code>i<sup>th</sup></code> vote was cast for <code>persons[i]</code> at time <code>times[i]</code>.</p>

<p>For each query at a time <code>t</code>, find the person that was leading the election at time <code>t</code>. Votes cast at time <code>t</code> will count towards our query. In the case of a tie, the most recent vote (among tied candidates) wins.</p>

<p>Implement the <code>TopVotedCandidate</code> class:</p>

<ul>
	<li><code>TopVotedCandidate(int[] persons, int[] times)</code> Initializes the object with the <code>persons</code> and <code>times</code> arrays.</li>
	<li><code>int q(int t)</code> Returns the number of the person that was leading the election at time <code>t</code> according to the mentioned rules.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;TopVotedCandidate&quot;, &quot;q&quot;, &quot;q&quot;, &quot;q&quot;, &quot;q&quot;, &quot;q&quot;, &quot;q&quot;]
[[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
<strong>Output</strong>
[null, 0, 1, 1, 0, 0, 1]

<strong>Explanation</strong>
TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
topVotedCandidate.q(3); // return 0, At time 3, the votes are [0], and 0 is leading.
topVotedCandidate.q(12); // return 1, At time 12, the votes are [0,1,1], and 1 is leading.
topVotedCandidate.q(25); // return 1, At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
topVotedCandidate.q(15); // return 0
topVotedCandidate.q(24); // return 0
topVotedCandidate.q(8); // return 1

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= persons.length &lt;= 5000</code></li>
	<li><code>times.length == persons.length</code></li>
	<li><code>0 &lt;= persons[i] &lt; persons.length</code></li>
	<li><code>0 &lt;= times[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>times</code> is sorted in a strictly increasing order.</li>
	<li><code>times[0] &lt;= t &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>q</code>.</li>
</ul>


---

## Solution

```java
class TopVotedCandidate {
    private TreeSet<Integer> time;
    private HashMap<Integer, Integer> winnerId;
    private HashMap<Integer, Integer> votes;
    private TreeSet<Tuple> set;
    private HashMap<Tuple, Integer> timeMap;
    private int timer;

    static class Tuple {
        int pId, vFreq, vTime;
        public Tuple(int pId, int vFreq, int vTime) {
            this.pId = pId;
            this.vFreq = vFreq;
            this.vTime = vTime;
        }
        @Override
        public String toString() {
            return "(" + pId + " " + vFreq + " " + vTime + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Tuple current = (Tuple)(obj);
            return current.pId == pId && current.vFreq == vFreq && current.vTime == vTime;
        }
        @Override
        public int hashCode() {
            return Objects.hash(pId, vFreq);
        }
    }

    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            int op1 = Integer.compare(second.vFreq, first.vFreq);
            if (op1 != 0)
                return op1;
            return Integer.compare(second.vTime, first.vTime); 
        }
    }

    public TopVotedCandidate(int[] persons, int[] times) {
        time = new TreeSet<>();
        timeMap = new HashMap<>();
        winnerId = new HashMap<>();
        set = new TreeSet<>(new customSort());
        votes = new HashMap<>();
        timer = 0;

        for (int i = 0; i < persons.length; i++) {
            int currTime = times[i];
            int currVote = persons[i]; 
            if (votes.containsKey(currVote)) {
                int prevVotes = votes.get(currVote);
                set.remove(new Tuple(currVote, votes.get(currVote), timeMap.get(new Tuple(currVote, votes.get(currVote), -1))));
                set.add(new Tuple(currVote, votes.get(currVote) + 1, timer));
                votes.put(currVote, votes.get(currVote) + 1);
                timeMap.put(new Tuple(currVote, votes.get(currVote), -1), timer);
                timer++;
            } else {
                set.add(new Tuple(currVote, 1, timer));
                votes.put(currVote, 1);
                timeMap.put(new Tuple(currVote, 1, -1), timer);
                timer++;
            }
            winnerId.put(currTime, set.first().pId);
            time.add(currTime);
        } 
    }
    
    public int q(int t) {
        Integer prev = time.floor(t);
        return winnerId.get(prev); 
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

