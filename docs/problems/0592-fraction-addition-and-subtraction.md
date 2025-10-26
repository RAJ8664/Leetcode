# 592. Fraction Addition And Subtraction

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/fraction-addition-and-subtraction/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0592-fraction-addition-and-subtraction){ .md-button }

---

<h2><a href="https://leetcode.com/problems/fraction-addition-and-subtraction">592. Fraction Addition and Subtraction</a></h2><h3>Medium</h3><hr><p>Given a string <code>expression</code> representing an expression of fraction addition and subtraction, return the calculation result in string format.</p>

<p>The final result should be an <a href="https://en.wikipedia.org/wiki/Irreducible_fraction" target="_blank">irreducible fraction</a>. If your final result is an integer, change it to the format of a fraction that has a denominator <code>1</code>. So in this case, <code>2</code> should be converted to <code>2/1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;-1/2+1/2&quot;
<strong>Output:</strong> &quot;0/1&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;-1/2+1/2+1/3&quot;
<strong>Output:</strong> &quot;1/3&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;1/3-1/2&quot;
<strong>Output:</strong> &quot;-1/6&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The input string only contains <code>&#39;0&#39;</code> to <code>&#39;9&#39;</code>, <code>&#39;/&#39;</code>, <code>&#39;+&#39;</code> and <code>&#39;-&#39;</code>. So does the output.</li>
	<li>Each fraction (input and output) has the format <code>&plusmn;numerator/denominator</code>. If the first input fraction or the output is positive, then <code>&#39;+&#39;</code> will be omitted.</li>
	<li>The input only contains valid <strong>irreducible fractions</strong>, where the <strong>numerator</strong> and <strong>denominator</strong> of each fraction will always be in the range <code>[1, 10]</code>. If the denominator is <code>1</code>, it means this fraction is actually an integer in a fraction format defined above.</li>
	<li>The number of given fractions will be in the range <code>[1, 10]</code>.</li>
	<li>The numerator and denominator of the <strong>final result</strong> are guaranteed to be valid and in the range of <strong>32-bit</strong> int.</li>
</ul>


---

## Solution

```java
import java.util.ArrayList;

class Solution {
    public String fractionAddition(String expression) {
        String s = "";
        if (expression.charAt(0) == '-')
            s = expression;
        else
            s = "+" + expression;

        int n = s.length();
        ArrayList<Integer> numerator = new ArrayList<>();
        ArrayList<Integer> denominator = new ArrayList<>();

        int i = 0, j = 0;
        while (i < n) {
            i = j;
            if (i >= n)
                break;
            boolean positive = true;
            if (s.charAt(i) == '-')
                positive = false;

            j++;
            int dig = 0;
            while (j < n && s.charAt(j) != '/')
                dig = dig * 10 + s.charAt(j++) - '0';
            if (positive)
                numerator.add(dig);
            else
                numerator.add(-dig);
            dig = 0;
            j++;
            while (j < n && (s.charAt(j) != '+' && s.charAt(j) != '-'))
                dig = dig * 10 + s.charAt(j++) - '0';
            denominator.add(dig);
        }

        int lcm = lcm(denominator);
        long sum = 0;
        for (int x = 0; x < numerator.size(); x++)
            sum += numerator.get(x) * lcm / denominator.get(x);

        String ans = "";
        ans += sum + "/" + lcm;
        ans = reduce(ans);
        return ans;
    }

    String reduce(String fraction) {
        String[] parts = fraction.split("/");
        int num = Integer.parseInt(parts[0]);
        int den = Integer.parseInt(parts[1]);
        if (num == 0)
            return "0/1";
        int gcd = gcd(Math.abs(num), Math.abs(den));
        num /= gcd;
        den /= gcd;
        if (den < 0) {
            num = -num;
            den = -den;
        }
        return num + "/" + den;
    }

    private int lcm(ArrayList<Integer> numbers) {
        return numbers.stream().reduce(
                   1, (x, y) -> (x * y) / gcd(x, y));
    }

    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

