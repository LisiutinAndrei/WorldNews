package models.utils.exceptions;

public class ValidationException extends GenericApplicationException {
    private String _field;

    public ValidationException() {
        this(null);
    }

    public ValidationException(String field) {
        super(ResponseCodeEnum.UnspecifiedValidationError);
        this._field = field;
    }

    public ValidationException(String message, String field) {
        super(message, ResponseCodeEnum.UnspecifiedValidationError);
        this._field = field;
    }

    public ValidationException(String message, Throwable cause, String field) {
        super(message, cause, ResponseCodeEnum.UnspecifiedValidationError);
        this._field = field;
    }

    public ValidationException(Throwable cause, String field) {
        super(cause, ResponseCodeEnum.UnspecifiedValidationError);
        this._field = field;
    }

    public String getCausedField() {
        return this._field;
    }

}
