package controllers;

import actors.WebSocketActor;
import akka.actor.Props;
import models.Account;
import models.Message;
import play.data.Form;
import play.libs.Akka;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

import java.util.Date;

/**
 * The main controller for handling messages.
 *
 * @author Alexander Hanschke
 */
public class Messages extends Controller {

    public static final Result TIMELINE = ok(views.html.timeline.render());

    public static Result timeline() {
        return TIMELINE;
    }

    public static Result byAccount(String username) {
        Account account = Account.byUsername(username);
        if (account == null) {
            return notFound(username);
        }

        return ok(Json.toJson(account.messages()));
    }

    public static Result create(String username) {
        Form<Message> form = Form.form(Message.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        Message message = form.get();
        message.account = Account.byUsername(username);
        message.timestamp = new Date();
        message.save();

        Akka.system().actorOf(Props.create(WebSocketActor.class)).tell(message, null);

        return created();
    }

    public static WebSocket<String> socket() {
        return WebSocketActor.SOCKET;
    }

}
