class Solution {
    public int maximumLength(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();
        first.add(nums[0]); second.add(nums[0]);
        for(int i = 1; i < n; i++) {
            int current = nums[i];
            if((current + first.get(first.size() - 1)) % 2 == 1)   
                first.add(current);
            if((current + second.get(second.size() - 1)) % 2 == 0) 
                second.add(current);
        }
        int res1 = Math.max(first.size(), second.size());
        if(n <= 1)
            return res1;
        first.clear(); second.clear();
        first.add(nums[1]); second.add(nums[1]);
        for(int i = 2; i < n; i++) {
            int current = nums[i];
            if((current + first.get(first.size() - 1)) % 2 == 1) 
                first.add(current);
            if((current + second.get(second.size() - 1)) % 2 == 0) 
                second.add(current);
        }
        int res2 = Math.max(first.size() , second.size());
        int ans = Math.max(res1, res2);
        int even = 0, odd = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] % 2 == 0) 
                even++;
            else if(nums[i] % 2 == 1) 
                odd++;
        }
        ans = Math.max(ans, even);
        ans = Math.max(ans, odd);
        return ans;
    }
}