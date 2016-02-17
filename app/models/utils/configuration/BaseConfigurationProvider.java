package models.utils.configuration;

import play.Configuration;

public class BaseConfigurationProvider implements IConfigurationProvider {

    Configuration _configuration;
    public BaseConfigurationProvider() {
        this._configuration = Configuration.root();
    }

    @Override
    public String get(String path) {
        return this._configuration.getString(path);
    }
}
