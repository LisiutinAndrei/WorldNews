package _infrastructure;

import com.google.inject.AbstractModule;
import models.domain.repositories.factory.DbRepositoriesFactory;
import models.domain.repositories.factory.IRepositoriesFactory;
import models.utils.configuration.BaseConfigurationProvider;
import models.utils.configuration.IConfigurationProvider;
import models.utils.security.BCryptEncryptionProvider;
import models.utils.security.IEncryptionProvider;

public class IoC extends AbstractModule {
    protected void configure() {

        this.bind(IEncryptionProvider.class)
                .to(BCryptEncryptionProvider.class);

        this.bind(IConfigurationProvider.class)
                .to(BaseConfigurationProvider.class);

        this.bind(IRepositoriesFactory.class)
                .to(DbRepositoriesFactory.class);
    }

    public static <T> T getService(Class<T> type) {
        return play.api.Play.unsafeApplication().injector().instanceOf(type);
    }
}