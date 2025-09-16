class Solution {
    public int earliestTime(int[][] tasks) {
        int mini = Integer.MAX_VALUE;
        for (int task[] : tasks) 
            mini = Math.min(mini, task[0] + task[1]);
        return mini;
    }
}