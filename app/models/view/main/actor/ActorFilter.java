package models.view.main.actor;

import models.utils.infrastructurePackages.Paging;

public class ActorFilter {
    public String name;
    public static final String NAME_FIELD = "name";
    public Paging paging;

    public boolean nameIsEmpty() {
        return this.name == null || this.name.trim().isEmpty();
    }
}
