package nl.edemtb.mtbclinicsapplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@MappedSuperclass
abstract class BasicUser {

    @NotBlank
    @Size(min = 1, max = 20, message = "Name must be between 1-20 characters")
    private String firstName;
    @NotBlank
    @Size(min = 1, max = 20, message = "Name must be between 1-20 characters")
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotNull
    private Integer mobileNumber;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
