/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */
package FinalProjectClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class User {

    final String userId;
    final String username;
    private String password;
    final String role; // customer, manager, teller
    private String email;
    private String fullName;
    private String status; // active/inactive

    // Constructor
    public User(String userId, String username, String password, String role, String email, String fullName, String status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.fullName = fullName;
        this.status = status;
    }

    // Methods
    public boolean login(String username, String password) {
        // Logic for authentication
        return true;
    }

    public void logout() {
        // Logic for logging out
    }

    public void updateProfile(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.updateProfile(this);
    }

    public void changePassword(String newPasswordHash) {
        this.password = newPasswordHash;
    }

    public String getInfo() {
        return String.format("User ID: %s, Username: %s, Role: %s, Email: %s, Full Name: %s, Status: %s",
                userId, username, role, email, fullName, status);
    }

    // get system configurations
    public Double getInterestRate() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Configurations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("InterestRate")) {
                    return Double.valueOf(parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error retrieving interest rate: " + e.getMessage());
        }
        return 3.5;
    }

    public Double getFees() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Configurations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("Fees")) {
                    return Double.valueOf(parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error retrieving fees: " + e.getMessage());
        }
        return 1.0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStatus() {
        return status;
    }
}
