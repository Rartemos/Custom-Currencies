package currency.currencies;

import currency.BaseCurrency;
import currency.interfaces.Auditable;

/**
 * Standard Innovation Inc. cryptocurrency.
 * Digital metric for copper assets.
 * Used for standard purchases, daily operations, and maintenance.
 */
public class DigitalCopper extends BaseCurrency implements Auditable {

    private static double dCuCirculatingSupply = 534_000_000_000.00;
    private static double copperReserveWeightKg = 534_000_000;

    /**
     * Creates a Digital Copper account with the specified balance.
     * The exchange rate is §0.60
     *
     * @param balance The initial amount of dCu.
     */
    public DigitalCopper(double balance) {
        super("dCu", "Digital Copper", balance, 0.60);
    }

    /**
     * Automated logic for daily maintenance budgets.
     */
    public void autoReplenish(boolean isServerOnline) {
        if (this.balance < 50) {
            System.out.println("[dCu] Balance low. Auto-requesting maintenance grant...");
            this.deposit(100, isServerOnline); 
        }
    }

    @Override
    public String getOperationalSector() {
        return "\nOperational Sector [dCu]: Standard Purchases, Daily Operations & Maintenance\n";
    }

    @Override
    public double calculateTax() {
        return 0.0; // No Tax
    }

    @Override
    public boolean isConfidential() {
        return false; // Publicly tracked
    }

    @Override
    public int getClearanceLevel() {
        return 1; // Entry-level employee access
    }

    @Override
    public double getCirculatingSupply() {
        return dCuCirculatingSupply;
    }

    @Override
    public double getPhysicalReserveWeight() {
        return copperReserveWeightKg;
    }

    @Override
    public boolean isFullyBacked() {
        return dCuCirculatingSupply <= (copperReserveWeightKg * UNITS_PER_KG);
    }
}
