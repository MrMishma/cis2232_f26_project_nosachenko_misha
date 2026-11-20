package ca.hccis.squash.bo;

import ca.hccis.squash.jpa.entity.VinylListing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VinylListingBOTest {

    @Test
    void calculateVinylPrice_sealedBlackTwelveInch() {
        VinylListing vinylListing = new VinylListing();
        vinylListing.setVinylIsSealed(true);
        vinylListing.setVinylColour("Black");
        vinylListing.setRecordSize("Twelve-inch");

        double actual = VinylListingBO.calculateVinylPrice(vinylListing);

        Assertions.assertEquals(92.00, actual);
        Assertions.assertEquals(92.00, vinylListing.getVinylPrice());
    }

    @Test
    void calculateVinylPrice_usedRedTwelveInch() {
        VinylListing vinylListing = new VinylListing();
        vinylListing.setVinylIsSealed(false);
        vinylListing.setVinylColour("Red");
        vinylListing.setRecordSize("Twelve-inch");

        Assertions.assertEquals(69.00, VinylListingBO.calculateVinylPrice(vinylListing));
    }

    @Test
    void calculateVinylPrice_usedBlackSevenInch_lowestPrice() {
        VinylListing vinylListing = new VinylListing();
        vinylListing.setVinylIsSealed(false);
        vinylListing.setVinylColour("Black");
        vinylListing.setRecordSize("Seven-inch");

        Assertions.assertEquals(11.50, VinylListingBO.calculateVinylPrice(vinylListing));
    }

    @Test
    void calculateVinylPrice_sealedWhiteTwelveInch_highestPrice() {
        VinylListing vinylListing = new VinylListing();
        vinylListing.setVinylIsSealed(true);
        vinylListing.setVinylColour("White");
        vinylListing.setRecordSize("Twelve-inch");

        Assertions.assertEquals(184.00, VinylListingBO.calculateVinylPrice(vinylListing));
    }

    @Test
    void calculateVinylPrice_usedWhiteTenInch() {
        VinylListing vinylListing = new VinylListing();
        vinylListing.setVinylIsSealed(false);
        vinylListing.setVinylColour("White");
        vinylListing.setRecordSize("Ten-inch");

        Assertions.assertEquals(46.00, VinylListingBO.calculateVinylPrice(vinylListing));
    }
}