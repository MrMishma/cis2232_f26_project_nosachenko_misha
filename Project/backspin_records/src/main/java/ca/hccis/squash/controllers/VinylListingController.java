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
import ca.hccis.squash.bo.VinylListingBO;
import ca.hccis.squash.util.CisUtility;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

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
    /**
     * Show an empty form to add a new vinyl listing.
     *
     * @param model
     * @return view for the add/edit form
     * @author Misha
     * @since 20261120
     */
    @RequestMapping("/add")
    public String add(Model model) {
        VinylListing vinylListing = new VinylListing();
        vinylListing.setVinylIsSealed(false); //Default the condition to Used
        model.addAttribute("vinylListing", vinylListing);
        return "vinyllisting/add";
    }
    /**
     * Load an existing vinyl listing into the add/edit form.
     *
     * @param id                 ID of the listing to edit (from the URL)
     * @param model
     * @param redirectAttributes used to pass a message to the list page
     * @return view for the add/edit form, or the list if not found
     * @author Misha
     * @since 20261120
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Optional<VinylListing> vinylListing = _vinylListingRepository.findById(id);

        if (vinylListing.isPresent()) {
            model.addAttribute("vinylListing", vinylListing.get());
            return "vinyllisting/add";
        }

        redirectAttributes.addFlashAttribute("messageError", "Listing " + id + " was not found");
        return "redirect:/vinyllisting";
    }
    /**
     * Process the add/edit form. Validates the input, calculates the price,
     * sets the created date for new listings and saves using Spring Data JPA.
     *
     * @param vinylListing       the listing from the form
     * @param bindingResult      validation results for the listing
     * @param redirectAttributes used to pass a message to the list page
     * @return the form again if there are errors, otherwise redirect to the list
     * @author Misha
     * @since 20261120
     */
    @RequestMapping("/submit")
    public String submit(@Valid @ModelAttribute("vinylListing") VinylListing vinylListing,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        //If validation failed, show the form again with the error messages
        if (bindingResult.hasErrors()) {
            logger.info("Vinyl listing validation errors: " + bindingResult.getErrorCount());
            return "vinyllisting/add";
        }

        boolean isNew = vinylListing.getId() == null;

        //New listings get the current date/time
        if (isNew) {
            vinylListing.setCreatedDateTime(CisUtility.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
        }

        //Calculate the price before saving (business rule in the BO)
        VinylListingBO.calculateVinylPrice(vinylListing);

        //save() inserts when id is null, updates when id has a value
        _vinylListingRepository.save(vinylListing);

        if (isNew) {
            redirectAttributes.addFlashAttribute("messageSuccess", "Listing added: " + vinylListing.getRecordName());
        } else {
            redirectAttributes.addFlashAttribute("messageSuccess", "Listing " + vinylListing.getId() + " updated");
        }
        return "redirect:/vinyllisting";
    }
}