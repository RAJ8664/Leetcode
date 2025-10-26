# 2620. Find Consecutive Integers From A Data Stream

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/find-consecutive-integers-from-a-data-stream/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2620-find-consecutive-integers-from-a-data-stream){ .md-button }

---

<h2><a href="https://leetcode.com/problems/find-consecutive-integers-from-a-data-stream">2620. Find Consecutive Integers from a Data Stream</a></h2><h3>Medium</h3><hr><p>For a stream of integers, implement a data structure that checks if the last <code>k</code> integers parsed in the stream are <strong>equal</strong> to <code>value</code>.</p>

<p>Implement the <strong>DataStream</strong> class:</p>

<ul>
	<li><code>DataStream(int value, int k)</code> Initializes the object with an empty integer stream and the two integers <code>value</code> and <code>k</code>.</li>
	<li><code>boolean consec(int num)</code> Adds <code>num</code> to the stream of integers. Returns <code>true</code> if the last <code>k</code> integers are equal to <code>value</code>, and <code>false</code> otherwise. If there are less than <code>k</code> integers, the condition does not hold true, so returns <code>false</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;DataStream&quot;, &quot;consec&quot;, &quot;consec&quot;, &quot;consec&quot;, &quot;consec&quot;]
[[4, 3], [4], [4], [4], [3]]
<strong>Output</strong>
[null, false, false, true, false]

<strong>Explanation</strong>
DataStream dataStream = new DataStream(4, 3); //value = 4, k = 3 
dataStream.consec(4); // Only 1 integer is parsed, so returns False. 
dataStream.consec(4); // Only 2 integers are parsed.
                      // Since 2 is less than k, returns False. 
dataStream.consec(4); // The 3 integers parsed are all equal to value, so returns True. 
dataStream.consec(3); // The last k integers parsed in the stream are [4,4,3].
                      // Since 3 is not equal to value, it returns False.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= value, num &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls will be made to <code>consec</code>.</li>
</ul>


---

## Solution

```java
class DataStream {
    static class Pair {
        int node, freq;
        public Pair(int node, int freq) {
            this.node = node;
            this.freq = freq;
        }
        @Override
        public String toString() {
            return "(" + node + " " + freq + ")";
        }
    }
    
    private Stack<Pair> st;
    private int need, time;
    public DataStream(int value, int k) {
        st = new Stack<>();
        this.need = value;
        this.time = k;
    }
    
    public boolean consec(int num) {
        if (st.size() == 0) {
            st.add(new Pair(num, 1));
        } else {
            if (st.peek().node == num) {
                st.add(new Pair(st.peek().node, st.peek().freq + 1));
            } else {
                st.add(new Pair(num, 1));
            }
        }
        if (st.peek().node == need && st.peek().freq >= time)
            return true;
        return false;
    }
}

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream obj = new DataStream(value, k);
 * boolean param_1 = obj.consec(num);
 */
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

