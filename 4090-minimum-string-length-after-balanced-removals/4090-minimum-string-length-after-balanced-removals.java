class Solution {
    public int minLengthAfterRemovals(String s) {
        int n = s.length();
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (st.size() == 0) 
                st.add(current);
            else {
                if (current == 'b' && st.peek() == 'a')
                    st.pop();
                else if (current == 'a' && st.peek() == 'b')
                    st.pop();
                else 
                    st.add(current);
            }
        }
        return st.size();
    }
}