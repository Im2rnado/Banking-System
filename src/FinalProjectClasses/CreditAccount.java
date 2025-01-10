/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */

package FinalProjectClasses;

public class CreditAccount extends Account {
    private double creditLimit;

    public CreditAccount(double creditLimit, String accountId, String userId, String accountType, double balance, String currency) {
        super(accountId, userId, accountType, balance, currency);
        this.creditLimit = creditLimit;
    }

    public void repayCredit(double amount) {
        if (amount > 0) {
            deposit(amount);
            System.out.println("Credit repaid with amount: " + amount);
        } else {
            System.out.println("Invalid repayment amount.");
        }
    }

    public boolean spendCredit(double amount) {
        if (amount > 0 && (getBalance() + creditLimit) >= amount) {
            withdraw(amount);
            System.out.println("Credit spent: " + amount);
            return true;
        } else {
            System.out.println("Transaction failed. Exceeds credit limit.");
            return false;
        }
    }

    public double getCreditLimit() {
        return creditLimit;
    }
}
