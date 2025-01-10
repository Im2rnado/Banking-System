package myFinalProjectStart;

import FinalProjectClasses.*;
import FinalProjectGUI.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Initialize DatabaseManager
        DatabaseManager dbManager = new DatabaseManager();

        // Create default users
        List<User> defaultUsers = new ArrayList<>();
        defaultUsers.add(new Customer("CUST001", "customer1", "customer123", "customer", "customer1@example.com", "John Doe", "Active"));
        defaultUsers.add(new Staff("STF001", "staff1", "staff123", "staff", "staff1@example.com", "Jane Smith", "Active"));
        defaultUsers.add(new Manager("MGR001", "manager1", "manager123", "manager", "manager1@example.com", "Alice Johnson", "Active"));
        defaultUsers.add(new Admin("ADM001", "admin1", "admin123", "admin", "admin1@example.com", "Bob Brown", "Active"));

        // Create default accounts
        List<Account> defaultAccounts = new ArrayList<>();
        defaultAccounts.add(new Account("ACC001", "CUST001", "Savings", 1500.0, "EGP"));
        defaultAccounts.add(new Account("ACC002", "CUST001", "Current", 796.0, "EGP"));
        defaultAccounts.add(new Account("ACC003", "CUST001", "Fixed Deposit", 5000.0, "USD"));

        // Write default users to the database (if not already present)
        for (User user : defaultUsers) {
            if (dbManager.fetchUserByUsername(user.getUsername()) == null) {
                dbManager.createUser(user);
            }
        }

        // Write default accounts to the database (if not already present)
        for (Account account : defaultAccounts) {
            if (dbManager.fetchAccountById(account.getAccountId()) == null) {
                dbManager.createAccount(account);
            }
        }

        // Launch the login page
        java.awt.EventQueue.invokeLater(() -> {
            new Login_Page().setVisible(true);
        });
    }
}
