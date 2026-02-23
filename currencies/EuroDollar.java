package currency.currencies;

import currency.BaseCurrency;

/**
 * Global currency system. Combined USD & Euros.
 * Standard Fiat currency by the Central Bank.
 */
public class EuroDollar extends BaseCurrency {
    
    /**
     * Creates a Eurodollar (ESD) account with the specified balance.
     * The value of §1.00 ESD = $2.00 USD.
     *
     * @param balance The initial amount of § ESD.
     */
    public EuroDollar(double balance) {
        super("ESD", "Eurodollar", balance, 1.0);
    }

    /**
     * Pays taxes to external governments
     * @param regionCode The code of the government (e.g., "NA_GOV", "EU_CENTRAL").
     * @param amount The tax amount to pay.
     */
    public void payTax(String regionCode, double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            String log = "External Tax Payment: " + amount + " ESD sent to [" + regionCode + "].";

            this.localLedger.add(log); 
            System.out.println("[ESD] " + log);

        } else {
            System.out.println("[ESD] Tax Payment Failed: Insufficient Funds.");
        }
    }

    /**
     * Converts ESD into corporate scrip (e.g., buying Digital Copper).
     * @param targetTicker The ticker of the asset to buy.
     * @param amount The amount of ESD to spend.
     */
    public void buyAsset(String targetTicker, double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            String log = "Conversion Initiated: " + amount + " ESD -> " + targetTicker;

            this.localLedger.add(log);
            System.out.println("[ESD] " + log);
        }
    }

    /**
     * Simulates a verification check against a central banking database.
     * 
     * @param bankServerOnline The bank server status. Must be online or offline.
     */
    
    @Override
    public void displayConnection(String code, boolean bankServerOnline) {

        if (bankServerOnline) {
            System.out.println("[" + code + "] Connection to Central Bank verified...");

        } else {
            System.out.println("[" + code + "] Connection to Central Bank failed...");
        }
    }

    @Override
    public String getOperationalSector() {
        return "Public Economy & External Markets";
    }

    @Override
    public double calculateTax() {
        return 0.0; // No internal corporate tax 
    }

    @Override
    public boolean isConfidential() {
        return false; // Fiat is publicly tracked by Central Banks
    }

    @Override
    public int getClearanceLevel() {
        return 0; // Public Access (Level 0)
    }
}
