package nl.edemtb.mtbclinicsapplication.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "registered_users")
public class RegisteredUser {

    @Id
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String password;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    @Email
    private String email;
    @Column
    @Pattern(regexp = "^\\(?([+]31|0031|0)-?6(\\s?|-)([0-9]\\s{0,3}){8}$")
    private Integer mobileNumber;
    @Column
    private String userRole;
    @Column(nullable = false)
    private Boolean isActive = true;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
