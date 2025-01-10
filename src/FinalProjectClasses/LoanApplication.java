/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */

package FinalProjectClasses;

import java.util.Date;

public class LoanApplication extends Loan {

    public LoanApplication(String loanId, double loanAmount, double interestRate, int duration, String customerId) {
        super(loanId, loanAmount, interestRate, duration, customerId);
    }

    public void submitApplication() {
        System.out.println("Loan application submitted for Loan ID: " + getLoanId());
        new AuditLog("loan_submit", getCustomerId(), new Date()).recordLog();
    }
}