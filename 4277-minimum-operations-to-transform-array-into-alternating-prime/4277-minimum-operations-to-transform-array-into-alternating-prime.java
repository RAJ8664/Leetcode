class Solution {
    private int maxi = (int)(1e6);
    private boolean isPrime[]; 
    public int minOperations(int[] nums) {
        int n = nums.length; 
        sieve(maxi);
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                if (isPrime[nums[i]]) {
                    continue;
                } else {
                    res += get_next_prime(nums[i]) - nums[i];
                }
            } else {
                if (!isPrime[nums[i]]) continue;
                else {
                    res += get_next_non_prime(nums[i]) - nums[i];
                }
            }
        }
        return res;
    }

    private int get_next_prime(int n) {
        while (!isPrime[n]) {
            n++;
        }
        return n;
    }

    private int get_next_non_prime(int n) {
        while (isPrime[n]) {
            n++;
        }
        return n;
    }

    private void sieve(int limit) {
        isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false; isPrime[1] = false;
        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}