/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */
package FinalProjectClasses;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer extends User {

    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public Customer(String userId, String username, String password, String role, String email, String fullName, String status) {
        super(userId, username, password, role, email, fullName, status);
    }

    // Fetch accounts from a file
    public void fetchAccounts(String filePath) {
        accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] accountData = line.split(",");
                if (accountData.length == 5 && accountData[1].equals(getUserId())) { // Check user ID
                    Account account = new Account(
                            accountData[0], // account_id
                            accountData[1], // user_id
                            accountData[2], // account_type
                            Double.parseDouble(accountData[3]), // balance
                            accountData[4] // currency
                    );
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            System.out.println("Error fetching accounts: " + e.getMessage());
        }
    }

    // Fetch transactions from a file
    public void fetchTransactions(String filePath) {
        transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] transactionData = line.split(",");
                if (transactionData.length == 7 && transactionData[1].equals(getUserId())) { // Check user ID
                    Transaction transaction = new Transaction(
                            transactionData[0], // id
                            transactionData[2], // transaction_type
                            Double.parseDouble(transactionData[3]), // amount
                            new DatabaseManager().fetchAccountById(transactionData[4]),
                            new DatabaseManager().fetchAccountById(transactionData[5]),
                            new Date(Long.parseLong(transactionData[6]))
                    );
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            System.out.println("Error fetching transactions: " + e.getMessage());
        }
    }

    // Get account by Id
    public Account getAccountById(String accountId) {
        getAccounts();

        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public double getTotalBalance() {
        getAccounts();
        
        return accounts.stream().mapToDouble(Account::getBalance).sum();
    }

    // Make a transaction and update the account balance in a file
    public boolean makeTransaction(String fromAccountId, String toAccountId, double amount, String accountsFilePath, String transactionsFilePath) {
        try {
            // Log the transaction in the transactions file
            try (BufferedWriter transactionWriter = new BufferedWriter(new FileWriter(transactionsFilePath, true))) {
                String transactionData = "Transfer," + userId + "," + fromAccountId + "," + toAccountId + "," + amount + "\n";
                transactionWriter.write(transactionData);
            }

            // Update balances in the accounts file
            updateAccountBalance(fromAccountId, -amount, accountsFilePath);
            updateAccountBalance(toAccountId, amount, accountsFilePath);

            System.out.println("Transaction successful.");
            return true;
        } catch (IOException e) {
            System.out.println("Error making transaction: " + e.getMessage());
        }
        return false;
    }

    // Update account balance in the file
    private void updateAccountBalance(String accountId, double amount, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);

        for (int i = 0; i < lines.size(); i++) {
            String[] accountData = lines.get(i).split(",");
            if (accountData[0].equals(accountId)) {
                double updatedBalance = Double.parseDouble(accountData[3]) + amount;
                accountData[3] = String.valueOf(updatedBalance);
                lines.set(i, String.join(",", accountData));
                break;
            }
        }

        Files.write(path, lines);
        System.out.println("Account balances updated successfully.");
    }

    // Apply for a loan (this can be stored in a separate file)
    public void applyForLoan(LoanApplication loanApplication, int durationInYears, String loanFilePath) {
        try (BufferedWriter loanWriter = new BufferedWriter(new FileWriter(loanFilePath, true))) {
            double interestRate = loanApplication.getInterestRate();
            double amount = loanApplication.getLoanAmount();
            String loanId = loanApplication.getLoanId();
            String loanData = loanId + "," + getUserId() + "," + amount + "," + interestRate + "," + durationInYears + "," + "pending,null\n";
            loanWriter.write(loanData);
            System.out.println("Loan application submitted successfully.");
        } catch (IOException e) {
            System.out.println("Error applying for loan: " + e.getMessage());
        }
    }

    public List<Account> getAccounts() {
        fetchAccounts("Accounts.txt");
        return accounts;
    }

    public List<Transaction> getTransactions() {
        fetchTransactions("Transactions.txt");
        return transactions;
    }
}
