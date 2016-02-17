package models.view.main.account;

import play.data.validation.Constraints;

public class SignIn {

    @Constraints.Required
    @Constraints.Email
    public String email;
    public static final String EMAIL_FIELD = "email";

    @Constraints.Required
    public String password;
    public static final String PASSWORD_FIELD = "password";
}
