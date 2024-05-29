package nl.edemtb.mtbclinicsapplication.dtos;

import nl.edemtb.mtbclinicsapplication.models.Authority;
import java.util.Set;

public class RegisteredUserDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Integer mobileNumber;
    private Boolean isActive;
    private String apikey;
    public Set<Authority> authorities;

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {this.apikey = apikey;}

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
