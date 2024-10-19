package app;

import dao.AdminDAO;
import dao.TenderDAO;
import dao.BidDAO;
import dao.VendorDAO;
import model.Admin;
import model.Vendor;  // Assuming you have Admin and Vendor models

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class TenderManagementApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminDAO adminDAO = new AdminDAO();
        TenderDAO tenderDAO = new TenderDAO();
        BidDAO bidDAO = new BidDAO();
        VendorDAO vendorDAO = new VendorDAO();

        System.out.println("Welcome to the Tender Management System");

        // Choose between Admin or Vendor
        System.out.println("Select your role:");
        System.out.println("1. Admin");
        System.out.println("2. Vendor");
        System.out.print("Enter your choice: ");

        int roleChoice = Integer.parseInt(scanner.nextLine());

        if (roleChoice == 1) {
            // Admin Login
            System.out.print("Enter Admin Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Admin Password: ");
            String password = scanner.nextLine();

            if (adminDAO.validateAdmin(username, password)) {
                System.out.println("Admin validated.");
                boolean running = true;

                while (running) {
                    System.out.println("\nChoose an action:");
                    System.out.println("1. Create a new Tender");
                    System.out.println("2. View all Tenders");
                    System.out.println("3. View Bids for a Tender");
                    System.out.println("4. Assign Tender to Vendor");
                    System.out.println("5. Register a New Admin");
                    System.out.println("6. Register a New Vendor");
                    System.out.println("7. Exit");
                    System.out.print("Enter your choice: ");

                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            // Create a new tender
                            System.out.print("Enter Tender Description: ");
                            String description = scanner.nextLine();
                            System.out.print("Enter Tender Budget: ");
                            BigDecimal budget = new BigDecimal(scanner.nextLine());
                            System.out.print("Enter Tender Deadline (yyyy-MM-dd): ");
                            LocalDate deadline = LocalDate.parse(scanner.nextLine());
                            tenderDAO.createTender(description, budget, deadline);
                            break;
                        case 2:
                            // View all tenders
                            tenderDAO.viewAllTenders();
                            break;
                        case 3:
                            // View bids for a specific tender
                            System.out.print("Enter Tender ID to view bids: ");
                            int tenderId = Integer.parseInt(scanner.nextLine());
                            bidDAO.viewBidsForTender(tenderId);
                            break;
                        case 4:
                            // Assign tender to a vendor
                            System.out.print("Enter Tender ID: ");
                            int assignTenderId = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter Vendor ID: ");
                            int vendorId = Integer.parseInt(scanner.nextLine());
                            tenderDAO.assignTenderToVendor(assignTenderId, vendorId);
                            break;
                        case 5:
                            // Register a new admin
                            System.out.print("Enter New Admin Username: ");
                            String newAdminUsername = scanner.nextLine();
                            System.out.print("Enter New Admin Password: ");
                            String newAdminPassword = scanner.nextLine();

                            Admin newAdmin = new Admin();
                            newAdmin.setUsername(newAdminUsername);
                            newAdmin.setPassword(newAdminPassword);
                            adminDAO.registerAdmin(newAdmin);
                            System.out.println("New admin registered successfully.");
                            break;
                        case 6:
                            // Register a new vendor
                            System.out.print("Enter New Vendor Username: ");
                            String newVendorUsername = scanner.nextLine();
                            System.out.print("Enter New Vendor Password: ");
                            String newVendorPassword = scanner.nextLine();

                            Vendor newVendor = new Vendor();
                            newVendor.setUsername(newVendorUsername);
                            newVendor.setPassword(newVendorPassword);
                            vendorDAO.registerVendor(newVendor);
                            System.out.println("New vendor registered successfully.");
                            break;
                        case 7:
                            running = false; // Exit the loop
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid admin credentials.");
            }

        } else if (roleChoice == 2) {
            // Vendor Login
            System.out.print("Enter Vendor Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Vendor Password: ");
            String password = scanner.nextLine();

            if (vendorDAO.validateVendor(username, password)) {
                System.out.println("Vendor validated.");
                boolean running = true;

                while (running) {
                    System.out.println("\nChoose an action:");
                    System.out.println("1. View all Tenders");
                    System.out.println("2. Place a Bid");
                    System.out.println("3. View Bid Status");
                    System.out.println("4. View Bid History");
                    System.out.println("5. Exit");
                    System.out.print("Enter your choice: ");

                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            // View all tenders
                            tenderDAO.viewAllTenders();
                            break;
                        case 2:
                            // Place a bid
                            System.out.print("Enter Tender ID: ");
                            int tenderId = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter Bid Amount: ");
                            BigDecimal bidAmount = new BigDecimal(scanner.nextLine());
                            vendorDAO.placeBid(tenderId, bidAmount);
                            break;
                        case 3:
                            // View status of a specific bid
                            System.out.print("Enter Tender ID: ");
                            int checkTenderId = Integer.parseInt(scanner.nextLine());
                            vendorDAO.viewBidStatus(checkTenderId);
                            break;
                        case 4:
                            // View bid history
                            vendorDAO.viewBidHistory();
                            break;
                        case 5:
                            running = false; // Exit the loop
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid vendor credentials.");
            }
        } else {
            System.out.println("Invalid role selection.");
        }

        scanner.close();
    }
}
