class Bank {
    private long account[];
    public Bank(long[] balance) {
        account = new long[balance.length + 1];
        for (int i = 1; i <= balance.length; i++)
            account[i] = balance[i - 1];
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 > account.length || account2 > account.length)
            return false;
        if (account[account1] >= money) {
            account[account1] -= money;
            account[account2] += money;
            return true;
        }
        return false;
    }

    public boolean deposit(int accountNum, long money) {
        if (accountNum > account.length)
            return false;
        account[accountNum] += money;
        return true;
    }

    public boolean withdraw(int accountNum, long money) {
        if (accountNum > account.length)
            return false;
        if (account[accountNum] >= money) {
            account[accountNum] -= money;
            return true;
        }
        return false;
    }
}

/**
    Your Bank object will be instantiated and called as such:
    Bank obj = new Bank(balance);
    boolean param_1 = obj.transfer(account1,account2,money);
    boolean param_2 = obj.deposit(account,money);
    boolean param_3 = obj.withdraw(account,money);
*/