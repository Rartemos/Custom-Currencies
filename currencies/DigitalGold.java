package currency.currencies;

import currency.BaseCurrency;
import currency.interfaces.Auditable;

/**
 * Premium Innovation Inc. cryptocurrency.
 * Digital metric for gold assets.
 * Used for specialized purchases.
 */
public class DigitalGold extends BaseCurrency implements Auditable {

    private static double dAuCirculatingSupply = 8_133_000_000.00;
    private static double goldReserveWeightKg = 8_133_000;

    /**
     * Creates a Digital Gold account with the specified balance.
     * The exchange rate is §83.50
     *
     * @param balance The initial amount of dAu.
     */
    public DigitalGold(double balance) {
        super("dAu", "Digital Gold", balance, 83.50);
    }

    @Override
    public String getOperationalSector() {
        return "\nOperational Sector [dAu]: Specialized purchases & exclusive equipment\n";
    }

    @Override
    public double calculateTax() {
        return 0.015; // 1.5% specialized processing fee
    }

    @Override
    public boolean isConfidential() {
        return false; // Publicly tracked
    }

    @Override
    public int getClearanceLevel() {
        return 3; // Specialized employee access
    }

    @Override
    public double getCirculatingSupply() {
        return dAuCirculatingSupply;
    }

    @Override
    public double getPhysicalReserveWeight() {
        return goldReserveWeightKg;
    }

    @Override
    public boolean isFullyBacked() {
        return dAuCirculatingSupply <= (goldReserveWeightKg * UNITS_PER_KG);
    }
}

