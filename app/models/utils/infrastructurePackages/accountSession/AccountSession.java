package models.utils.infrastructurePackages.accountSession;

import models.domain.orm.User;
import java.util.UUID;

public class AccountSession implements IAccountSession {

    public AccountSession() {
        //this._uuid = UUID.randomUUID();
    }

    private UUID _uuid;

    private User _user;

    @Override
    public UUID getUUID() {
        return this._uuid;
    }

    @Override
    public IAccountSession setUUID(UUID uuid) {
        this._uuid = uuid;
        return this;
    }

    @Override
    public User getUser() {
        return _user;
    }

    @Override
    public IAccountSession setUser(User user) {
        this._user = user;
        return this;
    }
}
