package models.domain;

import _infrastructure.IoC;
import models.domain.repositories.factory.IRepositoriesFactory;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;

public class BaseDomainLogic {

    //    @Inject
    public IRepositoriesFactory _repositoriesFactory;

    public BaseDomainLogic() {
        super();
        this._repositoriesFactory = IoC.getService(IRepositoriesFactory.class);
    }

    private EntityManager _getEntityManager() {
        return JPA.em();
    }

    protected <T> T _runSqlAction(play.libs.F.Function<EntityManager, T> func) {
        try {
            return JPA.withTransaction(() -> {
                return func.apply(this._getEntityManager());
            });
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    protected <P1, T> T _runSqlAction(play.libs.F.Function2<P1, EntityManager, T> func, P1 param1) {
        try {
            return JPA.withTransaction(() -> {
                return func.apply(param1, this._getEntityManager());
            });
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    protected <P1, P2, T> T _runSqlAction(play.libs.F.Function3<P1, P2, EntityManager, T> func, P1 param1, P2 param2) {
        try {
            return JPA.withTransaction(() -> {
                return func.apply(param1, param2, this._getEntityManager());
            });
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

}
