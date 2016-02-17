package models.utils.exceptions;

import java.util.List;

public class ValidationSummaryException extends GenericApplicationException {
    protected List<ValidationException> _validationErrors;

    public ValidationSummaryException(List<ValidationException> validationErrors) {
        super(ResponseCodeEnum.UnspecifiedValidationError);
        this._validationErrors = validationErrors;
    }

    public ValidationSummaryException(String message, List<ValidationException> validationErrors) {
        super(message, ResponseCodeEnum.UnspecifiedValidationError);
        this._validationErrors = validationErrors;
    }

    public List<ValidationException> getValidationErrors() {
        return this._validationErrors;
    }
}
