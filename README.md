# Banking Management System

A comprehensive Java-based banking management system with GUI interface.

## Features

### Account Management
- Multiple Account Types Support:
  - Savings Accounts (with interest rates)
  - Credit Accounts (with credit limits)
  - Foreign Currency Accounts (with exchange rates)
  - Bank Certificates (with maturity dates)
- Account Operations:
  - Create new accounts
  - View account details
  - Calculate total balance
  - Initiate transfers
  - View transaction history

### Transaction Management
- Transaction Types:
  - Deposits
  - Withdrawals
  - Transfers between accounts
  - Currency conversions
- Transaction logging and history tracking
- Real-time balance updates

### User Management
- Multiple user roles:
  - Customers
  - Staff
  - Managers
- Profile management
- Authentication and authorization
- Status tracking (active/blocked)

### Additional Features
- Interest rate calculations
- Foreign currency exchange
- Bank certificates management
- Loan application processing
- System audit logging
- Transaction fee handling

## Project Structure

### Core Classes
- `Account.java`: Base account functionality
- `SavingAccount.java`: Savings account with interest
- `CreditAccount.java`: Credit account management
- `ForeignCurrencyAccount.java`: Foreign currency handling
- `BankCertificate.java`: Certificate management
- `Transaction.java`: Transaction processing
- `User.java`: User management
- `Staff.java`: Staff operations
- `LoanApplication.java`: Loan processing

### Data Management
- File-based storage for:
  - User accounts
  - Transactions
  - System logs
  - Configurations

## Requirements

- Java Development Kit (JDK) 8 or higher

## Setup

1. Clone the repository
2. Open the project in NetBeans IDE
3. Build and run the project from the `Main.java` file

## Usage

1. Launch the application
2. Log in with appropriate credentials
3. Use the GUI to:
   - Manage accounts
   - Process transactions
   - View account details
   - Handle loan applications
   - Generate reports

## Security Features

- Password hashing for protection
- Role-based access control
- Transaction logging
- Audit trail maintenance

## Contributors

- Yassin Bedier
- Ibrahim Labib
- Islam Akram
- Ahmed Hatem
- Ahmed Khattab

## License

This project is licensed under the MIT License - see the LICENSE file for details
