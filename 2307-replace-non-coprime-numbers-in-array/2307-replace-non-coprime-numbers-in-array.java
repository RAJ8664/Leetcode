class Solution {

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }

    public List<Integer> replaceNonCoprimes(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            int current = nums[i];
            if (st.size() == 0)
                st.add(current);
            else {
                st.add(current);
                while (st.size() > 1) {
                    int last = st.pop(), sLast = st.pop();
                    if (gcd(last, sLast) > 1)
                        st.push(lcm(last, sLast));
                    else {
                        st.push(sLast);
                        st.push(last);
                        break;
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        while (st.size() > 0)
            res.add(st.pop());
        Collections.reverse(res);
        return res;
    }
}