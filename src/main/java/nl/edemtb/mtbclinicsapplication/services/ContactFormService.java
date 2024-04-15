package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.dtos.ContactFormDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.ContactFormMapper;
import nl.edemtb.mtbclinicsapplication.models.ContactForm;
import nl.edemtb.mtbclinicsapplication.repositories.ContactFormRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        if(contactForm.isPresent()) {
            return contactFormMapper.transferToDto(contactForm.get());
        }else{
            throw new RecordNotFoundException("Geen contact formulier gevonden");
        }
    }
    public ContactFormDto addContactForm(ContactFormDto contactFormDto) {

        ContactForm cf = contactFormMapper.transferTContactForm(contactFormDto);
        contactFormRepository.save(cf);
        return contactFormDto;
    }

    public Boolean deleteContactFormById(Long id) {

        if(contactFormRepository.existsById(id)) {
            contactFormRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
