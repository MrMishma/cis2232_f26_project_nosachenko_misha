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