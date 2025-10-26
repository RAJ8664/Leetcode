# 2179. Most Beautiful Item For Each Query

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/most-beautiful-item-for-each-query/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2179-most-beautiful-item-for-each-query){ .md-button }

---

<h2><a href="https://leetcode.com/problems/most-beautiful-item-for-each-query">2179. Most Beautiful Item for Each Query</a></h2><h3>Medium</h3><hr><p>You are given a 2D integer array <code>items</code> where <code>items[i] = [price<sub>i</sub>, beauty<sub>i</sub>]</code> denotes the <strong>price</strong> and <strong>beauty</strong> of an item respectively.</p>

<p>You are also given a <strong>0-indexed</strong> integer array <code>queries</code>. For each <code>queries[j]</code>, you want to determine the <strong>maximum beauty</strong> of an item whose <strong>price</strong> is <strong>less than or equal</strong> to <code>queries[j]</code>. If no such item exists, then the answer to this query is <code>0</code>.</p>

<p>Return <em>an array </em><code>answer</code><em> of the same length as </em><code>queries</code><em> where </em><code>answer[j]</code><em> is the answer to the </em><code>j<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
<strong>Output:</strong> [2,4,5,5,6,6]
<strong>Explanation:</strong>
- For queries[0]=1, [1,2] is the only item which has price &lt;= 1. Hence, the answer for this query is 2.
- For queries[1]=2, the items which can be considered are [1,2] and [2,4]. 
  The maximum beauty among them is 4.
- For queries[2]=3 and queries[3]=4, the items which can be considered are [1,2], [3,2], [2,4], and [3,5].
  The maximum beauty among them is 5.
- For queries[4]=5 and queries[5]=6, all items can be considered.
  Hence, the answer for them is the maximum beauty of all items, i.e., 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
<strong>Output:</strong> [4]
<strong>Explanation:</strong> 
The price of every item is equal to 1, so we choose the item with the maximum beauty 4. 
Note that multiple items can have the same price and/or beauty.  
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> items = [[10,1000]], queries = [5]
<strong>Output:</strong> [0]
<strong>Explanation:</strong>
No item has a price less than or equal to 5, so no item can be chosen.
Hence, the answer to the query is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= items.length, queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>items[i].length == 2</code></li>
	<li><code>1 &lt;= price<sub>i</sub>, beauty<sub>i</sub>, queries[j] &lt;= 10<sup>9</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Pair {
        int price;
        int beauty;
        public Pair(int price, int beauty) {
            this.price = price;
            this.beauty = beauty;
        }
        @Override
        public String toString() {
            return "(" + price + " " + beauty + ")";
        }
    }
    static class custom_sort implements Comparator<Pair> {
        @Override
        public int compare(Pair first, Pair second) {
            int op1 = Integer.compare(first.price, second.price);
            return op1;
        }
    }
    public int[] maximumBeauty(int[][] items, int[] queries) {
        int n = items.length;
        ArrayList<Pair> res = new ArrayList<>();
        for (int currItem[] : items) {
            res.add(new Pair(currItem[0] , currItem[1]));
        }
        Collections.sort(res, new custom_sort());
        int maxBeautyPref[] = new int[n + 1];
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < res.size(); i++) {
            maxi = Math.max(maxi, res.get(i).beauty);
            maxBeautyPref[i] = maxi;
        }
        int answer[] = new int[queries.length];
        int k = 0;
        for (int current_query : queries) {
            answer[k++] = binary_search(res, current_query, maxBeautyPref);
        }
        return answer;
    }

    static int binary_search(ArrayList<Pair> res, int current_price, int maxBeautyPref[]) {
        int n = res.size();
        int maxi = 0;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (res.get(mid).price > current_price) high = mid - 1;
            else if (res.get(mid).price <= current_price) {
                maxi = Math.max(maxi, maxBeautyPref[mid]);
                low = mid + 1;
            }
        }
        return maxi;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

