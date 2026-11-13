package ca.hccis.squash.repositories;

import ca.hccis.squash.jpa.entity.VinylListing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Spring Data JPA repository for the vinyl_listing table.
 * Provides CRUD operations (findAll, findById, save, deleteById)
 * without writing any SQL.
 *
 * @author Misha
 * @since 20261113
 */
@Repository
public interface VinylListingRepository extends CrudRepository<VinylListing, Integer>
{
    List<VinylListing> findBySellerNameContaining(String sellerName);
}