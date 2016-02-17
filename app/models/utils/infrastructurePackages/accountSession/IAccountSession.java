package models.utils.infrastructurePackages.accountSession;

import models.domain.orm.User;

import java.util.UUID;

public interface IAccountSession {
    UUID getUUID();
    IAccountSession setUUID(UUID uuid);
    User getUser();
    IAccountSession setUser(User user);
}
