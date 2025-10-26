# 2610. Closest Prime Numbers In Range

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/closest-prime-numbers-in-range/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/2610-closest-prime-numbers-in-range){ .md-button }

---

<h2><a href="https://leetcode.com/problems/closest-prime-numbers-in-range">2610. Closest Prime Numbers in Range</a></h2><h3>Medium</h3><hr><p>Given two positive integers <code>left</code> and <code>right</code>, find the two integers <code>num1</code> and <code>num2</code> such that:</p>

<ul>
	<li><code>left &lt;= num1 &lt; num2 &lt;= right </code>.</li>
	<li>Both <code>num1</code> and <code>num2</code> are <span data-keyword="prime-number">prime numbers</span>.</li>
	<li><code>num2 - num1</code> is the <strong>minimum</strong> amongst all other pairs satisfying the above conditions.</li>
</ul>

<p>Return the positive integer array <code>ans = [num1, num2]</code>. If there are multiple pairs satisfying these conditions, return the one with the <strong>smallest</strong> <code>num1</code> value. If no such numbers exist, return <code>[-1, -1]</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> left = 10, right = 19
<strong>Output:</strong> [11,13]
<strong>Explanation:</strong> The prime numbers between 10 and 19 are 11, 13, 17, and 19.
The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
Since 11 is smaller than 17, we return the first pair.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> left = 4, right = 6
<strong>Output:</strong> [-1,-1]
<strong>Explanation:</strong> There exists only one prime number in the given range, so the conditions cannot be satisfied.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;= right &lt;= 10<sup>6</sup></code></li>
</ul>

<p>&nbsp;</p>
<style type="text/css">.spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px 0px; font-size:150%; font-weight: bold; color:#000000; background-color:cyan; outline:0; 
}
.spoiler {overflow:hidden;}
.spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s ease;-o-transition: all 0s ease;transition: margin 0s ease;}
.spoilerbutton[value="Show Message"] + .spoiler > div {margin-top:-500%;}
.spoilerbutton[value="Hide Message"] + .spoiler {padding:5px;}
</style>


---

## Solution

```java
class Solution {
    public int[] closestPrimes(int left, int right) {
        int[] arr=new int[2];
        arr[0]=-1;
        arr[1]=-1;
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=left;i<=right;i++){
            if (isprime(i)) ans.add(i);
        }
        int len=ans.size();
        int min=Integer.MAX_VALUE;
        for(int i=0;i<len-1;i++){
            int x=Math.abs(ans.get(i)-ans.get(i+1));
            min=Math.min(min,x);
        }
        for(int i=0;i<len-1;i++){
            if(Math.abs(ans.get(i)-ans.get(i+1))==min){
                arr[0]=ans.get(i);
                arr[1]=ans.get(i+1);
                break;
            }
        }
        return arr;
    }
    public static boolean isprime(int n){
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

