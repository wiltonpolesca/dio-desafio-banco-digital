package wiltonpolesca.dio.dig.bank;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class Bank {
    private @Getter List<Client> clients = new ArrayList<>();
    
    public void addClient(Client client) {
        clients.add(client);
    }
    
    public void printClients() {
        for (Client client : clients) {
            client.printClientInfo();
            System.out.println(String.format("  %-95s", " ").replace(' ', '='));
        }
    }
    
}
