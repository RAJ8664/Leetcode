class Spreadsheet {
    private int arr[][];
    public Spreadsheet(int rows) {
        arr = new int[rows + 1][27];
    }
    
    public void setCell(String cell, int value) {
        int col = cell.charAt(0) - 'A';
        int row = 0;
        for (int i = 1; i < cell.length(); i++) 
            row = row * 10 + cell.charAt(i) - '0'; 
        arr[row][col] = value;
    }
    
    public void resetCell(String cell) {
        int col = cell.charAt(0) - 'A';
        int row = 0;
        for (int i = 1; i < cell.length(); i++)
            row = row * 10 + cell.charAt(i) - '0';
        arr[row][col] = 0; 
    }
    
    public int getValue(String formula) {
        /* Either first character can be a digit or first character can denote some column */
        formula = formula.substring(1, formula.length());
        if (!Character.isDigit(formula.charAt(0))) {
            //if it it not a digit
            int total = 0;
            int col1 = formula.charAt(0) - 'A';
            int row1 = 0, idx = -1;
            for (int i = 1; i < formula.length(); i++) {
                if (formula.charAt(i) == '+') {
                    idx = i;
                    break;
                }
                row1 = row1 * 10 + formula.charAt(i) - '0';
            }
            total += arr[row1][col1];
            
            //second half;
            // Again it could be some value or may denote to some cell;
            if (!Character.isDigit(formula.charAt(idx + 1))) {
                int col2 = formula.charAt(idx + 1) - 'A';
                int row2 = 0;
                for (int i = idx + 2; i < formula.length(); i++) {
                    row2 = row2 * 10 + formula.charAt(i) - '0';
                }
                total += arr[row2][col2];
            }
            else {
                int ans2 = 0;
                for (int i = idx + 1; i < formula.length(); i++) {
                    ans2 = ans2 * 10 + formula.charAt(i) - '0';
                }
                total += ans2;
            }
            return total;
        }       
        else {
            // first half is containg the value;
            int total = 0, idx = -1;
            for (int i = 0; i < formula.length(); i++) {
                if (formula.charAt(i) == '+') {
                    idx = i;
                    break;
                } 
                total = total * 10 + formula.charAt(i) - '0'; 
            }

            //second half 
            if (!Character.isDigit(formula.charAt(idx + 1))) {
                int col2 = formula.charAt(idx + 1) - 'A';
                int row2 = 0;
                for (int i = idx + 2; i < formula.length(); i++) {
                    row2 = row2 * 10 + formula.charAt(i) - '0';
                }
                total += arr[row2][col2];
            }
            else {
                int ans2 = 0;
                for (int i = idx + 1; i < formula.length(); i++) {
                    ans2 = ans2 * 10 + formula.charAt(i) - '0';
                }
                total += ans2;
            }
            return total;
        }
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */