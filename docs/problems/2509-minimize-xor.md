# 2509. Minimize Xor


---

<h2><a href="https://leetcode.com/problems/minimize-xor">2509. Minimize XOR</a></h2><h3>Medium</h3><hr><p>Given two positive integers <code>num1</code> and <code>num2</code>, find the positive integer <code>x</code> such that:</p>

<ul>
	<li><code>x</code> has the same number of set bits as <code>num2</code>, and</li>
	<li>The value <code>x XOR num1</code> is <strong>minimal</strong>.</li>
</ul>

<p>Note that <code>XOR</code> is the bitwise XOR operation.</p>

<p>Return <em>the integer </em><code>x</code>. The test cases are generated such that <code>x</code> is <strong>uniquely determined</strong>.</p>

<p>The number of <strong>set bits</strong> of an integer is the number of <code>1</code>&#39;s in its binary representation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num1 = 3, num2 = 5
<strong>Output:</strong> 3
<strong>Explanation:</strong>
The binary representations of num1 and num2 are 0011 and 0101, respectively.
The integer <strong>3</strong> has the same number of set bits as num2, and the value <code>3 XOR 3 = 0</code> is minimal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num1 = 1, num2 = 12
<strong>Output:</strong> 3
<strong>Explanation:</strong>
The binary representations of num1 and num2 are 0001 and 1100, respectively.
The integer <strong>3</strong> has the same number of set bits as num2, and the value <code>3 XOR 1 = 2</code> is minimal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num1, num2 &lt;= 10<sup>9</sup></code></li>
</ul>


## Solution

```java
class Solution {
    public int minimizeXor(int num1, int num2) {
        int numSetBits = count_setBits(num2);
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            if ((num1 & (1 << i)) != 0 && numSetBits > 0) {
                res |= (1 << i);
                numSetBits--;
            }
        }
        int index = 0;
        while (numSetBits > 0) {
            if ((res & (1 << index)) == 0) {
                res |= (1 << index);
                numSetBits--;
            }
            index++;
        }
        return res;
    }
    private int count_setBits(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

