package ca.hccis.squash.entity;

import ca.hccis.squash.jpa.entity.VinylListing;
import java.util.ArrayList;

/**
 * Entity class to hold the input and results of the vinyl reports.
 *
 * @author Misha
 * @since 20261002
 */
public class ReportVinyl {
    private String sellerName;
    private ArrayList<VinylListing> vinylListings;

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public ArrayList<VinylListing> getVinylListings() {
        return vinylListings;
    }

    public void setVinylListings(ArrayList<VinylListing> vinylListings) {
        this.vinylListings = vinylListings;
    }
}