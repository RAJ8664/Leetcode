class Solution {
    public String resultingString(String s) {
        int n = s.length();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (i == 0) st.add(current - 'a');
            else {
                if (current == 'a') {
                    System.out.println(i);
                    if (st.size() == 0) st.add(current - 'a');
                    else {
                        if (st.peek() == 'b' - 'a') st.pop();
                        else if (st.peek() == 'z' - 'a') st.pop();
                        else st.add(current - 'a');
                    }
                    continue;
                }
                if (current == 'z') {
                    if (st.size() == 0) st.add(current - 'a');
                    else {
                        if (st.peek() == 'a' - 'a') st.pop();
                        else if (st.peek() == 'y' - 'a') st.pop();
                        else st.add(current - 'a');
                    }
                    continue;
                }
                if (st.size() > 0 && (current - 'a' + 1) % 26 == st.peek()) {
                    st.pop();
                }
                else if (st.size() > 0 && (current - 'a' - 1) % 26 == st.peek()) {
                    st.pop();
                }
                else st.add(current - 'a');
            }
        }
        StringBuilder res = new StringBuilder();
        while (st.size() > 0) res.append((char)(st.pop() + 'a'));
        return res.reverse().toString();
    }
}