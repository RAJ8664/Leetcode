class Solution {
    static class Pair {
        int element;
        int index;
        public Pair(int element, int index) {
            this.element = element;
            this.index = index;
        }
        @Override
        public String toString() {
            return "(" + element + " " + index + ")";
        }
    }
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int maxi = 0;
        int arr[] = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '0') arr[j] = 0;
                else arr[j]++;
            }
            int res =  Largest_area_in_a_histogram(arr);
            maxi = Math.max(maxi, res);
        }
        return maxi;
    }

    private int Largest_area_in_a_histogram(int arr[]) {
        int n = arr.length;
        int next_smaller[] = new int[n];
        int prev_smaller[] = new int[n];
        Stack<Pair> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            int current = arr[i];
            while (st.size() > 0 && st.peek().element >= current) st.pop();
            if (st.size() == 0) next_smaller[i] = -1;
            else next_smaller[i] = st.peek().index;
            st.add(new Pair(current, i));
        }
        st.clear();
        for (int i = 0; i < n; i++) {
            int current = arr[i];
            while (st.size() > 0 && st.peek().element >= current) st.pop();
            if (st.size() == 0) prev_smaller[i] = -1;
            else prev_smaller[i] = st.peek().index;
            st.add(new Pair(current, i));
        }
        int maxi = 0;
        for (int i = 0; i < n; i++) {
            int prev_smaller_index = prev_smaller[i];
            int next_smaller_index = next_smaller[i];
            int left = 0;
            int right = 0;
            if (prev_smaller_index == -1) left += i;
            if (prev_smaller_index != -1) left += i - prev_smaller[i] - 1;
            if (next_smaller_index == -1) right += n - i - 1;
            if (next_smaller_index != -1) right += next_smaller_index - i - 1;
            maxi = Math.max(maxi, ((arr[i] * (left + right + 1))));
        }
        return maxi;
    }
}