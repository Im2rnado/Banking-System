/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */
package FinalProjectClasses;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SavingAccount extends Account {

    private double interestRate;

    public SavingAccount(double interestRate, String accountId, String userId, String accountType, double balance, String currency) {
        super(accountId, userId, accountType, balance, currency);
        this.interestRate = interestRate;
    }

    // Apply interest and update balance in a file
    public void applyInterest(String accountsFilePath, String transactionsFilePath) {
        double interest = getBalance() * (interestRate / 100);
        deposit(interest); // Assuming deposit adds the interest to the balance
        System.out.println("Interest applied: " + interest + ". New balance: " + getBalance());

        // Log the interest application in the transactions file
        try (BufferedWriter transactionWriter = new BufferedWriter(new FileWriter(transactionsFilePath, true))) {
            String transactionData = "Interest Applied," + getAccountId() + "," + interest + "\n";
            transactionWriter.write(transactionData);
        } catch (IOException e) {
            System.out.println("Error logging interest application: " + e.getMessage());
        }

        // Update the account balance in the accounts file
        try {
            Path path = Paths.get(accountsFilePath);
            List<String> lines = Files.readAllLines(path);

            for (int i = 0; i < lines.size(); i++) {
                String[] accountData = lines.get(i).split(",");
                if (accountData[0].equals(getAccountId())) {
                    accountData[3] = String.valueOf(getBalance());
                    lines.set(i, String.join(",", accountData));
                    break;
                }
            }

            Files.write(path, lines);
            System.out.println("Account balance updated successfully.");

        } catch (IOException e) {
            System.out.println("Error updating account balance: " + e.getMessage());
        }
    }

    public double getInterestRate() {
        return interestRate;
    }
}
