package nl.edemtb.mtbclinicsapplication.mappers;

import nl.edemtb.mtbclinicsapplication.dtos.ContactFormDto;
import nl.edemtb.mtbclinicsapplication.models.ContactForm;
import nl.edemtb.mtbclinicsapplication.repositories.ContactFormRepository;

public class ContactFormMapper {

    private final ContactFormRepository contactFormRepository;

    public ContactFormMapper(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }

    public ContactFormDto transferToDto(ContactForm contactForm) {
        var dto = new ContactFormDto();

        dto.setId(contactForm.getId());
        dto.setFirstName(contactForm.getFirstName());
        dto.setLastName(contactForm.getLastName());
        dto.setEmail(contactForm.getEmail());
        dto.setMobileNumber(contactForm.getMobileNumber());
        dto.setMessage(contactForm.getMessage());

        return dto;
    }
    public ContactForm transferTContactForm(ContactFormDto dto) {
        var contactForm = new ContactForm();

        contactForm.setId(dto.getId());
        contactForm.setFirstName(dto.getFirstName());
        contactForm.setLastName(dto.getLastName());
        contactForm.setEmail(dto.getEmail());
        contactForm.setMobileNumber(dto.getMobileNumber());
        contactForm.setMessage(dto.getMessage());

        return contactForm;
    }

}
