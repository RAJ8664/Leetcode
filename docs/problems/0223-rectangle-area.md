# 223. Rectangle Area

!!! warning "Difficulty: Medium"

[:octicons-link-external-24: LeetCode Problem](https://leetcode.com/problems/rectangle-area/){ .md-button }
[:octicons-code-24: View on GitHub](https://github.com/RAJ8664/Leetcode/tree/master/0223-rectangle-area){ .md-button }

---

<h2><a href="https://leetcode.com/problems/rectangle-area">223. Rectangle Area</a></h2><h3>Medium</h3><hr><p>Given the coordinates of two <strong>rectilinear</strong> rectangles in a 2D plane, return <em>the total area covered by the two rectangles</em>.</p>

<p>The first rectangle is defined by its <strong>bottom-left</strong> corner <code>(ax1, ay1)</code> and its <strong>top-right</strong> corner <code>(ax2, ay2)</code>.</p>

<p>The second rectangle is defined by its <strong>bottom-left</strong> corner <code>(bx1, by1)</code> and its <strong>top-right</strong> corner <code>(bx2, by2)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="Rectangle Area" src="https://assets.leetcode.com/uploads/2021/05/08/rectangle-plane.png" style="width: 700px; height: 365px;" />
<pre>
<strong>Input:</strong> ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
<strong>Output:</strong> 45
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
<strong>Output:</strong> 16
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-10<sup>4</sup> &lt;= ax1 &lt;= ax2 &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= ay1 &lt;= ay2 &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= bx1 &lt;= bx2 &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= by1 &lt;= by2 &lt;= 10<sup>4</sup></code></li>
</ul>


---

## Solution

```java
class Solution {
    static class Rectangle {
        int x1, y1, x2, y2;
        public Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
        @Override
        public String toString() {
            return "(" + x1 + " " + y1 + " " + x2 + " " + y2 + ")";
        }
        public int getArea() {
            return Math.abs(x2 - x1) * Math.abs(y2 - y1);
        }
        public static int getIntersectionArea(Rectangle r1, Rectangle r2) {
            int intersectX1 = Math.max(r1.x1, r2.x1);
            int intersectY1 = Math.max(r1.y1, r2.y1);
            int intersectX2 = Math.min(r1.x2, r2.x2);
            int intersectY2 = Math.min(r1.y2, r2.y2);
            if (intersectX1 < intersectX2 && intersectY1 < intersectY2) {
                int width = intersectX2 - intersectX1;
                int height = intersectY2 - intersectY1;
                return width * height;
            }
            return 0;  
        }
        public static int getAreaBetween(Rectangle r1, Rectangle r2) {
            int areaR1 = r1.getArea();
            int areaR2 = r2.getArea();
            int intersectionArea = getIntersectionArea(r1, r2);
            return (areaR1 + areaR2) - intersectionArea;
        }
    }
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        Rectangle r1 = new Rectangle(ax1, ay1, ax2, ay2);
        Rectangle r2 = new Rectangle(bx1, by1, bx2, by2);
        int res = r1.getAreaBetween(r1, r2);
        return res;
    }
}

```

## Complexity Analysis

- **Time Complexity**: `O(?)`
- **Space Complexity**: `O(?)`

## Approach

*Detailed explanation of the approach will be added here*

