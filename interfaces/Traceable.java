package currency.interfaces;

public interface Traceable {

    /**
     * Retrieves the full ledger history for compliance checks.
     * @return A formatted String of all past transactions.
     */
    String getTransactionHistory();
}
