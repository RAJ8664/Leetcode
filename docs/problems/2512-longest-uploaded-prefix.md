# 2512. Longest Uploaded Prefix

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/longest-uploaded-prefix/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2512-longest-uploaded-prefix){ .md-button }

---

<h2><a href="https://leetcode.com/problems/longest-uploaded-prefix">2512. Longest Uploaded Prefix</a></h2><h3>Medium</h3><hr><p>You are given a stream of <code>n</code> videos, each represented by a <strong>distinct</strong> number from <code>1</code> to <code>n</code> that you need to &quot;upload&quot; to a server. You need to implement a data structure that calculates the length of the <strong>longest uploaded prefix</strong> at various points in the upload process.</p>

<p>We consider <code>i</code> to be an uploaded prefix if all videos in the range <code>1</code> to <code>i</code> (<strong>inclusive</strong>) have been uploaded to the server. The longest uploaded prefix is the <strong>maximum </strong>value of <code>i</code> that satisfies this definition.<br />
<br />
Implement the <code>LUPrefix </code>class:</p>

<ul>
	<li><code>LUPrefix(int n)</code> Initializes the object for a stream of <code>n</code> videos.</li>
	<li><code>void upload(int video)</code> Uploads <code>video</code> to the server.</li>
	<li><code>int longest()</code> Returns the length of the <strong>longest uploaded prefix</strong> defined above.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;LUPrefix&quot;, &quot;upload&quot;, &quot;longest&quot;, &quot;upload&quot;, &quot;longest&quot;, &quot;upload&quot;, &quot;longest&quot;]
[[4], [3], [], [1], [], [2], []]
<strong>Output</strong>
[null, null, 0, null, 1, null, 3]

<strong>Explanation</strong>
LUPrefix server = new LUPrefix(4);   // Initialize a stream of 4 videos.
server.upload(3);                    // Upload video 3.
server.longest();                    // Since video 1 has not been uploaded yet, there is no prefix.
                                     // So, we return 0.
server.upload(1);                    // Upload video 1.
server.longest();                    // The prefix [1] is the longest uploaded prefix, so we return 1.
server.upload(2);                    // Upload video 2.
server.longest();                    // The prefix [1,2,3] is the longest uploaded prefix, so we return 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= video &lt;= n</code></li>
	<li>All values of <code>video</code> are <strong>distinct</strong>.</li>
	<li>At most <code>2 * 10<sup>5</sup></code> calls <strong>in total</strong> will be made to <code>upload</code> and <code>longest</code>.</li>
	<li>At least one call will be made to <code>longest</code>.</li>
</ul>


---

## Solution

```java
class LUPrefix {
    private static long seg[];
    private HashSet<Integer> set;
    public LUPrefix(int n) {
        seg = new long[4 * ((int)(1e5 + 1)) + 1];
        set = new HashSet<>();
    }
    
    public void upload(int video) {
        if (set.contains(video)) return;
        set.add(video);
        update(0, 0, (int)(1e5), video, video);
    }
    
    public int longest() {
        int low = 0;
        int high = (int)(1e5);
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ok(mid)) {
                ans = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return ans;
    }

    private boolean ok(int mid) {
        long current_sum = query(0 , 0 , (int)(1e5) , 0 , mid);
        long req_sum = mid * 1L * (mid + 1) / 2;
        if (req_sum == current_sum) return true;
        return false;
    }

    //Segment Tree Implementation;
    private static void update(int ind, int low, int high, int index, int val){
        if(low == high){
            seg[ind] = val;
            return;
        }
        int mid = low + (high - low) / 2;
        if(index <= mid) update(2 * ind + 1, low, mid, index, val);
        else update(2 * ind + 2, mid + 1, high ,index, val);
        seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];

    }
    private static long query(int ind, int low, int high , int l, int r){
        if(low >= l && high <= r) return seg[ind];
        if(low > r || high < l) return 0;
        int mid = low + (high - low) / 2;
        long left = query(2 * ind + 1, low , mid, l, r);
        long right = query(2 * ind + 2, mid + 1, high , l, r);
        return left + right;
    }
    
}

/**
 * Your LUPrefix object will be instantiated and called as such:
 * LUPrefix obj = new LUPrefix(n);
 * obj.upload(video);
 * int param_2 = obj.longest();
 */
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

