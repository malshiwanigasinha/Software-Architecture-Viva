import java.util.ArrayList;
import java.util.List;

// Mediator interface
interface MessageMediator {
    void sendMessage(String message, User sender, User receiver);
}

// Concrete Mediator
class ChatApp implements MessageMediator {
    private List<User> users;

    public ChatApp() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, User sender, User receiver) {
        // Send message to receiver
        receiver.receiveMessage(message);
    }

    public void addUser(User user) {
        users.add(user);
    }
}

// Colleague interface
abstract class User {
    protected MessageMediator mediator;
    protected String name;

    public User(MessageMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message, User receiver);

    public abstract void receiveMessage(String message);
}

// Concrete Colleague
class IndividualUser extends User {
    public IndividualUser(MessageMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message, User receiver) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this, receiver);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " receives: " + message);
    }
}

// Main class to test the Mediator pattern
public class MediatorPattern {
    public static void main(String[] args) {
        MessageMediator chatApp = new ChatApp();

        User user1 = new IndividualUser(chatApp, "Alice");
        User user2 = new IndividualUser(chatApp, "Bob");

        ((ChatApp) chatApp).addUser(user1);
        ((ChatApp) chatApp).addUser(user2);

        user1.send("Hi Bob!", user2);
        user2.send("Hello Alice!", user1);
    }
}
