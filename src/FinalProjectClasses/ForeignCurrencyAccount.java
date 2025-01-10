/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */
package FinalProjectClasses;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

public class ForeignCurrencyAccount extends Account {

    private final String currencyType;
    private final double exchangeRate;

    public ForeignCurrencyAccount(String currencyType, double exchangeRate, String accountId, String userId, String accountType, double balance, String currency) {
        super(accountId, userId, accountType, balance, currency);
        this.currencyType = currencyType;
        this.exchangeRate = exchangeRate;
    }

    // Convert currency and log transaction in a file
    public void convertCurrency(double amount, String targetCurrency, double targetExchangeRate, String accountsFilePath, String transactionsFilePath) {
        if (amount > 0 && amount <= getBalance()) {
            double convertedAmount = amount * targetExchangeRate / exchangeRate;
            withdraw(amount);  // Assuming the withdraw method updates the balance
            System.out.println("Converted " + amount + " " + currencyType + " to " + convertedAmount + " " + targetCurrency);

            // Log the currency conversion in the transactions file
            try (BufferedWriter transactionWriter = new BufferedWriter(new FileWriter(transactionsFilePath, true))) {
                String transactionData = "Currency Conversion," + getAccountId() + "," + convertedAmount + "," + new Date().toString() + "\n";
                transactionWriter.write(transactionData);
            } catch (IOException e) {
                System.out.println("Error logging currency conversion: " + e.getMessage());
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
        } else {
            System.out.println("Conversion failed. Invalid amount or insufficient funds.");
        }
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }
}
