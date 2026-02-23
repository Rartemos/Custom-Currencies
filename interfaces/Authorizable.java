package currency.interfaces;

public interface Authorizable {
    
    /**
     * Returns the security clearance level granted by holding this asset.
     * Level 1 (Standard) to Level 5 (Executive).
     */
    int getClearanceLevel();
}
