package com.mobilehub;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class BookingController {
    private final PhoneService phoneService;

    public BookingController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/booking")
    public String bookingForm(@RequestParam String phoneId, Model model) {
        Phone phone;
        try {
            phone = phoneService.findById(phoneId);
        } catch (IllegalArgumentException ex) {
            return "redirect:/?error=phone";
        }
        model.addAttribute("phone", phone);
        model.addAttribute("bookingRequest", new BookingRequest());
        return "booking";
    }

    @PostMapping("/booking")
    public String submitBooking(@RequestParam String phoneId,
                                @Valid @ModelAttribute BookingRequest bookingRequest,
                                BindingResult result,
                                Model model) {
        Phone phone;
        try {
            phone = phoneService.findById(phoneId);
        } catch (IllegalArgumentException ex) {
            return "redirect:/?error=phone";
        }

        if (!bookingRequest.isConsent()) {
            result.rejectValue("consent", "consent.required", "Please agree to be contacted about this enquiry.");
        }
        if (!"Phone call".equals(bookingRequest.getContactMethod())
                && !"SMS".equals(bookingRequest.getContactMethod())
                && !"Email".equals(bookingRequest.getContactMethod())) {
            result.rejectValue("contactMethod", "contact.invalid", "Choose a valid contact method.");
        }

        if (result.hasErrors()) {
            model.addAttribute("phone", phone);
            return "booking";
        }

        // Starter behavior: no personal data is stored or sent.
        // Integrate a database and secure follow-up workflow before production.
        model.addAttribute("reference", "MH-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        model.addAttribute("phone", phone);
        model.addAttribute("fullName", bookingRequest.getFullName());
        return "confirmation";
    }
}
