package nl.edemtb.mtbclinicsapplication.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.Size;

public class UnregisteredUserDto {

    private Long id;
    @Size(min = 2, max = 50)
    private String firstName;
    @Size(min = 2, max = 50)
    private String lastName;
    @Email(message = "You must enter a valid email address")
    private String email;
    @NegativeOrZero(message = "Number must be positive")
    private Integer mobileNumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
