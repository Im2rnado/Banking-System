/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */
package FinalProjectClasses;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Staff extends User {

    public Staff(String userId, String username, String password, String role, String email, String fullName, String status) {
        super(userId, username, password, role, email, fullName, status);
    }

    // View all transactions from a file
    public void viewAllTransactions(String transactionsFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(transactionsFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] transactionData = line.split(",");
                // Display transaction details
                System.out.println("Transaction ID: " + transactionData[0]
                        + ", Type: " + transactionData[1]
                        + ", Amount: " + transactionData[2]
                        + ", Date: " + transactionData[3]);
            }
        } catch (IOException e) {
            System.out.println("Error retrieving transactions: " + e.getMessage());
        }
    }

    // Process loan application (update status in a loan file)
    public void processLoanApplication(String loanId, String status) {
        try {
            Path path = Paths.get("Loans.txt");
            List<String> lines = Files.readAllLines(path);

            for (int i = 0; i < lines.size(); i++) {
                String[] loanData = lines.get(i).split(",");
                if (loanData[0].equals(loanId)) {
                    loanData[5] = status;
                    loanData[6] = new Date().toString();
                    lines.set(i, String.join(",", loanData));
                    break;
                }
            }

            Files.write(path, lines);
            System.out.println("Loan application processed successfully. Status: " + status);

        } catch (IOException e) {
            System.out.println("Error processing loan application: " + e.getMessage());
        }
    }

    // Approve loan
    public void approveLoan(Loan loan) {
        loan.approveLoan();
        processLoanApplication(loan.getLoanId(), "approved");
    }

    // Reject loan
    public void rejectLoan(Loan loan) {
        loan.rejectLoan();
        processLoanApplication(loan.getLoanId(), "rejected");
    }
}
