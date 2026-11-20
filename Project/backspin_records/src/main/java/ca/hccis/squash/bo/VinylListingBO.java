package ca.hccis.squash.bo;

import ca.hccis.squash.dao.VinylListingDAO;
import ca.hccis.squash.jpa.entity.VinylListing;
import ca.hccis.squash.util.CisUtilityFile;

import java.util.ArrayList;

/**
 * Business logic for vinyl listings.
 *
 * @author Misha
 * @since 20261002
 */
public class VinylListingBO {

    //Price calculation values (see README - Calculation)
    public static final double BASE_PRICE = 10.00;
    public static final double TAX_RATE = 0.15;

    public static final double MULTIPLIER_SEALED = 2.00;
    public static final double MULTIPLIER_USED = 1.00;

    public static final double MULTIPLIER_BLACK = 1.00;
    public static final double MULTIPLIER_RED = 1.50;
    public static final double MULTIPLIER_WHITE = 2.00;

    public static final double MULTIPLIER_SEVEN_INCH = 1.00;
    public static final double MULTIPLIER_TEN_INCH = 2.00;
    public static final double MULTIPLIER_TWELVE_INCH = 4.00;

    /**
     * Calculate the price of a vinyl listing and store it in the listing.
     * vinylPrice = BASE_PRICE x condition x colour x size, plus tax
     *
     * @param vinylListing the listing to price
     * @return the calculated price (rounded to 2 decimals)
     * @author Misha
     * @since 20261120
     */
    public static double calculateVinylPrice(VinylListing vinylListing) {

        //Condition: sealed (new) or used
        double conditionMultiplier = MULTIPLIER_USED;
        if (Boolean.TRUE.equals(vinylListing.getVinylIsSealed())) {
            conditionMultiplier = MULTIPLIER_SEALED;
        }

        //Colour of the record
        double colourMultiplier = MULTIPLIER_BLACK;
        if ("Red".equals(vinylListing.getVinylColour())) {
            colourMultiplier = MULTIPLIER_RED;
        } else if ("White".equals(vinylListing.getVinylColour())) {
            colourMultiplier = MULTIPLIER_WHITE;
        }

        //Size of the record
        double sizeMultiplier = MULTIPLIER_SEVEN_INCH;
        if ("Ten-inch".equals(vinylListing.getRecordSize())) {
            sizeMultiplier = MULTIPLIER_TEN_INCH;
        } else if ("Twelve-inch".equals(vinylListing.getRecordSize())) {
            sizeMultiplier = MULTIPLIER_TWELVE_INCH;
        }

        double price = BASE_PRICE * conditionMultiplier * colourMultiplier * sizeMultiplier;
        price = price * (1 + TAX_RATE);

        //Round to 2 decimal places (cents)
        price = Math.round(price * 100) / 100.0;

        vinylListing.setVinylPrice(price);
        return price;
    }

    /**
     * Run the seller name report using JDBC and write the results to a file.
     *
     * @param sellerName Full or partial seller name
     * @return list of matching listings
     * @author Misha
     * @since 20261002
     */
    public ArrayList<VinylListing> processSelectAllBySellerName(String sellerName) {

        //**********************************************************************
        // The report requirements state that jdbc is to be used to obtain the
        // data for the report, so the DAO is used here instead of the repository.
        //**********************************************************************
        VinylListingDAO vinylListingDAO = new VinylListingDAO();
        ArrayList<VinylListing> listings = vinylListingDAO.selectAllBySellerName(sellerName);

        //Write the report results to a file in c:\CIS2232\
        CisUtilityFile.writeReportToFile("SellerNameReport", listings);

        return listings;
    }
}