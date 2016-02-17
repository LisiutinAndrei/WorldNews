package _infrastructure;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class ActionAuthenticator extends Security.Authenticator {
    @Override
    public String getUsername(Context context) {
        return context.session().get("username");
    }

    @Override
    public Result onUnauthorized(Context context) {
        return super.onUnauthorized(context);
    }
}
