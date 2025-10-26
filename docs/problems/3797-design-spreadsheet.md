# 3797. Design Spreadsheet

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/design-spreadsheet/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/3797-design-spreadsheet){ .md-button }

---

<h2><a href="https://leetcode.com/problems/design-spreadsheet">3797. Design Spreadsheet</a></h2><h3>Medium</h3><hr><p>A spreadsheet is a grid with 26 columns (labeled from <code>&#39;A&#39;</code> to <code>&#39;Z&#39;</code>) and a given number of <code>rows</code>. Each cell in the spreadsheet can hold an integer value between 0 and 10<sup>5</sup>.</p>

<p>Implement the <code>Spreadsheet</code> class:</p>

<ul>
	<li><code>Spreadsheet(int rows)</code> Initializes a spreadsheet with 26 columns (labeled <code>&#39;A&#39;</code> to <code>&#39;Z&#39;</code>) and the specified number of rows. All cells are initially set to 0.</li>
	<li><code>void setCell(String cell, int value)</code> Sets the value of the specified <code>cell</code>. The cell reference is provided in the format <code>&quot;AX&quot;</code> (e.g., <code>&quot;A1&quot;</code>, <code>&quot;B10&quot;</code>), where the letter represents the column (from <code>&#39;A&#39;</code> to <code>&#39;Z&#39;</code>) and the number represents a <strong>1-indexed</strong> row.</li>
	<li><code>void resetCell(String cell)</code> Resets the specified cell to 0.</li>
	<li><code>int getValue(String formula)</code> Evaluates a formula of the form <code>&quot;=X+Y&quot;</code>, where <code>X</code> and <code>Y</code> are <strong>either</strong> cell references or non-negative integers, and returns the computed sum.</li>
</ul>

<p><strong>Note:</strong> If <code>getValue</code> references a cell that has not been explicitly set using <code>setCell</code>, its value is considered 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;Spreadsheet&quot;, &quot;getValue&quot;, &quot;setCell&quot;, &quot;getValue&quot;, &quot;setCell&quot;, &quot;getValue&quot;, &quot;resetCell&quot;, &quot;getValue&quot;]<br />
[[3], [&quot;=5+7&quot;], [&quot;A1&quot;, 10], [&quot;=A1+6&quot;], [&quot;B2&quot;, 15], [&quot;=A1+B2&quot;], [&quot;A1&quot;], [&quot;=A1+B2&quot;]]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, 12, null, 16, null, 25, null, 15] </span></p>

<p><strong>Explanation</strong></p>
Spreadsheet spreadsheet = new Spreadsheet(3); // Initializes a spreadsheet with 3 rows and 26 columns<br data-end="321" data-start="318" />
spreadsheet.getValue(&quot;=5+7&quot;); // returns 12 (5+7)<br data-end="373" data-start="370" />
spreadsheet.setCell(&quot;A1&quot;, 10); // sets A1 to 10<br data-end="423" data-start="420" />
spreadsheet.getValue(&quot;=A1+6&quot;); // returns 16 (10+6)<br data-end="477" data-start="474" />
spreadsheet.setCell(&quot;B2&quot;, 15); // sets B2 to 15<br data-end="527" data-start="524" />
spreadsheet.getValue(&quot;=A1+B2&quot;); // returns 25 (10+15)<br data-end="583" data-start="580" />
spreadsheet.resetCell(&quot;A1&quot;); // resets A1 to 0<br data-end="634" data-start="631" />
spreadsheet.getValue(&quot;=A1+B2&quot;); // returns 15 (0+15)</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rows &lt;= 10<sup>3</sup></code></li>
	<li><code>0 &lt;= value &lt;= 10<sup>5</sup></code></li>
	<li>The formula is always in the format <code>&quot;=X+Y&quot;</code>, where <code>X</code> and <code>Y</code> are either valid cell references or <strong>non-negative</strong> integers with values less than or equal to <code>10<sup>5</sup></code>.</li>
	<li>Each cell reference consists of a capital letter from <code>&#39;A&#39;</code> to <code>&#39;Z&#39;</code> followed by a row number between <code>1</code> and <code>rows</code>.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made in <strong>total</strong> to <code>setCell</code>, <code>resetCell</code>, and <code>getValue</code>.</li>
</ul>


---

## Solution

```java
class Spreadsheet {
    private int arr[][];
    public Spreadsheet(int rows) {
        arr = new int[rows + 1][27];
    }
    
    public void setCell(String cell, int value) {
        int col = cell.charAt(0) - 'A';
        int row = 0;
        for (int i = 1; i < cell.length(); i++) 
            row = row * 10 + cell.charAt(i) - '0'; 
        arr[row][col] = value;
    }
    
    public void resetCell(String cell) {
        int col = cell.charAt(0) - 'A';
        int row = 0;
        for (int i = 1; i < cell.length(); i++)
            row = row * 10 + cell.charAt(i) - '0';
        arr[row][col] = 0; 
    }
    
    public int getValue(String formula) {
        /* Either first character can be a digit or first character can denote some column */
        formula = formula.substring(1, formula.length());
        if (!Character.isDigit(formula.charAt(0))) {
            //if it it not a digit
            int total = 0;
            int col1 = formula.charAt(0) - 'A';
            int row1 = 0, idx = -1;
            for (int i = 1; i < formula.length(); i++) {
                if (formula.charAt(i) == '+') {
                    idx = i;
                    break;
                }
                row1 = row1 * 10 + formula.charAt(i) - '0';
            }
            total += arr[row1][col1];
            
            //second half;
            // Again it could be some value or may denote to some cell;
            if (!Character.isDigit(formula.charAt(idx + 1))) {
                int col2 = formula.charAt(idx + 1) - 'A';
                int row2 = 0;
                for (int i = idx + 2; i < formula.length(); i++) {
                    row2 = row2 * 10 + formula.charAt(i) - '0';
                }
                total += arr[row2][col2];
            }
            else {
                int ans2 = 0;
                for (int i = idx + 1; i < formula.length(); i++) {
                    ans2 = ans2 * 10 + formula.charAt(i) - '0';
                }
                total += ans2;
            }
            return total;
        }       
        else {
            // first half is containg the value;
            int total = 0, idx = -1;
            for (int i = 0; i < formula.length(); i++) {
                if (formula.charAt(i) == '+') {
                    idx = i;
                    break;
                } 
                total = total * 10 + formula.charAt(i) - '0'; 
            }

            //second half 
            if (!Character.isDigit(formula.charAt(idx + 1))) {
                int col2 = formula.charAt(idx + 1) - 'A';
                int row2 = 0;
                for (int i = idx + 2; i < formula.length(); i++) {
                    row2 = row2 * 10 + formula.charAt(i) - '0';
                }
                total += arr[row2][col2];
            }
            else {
                int ans2 = 0;
                for (int i = idx + 1; i < formula.length(); i++) {
                    ans2 = ans2 * 10 + formula.charAt(i) - '0';
                }
                total += ans2;
            }
            return total;
        }
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */
```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

