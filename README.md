# Tender Management System

The **Tender Management System** is a Java-based project that allows companies to efficiently manage the tendering process. It facilitates the creation of tenders, bidding by vendors, and selection of the most suitable bids, simplifying the overall procurement workflow.

## Features

### Administrator Role:
1. **Login**: Administrators can log in to their account.
2. **Vendor Management**: 
   - Register new vendors with unique usernames and passwords.
   - View the list of all registered vendors.
3. **Tender Management**:
   - Create new tenders with relevant details.
   - View the list of all tenders.
   - View all bids placed against each tender.
   - Assign a tender to the most suitable vendor.
  
### Vendor Role:
1. **Login**: Vendors can log in using credentials provided by the administrator.
2. **Tender Management**:
   - View all available tenders.
   - Place a bid against a tender (one bid per tender).
   - Track bid status (whether their bid was selected or not).
   - View the history of all their previous bids.

## Technologies Used
- **Java**: Core development language.
- **JDBC**: For database connectivity.
- **MySQL**: For database management.

## Database Schema
- **Vendor Table**: Stores vendor details like vendor ID, name, and login credentials.
- **Tender Table**: Stores tender details like tender ID, description, and status.
- **Bid Table**: Stores bid details such as bid ID, vendor ID, tender ID, bid amount, and selection status.

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/Akash-Jambulkar/TenderManagementAnudip.git
   ```
2. Import the project into your favorite IDE (e.g., Eclipse or IntelliJ IDEA).
3. Set up the MySQL database using the provided SQL scripts.
4. Update the database configuration in the project (JDBC URL, username, password).
5. Run the project.

## How to Use
- **Administrator**:
   - Log in using the admin credentials.
   - Manage vendors, tenders, and bids via the admin dashboard.
  
- **Vendor**:
   - Log in with the credentials provided by the administrator.
   - View current tenders and place bids.
   - Track bid status and view bid history.
