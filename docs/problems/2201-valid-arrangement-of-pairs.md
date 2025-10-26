# 2201. Valid Arrangement Of Pairs

!!! danger "Difficulty: Hard"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/valid-arrangement-of-pairs/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2201-valid-arrangement-of-pairs){ .md-button }

---

<h2><a href="https://leetcode.com/problems/valid-arrangement-of-pairs">2201. Valid Arrangement of Pairs</a></h2><h3>Hard</h3><hr><p>You are given a <strong>0-indexed</strong> 2D integer array <code>pairs</code> where <code>pairs[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>. An arrangement of <code>pairs</code> is <strong>valid</strong> if for every index <code>i</code> where <code>1 &lt;= i &lt; pairs.length</code>, we have <code>end<sub>i-1</sub> == start<sub>i</sub></code>.</p>

<p>Return <em><strong>any</strong> valid arrangement of </em><code>pairs</code>.</p>

<p><strong>Note:</strong> The inputs will be generated such that there exists a valid arrangement of <code>pairs</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> pairs = [[5,1],[4,5],[11,9],[9,4]]
<strong>Output:</strong> [[11,9],[9,4],[4,5],[5,1]]
<strong>Explanation:
</strong>This is a valid arrangement since end<sub>i-1</sub> always equals start<sub>i</sub>.
end<sub>0</sub> = 9 == 9 = start<sub>1</sub> 
end<sub>1</sub> = 4 == 4 = start<sub>2</sub>
end<sub>2</sub> = 5 == 5 = start<sub>3</sub>
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> pairs = [[1,3],[3,2],[2,1]]
<strong>Output:</strong> [[1,3],[3,2],[2,1]]
<strong>Explanation:</strong>
This is a valid arrangement since end<sub>i-1</sub> always equals start<sub>i</sub>.
end<sub>0</sub> = 3 == 3 = start<sub>1</sub>
end<sub>1</sub> = 2 == 2 = start<sub>2</sub>
The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> pairs = [[1,2],[1,3],[2,1]]
<strong>Output:</strong> [[1,2],[2,1],[1,3]]
<strong>Explanation:</strong>
This is a valid arrangement since end<sub>i-1</sub> always equals start<sub>i</sub>.
end<sub>0</sub> = 2 == 2 = start<sub>1</sub>
end<sub>1</sub> = 1 == 1 = start<sub>2</sub>
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= pairs.length &lt;= 10<sup>5</sup></code></li>
	<li><code>pairs[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub>, end<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>start<sub>i</sub> != end<sub>i</sub></code></li>
	<li>No two pairs are exactly the same.</li>
	<li>There <strong>exists</strong> a valid arrangement of <code>pairs</code>.</li>
</ul>


---

## Solution

```java
class Solution {
    public int[][] validArrangement(int[][] pairs) {
        Map<Integer, List<Integer>> graph = new HashMap<>(); 
        Map<Integer, Integer> node = new HashMap<>();
        for(int[] p : pairs){
            if(!graph.containsKey(p[0])) graph.put(p[0], new ArrayList<>());
            graph.get(p[0]).add(p[1]);  
            node.put(p[0], node.getOrDefault(p[0], 0) - 1);  
            node.put(p[1], node.getOrDefault(p[1], 0) + 1);  
        }
        
        int startNode = pairs[0][0];  
        for(Map.Entry<Integer, Integer> entry : node.entrySet()){
            if(entry.getValue() == -1){
                startNode = entry.getKey();  
                break; 
            }
        }
        
        List<Integer> circuit = new ArrayList<>();
        dfs(graph, startNode, circuit);
        Collections.reverse(circuit); 
        int[][] result = new int[pairs.length][2]; 
        for(int i = 1; i < circuit.size(); i++){
            result[i - 1][0] = circuit.get(i - 1); 
            result[i - 1][1] = circuit.get(i); 
        }
        return result;
    }

    public void dfs(Map<Integer, List<Integer>> graph, int u, List<Integer> res){
        while(graph.containsKey(u) && !graph.get(u).isEmpty()){
            int current = graph.get(u).remove(0);  
            dfs(graph, current, res);  
        }
        res.add(u);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

