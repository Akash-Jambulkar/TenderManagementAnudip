package dao;

import java.math.BigDecimal;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BidDAO {

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

    // View bids for a specific tender
    public void viewBidsForTender(int tenderId) {
        String sql = "SELECT b.bid_id, v.vendor_name, b.bid_amount, b.status FROM Bid b JOIN Vendor v ON b.vendor_id = v.vendor_id WHERE b.tender_id = ?";
        
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tenderId);
            ResultSet rs = ps.executeQuery();
            
            System.out.println("Bids for Tender ID: " + tenderId);
            while (rs.next()) {
                int bidId = rs.getInt("bid_id");
                String vendorName = rs.getString("vendor_name");
                BigDecimal bidAmount = rs.getBigDecimal("bid_amount");
                String status = rs.getString("status");
                
                System.out.println("Bid ID: " + bidId + ", Vendor: " + vendorName + ", Amount: " + bidAmount + ", Status: " + status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
