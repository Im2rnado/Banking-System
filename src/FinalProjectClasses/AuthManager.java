/**
 *
 * @author Ibrahim, Yassin, Islam, Hatem, and Khattab
 */
package FinalProjectClasses;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class AuthManager {

    public AuthManager() {
    }

    // Method to authenticate a user
    public boolean authenticateUser(String username, String password, User user) {
        if (user.getUsername().equals(username) && validatePassword(password, user.getPassword())) {
            new AuditLog("login", user.getUserId(), new Date()).recordLog();
            System.out.println("User authenticated successfully.");
            return true;
        }
        System.out.println("Authentication failed.");
        return false;
    }

    // Method to validate role access
    public boolean validateRoleAccess(User user, String requiredRole) {
        return user.getRole().equalsIgnoreCase(requiredRole);
    }

    // Method to hash passwords
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Method to validate password against the hash
    public boolean validatePassword(String password, String hash) {
        return hashPassword(password).equals(hash);
    }
}
