class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        int count1 = 0, count2 = 0, ans1 = -(int)(1e9), ans2 = -(int)(1e9);
        for (int i = 0; i < n; i++) {
            if (count1 == 0 && ans2 != nums[i]) {
                ans1 = nums[i];
                count1 = 1;
            }
            else if (count2 == 0 && ans1 != nums[i]) {
                count2 = 1;
                ans2 = nums[i];
            }
            else if (nums[i] == ans1) {
                count1++;
            }
            else if (nums[i] == ans2) {
                count2++;
            }
            else {
                if (count1 > 0) 
                    count1--;
                if(count2 > 0) 
                    count2--;
            }
        }
        int cnt1 = 0, cnt2 = 0;
        for (int ele : nums) {
            if (ele == ans1) 
                cnt1++;
            if (ele == ans2)
                cnt2++;
        }
        if (cnt1 > n / 3) 
            if (ans1 != -(int)(1e9)) 
                res.add(ans1);
        if (cnt2 > n / 3)
            if(ans2 != -(int)(1e9)) 
                res.add(ans2);
        return res; 
    }
}