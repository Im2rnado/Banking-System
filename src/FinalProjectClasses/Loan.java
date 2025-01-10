/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */
package FinalProjectClasses;

import java.util.Date;

// Base Loan class
public class Loan {

    private String loanId;
    private double loanAmount;
    private double interestRate;
    private String loanStatus; // "pending", "approved", "rejected"
    private int duration;
    private String customerId;
    private Date approvalDate;

    // Constructor
    public Loan(String loanId, double loanAmount, double interestRate, int duration, String customerId) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.duration = duration;
        this.loanStatus = "pending";
        this.customerId = customerId;
    }

    public Loan(String loanId, double loanAmount, double interestRate, int duration, String customerId, String status) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.duration = duration;
        this.loanStatus = status;
        this.customerId = customerId;
    }

    public Loan(String loanId, double loanAmount, double interestRate, int duration, String customerId, String status, Date approvalDate) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.duration = duration;
        this.loanStatus = status;
        this.customerId = customerId;
        this.approvalDate = approvalDate;
    }

    // Getters and Setters
    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Methods
    public void approveLoan() {
        if ("pending".equals(this.loanStatus)) {
            this.loanStatus = "approved";
            this.approvalDate = new Date();
            System.out.println("Loan " + loanId + " has been approved.");
            new AuditLog("loan_approved", customerId, new Date()).recordLog();
        } else {
            System.out.println("Loan " + loanId + " cannot be approved. Current status: " + loanStatus);
        }
    }

    public void rejectLoan() {
        if ("pending".equals(this.loanStatus)) {
            this.loanStatus = "rejected";
            System.out.println("Loan " + loanId + " has been rejected.");
            new AuditLog("loan_rejected", customerId, new Date()).recordLog();
        } else {
            System.out.println("Loan " + loanId + " cannot be rejected. Current status: " + loanStatus);
        }
    }

    public void displayLoanDetails() {
        System.out.println("Loan ID: " + loanId);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Loan Amount: " + loanAmount);
        System.out.println("Interest Rate: " + interestRate);
        System.out.println("Loan Status: " + loanStatus);
        if (approvalDate != null) {
            System.out.println("Approval Date: " + approvalDate);
        }
    }
}
