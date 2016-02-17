package controllers;

import _infrastructure.IoC;
import models.domain.orm.User;
import models.utils.infrastructurePackages.accountSession.AccountSession;
import models.utils.infrastructurePackages.accountSession.IAccountSession;
import play.cache.CacheApi;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.UUID;

public class AccountSessionAction extends Action.Simple {

    public AccountSessionAction() {
        super();
        this._cache = IoC.getService(CacheApi.class);
    }

    //    @Inject
    protected CacheApi _cache;
    protected static final String ACCOUNT_SESSION_ID_NAME = "uuid";
    protected static final String ACCOUNT_SESSION_CONTEXT_NAME = "_accountSession_";

    protected final String _getAccountSessionKey(Http.Context context) {
        String key = "__accountSession__" + context.session().get(ACCOUNT_SESSION_ID_NAME);
        return key;
    }

    protected IAccountSession _getAccountSessionOrNull(Http.Context context) {
        IAccountSession accountSession = this._cache.get(this._getAccountSessionKey(context));
        return accountSession;
    }

    protected IAccountSession _setAccountSession(IAccountSession accountSession, Http.Context context) {
        this._cache.set(this._getAccountSessionKey(context), accountSession);
        return accountSession;
    }

    protected IAccountSession _createAccountSession(User user, Http.Context context) {
        IAccountSession accountSession = this._getAccountSessionOrNull(context);
        if (accountSession != null) {
            //something goes wrong - destroying session
            this._destroyAccountSession(context);
        }

        UUID uuid = UUID.randomUUID();
        context.session().put(ACCOUNT_SESSION_ID_NAME, String.valueOf(uuid));

        accountSession = new AccountSession()
                .setUUID(uuid)
                .setUser(user);

        this._setAccountSession(accountSession, context);
        return accountSession;
    }

    protected void _destroyAccountSession(Http.Context context) {
        String key = this._getAccountSessionKey(context);
        if (key != null) {
            this._cache.remove(key);
            context.session().remove(ACCOUNT_SESSION_ID_NAME);
        }
    }

    public F.Promise<Result> call(Http.Context context) throws Throwable {
        IAccountSession session = this._getAccountSessionOrNull(context);
        context.args.put(ACCOUNT_SESSION_CONTEXT_NAME, session);
        return delegate.call(context);
    }

    public static IAccountSession get() {
        return (IAccountSession) Http.Context.current().args.get(ACCOUNT_SESSION_CONTEXT_NAME);
    }
}
