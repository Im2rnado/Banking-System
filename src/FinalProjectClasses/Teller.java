/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */
package FinalProjectClasses;

public class Teller extends Staff {

    // Constructor
    public Teller(String userId, String username, String password, String role, String email, String fullName, String status) {
        super(userId, username, password, role, email, fullName, status);
    }

    // Method to process deposits
    public void processDeposit(Account account, double amount) {
        account.deposit(amount);
        System.out.println("Deposit of " + amount + " processed for account: " + account.getAccountId());
    }

    // Method to process withdrawals
    public void processWithdrawal(Account account, double amount) {
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal of " + amount + " processed for account: " + account.getAccountId());
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    // Method to process transfers
    public void processTransfer(Account sourceAccount, Account targetAccount, double amount) {
        if (sourceAccount.transferTo(targetAccount, amount)) {
            System.out.println("Transfer of " + amount + " from account " + sourceAccount.getAccountId() + " to " + targetAccount.getAccountId() + " completed.");
        } else {
            System.out.println("Transfer failed due to insufficient funds or other issues.");
        }
    }
}
