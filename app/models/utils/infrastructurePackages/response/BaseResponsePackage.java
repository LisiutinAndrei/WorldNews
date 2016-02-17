package models.utils.infrastructurePackages.response;

import models.utils.exceptions.ResponseCodeEnum;
import models.utils.exceptions.GenericApplicationException;
import models.utils.infrastructurePackages.accountSession.IAccountSession;

public class BaseResponsePackage<T> implements IResponsePackage<T> {

    private IAccountSession _accountSession;
    private T _responseData;
    private ResponseCodeEnum _responseCode;
    private long _responseID;
    private String _responseString;

    public BaseResponsePackage() {
        this(null);
    }

    public BaseResponsePackage(IAccountSession accountSession) {
        this.setAccountSession(accountSession);
        this.setResponseData(null);
        this.setResponseID(0);
        this.setResponseCode(ResponseCodeEnum.OK);
        this.setResponseString(null);
    }

    @Override
    public IAccountSession getAccountSession() {
        return this._accountSession;
    }

    @Override
    public BaseResponsePackage<T> setAccountSession(IAccountSession accountSession) {
        this._accountSession = accountSession;
        return this;
    }

    @Override
    public T getResponseData() {
        return this._responseData;
    }

    @Override
    public BaseResponsePackage<T> setResponseData(T responseData) {
        this._responseData = responseData;
        return this;
    }

    @Override
    public ResponseCodeEnum getResponseCode() {
        return this._responseCode;
    }

    @Override
    public BaseResponsePackage<T> setResponseCode(ResponseCodeEnum resultCode) {
        this._responseCode = resultCode;
        return this;
    }

    @Override
    public IResponsePackage<T> throwExceptionIfError() throws GenericApplicationException {
        if (this._responseCode.isError()) {
            throw new GenericApplicationException(this._responseCode);
        }
        return this;
    }

    //    @Override
    public long getResponseID() {
        return this._responseID;
    }

    //    @Override
    public BaseResponsePackage<T> setResponseID(long responseID) {
        this._responseID = responseID;
        return this;
    }

    //    @Override
    public String getResponseString() {
        return this._responseString;
    }

    //    @Override
    public BaseResponsePackage<T> setResponseString(String responseString) {
        this._responseString = responseString;
        return this;
    }
}
