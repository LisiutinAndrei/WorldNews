package controllers;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import models.domain.orm.User;
import models.utils.infrastructurePackages.accountSession.IAccountSession;
import models.utils.infrastructurePackages.request.BaseRequestPackage;
import models.utils.infrastructurePackages.request.IRequestPackage;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.With;

@With(AccountSessionAction.class)
public class BaseController extends Controller {
    public BaseController() {
        super();
        this._accountSessionManager = new AccountSessionAction();
    }

    AccountSessionAction _accountSessionManager;

    protected final String _getAccountSessionKey() {
        return this._accountSessionManager._getAccountSessionKey(Http.Context.current());
    }

    protected IAccountSession _getAccountSessionOrNull() {
        return this._accountSessionManager._getAccountSessionOrNull(Http.Context.current());
    }

    protected IAccountSession _setAccountSession(IAccountSession accountSession) {
        return this._accountSessionManager._setAccountSession(accountSession, Http.Context.current());
    }

    protected IAccountSession _createAccountSession(User user) {
        return this._accountSessionManager._createAccountSession(user, Http.Context.current());
    }

    protected void _destroyAccountSession() {
        this._accountSessionManager._destroyAccountSession(Http.Context.current());
    }

    protected <T> IRequestPackage _createRequestPackage() {
        IAccountSession account = this._getAccountSessionOrNull();
        IRequestPackage<T> request = new BaseRequestPackage<T>(account);
        return request;
    }

    protected String _json(Object o) {
        Json.mapper().registerModule(new Hibernate5Module());
        return Json.stringify(Json.toJson(o));
    }
}

