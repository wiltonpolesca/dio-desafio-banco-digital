package wiltonpolesca.dio.dig.bank;

public class SavingAccount extends BankAccount {

    private static int SEQUENCIAL = 200000;

    public SavingAccount() {
        this.setBranch(this.DEFAULT_BRANCH);
        this.setAccountNumber(++SEQUENCIAL);
    }

    public SavingAccount(int branch, int accountNumber) {
        super(branch, accountNumber);
    }

    @Override
    BankType type() {
        return BankType.Saving;
    }
    
}
