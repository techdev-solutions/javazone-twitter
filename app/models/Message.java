package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author <a href="mailto:alexander.hanschke@techdev.de">Alexander Hanschke</a>
 */
@Entity
public class Message extends Model {

    @Id
    @GeneratedValue
    public long id;

    @Constraints.Required
    @Constraints.MaxLength(160)
    public String content;
    public Date timestamp;

    @ManyToOne
    public Account account;

}
