# 2778. Frequency Tracker

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/frequency-tracker/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2778-frequency-tracker){ .md-button }

---

<h2><a href="https://leetcode.com/problems/frequency-tracker">2778. Frequency Tracker</a></h2><h3>Medium</h3><hr><p>Design a data structure that keeps track of the values in it and answers some queries regarding their frequencies.</p>

<p>Implement the <code>FrequencyTracker</code> class.</p>

<ul>
	<li><code>FrequencyTracker()</code>: Initializes the <code>FrequencyTracker</code> object with an empty array initially.</li>
	<li><code>void add(int number)</code>: Adds <code>number</code> to the data structure.</li>
	<li><code>void deleteOne(int number)</code>: Deletes <strong>one</strong> occurrence of <code>number</code> from the data structure. The data structure <strong>may not contain</strong> <code>number</code>, and in this case nothing is deleted.</li>
	<li><code>bool hasFrequency(int frequency)</code>: Returns <code>true</code> if there is a number in the data structure that occurs <code>frequency</code> number of times, otherwise, it returns <code>false</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;FrequencyTracker&quot;, &quot;add&quot;, &quot;add&quot;, &quot;hasFrequency&quot;]
[[], [3], [3], [2]]
<strong>Output</strong>
[null, null, null, true]

<strong>Explanation</strong>
FrequencyTracker frequencyTracker = new FrequencyTracker();
frequencyTracker.add(3); // The data structure now contains [3]
frequencyTracker.add(3); // The data structure now contains [3, 3]
frequencyTracker.hasFrequency(2); // Returns true, because 3 occurs twice

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input</strong>
[&quot;FrequencyTracker&quot;, &quot;add&quot;, &quot;deleteOne&quot;, &quot;hasFrequency&quot;]
[[], [1], [1], [1]]
<strong>Output</strong>
[null, null, null, false]

<strong>Explanation</strong>
FrequencyTracker frequencyTracker = new FrequencyTracker();
frequencyTracker.add(1); // The data structure now contains [1]
frequencyTracker.deleteOne(1); // The data structure becomes empty []
frequencyTracker.hasFrequency(1); // Returns false, because the data structure is empty

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input</strong>
[&quot;FrequencyTracker&quot;, &quot;hasFrequency&quot;, &quot;add&quot;, &quot;hasFrequency&quot;]
[[], [2], [3], [1]]
<strong>Output</strong>
[null, false, null, true]

<strong>Explanation</strong>
FrequencyTracker frequencyTracker = new FrequencyTracker();
frequencyTracker.hasFrequency(2); // Returns false, because the data structure is empty
frequencyTracker.add(3); // The data structure now contains [3]
frequencyTracker.hasFrequency(1); // Returns true, because 3 occurs once

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= number &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= frequency &lt;= 10<sup>5</sup></code></li>
	<li>At most, <code>2 *&nbsp;10<sup>5</sup></code>&nbsp;calls will be made to <code>add</code>, <code>deleteOne</code>, and <code>hasFrequency</code>&nbsp;in <strong>total</strong>.</li>
</ul>


---

## Solution

```java
class FrequencyTracker {
    private int freq[];
    private HashMap<Integer, Integer> map;
    public FrequencyTracker() {
        freq = new int[(int)(1e5 + 1)]; 
        map = new HashMap<>();
    }
    
    public void add(int number) {
        if (map.containsKey(number)) {
            int pastFreq = map.getOrDefault(number, 0);
            freq[pastFreq]--;
            map.put(number, map.getOrDefault(number, 0) + 1);
            freq[map.get(number)]++;
        }
        else {
            map.put(number, 1);
            freq[1]++;
        }
    }
    
    public void deleteOne(int number) {
        if (map.getOrDefault(number, 0) > 0) {
            freq[map.getOrDefault(number, 0)]--;
            freq[map.getOrDefault(number, 0) - 1]++;
            map.put(number, map.getOrDefault(number, 0) - 1);
            if (map.getOrDefault(number, 0) == 0)
                map.remove(number);
        } 
    }
    
    public boolean hasFrequency(int frequency) {
        if (freq[frequency] > 0) 
            return true;
        return false; 
    }
}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * FrequencyTracker obj = new FrequencyTracker();
 * obj.add(number);
 * obj.deleteOne(number);
 * boolean param_3 = obj.hasFrequency(frequency);
 */
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

