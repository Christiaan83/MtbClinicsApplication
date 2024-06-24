package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.dtos.ContactFormDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.ContactFormMapper;
import nl.edemtb.mtbclinicsapplication.models.ContactForm;
import nl.edemtb.mtbclinicsapplication.repositories.ContactFormRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactFormService {

    private final ContactFormRepository contactFormRepository;
    private final ContactFormMapper contactFormMapper;

    public ContactFormService(ContactFormRepository contactFormRepository, ContactFormMapper contactFormMapper) {
        this.contactFormRepository = contactFormRepository;
        this.contactFormMapper = contactFormMapper;
    }

    public List<ContactFormDto> getAllContactForms() {

        List<ContactFormDto> dtos = new ArrayList<>();
        List<ContactForm> contactForms = contactFormRepository.findAll();
        for (ContactForm contactForm : contactForms) {
            dtos.add(contactFormMapper.transferToDto(contactForm));
        }
        return dtos;
    }

    public ContactFormDto getContactFormById(Long id) {
        Optional<ContactForm> contactForm = contactFormRepository.findById(id);

        if (contactForm.isPresent()) {
            return contactFormMapper.transferToDto(contactForm.get());
        } else {
            throw new RecordNotFoundException("No contact form found");
        }
    }

    public ContactFormDto saveContactForm(ContactFormDto contactFormDto) {

        ContactForm cf = contactFormMapper.transferToContactForm(contactFormDto);
        contactFormRepository.save(cf);
        return contactFormMapper.transferToDto(cf);
    }

    public void deleteContactFormById(Long id) {

        if (contactFormRepository.existsById(id)) {
            contactFormRepository.deleteById(id);
        }
    }
}
