package models.view.main.keyword;

import models.domain.orm.Keyword;
import models.utils.infrastructurePackages.Paging;
import models.view.main.BasePagedFilterModel;
import play.data.Form;

import java.util.List;

public class KeywordsPaged extends BasePagedFilterModel<Keyword, KeywordFilter> {
    public KeywordsPaged(List<Keyword> list, int count, Paging paging, Form<KeywordFilter> form) {
        super(list, count, paging, form);
    }
}
