import java.io.Serializable;

public class Message implements Serializable {
    String message;
    String sender;

    public Message(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public Message(String message) {
        this.message = message;
        this.sender = "unknown";
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public static Message ping() {
        return new Message("PING", "PING");
    }

    public boolean isPing() {
        return message.equals("PING") && sender.equals("PING");
    }

    @Override
    public String toString() {
        return "message='" + message + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
    
}
