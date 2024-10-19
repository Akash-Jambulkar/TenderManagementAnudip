package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TenderDAO {

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

    // Create new tender
    public void createTender(String description, BigDecimal budget, LocalDate deadline) {
        String sql = "INSERT INTO Tender (description, budget, deadline, status) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, description);
            ps.setBigDecimal(2, budget);
            ps.setDate(3, java.sql.Date.valueOf(deadline));
            ps.setString(4, "Open");  // Default status for a new tender
            
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Tender created successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View all tenders
    public void viewAllTenders() {
        String sql = "SELECT * FROM Tender";
        
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            System.out.println("Available Tenders:");
            while (rs.next()) {
                int tenderId = rs.getInt("tender_id");
                String description = rs.getString("description");
                BigDecimal budget = rs.getBigDecimal("budget");
                LocalDate deadline = rs.getDate("deadline").toLocalDate();
                String status = rs.getString("status");
                
                System.out.println("Tender ID: " + tenderId + ", Description: " + description + ", Budget: " + budget + ", Deadline: " + deadline + ", Status: " + status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Assign tender to a vendor
    public void assignTenderToVendor(int tenderId, int vendorId) {
        String sql = "UPDATE Tender SET assigned_vendor_id = ?, status = 'Assigned' WHERE tender_id = ?";
        
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, vendorId);
            ps.setInt(2, tenderId);
            
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Tender assigned to Vendor ID: " + vendorId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
