package wiltonpolesca.dio.dig.bank;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        cleanConsole();

        Bank bank = new Bank();
        bank.addClient(AddClient("Wilton Polesca", "wiltonpolesca@gmail.com"));
        bank.addClient(AddClient("Anonymous", "anonimous@gmail.com"));

        bank.printClients();
    }

    /*
     * Generates two accounts, one for chequing and one for saving, and 
     * generate some random values to show deposit, withdrawal and transfer
     */
    private static Client AddClient(String name, String email) {
        Client client = new Client(name, email);

        Random random = new Random();
        
        IAccount chequingAccount = client.addAccount(BankType.Chequing);
        IAccount savingAccount = client.addAccount(BankType.Saving);

        Double firstValue = random.nextDouble() * 1000;        
        
        try {
            chequingAccount.deposit(firstValue);
            double tranfer = firstValue * 0.10;
            firstValue -= tranfer;
            chequingAccount.transfer(tranfer, savingAccount);
            double withdrawal = firstValue * 0.24;
            firstValue -= withdrawal;
            chequingAccount.withdrawal(withdrawal);
            withdrawal = tranfer * 0.5;
            tranfer -= withdrawal;
            savingAccount.withdrawal(withdrawal);
            savingAccount.deposit(random.nextDouble());

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return client;
    }

    private static void cleanConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
