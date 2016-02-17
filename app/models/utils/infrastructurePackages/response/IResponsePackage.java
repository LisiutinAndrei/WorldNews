package models.utils.infrastructurePackages.response;

import models.utils.exceptions.ResponseCodeEnum;
import models.utils.exceptions.GenericApplicationException;
import models.utils.infrastructurePackages.accountSession.IAccountSession;


public interface IResponsePackage<T> {
    IAccountSession getAccountSession();
    IResponsePackage<T> setAccountSession(IAccountSession accountSession);

    T getResponseData();
    IResponsePackage<T> setResponseData(T responseData);

//    long getResponseID();
//    IResponsePackage<T> setResponseID(long responseID);
//
//    String getResponseString();
//    IResponsePackage<T> setResponseString(String responseString);

    ResponseCodeEnum getResponseCode();
    IResponsePackage<T> setResponseCode(ResponseCodeEnum resultCode);

    IResponsePackage<T> throwExceptionIfError() throws GenericApplicationException;
}
