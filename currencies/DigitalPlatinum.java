package currency.currencies;

import currency.BaseCurrency;
import currency.interfaces.Auditable;

/**
 * Enterprise Innovation Inc. cryptocurrency.
 * Digital metric for Platinum assets.
 * Used for enterprise purchases, infrastructure, and logistics.
 */
public class DigitalPlatinum extends BaseCurrency implements Auditable {

    private static double dPtCirculatingSupply = 800_000_000.00;
    private static double platinumReserveWeightKg = 800_000;

    /**
     * Creates a Digital Platinum account with the specified balance.
     * The exchange rate is §50.00
     *
     * @param balance The initial amount of dPt.
     */
    public DigitalPlatinum(double balance) {
        super("dPt", "Digital Platinum", balance, 50.0);
    }

    public boolean executeSmartContract(String contractId, double amount, String destinationId) {
        System.out.println("\n--- Initiating Smart Contract Protocol ---");
        System.out.println("[dPt] Initializing Smart Contract: " + contractId);

        double fee = amount * calculateTax();
        double totalCost = amount + fee;

        // Verify sufficient funds
        if (this.balance < totalCost) {
            System.out.println("[dPt] Contract Failed: Insufficient funds.");
            System.out.println("      Requires " + totalCost + " dPt (including " + fee + " dPt fee).");
            return false;
        }

        // Checking contract validity
        if (!contractId.startsWith("ENT-")) {
            System.out.println("[dPt] Contract Failed: Invalid enterprise contract ID format. Must begin with 'ENT-'.");
            return false;
        }

        // Transfer execution
        this.balance -= totalCost;
        String logEntry = "Contract [" + contractId + "] executed. " + amount + " dPt transferred to " + destinationId + ". Tax Paid: " + fee + " dPt.";

        System.out.println("[dPt] SUCCESS: " + logEntry);
        System.out.println("[dPt] New Balance: " + this.balance + " dPt");

        // Logs the transaction in the local ledger
        this.localLedger.add(logEntry);
        return true;
    }

    @Override
    public String getOperationalSector() {
        return "\nOperational Sector [dPt]: Enterprise & Contract Purchases\n";
    }

    @Override
    public double calculateTax() {
        return 0.02; // 2% contract fee
    }

    @Override
    public boolean isConfidential() {
        return false; // Publicly tracked
    }

    @Override
    public int getClearanceLevel() {
        return 4; // Enterprise contract access
    }

    @Override
    public double getCirculatingSupply() {
        return dPtCirculatingSupply;
    }

    @Override
    public double getPhysicalReserveWeight() {
        return platinumReserveWeightKg;
    }

    @Override
    public boolean isFullyBacked() {
        return dPtCirculatingSupply <= (platinumReserveWeightKg * UNITS_PER_KG);
    }
}

