package currency.interfaces;

public interface Auditable {

    int UNITS_PER_KG = 1_000; // 1,000 digital units per physical kg.
    
    /**
     * Retrieves the total amount of this currency currently circulating in the global grid.
     *
     * @return The circulating digital supply as a double.
     */
    double getCirculatingSupply();

    /**
     * Retrieves the total physical weight of the backing asset held in corporate vaults.
     *
     * @return The physical reserve weight in kilograms.
     */
    double getPhysicalReserveWeight();

    /**
     * Runs an economic audit to ensure the digital supply does not exceed the
     * allowable limits dictated by the physical reserves.
     *
     * @return true if the currency is safely backed, false if it is over-leveraged.
     */
    boolean isFullyBacked();
}
