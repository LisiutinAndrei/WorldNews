package models.utils.infrastructurePackages.request;

import models.utils.infrastructurePackages.accountSession.IAccountSession;

public class BaseRequestPackage<T> implements IRequestPackage<T> {

    private IAccountSession _accountSession;
    private T _requestData;
    private long _requestID;
    private String _requestString;

    public BaseRequestPackage () {
        this(null);
    }

    public BaseRequestPackage (IAccountSession accountSession) {
        this.setAccountSession(accountSession);
        this.setRequestData(null);
        this.setRequestID(0);
        this.setRequestString(null);
    }

    @Override
    public IAccountSession getAccountSession() {
        return this._accountSession;
    }

    @Override
    public BaseRequestPackage<T> setAccountSession(IAccountSession accountSession) {
        this._accountSession = accountSession;
        return this;
    }

    @Override
    public T getRequestData() {
        return this._requestData;
    }

    @Override
    public BaseRequestPackage<T> setRequestData(T requestData) {
        this._requestData = requestData;
        return this;
    }

//    @Override
    public long getRequestID() {
        return this._requestID;
    }

//    @Override
    public BaseRequestPackage<T> setRequestID(long requestID) {
        this._requestID = requestID;
        return this;
    }
//    @Override
    public String getRequestString() {
        return this._requestString;
    }

//    @Override
    public BaseRequestPackage<T> setRequestString(String requestString) {
        this._requestString = requestString;
        return this;
    }
}
