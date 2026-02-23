package currency.interfaces;

/**
 * Represents any asset in the Global Ledger System that has a calculable monetary value.
 *
 * This interface allows the system to treat disparate assets uniformly for net worth calculations.
 */

public interface Valuable {
    
    /**
     * Calculates the current market value of the asset in credits
     * 
     * @return The total value of the asset as a double.
     */
    public double convertToEurodollars();

    /**
     * Retrieves the ISO code of a currency.
     * 
     * @return The ISO code of the currency.
     */
    public String getIsoCode();
}
