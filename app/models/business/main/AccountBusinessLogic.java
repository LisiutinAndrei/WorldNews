package models.business.main;

import _infrastructure.IoC;
import models.business.BaseBusinessLogic;
import models.domain.orm.User;
import models.domain.repositories.IUserRepository;
import models.utils.exceptions.ValidationException;
import models.utils.exceptions.ValidationSummaryException;
import models.utils.infrastructurePackages.accountSession.IAccountSession;
import models.utils.infrastructurePackages.request.IRequestPackage;
import models.utils.infrastructurePackages.response.IResponsePackage;
import models.view.main.account.EmailConfirmation;
import models.view.main.account.SignIn;
import models.view.main.account.SignUp;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AccountBusinessLogic extends BaseBusinessLogic {
    public AccountBusinessLogic() {
        super();
        this._mailerClient = IoC.getService(MailerClient.class);
    }

    //    @Inject
    MailerClient _mailerClient;

    private IUserRepository _getUserRepository(IAccountSession account, EntityManager entityManager) {
        return this._repositoriesFactory.createUserRepository(account, entityManager);
    }

    /**
     * Trying to log in
     *
     * @param request
     * @return
     */
    public IResponsePackage<User> userSignIn(IRequestPackage<SignIn> request) throws ValidationSummaryException {
        IAccountSession account = request.getAccountSession();
        SignIn signIn = request.getRequestData();

        User passed = this.runSqlAction((entityManager) -> {
            return this._getUserRepository(account, entityManager).tryLogIn(signIn.email, signIn.password);
        });
        return this.<User>_createResponse(account)
                .setResponseData(passed);
    }

    /**
     * Creating a new system user
     *
     * @param request
     * @return
     */
    public IResponsePackage<User> userSignUp(IRequestPackage<SignUp> request) throws ValidationSummaryException {
        IAccountSession account = request.getAccountSession();
        SignUp signUp = request.getRequestData();

        User user = this.runSqlAction((entityManager) -> {
            User _user = new User()
                    .setCreationDate(new Date())
                    .setEmail(signUp.email)
                    .setFullName(signUp.fullName)
                    .setPasswordEncrypted(signUp.password)
                    .setConfirmationToken(String.valueOf(UUID.randomUUID()))
                    .setConfirmed(false)
                    .setUserID(0);

            return this._getUserRepository(account, entityManager).create(_user);
        });

        Email email = new Email();
        email.setSubject("[worldnews.tu-ilmenau.de] Email confirmation");
        email.setFrom("WorldNews Team <lisutin.andrey@gmail.com>");
        email.addTo(user.getFullName() + "<" + user.getEmail() + ">");

        String url = this._getConfig("server.hostroot") + "/account/confirmSignUp/" + user.getConfirmationToken() +
                "/" + user.getUserID();

        email.setBodyHtml("<html><body>Complete your registration. Click ok the link below: " +
                "<a href=\"" + url + "\">" + url + "</a></body></html>");

        _mailerClient.send(email);

        return this.<User>_createResponse(account)
                .setResponseData(user);
    }

    public IResponsePackage<User> userConfirm(IRequestPackage<EmailConfirmation> request) throws ValidationSummaryException {
        EmailConfirmation confirmation = request.getRequestData();
        IAccountSession account = request.getAccountSession();

        User user = this.runSqlAction((entityManager) -> {
            return this._getUserRepository(account, entityManager).getByID(confirmation.userID);
        });

        List<ValidationException> exceptions = new ArrayList<ValidationException>();
        if (user == null) {
            exceptions.add(new ValidationException("Didn't find the user by ID", User.USER_ID_COLUMN));
        }
        if (user.isConfirmed()) {
            exceptions.add(new ValidationException("Email is already successfully confirmed", User.USER_ID_COLUMN));
        }
        if (!confirmation.token.equals(user.getConfirmationToken())) {
            exceptions.add(new ValidationException("Confirmation token is wrong", User.CONFIRMATION_TOKEN_COLUMN));
        }
        if (!exceptions.isEmpty()) {
            throw new ValidationSummaryException(exceptions);
        }

        user.setConfirmed(true);
        final User finalUser = user;
        user = this.runSqlAction((entityManager) -> {
            return this._getUserRepository(account, entityManager).update(finalUser);
        });

        return this.<User>_createResponse(account)
                .setResponseData(user);
    }
}
