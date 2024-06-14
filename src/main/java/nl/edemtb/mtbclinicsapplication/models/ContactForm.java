package nl.edemtb.mtbclinicsapplication.models;

import jakarta.persistence.*;

@Entity
@Table(name = "contact_forms")
public class ContactForm extends BasicUser {

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = Integer.MAX_VALUE)
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
