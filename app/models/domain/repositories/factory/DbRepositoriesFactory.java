package models.domain.repositories.factory;

import models.domain.repositories.IUserRepository;
import models.domain.repositories.UserRepository;
import models.utils.infrastructurePackages.accountSession.IAccountSession;

import javax.persistence.EntityManager;

public class DbRepositoriesFactory implements IRepositoriesFactory {
    @Override
    public IUserRepository createUserRepository(IAccountSession account, EntityManager entityManager) {
        return new UserRepository(account, entityManager);
    }
}
