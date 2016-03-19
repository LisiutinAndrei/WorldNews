package controllers.main;

import _infrastructure.ActionAuthenticator;
import controllers.BaseController;
import models.business.main.AccountBusinessLogic;
import models.domain.orm.User;
import models.domain.orm.UserEvent;
import models.utils.exceptions.BaseApplicationException;
import models.utils.exceptions.ValidationException;
import models.utils.exceptions.ValidationSummaryException;
import models.utils.infrastructurePackages.accountSession.IAccountSession;
import models.utils.infrastructurePackages.response.IResponsePackage;
import models.view.main.account.EmailConfirmation;
import models.view.main.account.SignIn;
import models.view.main.account.SignUp;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import play.twirl.api.Content;
import views.html.main.account.signIn;
import views.html.main.account.signUp;
import views.html.main.account.signUpConfirmation;
import views.html.main.account.signUpConfirmed;

import static play.data.Form.form;

public class AccountController extends BaseController {

    public static final Content SIGN_IN(Form<SignIn> form) {
        return signIn.render(form);
    }

    public static final Content SIGN_UP(Form<SignUp> form) {
        return signUp.render(form);
    }

    public static final Result REDIRECT_TO_INDEX() {
        return redirect(controllers.main.routes.ApplicationController.index());
    }

    public Result signIn() {
        IAccountSession sess = this._getAccountSessionOrNull();

        Form<SignIn> emptyForm = form(SignIn.class);
        return ok(SIGN_IN(emptyForm));
    }

    public Result signInSubmit() {
        Form<SignIn> signInForm = form(SignIn.class).bindFromRequest();
        if (signInForm.hasErrors()) {
            return badRequest(SIGN_IN(signInForm));
        }
        SignIn signIn = signInForm.get();

        AccountBusinessLogic logic = new AccountBusinessLogic();
        User loggedOn = null;
        try {
            IResponsePackage<User> response = logic.userSignIn(this._createRequestPackage().setRequestData(signIn));
            loggedOn = response.getResponseData();
        } catch (ValidationSummaryException vse) {
            for (ValidationException ex : vse.getValidationErrors()) {
                switch (ex.getCausedField()) {
                    case User.EMAIL_COLUMN:
                        signInForm.reject(SignIn.EMAIL_FIELD, ex.getMessage());
                        break;
                    case User.PASSWORD_COLUMN:
                        signInForm.reject(SignIn.PASSWORD_FIELD, ex.getMessage());
                        break;
                    default:
                        signInForm.reject(ex.getMessage());
                }
            }
            return badRequest(SIGN_IN(signInForm));
        }
        if (loggedOn == null) {
            signInForm.reject("User's authentication data is incorrect");
            return badRequest(SIGN_IN(signInForm));
        }

        IAccountSession account = this._createAccountSession(loggedOn);

        return REDIRECT_TO_INDEX();
    }

    public Result signUp() {
        IAccountSession sess = this._getAccountSessionOrNull();

        Form<SignUp> emptyForm = form(SignUp.class);
        return ok(SIGN_UP(emptyForm));
    }

    public Result signUpSubmit() throws BaseApplicationException {
        Form<SignUp> signUpForm = form(SignUp.class).bindFromRequest();
        if (signUpForm.hasErrors()) {
            return badRequest(SIGN_UP(signUpForm));
        }
        SignUp signUp = signUpForm.get();

        try {

            AccountBusinessLogic logic = new AccountBusinessLogic();
            IResponsePackage<User> response = logic.userSignUp(this._createRequestPackage().setRequestData(signUp));
            User newUser = response.getResponseData();
        } catch (ValidationSummaryException vse) {
            for (ValidationException ex : vse.getValidationErrors()) {
                switch (ex.getCausedField()) {
                    case User.EMAIL_COLUMN:
                        signUpForm.reject(SignUp.EMAIL_FIELD, ex.getMessage());
                        break;
                    case User.PASSWORD_COLUMN:
                        signUpForm.reject(SignUp.PASSWORD_FIELD, ex.getMessage());
                        break;
                    case User.FULL_NAME_COLUMN:
                        signUpForm.reject(SignUp.FULL_NAME_FIELD, ex.getMessage());
                        break;
                    default:
                        signUpForm.reject(ex.getMessage());
                }
            }
            return badRequest(SIGN_UP(signUpForm));
        }
        return ok(signUpConfirmation.render());
    }

    public Result confirmSignUp(String token, Long userID) {
        AccountBusinessLogic logic = new AccountBusinessLogic();
        EmailConfirmation confirm = new EmailConfirmation(token, userID);

        try {
            IResponsePackage<User> response = logic.userConfirm(this._createRequestPackage().setRequestData(confirm));
            User user = response.getResponseData();
        } catch (ValidationSummaryException vse) {
            for (ValidationException ex : vse.getValidationErrors()) {
                flash("error", ex.getMessage());
            }
        }
        return ok(signUpConfirmed.render());
    }

    @Security.Authenticated(ActionAuthenticator.class)
    public Result signOut() {
        this._destroyAccountSession();
        return REDIRECT_TO_INDEX();
    }

    @Security.Authenticated(ActionAuthenticator.class)
    public Result details() throws BaseApplicationException {
        IAccountSession session = this._getAccountSessionOrNull();
        if (session != null || session.isUserLoggedIn()) {
            return this._details(session.getUser().getUserID());
        } else {
            throw new BaseApplicationException("User not logged in");
        }
    }

    public Result _details(Long userID) throws BaseApplicationException {
        IResponsePackage<User> resp = new AccountBusinessLogic().getUserWithEventsByID(this._createRequestPackage()
                .setRequestData(userID));
        User event = resp.getResponseData();
        Form<User> eventForm = form(User.class).fill(event);
        flash("userJson", this._json(event));
        return ok(views.html.main.account.details.render(eventForm));
    }

    @Security.Authenticated(ActionAuthenticator.class)
    public Result visitEvent(Long eventID) {
        IResponsePackage<UserEvent> resp = new AccountBusinessLogic().visitEvent(this._createRequestPackage()
                .setRequestData(eventID));
        return ok(this._json(resp));
    }
}
