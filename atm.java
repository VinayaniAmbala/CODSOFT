import java.util.Scanner;

class ATM {
    private Account account;
    private String username;
    private String password;

    public ATM() {
        
    }

    public void setupUsernamePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a new username: ");
        username = scanner.nextLine();
        System.out.print("Enter a new password: ");
        password = scanner.nextLine();
        System.out.print("Enter the amount in your account: $");
        double initialBalance = getValidAmount();  
        account = new Account(initialBalance);  
        System.out.println("Username, password, and amount is set up successfully!");
    }

    public boolean authenticateUser() {
        Scanner scanner = new Scanner(System.in);
        String enteredUsername;
        String enteredPassword;
        int attempts = 0;

        while (attempts < 3) {
            System.out.print("Enter username: ");
            enteredUsername = scanner.nextLine();
            System.out.print("Enter password: ");
            enteredPassword = scanner.nextLine();

            if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                return true; 
            } else {
                attempts++;
                System.out.println("Incorrect username or password. You have " + (3 - attempts) + " attempts left.");
            }
        }
        return false; 
    }

    public void displayMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Please choose an option (1-4): ");
    }

    public void processChoice(int choice) {
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case 1: 
                System.out.print("Enter amount to withdraw: $");
                double withdrawAmount = getValidAmount(); 
                if (withdrawAmount > 0) {
                    if (withdrawAmount <= account.getBalance()) {
                        if (account.withdraw(withdrawAmount)) {
                            System.out.println("Withdrawal successful! Remaining balance: $" + account.getBalance());
                        }
                    } else {
                        System.out.println("Insufficient funds. Your current balance is: $" + account.getBalance());
                    }
                } else {
                    System.out.println("Withdrawal amount must be positive. Please try again.");
                }
                break;
            case 2: 
                System.out.print("Enter amount to deposit: $");
                double depositAmount = getValidAmount(); 
                if (depositAmount > 0) {
                    if (account.deposit(depositAmount)) {
                        System.out.println("Deposit successful! New balance: $" + account.getBalance());
                    }
                } else {
                    System.out.println("Deposit amount must be positive. Please try again.");
                }
                break;
            case 3: 
                System.out.println("Your current balance is: $" + account.getBalance());
                break;
            case 4: 
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");

        
        setupUsernamePassword();

        
        if (authenticateUser()) {
            System.out.println("Login successful!");

            int userChoice;
            while (true) {
                displayMenu();
                userChoice = getValidChoice(); 
                if (userChoice == 4) {
                    processChoice(userChoice); 
                    break;
                }
                processChoice(userChoice); 
            }
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }

    public int getValidChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice < 1 || choice > 4) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid option. Please choose a valid option between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
            }
        }

        return choice;
    }

    public double getValidAmount() {
        Scanner scanner = new Scanner(System.in);
        double amount = -1;

        while (amount <= 0) {
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) {
                    System.out.println("Please enter a positive amount.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return amount;
    }

    public static void main(String[] args) {
        ATM atm = new ATM();  
        atm.start();  
    }
}

class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false; 
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false; 
    }

    public double getBalance() {
        return balance;
    }
}
