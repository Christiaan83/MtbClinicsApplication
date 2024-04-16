package nl.edemtb.mtbclinicsapplication.controllers;

import nl.edemtb.mtbclinicsapplication.dtos.ContactFormDto;
import nl.edemtb.mtbclinicsapplication.services.ContactFormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/contact-form")
@RestController
public class ContactFormController {

    private final ContactFormService contactFormService;

    public ContactFormController(ContactFormService contactFormService) {
        this.contactFormService = contactFormService;
    }

    @GetMapping()
    public ResponseEntity<List<ContactFormDto>> getContactForms() {

        List<ContactFormDto> contactForms = contactFormService.getAllContactForms();
        return ResponseEntity.ok(contactForms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactFormDto> getContactFormById(@PathVariable Long id) {

        ContactFormDto contactForm = contactFormService.getContactFormById(id);
        return ResponseEntity.ok(contactForm);
    }

    @PostMapping()
    public ResponseEntity<ContactFormDto> saveContactForm(@RequestBody ContactFormDto contactForm) {

        ContactFormDto contactFormDto =contactFormService.saveContactForm(contactForm);
        return ResponseEntity.created(null).body(contactFormDto);
    }
    @DeleteMapping("/{id}")

    public ResponseEntity<Object> deleteContactFormBy(@PathVariable Long id) {
        contactFormService.deleteContactFormById(id);
        return ResponseEntity.noContent().build();
    }
}
