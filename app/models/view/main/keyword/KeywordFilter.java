package models.view.main.keyword;

import models.utils.infrastructurePackages.Paging;

public class KeywordFilter {
    public String name;
    public static final String NAME_FIELD = "name";

    public Paging paging;

    public boolean nameIsEmpty() {
        return this.name == null || this.name.trim().isEmpty();
    }
}
