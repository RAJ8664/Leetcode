class Solution {
    public int triangularSum(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) 
            temp.add(nums[i]);
        while (true) {
            if (temp.size() == 1) 
                break;
            else {
                ArrayList<Integer> current = new ArrayList<>();
                for (int i = 0; i < temp.size() - 1; i++) 
                    current.add((temp.get(i) + temp.get(i + 1)) % 10);
                temp.clear();
                for (int ele : current)
                    temp.add(ele);
                if (temp.size() == 1) 
                    break;
            }
        }
        return temp.get(0);
    }
}