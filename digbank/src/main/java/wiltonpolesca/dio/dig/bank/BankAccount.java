package wiltonpolesca.dio.dig.bank;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public abstract class BankAccount implements IAccount {
    private final String INSUFFICIENT_BALANCE = "Insufficient balance";
    private final String INVALID_VALUE = "Value must be greater than zero";

    protected final int DEFAULT_BRANCH = 1;

    private @Getter @Setter int branch;
    private @Getter @Setter int accountNumber;
    private @Getter double balance;

    private List<AccountTransaction> history = new LinkedList<>();

    public BankAccount() {
    }

    public BankAccount(int branch, int accountNumber) {
        this.branch = branch;
        this.accountNumber = accountNumber;
    }

    abstract BankType type();

    public void withdrawal(double value) throws Exception {

        withdraw("Withdrawal", value);
    }

    public void withdraw(String description, double value) throws Exception {
        checkValueAndBalance(value);

        if (balance - value >= 0) {
            balance -= value;
        }

        history.add(new AccountTransaction(description, value * -1, LocalDateTime.now()));
    }

    public void deposit(double value) throws Exception {
        deposit("Deposit", value);
    }

    public void deposit(String description, double value) throws Exception {

        if (value <= 0) {
            throw new Exception(INVALID_VALUE);
        }

        balance += value;
        history.add(new AccountTransaction(description, value, LocalDateTime.now()));
    }

    public void transfer(double value, IAccount destination) throws Exception {
        checkValueAndBalance(value);
        withdraw(String.format("Transfer to: %s - %s", destination.getBranch(), destination.getAccountNumber()), value);
        destination.deposit(
                String.format("Transfer from: %s - %s", getBranch(), getAccountNumber()),
                value);
    }

    private void checkValueAndBalance(double value) throws Exception {
        if (value <= 0) {
            throw new Exception(INVALID_VALUE);
        }

        if (balance - value < 0) {
            throw new Exception(INSUFFICIENT_BALANCE + " - Balance: " + balance + "Value:" + value);
        }
    }

    public List<AccountTransaction> getHistory() {
        return history;
    }

    public String accountInfo() {
        return String.format("  Type: %-9s Branch: %5s   Account: %10s Balance: $%15.2f",
                type(),
                getBranch(),
                getAccountNumber(),
                getBalance());
    }

    public void printAccountInfo() {
        System.out.println(accountInfo());
    }

    public void printHistory() {
        printLine(98);
        printAccountInfo();
        printLine(98);
        for (AccountTransaction transaction : getHistory()) {
            System.out.println("  " + transaction.toString());
        }
    }

    private void printLine(int length) {
        System.out.println(String.format("  %-" + length + "s", " ").replace(' ', '-'));
    }

}
