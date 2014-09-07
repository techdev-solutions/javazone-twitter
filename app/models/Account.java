package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * The domain model for user accounts.
 * Handling accounts should be done in a dedicated account controller eventually.
 *
 * @author Alexander Hanschke
 */
@Entity
public class Account extends Model {

    @Id
    @GeneratedValue
    public long id;

    public String username;
    public String firstname;
    public String lastname;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST)
    public List<Message> messages;

    public static final Finder<Long, Account> find = new Finder<>(Long.class, Account.class);

    public static Account byUsername(String username) {
        return find.where().eq("username", username).findUnique();
    }

    public List<Message> messages() {
        return messages;
    }

}
