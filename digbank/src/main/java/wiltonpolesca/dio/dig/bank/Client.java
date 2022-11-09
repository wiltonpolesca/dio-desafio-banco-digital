package wiltonpolesca.dio.dig.bank;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

public class Client {
    private @Getter @NonNull String name;
    private @Getter @NonNull String email;

    public Client(@NonNull String name, @NonNull String email) {
        this.name = name;
        this.email = email;
    }

    private @Getter List<IAccount> accounts = new ArrayList<>(); 

    public IAccount addAccount(BankType type) {
        IAccount account = type == BankType.Chequing ? new ChequingAccount() : new SavingAccount();
        accounts.add(account);
        
        return account;
    }

    public IAccount addAccount(BankType type, int branch, int account) throws Exception {
        IAccount acc = getAccount(branch, account);

        if (acc != null) {
            throw new Exception(
                    "The new account can not be added. Already exists an account with the branch and account number informed.");
        }

        IAccount newAccount = type == BankType.Chequing ? new ChequingAccount(branch, account)
                : new SavingAccount(branch, account);

        accounts.add(newAccount);
        return newAccount;
    }

    public IAccount getAccount(int branch, int account) {
        IAccount acc = this.accounts.stream()
                .filter((x) -> x.getBranch() == branch && x.getAccountNumber() == account)
                .findFirst()
                .orElse(null);

        return acc;
    }

    public void printClientInfo() {
        System.out.println(String.format("Name : %s", getName()));
        System.out.println(String.format("Email: %s", getEmail()));
        for (IAccount account : accounts) {
            account.printHistory();
        }
    }

}
