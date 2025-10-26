# 2434. Design A Number Container System

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/design-a-number-container-system/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2434-design-a-number-container-system){ .md-button }

---

<h2><a href="https://leetcode.com/problems/design-a-number-container-system">2434. Design a Number Container System</a></h2><h3>Medium</h3><hr><p>Design a number container system that can do the following:</p>

<ul>
	<li><strong>Insert </strong>or <strong>Replace</strong> a number at the given index in the system.</li>
	<li><strong>Return </strong>the smallest index for the given number in the system.</li>
</ul>

<p>Implement the <code>NumberContainers</code> class:</p>

<ul>
	<li><code>NumberContainers()</code> Initializes the number container system.</li>
	<li><code>void change(int index, int number)</code> Fills the container at <code>index</code> with the <code>number</code>. If there is already a number at that <code>index</code>, replace it.</li>
	<li><code>int find(int number)</code> Returns the smallest index for the given <code>number</code>, or <code>-1</code> if there is no index that is filled by <code>number</code> in the system.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;NumberContainers&quot;, &quot;find&quot;, &quot;change&quot;, &quot;change&quot;, &quot;change&quot;, &quot;change&quot;, &quot;find&quot;, &quot;change&quot;, &quot;find&quot;]
[[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
<strong>Output</strong>
[null, -1, null, null, null, null, 1, null, 2]

<strong>Explanation</strong>
NumberContainers nc = new NumberContainers();
nc.find(10); // There is no index that is filled with number 10. Therefore, we return -1.
nc.change(2, 10); // Your container at index 2 will be filled with number 10.
nc.change(1, 10); // Your container at index 1 will be filled with number 10.
nc.change(3, 10); // Your container at index 3 will be filled with number 10.
nc.change(5, 10); // Your container at index 5 will be filled with number 10.
nc.find(10); // Number 10 is at the indices 1, 2, 3, and 5. Since the smallest index that is filled with 10 is 1, we return 1.
nc.change(1, 20); // Your container at index 1 will be filled with number 20. Note that index 1 was filled with 10 and then replaced with 20. 
nc.find(10); // Number 10 is at the indices 2, 3, and 5. The smallest index that is filled with 10 is 2. Therefore, we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= index, number &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls will be made <strong>in total</strong> to <code>change</code> and <code>find</code>.</li>
</ul>


---

## Solution

```java
class NumberContainers {
    private HashMap<Integer, TreeSet<Integer>> map;
    private HashMap<Integer, Integer> info;
    public NumberContainers() {
        map = new HashMap<>();
        info = new HashMap<>();
    }
    public void change(int index, int number) {
        if (info.containsKey(index)) {
            int prev_number = info.get(index);
            TreeSet<Integer> prev = map.get(prev_number);
            prev.remove(index);
            if (!map.containsKey(number)) map.put(number, new TreeSet<>());
            map.get(number).add(index);
            info.put(index, number);
        }
        else {
            if (!map.containsKey(number)) map.put(number, new TreeSet<>());
            map.get(number).add(index);
            info.put(index, number);
        }
    }
    public int find(int number) {
        if (map.containsKey(number)) {
            TreeSet<Integer> temp = new TreeSet<>();
            temp = map.get(number);
            if (temp.size() == 0) return -1;
            return temp.first();
        }
        return -1;
    }
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.change(index,number);
 * int param_2 = obj.find(number);
 */
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

