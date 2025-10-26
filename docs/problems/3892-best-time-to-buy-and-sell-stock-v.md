# 3892. Best Time To Buy And Sell Stock V


---

<h2><a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-v">3892. Best Time to Buy and Sell Stock V</a></h2><h3>Medium</h3><hr><p>You are given an integer array <code>prices</code> where <code>prices[i]</code> is the price of a stock in dollars on the <code>i<sup>th</sup></code> day, and an integer <code>k</code>.</p>

<p>You are allowed to make at most <code>k</code> transactions, where each transaction can be either of the following:</p>

<ul>
	<li>
	<p><strong>Normal transaction</strong>: Buy on day <code>i</code>, then sell on a later day <code>j</code> where <code>i &lt; j</code>. You profit <code>prices[j] - prices[i]</code>.</p>
	</li>
	<li>
	<p><strong>Short selling transaction</strong>: Sell on day <code>i</code>, then buy back on a later day <code>j</code> where <code>i &lt; j</code>. You profit <code>prices[i] - prices[j]</code>.</p>
	</li>
</ul>

<p><strong>Note</strong> that you must complete each transaction before starting another. Additionally, you can't buy or sell on the same day you are selling or buying back as part of a previous transaction.</p>

<p>Return the <strong>maximum</strong> total profit you can earn by making <strong>at most</strong> <code>k</code> transactions.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [1,7,9,8,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>
We can make $14 of profit through 2 transactions:

<ul>
	<li>A normal transaction: buy the stock on day 0 for $1 then sell it on day 2 for $9.</li>
	<li>A short selling transaction: sell the stock on day 3 for $8 then buy back on day 4 for $2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [12,16,19,19,8,1,19,13,9], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">36</span></p>

<p><strong>Explanation:</strong></p>
We can make $36 of profit through 3 transactions:

<ul>
	<li>A normal transaction: buy the stock on day 0 for $12 then sell it on day 2 for $19.</li>
	<li>A short selling transaction: sell the stock on day 3 for $19 then buy back on day 4 for $8.</li>
	<li>A normal transaction: buy the stock on day 5 for $1 then sell it on day 6 for $19.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= prices.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= prices.length / 2</code></li>
</ul>


## Solution

```java
class Solution {
    private long dp[][][][];
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        dp = new long[n + 1][2][2][k + 1];
        for (long current[][][] : dp) for (long current1[][] : current) for (long current2[] : current1) Arrays.fill(current2, -1);
        long res = solve(0, 0, 0, k, prices);
        return res;
    }
    private long solve(int ind, int hasBought, int hasSold, int Rem, int prices[]) {
        if (ind == prices.length - 1) {
            if (hasBought == 1) return prices[ind];
            else if (hasSold == 1) return -prices[ind];
            return 0;
        }
        if (ind >= prices.length || Rem <= 0) return 0;
        if (dp[ind][hasBought][hasSold][Rem] != -1) return dp[ind][hasBought][hasSold][Rem];
        if (hasBought == 0 && hasSold == 0) {
            long op1 = 0, op2 = 0, op3 = 0;
            op1 = -prices[ind] + solve(ind + 1, 1, 0, Rem, prices);
            op2 = prices[ind] + solve(ind + 1, 0, 1, Rem, prices);
            op3 = solve(ind + 1, 0, 0, Rem, prices);
            return dp[ind][hasBought][hasSold][Rem] = Math.max(op1, Math.max(op2, op3));
        }
        else if (hasBought == 1 && hasSold == 0) {
            long op1 = 0, op2 = 0;
            op1 = prices[ind] + solve(ind + 1, 0, 0, Rem - 1, prices);
            op2 = solve(ind + 1, hasBought, hasSold, Rem, prices);
            return dp[ind][hasBought][hasSold][Rem] = Math.max(op1, op2);
        }
        else if (hasBought == 0 && hasSold == 1) {
            long op1 = 0, op2 = 0;
            op1 = -prices[ind] + solve(ind + 1, 0, 0, Rem - 1, prices);
            op2 = solve(ind + 1, hasBought, hasSold, Rem, prices);
            return dp[ind][hasBought][hasSold][Rem] = Math.max(op1, op2);
        }
        return 0;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

