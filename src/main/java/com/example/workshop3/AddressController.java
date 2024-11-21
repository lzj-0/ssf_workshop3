package com.example.workshop3;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@Controller
public class AddressController {

    @Autowired
    private ContactsRedis contactsRedis;

    @GetMapping("/contact")
    public String form(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String createContact(@Valid Contact contact, BindingResult binding, Model model) throws IOException {

        if (binding.hasErrors()) {
            return "contact";
        }

        LocalDate dob = contact.getDateOfBirth();
        Integer age = Period.between(dob, LocalDate.now()).getYears();

        if (age < 10 || age > 100) {
            FieldError err = new FieldError("contact", "dateOfBirth", "Age has to be between 10 and 100");
            binding.addError(err);
            return "contact";
        }

        Random random = new Random();
        
        String id = Integer.toHexString(random.nextInt()).substring(0, 8);
        contactsRedis.addContact(contact, id);

        model.addAttribute("contact", contact);

        return "submit";
    }

    @GetMapping("/contact/{id}")
    public String getContactbyId(@PathVariable String id, Model model) throws IOException {
        Contact contact = contactsRedis.getContactById(id);

        if (contact == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
        } else {
            model.addAttribute("contact", contact);
            return "getcontact";
        }
        
    }

    @GetMapping("/contacts")
    public String allContacts(Model model) throws IOException {
        Map<String, String> contactMap = contactsRedis.getAllContacts();
        model.addAttribute("contactmap", contactMap);
        return "contacts";
    }

}
