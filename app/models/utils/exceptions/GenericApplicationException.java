package models.utils.exceptions;

public class GenericApplicationException extends BaseApplicationException {
    protected ResponseCodeEnum _exceptionCode;

    public GenericApplicationException() {
        this(ResponseCodeEnum.Undefined);
    }

    public GenericApplicationException(ResponseCodeEnum exceptionCode) {
        super();
        this._exceptionCode = exceptionCode;
    }

    public GenericApplicationException(String message, ResponseCodeEnum exceptionCode) {
        super(message);
        this._exceptionCode = exceptionCode;
    }

    public GenericApplicationException(String message, Throwable cause, ResponseCodeEnum exceptionCode) {
        super(message, cause);
        this._exceptionCode = exceptionCode;
    }

    public GenericApplicationException(Throwable cause, ResponseCodeEnum exceptionCode) {
        super(cause);
        this._exceptionCode = exceptionCode;
    }

    public ResponseCodeEnum getExceptionCode() {
        return this._exceptionCode;
    }
}
