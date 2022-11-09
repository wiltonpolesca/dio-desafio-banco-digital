package wiltonpolesca.dio.dig.bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;

public class AccountTransaction {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private @Getter String transaction;
    private @Getter double value;
    private @Getter LocalDateTime timestamp;

    public AccountTransaction(String transaction, double value, LocalDateTime timestamp) {
        this.transaction = transaction;
        this.value = value;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        int descLength = transaction.length() > 30 ? 30 : transaction.length();
        
        return String.format("%s %-30s $%15.2f",
                timestamp.format(formatter),
                transaction.substring(0, descLength),
                value);
    }

}
