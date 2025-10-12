class ExamTracker {
    private ArrayList<Long> pref;
    private ArrayList<Pair> res;
    static class Pair {
        int time, cost;
        public Pair(int time, int cost) {
            this.time = time;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "(" + time + ", " + cost + ")";
        }
    }

    public ExamTracker() {
        res = new ArrayList<>();
        pref = new ArrayList<>();
    }

    public void record(int time, int score) {
        long toAdd = 0;
        if (pref.size() > 0)
            toAdd += pref.get(pref.size() - 1) * 1L  + score * 1L;
        else
            toAdd = score * 1L;
        pref.add(toAdd);
        res.add(new Pair(time, score));
    }

    public long totalScore(int startTime, int endTime) {
        long res = 0;
        int left = getLeft(startTime), right = getRight(endTime);
        if (left == - 1 || right == -1)
            return 0L;
        res = pref.get(right);
        if (left - 1 >= 0)
            res  -= pref.get(left - 1);
        return res;
    }

    private int getLeft(int target) {
        int low = 0, high = res.size() - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (res.get(mid).time >= target) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }

    private int getRight(int target) {
        int low = 0, high = res.size() - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (res.get(mid).time <= target) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return ans;
    }
}

/**
    Your ExamTracker object will be instantiated and called as such:
    ExamTracker obj = new ExamTracker();
    obj.record(time,score);
    long param_2 = obj.totalScore(startTime,endTime);
*/

/**
 * Your ExamTracker object will be instantiated and called as such:
 * ExamTracker obj = new ExamTracker();
 * obj.record(time,score);
 * long param_2 = obj.totalScore(startTime,endTime);
 */