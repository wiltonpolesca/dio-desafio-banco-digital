package wiltonpolesca.dio.dig.bank;

import java.util.List;

public interface IAccount {
    int getBranch();
    int getAccountNumber();
    double getBalance();
    List<AccountTransaction> getHistory();
    void withdrawal(double value) throws Exception;
    void deposit(double value) throws Exception;
    void withdraw(String description, double value) throws Exception;
    void deposit(String description, double value) throws Exception;
    void transfer(double value, IAccount destination) throws Exception;
    
    void printAccountInfo();
    void printHistory();
}