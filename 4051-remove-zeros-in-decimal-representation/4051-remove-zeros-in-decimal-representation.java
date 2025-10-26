class Solution {
    public long removeZeros(long n) {  
        long temp = n;
        StringBuilder res = new StringBuilder();
        while (temp > 0) {
            long dig = temp % 10;
            if (dig > 0)
                res.append(dig);
            temp /= 10;
        }
        res.reverse(); 
        return Long.parseLong(res.toString());
    }
}