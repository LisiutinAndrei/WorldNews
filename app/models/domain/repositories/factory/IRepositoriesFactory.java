package models.domain.repositories.factory;

import models.domain.repositories.IUserRepository;
import models.utils.infrastructurePackages.accountSession.IAccountSession;

import javax.persistence.EntityManager;

public interface IRepositoriesFactory {
    IUserRepository createUserRepository(IAccountSession account, EntityManager entityManager);
}
