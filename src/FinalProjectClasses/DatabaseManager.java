/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */
package FinalProjectClasses;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager {

    private final String userFile = "Users.txt";
    private final String transactionFile = "Transactions.txt";
    private final String accountFile = "Accounts.txt";
    private final String logFile = "SystemLogs.txt";

    public DatabaseManager() {
        createFileIfNotExists(userFile);
        createFileIfNotExists(transactionFile);
        createFileIfNotExists(accountFile);
        createFileIfNotExists(logFile);
    }

    private void createFileIfNotExists(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Failed to create file: " + fileName);
            e.printStackTrace();
        }
    }

    public void createUser(User user) {
        AuthManager authManager = new AuthManager();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(user.getUserId()).append(",")
                    .append(user.getUsername()).append(",")
                    .append(authManager.hashPassword(user.getPassword())).append(",")
                    .append(user.getRole()).append(",")
                    .append(user.getEmail()).append(",")
                    .append(user.getFullName()).append(",")
                    .append(user.getStatus());

            writer.write(sb.toString());
            writer.newLine();
            new AuditLog("user_created", user.getUserId(), new Date()).recordLog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(userFile));
            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts.length == 7 && parts[0].equals(user.getUserId())) {
                    lines.remove(i);
                    Files.write(Paths.get(userFile), lines);
                    new AuditLog("user_deleted", user.getUserId(), new Date()).recordLog();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to delete user");
            e.printStackTrace();
        }
    }

    public void createAccount(Account account) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(accountFile, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(account.getAccountId()).append(",")
                    .append(account.getUserId()).append(",")
                    .append(account.getAccountType()).append(",")
                    .append(account.getBalance()).append(",")
                    .append(account.getCurrency());

            writer.write(sb.toString());
            writer.newLine();
            new AuditLog("account_created", account.getUserId(), new Date()).recordLog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> fetchAllUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    User user = new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Account> fetchAllAccounts() {
        List<Account> accounts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(accountFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Account account = new Account(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), parts[4]);
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public User fetchUserByUsername(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7 && parts[1].equals(username)) {
                    return new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUserRole(User user, String newRole) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(userFile));
            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts.length == 7 && parts[0].equals(user.getUserId())) {
                    parts[3] = newRole;
                    lines.set(i, String.join(",", parts));
                    Files.write(Paths.get(userFile), lines);
                    new AuditLog("update_role", user.getUserId(), new Date()).recordLog();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to update user role");
            e.printStackTrace();
        }
    }

    // Return customer from user
    public Customer returnCustomerFromUser(User user) {
        return new Customer(user.getUserId(), user.getUsername(), user.getPassword(), user.getRole(), user.getEmail(), user.getFullName(), user.getStatus());
    }

    // Return admin from user
    public Admin returnAdminFromUser(User user) {
        return new Admin(user.getUserId(), user.getUsername(), user.getPassword(), user.getRole(), user.getEmail(), user.getFullName(), user.getStatus());
    }

    // Return manager from user
    public Manager returnManagerFromUser(User user) {
        return new Manager(user.getUserId(), user.getUsername(), user.getPassword(), user.getRole(), user.getEmail(), user.getFullName(), user.getStatus());
    }

    // Return staff from user
    public Staff returnStaffFromUser(User user) {
        return new Staff(user.getUserId(), user.getUsername(), user.getPassword(), user.getRole(), user.getEmail(), user.getFullName(), user.getStatus());
    }

    // Fetch account by accountId
    public Account fetchAccountById(String accountId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(accountFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5 && parts[0].equals(accountId)) {
                    return new Account(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), parts[4]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Save transaction
    public void saveTransaction(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(transactionFile, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(transaction.getTransactionType()).append(",")
                    .append(transaction.getSourceAccount().getAccountId()).append(",")
                    .append(transaction.getTargetAccount() != null ? transaction.getTargetAccount().getAccountId() : "null").append(",")
                    .append(transaction.getAmount()).append(",")
                    .append(transaction.getDate().getTime());

            writer.write(sb.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Return Transactions in List
    public List<Transaction> fetchTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(transactionFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    Transaction transaction = new Transaction(parts[0], parts[2], Double.parseDouble(parts[3]), fetchAccountById(parts[4]), fetchAccountById(parts[5]), new Date(Long.parseLong(parts[6])));
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void updateAccountBalance(Account account) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(accountFile));
            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts.length == 3 && parts[0].equals(account.getAccountId())) {
                    lines.set(i, account.getAccountId() + "," + account.getBalance() + "," + parts[2]);
                    break;
                }
            }
            Files.write(Paths.get(accountFile), lines);
            new AuditLog("update_balance", account.getUserId(), new Date()).recordLog();
        } catch (IOException e) {
            System.out.println("Failed to update account balance");
            e.printStackTrace();
        }
    }

    public void updateProfile(User user) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(userFile));
            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts.length == 7 && parts[0].equals(user.getUserId())) {
                    lines.set(i, user.getUserId() + "," + user.getUsername() + ","
                            + user.getPassword() + "," + user.getRole() + ","
                            + user.getEmail() + "," + user.getFullName() + ","
                            + user.getStatus());
                    break;
                }
            }
            Files.write(Paths.get(userFile), lines);
            new AuditLog("update_profile", user.getUserId(), new Date()).recordLog();
        } catch (IOException e) {
            System.out.println("Failed to update user profile");
            e.printStackTrace();

        }
    }

    public void logSystemAction(String action, String userId, Date date) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(logFile));
            int logCount = lines.size();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                writer.write("LG" + logCount + "," + action + "," + userId + "," + date.getTime());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<AuditLog> fetchAuditLogs() {
        List<AuditLog> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    AuditLog log = new AuditLog(parts[1], parts[2], new Date(Long.parseLong(parts[3])), parts[0]);
                    logs.add(log);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logs;
    }

    public boolean updateCustomerStatus(String customerId, String newStatus) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(userFile));
            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts.length == 7 && parts[0].equals(customerId)) {
                    parts[6] = newStatus;
                    lines.set(i, String.join(",", parts));
                    Files.write(Paths.get(userFile), lines);
                    new AuditLog("update_status", customerId, new Date()).recordLog();
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to update customer status");
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCustomerProfile(Customer customer) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(userFile));
            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts.length == 7 && parts[0].equals(customer.getUserId())) {
                    parts[1] = customer.getUsername();
                    parts[4] = customer.getEmail();
                    parts[5] = customer.getFullName();
                    lines.set(i, String.join(",", parts));
                    Files.write(Paths.get(userFile), lines);
                    new AuditLog("update_profile", customer.getUserId(), new Date()).recordLog();
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to update customer profile");
            e.printStackTrace();
        }
        return false;
    }

    public Customer getCustomerById(String customerId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7 && parts[0].equals(customerId)) {
                    return new Customer(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7 && parts[3].equals("customer")) {
                    customers.add(new Customer(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
