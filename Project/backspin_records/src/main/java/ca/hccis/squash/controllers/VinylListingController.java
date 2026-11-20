package ca.hccis.squash.controllers;

import ca.hccis.squash.jpa.entity.VinylListing;
import ca.hccis.squash.repositories.VinylListingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the vinyl listing CRUD pages.
 *
 * @author Misha
 * @since 20261113
 */
@Controller
@RequestMapping("/vinyllisting")
public class VinylListingController {

    private static final Logger logger = LoggerFactory.getLogger(VinylListingController.class);

    private final VinylListingRepository _vinylListingRepository;

    @Autowired
    public VinylListingController(VinylListingRepository vinylListingRepository) {
        _vinylListingRepository = vinylListingRepository;
    }

    /**
     * Show the list of all vinyl listings.
     *
     * @param model
     * @return view for the vinyl listing list
     * @author Misha
     * @since 20261113
     */
    @RequestMapping("")
    public String home(Model model) {
        logger.info("Loading the vinyl listing list");
        Iterable<VinylListing> listings = _vinylListingRepository.findAll();
        model.addAttribute("listings", listings);
        return "vinyllisting/list";
    }
}