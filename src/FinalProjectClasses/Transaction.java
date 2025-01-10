/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */

package FinalProjectClasses;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Transaction {
    private String transactionId; // Unique ID for the transaction
    private String transactionType; // Type: "deposit", "withdraw", "transfer"
    private double amount; // Transaction amount
    private Account sourceAccount; // Source account for the transaction
    private Account targetAccount; // Target account for transfer (optional)
    private Date date; // Date of the transaction
    private final String transactionFile = "Transactions.txt"; // File to store transactions

    // Constructor
    public Transaction(String transactionId, String transactionType, double amount, Account sourceAccount, Account targetAccount, Date date) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.date = date;
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Process the transaction
    public boolean processTransaction() {
        switch (transactionType.toLowerCase()) {
            case "deposit":
                sourceAccount.deposit(amount);
                logTransaction(transactionId, sourceAccount.getUserId(), transactionType, String.valueOf(amount), sourceAccount.getAccountId(), sourceAccount.getAccountId(), date.getTime());
                return true;
                
            case "withdraw":
                if (sourceAccount.withdraw(amount))
                {
                    logTransaction(transactionId, sourceAccount.getUserId(), transactionType, String.valueOf(amount), sourceAccount.getAccountId(), sourceAccount.getAccountId(), date.getTime());
                    return true;
                } else {
                    return false;
                }

            case "transfer":
                if (sourceAccount.withdraw(amount)) {
                    targetAccount.deposit(amount);
                    logTransaction(transactionId, sourceAccount.getUserId(), transactionType, String.valueOf(amount), sourceAccount.getAccountId(), targetAccount.getAccountId(), date.getTime());
                    return true;
                }
                return false;

            default:
                throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
        }
    }

    private void logTransaction(String transactionId, String userId, String transactionType, String amount, String sourceAccountId, String targetAccountId, Long transactionDate) {
        new AuditLog(transactionType, userId, new Date()).recordLog();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(transactionFile, true))) {
            writer.write(transactionId + "," + userId + "," + transactionType + "," + amount + "," + sourceAccountId + "," + targetAccountId + "," + transactionDate);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get transaction details
    public String getTransactionDetails() {
        return "Transaction ID: " + transactionId + "\n"
                + "Transaction Type: " + transactionType + "\n"
                + "Amount: " + amount + "\n"
                + "Source Account: " + sourceAccount.getAccountId() + "\n"
                + (targetAccount != null ? "Target Account: " + targetAccount.getAccountId() + "\n" : "")
                + "Date: " + date;
    }
}


    

