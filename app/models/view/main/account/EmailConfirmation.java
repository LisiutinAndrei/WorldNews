package models.view.main.account;

public class EmailConfirmation {
    public EmailConfirmation(String token, long userID) {
        this.token = token;
        this.userID = userID;
    }

    public String token;
    public long userID;
}
