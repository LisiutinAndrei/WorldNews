package models.business;

import _infrastructure.IoC;
import models.domain.repositories.factory.IRepositoriesFactory;
import models.utils.configuration.IConfigurationProvider;
import models.utils.infrastructurePackages.accountSession.IAccountSession;
import models.utils.infrastructurePackages.response.BaseResponsePackage;
import models.utils.infrastructurePackages.response.IResponsePackage;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;

public class BaseBusinessLogic {

//    @Inject
    public IRepositoriesFactory _repositoriesFactory;

//    @Inject
    private IConfigurationProvider _config;

    public BaseBusinessLogic() {
        super();
        this._config = IoC.getService(IConfigurationProvider.class);
        this._repositoriesFactory = IoC.getService(IRepositoriesFactory.class);
    }
    private EntityManager _getEntityManager() {
        return JPA.em();
    }

    protected <T> IResponsePackage<T> _createResponse(IAccountSession accountSession) {
        IResponsePackage<T> response = new BaseResponsePackage<T>(accountSession);
        return response;
    }
    protected String _getConfig(String path) {
        return this._config.get(path);
    }

    protected <T> T runSqlAction(play.libs.F.Function<EntityManager, T> func) {
        try {
            return JPA.withTransaction(() -> {
                return func.apply(this._getEntityManager());
            });
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    protected <P1, T> T runSqlAction(play.libs.F.Function2<P1, EntityManager, T> func, P1 param1) {
        try {
            return JPA.withTransaction(() -> {
                return func.apply(param1, this._getEntityManager());
            });
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    protected <P1, P2, T> T runSqlAction(play.libs.F.Function3<P1, P2, EntityManager, T> func, P1 param1, P2 param2) {
        try {
            return JPA.withTransaction(() -> {
                return func.apply(param1, param2, this._getEntityManager());
            });
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

}
