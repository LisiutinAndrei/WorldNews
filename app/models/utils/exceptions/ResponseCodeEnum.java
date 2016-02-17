package models.utils.exceptions;

public enum ResponseCodeEnum {
    OK(false),
    Undefined(false),
    Warning(false),
    UnspecifiedValidationError(false),
    Exception(true);

    ResponseCodeEnum(boolean isError) {
        this(isError, null);
    }

    ResponseCodeEnum(String message) {
        this(true, message);
    }

    ResponseCodeEnum(boolean isError, String message) {
        this._isError = isError;
        this._message = message;
    }

    private boolean _isError;
    private String _message;

    public boolean isError() {
        return this._isError;
    }

    public String getMessage() {
        return this._message;
    }
}
