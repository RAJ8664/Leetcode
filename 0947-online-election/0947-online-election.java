class TopVotedCandidate {
    private TreeSet<Integer> time;
    private HashMap<Integer, Integer> winnerId;
    private HashMap<Integer, Integer> votes;
    private TreeSet<Tuple> set;
    private HashMap<Tuple, Integer> timeMap;
    private int timer;

    static class Tuple {
        int pId, vFreq, vTime;
        public Tuple(int pId, int vFreq, int vTime) {
            this.pId = pId;
            this.vFreq = vFreq;
            this.vTime = vTime;
        }
        @Override
        public String toString() {
            return "(" + pId + " " + vFreq + " " + vTime + ")";
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Tuple current = (Tuple)(obj);
            return current.pId == pId && current.vFreq == vFreq && current.vTime == vTime;
        }
        @Override
        public int hashCode() {
            return Objects.hash(pId, vFreq);
        }
    }

    static class customSort implements Comparator<Tuple> {
        @Override
        public int compare(Tuple first, Tuple second) {
            int op1 = Integer.compare(second.vFreq, first.vFreq);
            if (op1 != 0)
                return op1;
            return Integer.compare(second.vTime, first.vTime); 
        }
    }

    public TopVotedCandidate(int[] persons, int[] times) {
        time = new TreeSet<>();
        timeMap = new HashMap<>();
        winnerId = new HashMap<>();
        set = new TreeSet<>(new customSort());
        votes = new HashMap<>();
        timer = 0;

        for (int i = 0; i < persons.length; i++) {
            int currTime = times[i];
            int currVote = persons[i]; 
            if (votes.containsKey(currVote)) {
                int prevVotes = votes.get(currVote);
                set.remove(new Tuple(currVote, votes.get(currVote), timeMap.get(new Tuple(currVote, votes.get(currVote), -1))));
                set.add(new Tuple(currVote, votes.get(currVote) + 1, timer));
                votes.put(currVote, votes.get(currVote) + 1);
                timeMap.put(new Tuple(currVote, votes.get(currVote), -1), timer);
                timer++;
            } else {
                set.add(new Tuple(currVote, 1, timer));
                votes.put(currVote, 1);
                timeMap.put(new Tuple(currVote, 1, -1), timer);
                timer++;
            }
            winnerId.put(currTime, set.first().pId);
            time.add(currTime);
        } 
    }
    
    public int q(int t) {
        Integer prev = time.floor(t);
        return winnerId.get(prev); 
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */