package models.utils.infrastructurePackages.request;

import models.utils.infrastructurePackages.accountSession.IAccountSession;

public interface IRequestPackage<T> {

    IAccountSession getAccountSession();
    IRequestPackage<T> setAccountSession(IAccountSession accountSession);

    T getRequestData();
    IRequestPackage<T> setRequestData(T requestData);

//    long getRequestID();
//    IRequestPackage<T> setRequestID(long requestID);
//
//    String getRequestString();
//    IRequestPackage<T> setRequestString(String requestString);
}
