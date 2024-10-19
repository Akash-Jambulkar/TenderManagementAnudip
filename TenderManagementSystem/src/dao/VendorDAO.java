package dao;

import model.Vendor;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendorDAO {

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

    // Validate Vendor Login
    public boolean validateVendor(String username, String password) {
        String sql = "SELECT * FROM Vendor WHERE username = ? AND password = ?";
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Returns true if a match is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Register a new Vendor
    public void registerVendor(Vendor vendor) {
        String sql = "INSERT INTO Vendor (vendor_name, username, password, contact_info) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vendor.getVendorName());    
            ps.setString(2, vendor.getUsername());      
            ps.setString(3, vendor.getPassword());      
            ps.setString(4, vendor.getContactInfo());   
            ps.executeUpdate();
            System.out.println("Vendor registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to place a bid on a tender
    public void placeBid(int tenderId, BigDecimal bidAmount) {
        String sql = "INSERT INTO bid (tender_id, bid_amount) VALUES (?, ?)";
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tenderId);
            ps.setBigDecimal(2, bidAmount);
            ps.executeUpdate();
            System.out.println("Bid placed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to view the status of a bid for a specific tender
    public void viewBidStatus(int tenderId) {
        String sql = "SELECT status FROM bid WHERE tender_id = ?";
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tenderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String status = rs.getString("status");
                System.out.println("Bid Status for Tender ID " + tenderId + ": " + status);
            } else {
                System.out.println("No bid found for Tender ID " + tenderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to view the bid history for the logged-in vendor
    public void viewBidHistory() {
        String sql = "SELECT tender_id, bid_amount, status FROM bid";
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<String> bidHistory = new ArrayList<>();
            while (rs.next()) {
                int tenderId = rs.getInt("tender_id");
                BigDecimal bidAmount = rs.getBigDecimal("bid_amount");
                String status = rs.getString("status");
                bidHistory.add("Tender ID: " + tenderId + ", Bid Amount: " + bidAmount + ", Status: " + status);
            }
            if (bidHistory.isEmpty()) {
                System.out.println("No bid history found.");
            } else {
                bidHistory.forEach(System.out::println);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
