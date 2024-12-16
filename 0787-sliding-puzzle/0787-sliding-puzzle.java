class Solution {
    public int slidingPuzzle(int[][] board) {
        int n = board.length , m = board[0].length;
        String target = "123450";
        StringBuilder start = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) start.append(num);
        }
        if (start.toString().equals(target)) return 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(start.toString());
        set.add(start.toString());
        int[][] directions = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(target)) return steps;
                int zeroPos = current.indexOf('0');
                for (int nextPos : directions[zeroPos]) {
                    StringBuilder current1 = new StringBuilder(current);
                    current1.setCharAt(zeroPos, current.charAt(nextPos));
                    current1.setCharAt(nextPos, '0');
                    String new_str = current1.toString();
                    if (!set.contains(new_str)) {
                        set.add(new_str);
                        queue.offer(new_str);
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
