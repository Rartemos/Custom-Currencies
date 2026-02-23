package currency;

import currency.interfaces.*;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract base class representing all forms of monetary currency.
 */
public abstract class BaseCurrency implements Valuable, Authorizable, Traceable {

    protected String isoCode;
    protected String name;
    protected double balance;
    protected double exchangeRate;

    protected List<String> localLedger = new ArrayList<>();

    /**
     * Constructs a new Currency account.
     *
     * @param isoCode      The 3-letter currency code.
     * @param name         The common name of the currency.
     * @param balance      The initial starting balance.
     * @param exchangeRate The value of 1 unit of this currency in Credits.
     */
    public BaseCurrency(String isoCode, String name, double balance, double exchangeRate) {
        this.isoCode = isoCode;
        this.name = name;
        this.balance = balance;
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String getTransactionHistory() {
        if (localLedger.isEmpty()) {
            return "[" + isoCode + "] Ledger is empty.\n";
        }

        return "[" + isoCode + "] STANDARD LEDGER:\n" + String.join("\n", localLedger);
    }

    /**
     * Adds funds to the account balance.
     * 
     * @param amount The amount of currency to add. Must be positive.
     * @param bankServerOnline The bank server status. Must be online or offline.
     */
    public void deposit(double amount, boolean bankServerOnline) {
        if (amount > 0 && bankServerOnline) {

            double tax = amount * calculateTax();
            double finalAmount = amount - tax;
            this.balance += finalAmount;

            System.out.println("\nDeposited " + finalAmount + " " + isoCode + " (Tax: " + tax + ")");
            System.out.println("New Balance: " + this.balance + " " + isoCode + "\n");

            // Automatically logs deposit to the local ledger
            String log = "Deposited " + amount + " " + isoCode + " at " + System.currentTimeMillis();
            this.localLedger.add(log);

        } else if (amount <= 0) {
            System.out.println("Deposit failed: Amount must be greater than 0.");
            
        } else {
            System.out.println("Deposit failed: Transaction request could not be verified.");
        }
    }

    /**
     * Calculates the value of the holding by multiplying the balance by the exchange rate.
     * 
     * @return The total value in ESD.
     */
    public double convertToEurodollars() {
        return this.balance * this.exchangeRate;
    }
    
    /**
     * Retrieves the ISO Code of a currency.
     * @return The ISO Code of the currency.
     */
    public String getIsoCode() {
        return this.isoCode;
    }

    /**
     * Displays the server connection status.
     * 
     * @param code The ISO code of the currency.
     * @param bankServerOnline The bank server status. Either online or offline.
     */
    public void displayConnection(String code, boolean bankServerOnline) {
        if (bankServerOnline) {
            System.out.println("[" + code + "] Connection to Server Network verified...");

        } else {
            System.out.println("[" + code + "] Connection to Server Network failed...");
        }
    }

    /**
     * Defines which corporate sector uses this currency.
     */
    public abstract String getOperationalSector();

    /**
     * Defines the corporate tax rate for transfers.
     */
    public abstract double calculateTax();

    /**
     * Determines if transactions should be hidden from the public global ledger.
     */
    public abstract boolean isConfidential();
}

