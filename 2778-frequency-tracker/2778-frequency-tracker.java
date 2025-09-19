class FrequencyTracker {
    private int freq[];
    private HashMap<Integer, Integer> map;
    public FrequencyTracker() {
        freq = new int[(int)(1e5 + 1)]; 
        map = new HashMap<>();
    }
    
    public void add(int number) {
        if (map.containsKey(number)) {
            int pastFreq = map.getOrDefault(number, 0);
            freq[pastFreq]--;
            map.put(number, map.getOrDefault(number, 0) + 1);
            freq[map.get(number)]++;
        }
        else {
            map.put(number, 1);
            freq[1]++;
        }
    }
    
    public void deleteOne(int number) {
        if (map.getOrDefault(number, 0) > 0) {
            freq[map.getOrDefault(number, 0)]--;
            freq[map.getOrDefault(number, 0) - 1]++;
            map.put(number, map.getOrDefault(number, 0) - 1);
            if (map.getOrDefault(number, 0) == 0)
                map.remove(number);
        } 
    }
    
    public boolean hasFrequency(int frequency) {
        if (freq[frequency] > 0) 
            return true;
        return false; 
    }
}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * FrequencyTracker obj = new FrequencyTracker();
 * obj.add(number);
 * obj.deleteOne(number);
 * boolean param_3 = obj.hasFrequency(frequency);
 */