import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice = 0;
        
        System.out.println("===================================");
        System.out.println("       Welcome to the ATM          ");
        System.out.println("===================================");

        while (choice != 4) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Please select an option (1-4): ");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please select a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); 
            }
        }
        scanner.close();
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f\n", account.getBalance());
    }

    private void deposit() {
        System.out.print("Enter the amount you wish to deposit: $");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            if (account.deposit(amount)) {
                System.out.printf("Successfully deposited: $%.2f\n", amount);
                System.out.printf("New balance: $%.2f\n", account.getBalance());
            } else {
                System.out.println("Transaction failed. Please enter a valid positive amount.");
            }
        } else {
            System.out.println("Invalid input. Please enter a numerical amount.");
            scanner.next(); 
        }
    }

    private void withdraw() {
        System.out.print("Enter the amount you wish to withdraw: $");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            if (amount <= 0) {
                System.out.println("Transaction failed. Please enter a valid positive amount.");
            } else if (account.withdraw(amount)) {
                System.out.printf("Successfully withdrew: $%.2f\n", amount);
                System.out.printf("Remaining balance: $%.2f\n", account.getBalance());
            } else {
                System.out.println("Transaction failed. Insufficient funds.");
            }
        } else {
            System.out.println("Invalid input. Please enter a numerical amount.");
            scanner.next(); 
        }
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.00);
        ATM atmInterface = new ATM(userAccount);
        atmInterface.displayMenu();
    }
}
