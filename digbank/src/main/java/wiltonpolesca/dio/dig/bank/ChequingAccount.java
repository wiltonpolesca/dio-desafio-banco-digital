package wiltonpolesca.dio.dig.bank;

public class ChequingAccount extends BankAccount {

    private static int SEQUENCIAL = 1;
    
    public ChequingAccount() {
        this.setBranch(this.DEFAULT_BRANCH);
        this.setAccountNumber(++SEQUENCIAL);
    }
    
    public ChequingAccount(int branch, int accountNumber) {
        super(branch, accountNumber);
    }

    @Override
    BankType type() {
        return BankType.Chequing;
    }   
}
