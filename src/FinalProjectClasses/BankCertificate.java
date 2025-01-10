/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */

package FinalProjectClasses;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BankCertificate extends Account {
    private String maturityDate;
    private double interestRate;

    private final String accountFile = "Accounts.txt";

    public BankCertificate(String maturityDate, double interestRate, String accountId, String userId, String accountType, double balance, String currency) {
        super(accountId, userId, accountType, balance, currency);
        this.maturityDate = maturityDate;
        this.interestRate = interestRate;
    }

    public void applyInterestOnMaturity() {
    double interest = getBalance() * (interestRate / 100);
    double newBalance = getBalance() + interest;
    setBalance(newBalance);

    System.out.println("Maturity reached. Interest applied: " + interest + ". New balance: " + newBalance);

    // Update balance in the file
    try {
        List<String> lines = Files.readAllLines(Paths.get(accountFile));
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            if (parts[0].equals(getAccountId())) {
                parts[4] = String.valueOf(newBalance);
                lines.set(i, String.join(",", parts));
                break;
            }
        }
        Files.write(Paths.get(accountFile), lines);
    } catch (IOException e) {
        System.out.println("Error updating balance on maturity: " + e.getMessage());
        e.printStackTrace();
    }
}

    public String getMaturityDate() {
        return maturityDate;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
