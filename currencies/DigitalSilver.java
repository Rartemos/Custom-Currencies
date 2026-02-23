package currency.currencies;

import currency.BaseCurrency;
import currency.interfaces.Auditable;

/**
 * Advanced Innovation Inc. cryptocurrency.
 * Digital metric for silver assets.
 * Used for R&D purchases, software licensing, and advanced equipment.
 */
public class DigitalSilver extends BaseCurrency implements Auditable {

    private static double dAgCirculatingSupply = 65_000_000_000.00;
    private static double silverReserveWeightKg = 65_000_000;

    /**
     * Creates a Digital Silver account with the specified balance.
     * The exchange rate is §1.40
     *
     * @param balance The initial amount of dAg.
     */
    public DigitalSilver(double balance) {
        super("dAg", "Digital Silver", balance, 1.40);
    }

    @Override
    public String getOperationalSector() {
        return "\nOperational Sector [dAg]: R&D, Licensing & Advanced Equipment\n";
    }

    @Override
    public double calculateTax() {
        return 0.01; // 1% minimal processing fee
    }

    @Override
    public boolean isConfidential() {
        return false; // Publicly tracked
    }

    @Override
    public int getClearanceLevel() {
        return 2; // Research & enhanced employee access
    }

    @Override
    public double getCirculatingSupply() {
        return dAgCirculatingSupply;
    }

    @Override
    public double getPhysicalReserveWeight() {
        return silverReserveWeightKg;
    }

    @Override
    public boolean isFullyBacked() {
        return dAgCirculatingSupply <= (silverReserveWeightKg * UNITS_PER_KG);
    }
}

