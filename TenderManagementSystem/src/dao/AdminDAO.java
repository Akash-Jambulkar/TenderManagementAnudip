package dao;

import model.Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/TenderManagement";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1234";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Validate Admin (existing method)
    public boolean validateAdmin(String username, String password) {
        // Implement validation logic
        return true;
    }

    // Register a new Admin
    public void registerAdmin(Admin admin) {
        String sql = "INSERT INTO Admin (username, password) VALUES (?, ?)";
        
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.executeUpdate();
            System.out.println("Admin registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
