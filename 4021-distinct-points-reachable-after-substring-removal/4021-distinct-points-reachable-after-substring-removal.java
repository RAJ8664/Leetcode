class Solution {
    private int xP[], yP[];
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "(" + x + " " + y + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Pair current = (Pair)(obj);
            return current.x == x && current.y == y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    public int distinctPoints(String s, int k) {
        int n = s.length();

        xP = new int[n];
        yP = new int[n];
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (current == 'U') {
                xP[i] = 0;
                yP[i] = 1;
            } else if (current == 'D') {
                xP[i] = 0;
                yP[i] = -1; 
            } else if (current == 'L') {
                xP[i] = -1;
                yP[i] = 0;
            } else if (current == 'R') {
                xP[i] = 1;
                yP[i] = 0;
            }
        }

        for (int i = 1; i < n; i++) {
            xP[i] += xP[i - 1];
            yP[i] += yP[i - 1];
        }

        HashSet<Pair> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (i + k - 1 < n) {
                int l = i, r = i + k - 1;
                int windowSumX = xP[r], windowSumY = yP[r];
                if (l - 1 >= 0) {
                    windowSumX -= xP[l - 1];
                    windowSumY -= yP[l - 1];
                }
                int currX = xP[n - 1] - windowSumX, currY = yP[n - 1] - windowSumY;
                set.add(new Pair(currX, currY));
            }
        }

        return set.size(); 
    }
}