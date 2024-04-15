package nl.edemtb.mtbclinicsapplication.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact_forms")
public class ContactForm extends BasicUser {

    @Column(length = Integer.MAX_VALUE)
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
