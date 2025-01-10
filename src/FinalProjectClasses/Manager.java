/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */
package FinalProjectClasses;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Manager extends Staff {

    // Constructor
    public Manager(String userId, String username, String password, String role, String email, String fullName, String status) {
        super(userId, username, password, role, email, fullName, status);
    }

    // Method to view customer accounts from a file
    public void viewCustomerAccounts(Customer customer, String accountsFilePath) {
        System.out.println("Viewing accounts for customer: " + customer.getFullName());

        // Read the accounts file and display account balances
        try (BufferedReader br = new BufferedReader(new FileReader(accountsFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] accountData = line.split(",");
                // Check if the account belongs to the current customer
                if (accountData.length == 5 && accountData[1].equals(customer.getUserId())) { // Compare with customer ID
                    String accountId = accountData[0];
                    double balance = Double.parseDouble(accountData[3]);
                    System.out.println("Account ID: " + accountId + ", Balance: " + balance);
                }
            }
        } catch (IOException e) {
            System.out.println("Error viewing customer accounts: " + e.getMessage());
        }
    }

    public List<Loan> getPendingLoans() {
        List<Loan> loans = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Loans.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] loanData = line.split(",");
                if (loanData.length == 7 && loanData[5].equals("pending")) {
                    Loan loan = new Loan(
                            loanData[0], // id
                            Double.parseDouble(loanData[2]), // amount
                            Double.parseDouble(loanData[3]), // interest_rate
                            Integer.parseInt(loanData[4]), // duration
                            loanData[1], // customer_id
                            loanData[5] // status
                    );
                    loans.add(loan);
                }
            }
        } catch (IOException e) {
            System.out.println("Error fetching loans: " + e.getMessage());
        }

        return loans;
    }
}
