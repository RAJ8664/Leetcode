class Solution {
    public int numberOfBeams(String[] bank) {
        int n = bank.length;
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            String  current = bank[i];
            int count = 0;
            for(int j = 0; j < current.length(); j++) {
                if(current.charAt(j) == '1') 
                    count++;
            }
            if(count > 0) 
                res.add(count);
        }
        int sum = 0;
        for(int i = 0; i < res.size() - 1; i++) 
            sum += res.get(i) * res.get(i + 1);
        return sum;
    }
}