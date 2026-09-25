package ca.hccis.backspin.entity;

import ca.hccis.backspin.util.CisUtility;

/** Stores one vinyl order */
public class Order {
    private int orderNumber;
    private String sellerName;
    private String customerName;
    private String recordName;
    private int releaseYear;
    private boolean vinylIsSealed;
    private String vinylColour;
    private String vinylGenre;
    private int recordSize;
    public Order() {
    }

    public void getInformation() {
        orderNumber = CisUtility.getInputInt("Order number:");
        getRecordDetails();
    }

    public void getRecordDetails() {
        sellerName = CisUtility.getInputString("Seller name:");
        customerName = CisUtility.getInputString("Customer name:");
        recordName = CisUtility.getInputString("Record name:");
        releaseYear = CisUtility.getInputInt("Release year:");
        vinylIsSealed = CisUtility.getInputBoolean("Is the vinyl sealed?");
        vinylColour = CisUtility.getInputString("Colour (black, red, white):");
        vinylGenre = CisUtility.getInputString("Genre (country, pop, rock, jazz, electronic):");
        recordSize = CisUtility.getInputInt("Size in inches (7, 10, 12):");
        // TODO: Add vinyl price and calculation later.
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public boolean getVinylIsSealed() {
        return vinylIsSealed;
    }

    public void setVinylIsSealed(boolean vinylIsSealed) {
        this.vinylIsSealed = vinylIsSealed;
    }

    public String getVinylColour() {
        return vinylColour;
    }

    public void setVinylColour(String vinylColour) {
        this.vinylColour = vinylColour;
    }

    public String getVinylGenre() {
        return vinylGenre;
    }

    public void setVinylGenre(String vinylGenre) {
        this.vinylGenre = vinylGenre;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }
    @Override
    public String toString() {
        return "Order number: " + orderNumber
                + ", Seller: " + sellerName
                + ", Customer: " + customerName
                + ", Record: " + recordName
                + ", Year: " + releaseYear
                + ", Sealed: " + vinylIsSealed
                + ", Colour: " + vinylColour
                + ", Genre: " + vinylGenre
                + ", Size: " + recordSize
                + ", Price: not calculated";
    }
}
