package chatfinal;

public class ChatMessage {
    private String sender;
    private String payload;

    public ChatMessage(String sender, String payload) {
        this.sender = sender;
        this.payload = payload;
    }

    public String getSender() {
        return sender;
    }

    public String getPayload() {
        return payload;
    }
}
