package wiltonpolesca.dio.dig.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class BankAccountTest {
    private Bank bank;
    private Client client;
    private IAccount account;

    @BeforeEach
    void createData() {
        bank = new Bank();
        client = new Client("Client", "client@client.com");
        account = client.addAccount(BankType.Chequing);
        bank.addClient(client);
    }

    @Test
    void shouldNotBePossibleCreateTwoAccountsWithTheSameBranchAndNumber() {
        assertDoesNotThrow(() -> client.addAccount(BankType.Chequing, 1, 10));
        assertThrows(Exception.class, () -> client.addAccount(BankType.Chequing, 1, 10));
    }

    void shouldNotBePossibleToDepositANegativeValue() {
        assertThrows(Exception.class, () -> account.deposit(-10.2));
    }

    void shouldNotBePossibleToWithdrawalANegativeValue() {
        assertDoesNotThrow(() -> account.deposit(100.00));
        assertThrows(Exception.class, () -> account.withdrawal(-10.0));
    }

    void shouldNotBePossibleToWithdrawlAValueGreaterThanTheBalance() {
        assertDoesNotThrow(() -> account.deposit(100.00));
        assertThrows(Exception.class, () -> account.withdrawal(10000.00));
    }

    void shouldNotBePossibleToTransferAValueGreaterThanTheBalance() {
        assertDoesNotThrow(() -> account.deposit(100.00));
        IAccount secondAccount = client.addAccount(BankType.Chequing);
        assertThrows(Exception.class, () -> account.transfer(10000.00, secondAccount));
    }
    
    void shouldAllTransactionsBeSavedInMemory() {
        assertDoesNotThrow(() -> account.deposit(100.00));
        assertDoesNotThrow(() -> account.withdrawal(20.00));
        IAccount secondAccount = client.addAccount(BankType.Chequing);
        assertDoesNotThrow(() -> account.transfer(20.00, secondAccount));
        assertDoesNotThrow(() -> account.deposit(45));
        
        List<AccountTransaction> transactinons = account.getHistory();
        assertEquals(5, transactinons.size());
    }
    
    void shouldTheBalanceBeUpdateAccodingToTransactions() {
        assertDoesNotThrow(() -> account.deposit(100.00));
        assertDoesNotThrow(() -> account.withdrawal(20.00));
        IAccount secondAccount = client.addAccount(BankType.Chequing);
        assertDoesNotThrow(() -> account.transfer(20.00, secondAccount));
        assertDoesNotThrow(() -> account.deposit(45));
        
        assertEquals(105, account.getBalance());
        assertEquals(20, secondAccount.getBalance());
    }
    
}
