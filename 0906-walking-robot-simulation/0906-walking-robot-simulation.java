class Solution {
    static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair current = (Pair)(obj);
            return current.x == x && current.y == y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        @Override
        public String toString() {
            return "(" + x + " " + y + ")";
        }
    }
    public int robotSim(int[] arr, int[][] obstacles) {
        int n = arr.length;
        char dir = 'N';
        HashSet<Pair> set = new HashSet<>();
        for (int points[] : obstacles) {
            int x = points[0];
            int y = points[1];
            set.add(new Pair(x, y));
        }
        int maxi = 0;
        int current_x = 0;
        int current_y = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == -2) {
                if (dir == 'N') dir = 'W';
                else if (dir == 'W') dir = 'S';
                else if (dir == 'E') dir = 'N';
                else if (dir == 'S') dir = 'E';
            } 
            else if (arr[i] == -1) {
                if (dir == 'N') dir = 'E';
                else if (dir == 'W') dir = 'N';
                else if (dir == 'E') dir = 'S';
                else if (dir == 'S') dir = 'W';
            }
            else {
                if (dir == 'W') {
                    int count = Math.abs(arr[i]);
                    maxi = Math.max(maxi, (current_x * current_x + current_y * current_y));
                    while (!set.contains(new Pair(current_x - 1, current_y)) && count > 0) {
                        current_x--;
                        maxi = Math.max(maxi, (current_x * current_x + current_y * current_y));
                        count--;
                    }
                    
                }
                if (dir == 'E') {
                    int count = Math.abs(arr[i]);
                    maxi = Math.max(maxi, (current_x * current_x + current_y * current_y));
                    while (!set.contains(new Pair(current_x + 1, current_y)) && count > 0) {
                        current_x++;
                        maxi = Math.max(maxi, (current_x * current_x + current_y * current_y));
                        count--;
                    }
                }
                if (dir == 'N') {
                    int count = Math.abs(arr[i]);
                    maxi = Math.max(maxi, (current_x * current_x + current_y * current_y));
                    while (!set.contains(new Pair(current_x , current_y + 1)) && count > 0) {
                        current_y++;
                        maxi = Math.max(maxi, (current_x * current_x + current_y * current_y));
                        count--;
                    }
                }
                if (dir == 'S') {
                    int count = Math.abs(arr[i]);
                    maxi = Math.max(maxi, (current_x * current_x + current_y * current_y));
                    while (!set.contains(new Pair(current_x , current_y - 1)) && count > 0) {
                        current_y--;
                        maxi = Math.max(maxi, (current_x * current_x + current_y * current_y));
                        count--;
                    }
                }
            }
        }
        return maxi;
    }
}