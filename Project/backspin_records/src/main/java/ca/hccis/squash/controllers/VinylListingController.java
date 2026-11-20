package ca.hccis.squash.controllers;

import ca.hccis.squash.jpa.entity.VinylListing;
import ca.hccis.squash.repositories.VinylListingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    /**
     * Delete a vinyl listing and return to the list.
     *
     * @param id                 ID of the listing to delete (from the URL)
     * @param redirectAttributes used to pass a message to the list page
     * @return redirect to the vinyl listing list
     * @author Misha
     * @since 20261120
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {

        //Make sure the listing exists before trying to delete it
        if (!_vinylListingRepository.existsById(id)) {
            redirectAttributes.addFlashAttribute("messageError", "Listing " + id + " was not found");
            return "redirect:/vinyllisting";
        }

        try {
            _vinylListingRepository.deleteById(id);
            logger.info("Deleted vinyl listing " + id);
            redirectAttributes.addFlashAttribute("messageSuccess", "Listing " + id + " deleted");
        } catch (Exception e) {
            logger.error("Error deleting vinyl listing " + id + ": " + e);
            redirectAttributes.addFlashAttribute("messageError", "Could not delete listing " + id);
        }

        return "redirect:/vinyllisting";
    }
}