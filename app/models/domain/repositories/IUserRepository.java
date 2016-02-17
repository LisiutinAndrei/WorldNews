package models.domain.repositories;

import models.domain.orm.User;
import models.utils.exceptions.ValidationSummaryException;

public interface IUserRepository {
    User getByID(long id);
    User save(User obj) throws ValidationSummaryException;
    User create(User obj) throws ValidationSummaryException;
    User update(User obj) throws ValidationSummaryException;
    User tryLogIn(String email, String password) throws ValidationSummaryException;
}
