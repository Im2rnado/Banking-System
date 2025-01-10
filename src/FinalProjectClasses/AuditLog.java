/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */
package FinalProjectClasses;

import java.util.Date;

public class AuditLog {

    private String logId;
    private String action;
    private String user;
    Date date;

    public AuditLog(String action, String user, Date date, String logId) {
        this.action = action;
        this.user = user;
        this.date = date;
        this.logId = logId;
    }

    public AuditLog(String action, String user, Date date) {
        this.action = action;
        this.user = user;
        this.date = date;
    }

    public AuditLog(String action, String user) {
        this.action = action;
        this.user = user;
    }

    public AuditLog(String action) {
        this.action = action;
    }

    public boolean recordLog() {
        new DatabaseManager().logSystemAction(action, user, date);
        return true;
    }

    public void viewAuditLog() {
        System.out.println("Audit Log Details:");
        System.out.println("Action: " + action);
        System.out.println("User: " + user);
        System.out.println("Date: " + date);
    }

    public String getLogDetails() {
        return "Log ID" + logId + "Action: " + action + ", User: " + user + ", Date: " + date;
    }

    // Getters and Setters
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
