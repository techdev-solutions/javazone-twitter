package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import models.Message;
import play.libs.Json;
import play.mvc.WebSocket;

import java.util.HashSet;
import java.util.Set;

/**
 * An Akka actor to inform listeners about new messages.
 * This actor is invoked by the {@code Messages} controller upon a message has been created.
 * Notifications are sent via a WebSocket to all registered isteners.
 *
 * @author Alexander Hanschke
 */
public class WebSocketActor extends UntypedActor {

    private static final Set<ActorRef> channels = new HashSet<>();

    public static final WebSocket<String> SOCKET = WebSocket.withActor(WebSocketActor::props);

    public WebSocketActor() {}

    private ActorRef out;

    public WebSocketActor(ActorRef out) {
        this.out = out;
        this.channels.add(out);
    }

    public static Props props(ActorRef out) {
        return Props.create(WebSocketActor.class, out);
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Message) {
            broadcast((Message) message);
        } else {
            unhandled(message);
        }
    }

    @Override
    public void postStop() throws Exception {
        this.channels.remove(out);
        super.postStop();
    }

    private void broadcast(Message message) {
        String json = Json.toJson(message).toString();
        for (ActorRef channel : channels) {
            channel.tell(json, self());
        }
    }
}
