package nl.edemtb.mtbclinicsapplication.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "contact_forms")
public class ContactForm extends BasicUser {

    @Id
    @GeneratedValue(generator = "sequence-generator6")
    @GenericGenerator(name = "sequence-generator6",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@Parameter(name = "sequence_form", value = "route_form"),
                    @Parameter(name = "initial_value", value = "1003"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
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
