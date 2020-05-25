package ua.edu.ukma.ykrukovska.practice2.messages;

public class InsecureMessage {
    private int type;
    private int userId;
    private String message;

    public InsecureMessage(int type, int userId, String message) {
        this.type = type;
        this.userId = userId;
        this.message = message;
    }


    public int getType() {
        return type;
    }

    public int getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Deciphered message (" + "type = " + type +
                ", userId = " + userId +
                ", message = " + message + ")";
    }
}