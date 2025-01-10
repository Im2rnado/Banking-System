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

public class Account {

    private String accountId;
    private String userId;
    private String accountType;
    private double balance;
    private String currency;

    public Account(String accountId, String userId, String accountType, double balance, String currency) {
        this.accountId = accountId;
        this.userId = userId;
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
            new AuditLog("deposit", userId, new Date()).recordLog();
            updateAccountBalance();
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
            new AuditLog("withdraw", userId, new Date()).recordLog();
            updateAccountBalance();
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
            return false;
        }
    }

    public boolean transferTo(Account targetAccount, double amount) {
        if (this.withdraw(amount)) {
            targetAccount.deposit(amount);
            System.out.println("Transfer successful to account ID: " + targetAccount.getAccountId());
            new AuditLog("transfer", userId, new Date()).recordLog();
            return true;
        }
        System.out.println("Transfer failed due to insufficient funds or invalid amount.");
        return false;
    }

    private void updateAccountBalance() {
        try {
            Path path = Paths.get("Accounts.txt");
            List<String> lines = Files.readAllLines(path);

            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts.length == 5 && parts[0].equals(accountId)) {
                    lines.set(i, accountId + "," + userId + "," + accountType + "," + balance + "," + currency);
                    break;
                }
            }

            Files.write(path, lines);
        } catch (IOException e) {
            System.err.println("Failed to update account balance: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public double viewBalance() {
        return this.balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
