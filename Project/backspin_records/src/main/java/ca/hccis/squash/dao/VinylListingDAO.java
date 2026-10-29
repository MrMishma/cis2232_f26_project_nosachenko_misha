package ca.hccis.squash.dao;

import ca.hccis.squash.jpa.entity.VinylListing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * DAO class to access the vinyl_listing table using JDBC.
 *
 * @author Misha
 * @since 20261002
 */
public class VinylListingDAO {

    private static final Logger logger = LoggerFactory.getLogger(VinylListingDAO.class);
    private Connection conn = null;

    /**
     * Open a connection to the database using the settings in application.properties.
     *
     * @author Misha
     * @since 20261002
     */
    public VinylListingDAO() {
        ResourceBundle rb = ResourceBundle.getBundle("application");
        String connectionString = rb.getString("spring.datasource.url");
        String userName = rb.getString("spring.datasource.username");
        String password = rb.getString("spring.datasource.password");

        try {
            conn = DriverManager.getConnection(connectionString, userName, password);
        } catch (SQLException e) {
            logger.error("Error connecting to database: " + e);
        }
    }

    /**
     * Select all listings where the seller name contains the value passed in.
     *
     * @param sellerName Full or partial seller name
     * @return list of matching listings (empty if none found)
     * @author Misha
     * @since 20261002
     */
    public ArrayList<VinylListing> selectAllBySellerName(String sellerName) {
        ArrayList<VinylListing> listings = new ArrayList<>();
        if (conn == null) {
            return listings;
        }

        String sql = "SELECT * FROM vinyl_listing WHERE sellerName LIKE ? ORDER BY recordName";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + sellerName + "%");
            ResultSet rs = stmt.executeQuery();
            listings = loadList(rs);
        } catch (SQLException e) {
            logger.error("Error selecting by seller name: " + e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("Error closing connection: " + e);
            }
        }
        return listings;
    }

    /**
     * Load the rows of a result set into a list of listings.
     *
     * @param rs Result set from a vinyl_listing query
     * @return list of listings
     * @throws SQLException if a column can not be read
     * @author Misha
     * @since 20261002
     */
    private ArrayList<VinylListing> loadList(ResultSet rs) throws SQLException {
        ArrayList<VinylListing> listings = new ArrayList<>();
        while (rs.next()) {
            VinylListing listing = new VinylListing();
            listing.setId(rs.getInt("id"));
            listing.setRecordName(rs.getString("recordName"));
            listing.setSellerName(rs.getString("sellerName"));
            listing.setReleaseYear(rs.getInt("releaseYear"));
            listing.setVinylGenre(rs.getString("vinylGenre"));
            listing.setVinylColour(rs.getString("vinylColour"));
            listing.setRecordSize(rs.getString("recordSize"));
            listing.setVinylIsSealed(rs.getBoolean("vinylIsSealed"));
            listing.setVinylPrice(rs.getDouble("vinylPrice"));
            listing.setCreatedDateTime(rs.getString("createdDateTime"));
            listings.add(listing);
        }
        return listings;
    }
}