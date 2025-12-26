public class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int Y = 0;

        for (int i = 0; i < n; i++) {
            Y += (customers.charAt(i) == 'Y' ? 1 : 0);
        }

        int min_p = n;
        int hour = n;
        int y_found = 0;
        int n_found = 0;

        for (int h = 0; h <= n; h++) {
            int y_remaining = Y - y_found;
            int pen = y_remaining + n_found;

            if (pen < min_p) {
                hour = h;
                min_p = pen;
            }

            if (h < n && customers.charAt(h) == 'N') {
                n_found++;
            }

            if (h < n && customers.charAt(h) == 'Y') {
                y_found++;
            }
        }

        return hour;
    }
}