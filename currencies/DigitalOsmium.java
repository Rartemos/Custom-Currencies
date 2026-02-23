package currency.currencies;

import currency.BaseCurrency;
import currency.interfaces.Auditable;

import java.security.MessageDigest; 
import java.util.Base64;

/**
 * Highest tier Innovation Inc. cryptocurrency.
 * Digital metric for osmium assets.
 * Used for confidential purchases, security, and black projects.
 */
public class DigitalOsmium extends BaseCurrency implements Auditable {

    private static double dOsCirculatingSupply = 80_000_000.00;
    private static double osmiumReserveWeightKg = 80_000;

    private StringBuilder encryptedLog = new StringBuilder();

    /**
     * Creates a Digital Osmium account with the specified balance.
     * The exchange rate is fixed at §1,250.00
     *
     * @param balance The initial amount of dOs.
     */
    public DigitalOsmium(double balance) {
        super("dOs", "Digital Osmium", balance, 1_250.0);
    }

    /**
     * Executes a Black Ops transfer. The details are hashed before being logged.
     * 
     * @param targetCode The encrypted ID of the receiver.
     * @param amount The amount of dOs to transfer.
     * @return true if successful
     */
    public boolean executeCovertTransfer(String targetCode, double amount) {
        System.out.println("\n--- Initiating Covert Protocol ---");

        // Verify sufficient funds
        if (this.balance < amount) {
            System.out.println("[dOs] Protocol Failed: Insufficient funds.\n");
            return false;
        }

        // Transaction execution
        this.balance -= amount; 

        String rawData = "TARGET: " + targetCode + " | AMOUNT: " + amount + " | TIMESTAMP:" + System.currentTimeMillis(); // Creates sensitive raw data string
        String txHash = generateSHA256Hash(rawData); // Hashes raw data

        this.encryptedLog.append("\n[TX_HASH] ").append(txHash); // Saves only the hash to the ledger

        System.out.println("[dOs] SUCCESS: Transfer complete. Ledger appended with cryptographic hash.\n");
        return true;
    }

    /**
     * A private helper method that handles the complex math of SHA-256 hashing.
     */
    private String generateSHA256Hash(String data) {
        try {
            
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); // Gets the SHA-256 algorithm
            byte[] hashBytes = digest.digest(data.getBytes("UTF-8")); // Converts string into bytes, then hashes it
            
            return Base64.getEncoder().encodeToString(hashBytes); // Converts bytes to readable Base64 string
        
        } catch (Exception e) {
            return "\nENCRYPTION_ERROR_" + System.currentTimeMillis();
        }
    }

    @Override
    public String getTransactionHistory() {
        return "\n--- CONFIDENTIAL LEDGER ---\n" + this.encryptedLog.toString();
    }

    @Override
    public void displayConnection(String code, boolean bankServerOnline) {

        if (bankServerOnline) {
            System.out.println("[" + code + "] Connection to Private Network verified...");

        } else {
            System.out.println("[" + code + "] Connection to Private Network failed...");
        }
    }

    @Override
    public String getOperationalSector() {
        return "Confidential & Black Projects"; 
    }

    @Override
    public double calculateTax() {
        return 0.0; // Confidential operations are tax-free
    }

    @Override
    public boolean isConfidential() {
        return true; // Confidential operations are private
    }

    @Override
    public int getClearanceLevel() {
        return 5; // Highest access level
    }


    @Override
    public double getCirculatingSupply() {
        return dOsCirculatingSupply;
    }

    @Override
    public double getPhysicalReserveWeight() {
        return osmiumReserveWeightKg;
    }

    @Override
    public boolean isFullyBacked() {
        return dOsCirculatingSupply <= (osmiumReserveWeightKg * UNITS_PER_KG);
    }
}

