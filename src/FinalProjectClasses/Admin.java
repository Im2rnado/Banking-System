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
import java.util.stream.Collectors;

public class Admin extends User {

    private final String userFile = "Users.txt";
    private final String transactionFile = "Transactions.txt";
    private final String logFile = "SystemLogs.txt";

    public Admin(String userId, String username, String password, String role, String email, String fullName, String status) {
        super(userId, username, password, role, email, fullName, status);
    }

    public void createStaffAccount(Staff staff) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, true))) {
            writer.write(staff.getUserId() + "," + staff.getUsername() + "," + staff.getPassword() + ",Staff," + staff.getEmail() + "," + staff.getFullName() + ",true");
            writer.newLine();
            System.out.println("Staff account created successfully.");
            new AuditLog("staff_account_created", staff.getUserId(), new Date()).recordLog();
        } catch (IOException e) {
            System.out.println("Error creating staff account: " + e.getMessage());
        }
    }

    public void deleteUserAccount(User user) {
        try {
            Path path = Paths.get(userFile);
            List<String> lines = Files.readAllLines(path);

            List<String> remainingLines = lines.stream()
                    .filter(line -> {
                        String[] parts = line.split(",");
                        return !parts[0].equals(user.getUserId());
                    })
                    .collect(Collectors.toList());

            Files.write(path, remainingLines);
            System.out.println("User account deleted successfully.");
            new AuditLog("user_account_deleted", user.getUserId(), new Date()).recordLog();

        } catch (IOException e) {
            System.err.println("Error deleting user account: " + e.getMessage());
        }
    }

    public void viewAllTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(transactionFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println("Transaction ID: " + parts[0] + ", Type: " + parts[1] + ", Amount: " + parts[3] + ", Date: " + parts[4]);
            }
        } catch (IOException e) {
            System.out.println("Error retrieving transactions: " + e.getMessage());
        }
    }

    public void setSystemConfigurations(double interestRate, double fees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Configurations.txt"))) {
            writer.write("InterestRate," + interestRate);
            writer.newLine();
            writer.write("Fees," + fees);
            System.out.println("System configurations updated successfully.");
            new AuditLog("system_configurations_updated", getUserId(), new Date()).recordLog();
        } catch (IOException e) {
            System.out.println("Error updating system configurations: " + e.getMessage());
        }
    }

    public void resetSystemConfigurations() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Configurations.txt"))) {
            writer.write("InterestRate,3.5");
            writer.newLine();
            writer.write("Fees,1.0");
            System.out.println("System configurations reset successfully.");
            new AuditLog("system_configurations_reset", getUserId(), new Date()).recordLog();
        } catch (IOException e) {
            System.out.println("Error resetting system configurations: " + e.getMessage());
        }
    }

    public void viewSystemLogs() {
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println("Log ID: " + parts[0] + ", Action: " + parts[1] + ", User ID: " + parts[2] + ", Date: " + parts[3]);
            }
        } catch (IOException e) {
            System.out.println("Error retrieving logs: " + e.getMessage());
        }
    }

    public String assignRoles(User user, String role) {
        try {
            Path path = Paths.get(userFile);
            List<String> lines = Files.readAllLines(path);

            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts[0].equals(user.getUserId())) {
                    parts[3] = role;
                    lines.set(i, String.join(",", parts));
                    break;
                }
            }

            Files.write(path, lines);
            System.out.println("Assigned role '" + role + "' to user: " + user.getUsername());
            new AuditLog("role_assigned", user.getUserId(), new Date()).recordLog();
            return role;

        } catch (IOException e) {
            System.out.println("Error assigning role: " + e.getMessage());
            return null;
        }
    }
}
