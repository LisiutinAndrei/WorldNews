package _infrastructure;

import com.google.inject.Provider;
import play.Configuration;
import play.Environment;
import play.api.OptionalSourceMapper;
import play.api.UsefulException;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;

public class ExceptionHandler extends DefaultHttpErrorHandler {

    @Inject
    public ExceptionHandler(Configuration configuration, Environment environment,
                        OptionalSourceMapper sourceMapper, Provider<Router> routes) {
        super(configuration, environment, sourceMapper, routes);
    }

    protected Promise<Result> onProdServerError(RequestHeader request, UsefulException exception) {
        return Promise.<Result>pure(
                Results.internalServerError("A server error occurred: " + exception.getMessage())
        );
    }

//    protected Promise<Result> onForbidden(RequestHeader request, String message) {
//        return Promise.<Result>pure(
//                Results.forbidden("You're not allowed to access this resource.")
//        );
//    }
}
