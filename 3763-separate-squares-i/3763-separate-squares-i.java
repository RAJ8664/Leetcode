class Solution {
    public double separateSquares(int[][] squares) {
        double total = 0.0, low = 2e9, high = 0.0;
        for (int[] sq : squares) {
            double y = sq[1];
            double l = sq[2];
            total += l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }
        double half = total / 2.0;
        for (int i = 0; i < 100; i++) {
            double mid = low + (high - low) / 2.0;
            if (ok(squares, mid) >= half)
                high = mid;
            else
                low = mid;
        }
        return high;
    }

    private double ok(int[][] squares, double currentY) {
        double area = 0.0;
        for (int[] sq : squares) {
            double y = sq[1];
            double l = sq[2];
            double top = y + l;
            if (y >= currentY)
                continue;
            else if (top <= currentY)
                area += l * l;
            else
                area += l * (currentY - y);
        }
        return area;
    }
}