package ca.hccis.squash.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Objects;

/**
 * Entity class to hold a vinyl record listed for sale.
 *
 * @author Misha
 * @since 20261002
 */
@Entity
@Table(name = "vinyl_listing")
public class VinylListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Record name is required")
    @Size(max = 100, message = "Record name must be 100 characters or less")
    @Column(name = "recordName", nullable = false, length = 100)
    private String recordName;

    @NotBlank(message = "Seller name is required")
    @Size(max = 50, message = "Seller name must be 50 characters or less")
    @Column(name = "sellerName", nullable = false, length = 50)
    private String sellerName;

    @NotNull(message = "Release year is required")
    @Min(value = 1948, message = "Release year must be 1948 or later")
    @Max(value = 2026, message = "Release year cannot be in the future")
    @Column(name = "releaseYear", nullable = false)
    private Integer releaseYear;

    @NotBlank(message = "Genre is required")
    @Pattern(regexp = "Country|Pop|Rock|Jazz|Electronic", message = "Genre must be Country, Pop, Rock, Jazz or Electronic")
    @Column(name = "vinylGenre", nullable = false, length = 20)
    private String vinylGenre;

    @NotBlank(message = "Colour is required")
    @Pattern(regexp = "Black|Red|White", message = "Colour must be Black, Red or White")
    @Column(name = "vinylColour", nullable = false, length = 10)
    private String vinylColour;

    @NotBlank(message = "Size is required")
    @Pattern(regexp = "Seven-inch|Ten-inch|Twelve-inch", message = "Size must be Seven-inch, Ten-inch or Twelve-inch")
    @Column(name = "recordSize", nullable = false, length = 15)
    private String recordSize;

    @NotNull(message = "Condition (sealed or used) is required")
    @Column(name = "vinylIsSealed", nullable = false)
    private Boolean vinylIsSealed;

    //No validation annotations: calculated in VinylListingBO after validation runs
    @Column(name = "vinylPrice", nullable = false)
    private Double vinylPrice;

    //No validation annotations: set in the controller after validation runs
    @Column(name = "createdDateTime", nullable = false, length = 20)
    private String createdDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getVinylGenre() {
        return vinylGenre;
    }

    public void setVinylGenre(String vinylGenre) {
        this.vinylGenre = vinylGenre;
    }

    public String getVinylColour() {
        return vinylColour;
    }

    public void setVinylColour(String vinylColour) {
        this.vinylColour = vinylColour;
    }

    public String getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(String recordSize) {
        this.recordSize = recordSize;
    }

    public Boolean getVinylIsSealed() {
        return vinylIsSealed;
    }

    public void setVinylIsSealed(Boolean vinylIsSealed) {
        this.vinylIsSealed = vinylIsSealed;
    }

    public Double getVinylPrice() {
        return vinylPrice;
    }

    public void setVinylPrice(Double vinylPrice) {
        this.vinylPrice = vinylPrice;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return id + " | " + recordName + " | " + sellerName + " | " + releaseYear + " | "
                + vinylGenre + " | " + vinylColour + " | " + recordSize + " | "
                + (vinylIsSealed ? "Sealed" : "Used") + " | $" + String.format("%.2f", vinylPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof VinylListing)) return false;
        VinylListing that = (VinylListing) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}