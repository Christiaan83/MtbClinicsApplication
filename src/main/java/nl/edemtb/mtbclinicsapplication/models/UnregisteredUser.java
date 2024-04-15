package nl.edemtb.mtbclinicsapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "unregistered_users")
public class UnregisteredUser extends BasicUser{

}
