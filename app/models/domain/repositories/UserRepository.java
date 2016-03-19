package models.domain.repositories;

import _infrastructure.IoC;
import models.domain.orm.User;
import models.utils.exceptions.ValidationException;
import models.utils.exceptions.ValidationSummaryException;
import models.utils.infrastructurePackages.accountSession.IAccountSession;
import models.utils.security.IEncryptionProvider;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepository implements IUserRepository {

    private EntityManager _entityManager;
    private IAccountSession _accountSession;
    //    @Inject
    private IEncryptionProvider _encryptionProvider;

    public UserRepository(IAccountSession accountSession, EntityManager entityManager) {
        super();
        this._accountSession = accountSession;
        this._entityManager = entityManager;
        this._encryptionProvider = IoC.getService(IEncryptionProvider.class);
    }

    @Override
    public User getByID(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("User id must be greater than zero");
        }
        List<User> users = (List<User>) this._entityManager.createQuery("FROM models.domain.orm.User u WHERE u._userID = :userID")
                .setParameter("userID", id)
                .getResultList();
        User obj = users.size() == 1 ? users.get(0) : null;
//        if (obj != null) {
//            obj.setPasswordEncrypted("");
//        }
        return obj;
    }

    protected List<ValidationException> validate(User obj) {
        List<ValidationException> errorsList = new ArrayList<ValidationException>();
        if (obj.getEmail() == null || obj.getEmail().isEmpty()) {
            errorsList.add(new ValidationException("User's email must not be empty", User.EMAIL_COLUMN));
        }
        if (obj.getFullName() == null || obj.getFullName().isEmpty()) {
            errorsList.add(new ValidationException("User's full name must not be empty", User.FULL_NAME_COLUMN));
        }
        if (obj.getPasswordEncrypted() == null || obj.getPasswordEncrypted().isEmpty()) {
            errorsList.add(new ValidationException("User's password must not be empty", User.PASSWORD_COLUMN));
        }

        List<User> users = (List<User>) this._entityManager.createQuery("FROM models.domain.orm.User u WHERE u._email = :email")
                .setParameter("email", obj.getEmail())
                .getResultList();
        if (users.stream().filter(u -> u.getUserID() != obj.getUserID()).count() > 0) {
            errorsList.add(new ValidationException("There is another user with the same email", User.EMAIL_COLUMN));
        }

        users = (List<User>) this._entityManager.createQuery("FROM models.domain.orm.User u WHERE u._fullName = :fullName")
                .setParameter("fullName", obj.getFullName())
                .getResultList();
        if (users.stream().filter(u -> u.getUserID() != obj.getUserID()).count() > 0) {
            errorsList.add(new ValidationException("There is another user with the same full name", User.FULL_NAME_COLUMN));
        }

        return errorsList;
    }

    @Override
    public User save(User obj) throws ValidationSummaryException {
        if (obj.getUserID() <= 0) {
            obj.setCreationDate(obj.getCreationDate() == null ? new Date() : obj.getCreationDate());
        }

        List<ValidationException> errorsList = this.validate(obj);
        if (!errorsList.isEmpty()) {
            throw new ValidationSummaryException(errorsList);
        }
        if (obj.getUserID() <= 0) {
            obj.setPasswordEncrypted(this._encryptionProvider.encryptPassword(obj.getPasswordEncrypted()));
            this._entityManager.persist(obj);
        } else {
            this._entityManager.merge(obj);
        }
//        obj.setPasswordEncrypted("");
        return obj;
    }

    @Override
    public User create(User obj) throws ValidationSummaryException {
        if (obj.getUserID() > 0) {
            throw new IllegalArgumentException("User ID must be less or equal to zero");
        }
        return this.save(obj);
    }

    @Override
    public User update(User obj) throws ValidationSummaryException {
        if (obj.getUserID() <= 0) {
            throw new IllegalArgumentException("User ID must be greater than zero");
        }
        return this.save(obj);
    }

    @Override
    public User tryLogIn(String email, String password) throws ValidationSummaryException {
        List<ValidationException> errorsList = new ArrayList<ValidationException>();
        if (email == null || email.isEmpty()) {
            errorsList.add(new ValidationException("User's email must not be empty", User.EMAIL_COLUMN));
        }
        if (password == null || password.isEmpty()) {
            errorsList.add(new ValidationException("User's password must not be empty", User.PASSWORD_COLUMN));
        }

        if (!errorsList.isEmpty()) {
            throw new ValidationSummaryException(errorsList);
        }

        List<User> users = (List<User>) this._entityManager.createQuery("FROM models.domain.orm.User u WHERE u._email = :email")
                .setParameter("email", email)
                .getResultList();
        User user = users.size() == 1 ? users.get(0) : null;

        if (user == null) {
            return null;
        }
        if (!user.isConfirmed()) {
            errorsList.add(new ValidationException("User's email is not confirmed", User.EMAIL_COLUMN));
            throw new ValidationSummaryException(errorsList);
        }

        boolean pass = this._encryptionProvider.checkPassword(password, user.getPasswordEncrypted());
//        user.setPasswordEncrypted("");
        return pass ? user : null;
    }


}
