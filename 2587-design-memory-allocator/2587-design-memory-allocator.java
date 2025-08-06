class Allocator {
    private int memory[];
    public Allocator(int n) {
        memory = new int[n];
    }
    public int allocate(int size, int mID) {
        int startIdx = -1;
        int cons = 0;
        int count = 0;
        boolean flag = false;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == 0) {
                count++;
                if (startIdx == -1)
                    startIdx = i;
                if (count == size) {
                    flag = true;
                    break;
                }
            } else {
                count = 0;
                startIdx = -1;
            }
        }
        if (flag == false)
            return -1;

        for (int i = startIdx; i < startIdx + size; i++)
            memory[i] = mID;
        return startIdx;
    }
    public int freeMemory(int mID) {
        int count = 0;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == mID) {
                memory[i] = 0;
                count++;
            }
        }
        return count;
    }
}

/**
    Your Allocator object will be instantiated and called as such:
    Allocator obj = new Allocator(n);
    int param_1 = obj.allocate(size,mID);
    int param_2 = obj.freeMemory(mID);
*/