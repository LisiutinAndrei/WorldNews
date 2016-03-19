package _infrastructure;

import controllers.AccountSessionAction;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class ActionAuthenticator extends Security.Authenticator {
    @Override
    public String getUsername(Context context) {
        return context.session().get(AccountSessionAction.ACCOUNT_SESSION_ID_NAME);
    }

    @Override
    public Result onUnauthorized(Context context) {
        return super.onUnauthorized(context);
    }
}
