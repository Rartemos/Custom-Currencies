package currency;

import currency.currencies.*;
import currency.interfaces.*;
import java.util.ArrayList;
import java.util.List;

public class LedgerSystem {
    public static void main(String[] args) {

        final boolean isBankServerOnline = true;
        
        List<Valuable> myPortfolio = new ArrayList<>();

        // Assets
        EuroDollar myCash = new EuroDollar(1_000_000);
        DigitalCopper myCopper = new DigitalCopper(10_000_000);
        DigitalSilver mySilver = new DigitalSilver(2_500_000);
        DigitalGold myGold = new DigitalGold(850_000);
        DigitalPlatinum myPlatinum = new DigitalPlatinum(50_000);
        DigitalOsmium myOsmium = new DigitalOsmium(5_000);

        myPortfolio.add(myCash);
        myPortfolio.add(myCopper);
        myPortfolio.add(mySilver);
        myPortfolio.add(myGold);
        myPortfolio.add(myPlatinum);
        myPortfolio.add(myOsmium);
        
        displayPortfolioConnection(myPortfolio, isBankServerOnline);
        displayPortfolioValue(myPortfolio);

        // Give the wallet a starting balance of 5,000 dPt
        DigitalPlatinum corporateWallet = new DigitalPlatinum(5000.0);
        
        // Scenario 1: Successful B2B Contract
        // Transfers 1000 dPt. Fee will be 20 dPt (2%). Total deducted: 1020 dPt.
        corporateWallet.executeSmartContract("ENT-8921A", 1000.0, "Militech Logistics");

        // Scenario 2: Failed validation (Wrong ID format)
        corporateWallet.executeSmartContract("SUB-4412", 500.0, "Orbital Air");
    }

    /**
     * Displays the server connection status.
     * @param myPortfolio The list of assets in the portfolio.
     * @param isBankServerOnline The bank server status. Either online or offline.
     */
    public static void displayPortfolioConnection(List<Valuable> myPortfolio, boolean isBankServerOnline) {

        System.out.println("\n--- Server Connection Status ---\n");

        for (Valuable asset : myPortfolio) {
            if (asset instanceof BaseCurrency) {
                ((BaseCurrency) asset).displayConnection(asset.getIsoCode(), isBankServerOnline);
            }
        }

        System.out.println("\n--- Server Connection Complete ---\n");
    }

    /**
     * Displays the portfolio, its contents, and the total net worth.
     * @param myPortfolio The list of assets in the portfolio.
     */
    public static void displayPortfolioValue(List<Valuable> myPortfolio) {

            System.out.println("\n--- Generating Portfolio Report ---\n");
        
            double totalNetWorth = 0;
            
            for (Valuable asset : myPortfolio) {
                double value = asset.convertToEurodollars();
                System.out.println("Item Value: " + value + " Credits");
                totalNetWorth += value;
            }
            
            System.out.println("\n-----------------------------------\n");
            System.out.println("TOTAL NET WORTH: " + totalNetWorth + " Credits\n");
    }
}
