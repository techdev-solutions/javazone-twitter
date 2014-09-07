import models.Account;
import models.Message;
import org.junit.Test;
import play.libs.ws.WS;
import play.mvc.Result;
import play.test.WithApplication;
import play.twirl.api.Content;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;


/**
 * A couple of different tests to demo the capabilities of Play.
 *
 * @author Alexander Hanschke
 */
public class ApplicationTest extends WithApplication {

    @Test
    public void renderTimeline() {
        Content html = views.html.timeline.render();
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Fork me on GitHub");
    }

    @Test
    public void saveAccount() {
        assertThat(Account.find.all()).hasSize(1);
        john().save();
        assertThat(Account.find.all()).hasSize(2);
    }

    @Test
    public void saveMessage() {
        Account john = john();
        john.save();

        assertThat(john.messages()).isEmpty();

        Message message = new Message();
        message.timestamp = new Date();
        message.content = "Test Message by John";
        message.account = john;
        message.save();

        assertThat(Account.byUsername("therealjohn").messages()).hasSize(1);
    }

    @Test
    public void testInServer() {
        running(testServer(3333), () -> {
            assertThat(
                WS.url("http://localhost:3333/alexhanschke/messages").get().get(5, TimeUnit.SECONDS).getStatus()
            ).isEqualTo(OK);
        });
    }

    @Test
    public void testBadRoute() {
        Result result = route(fakeRequest(GET, "/some/fake/route"));
        assertThat(result).isNull();
    }

    private Account john() {
        Account john = new Account();
        john.firstname = "John";
        john.lastname = "Doe";
        john.username = "therealjohn";

        return john;
    }


}
