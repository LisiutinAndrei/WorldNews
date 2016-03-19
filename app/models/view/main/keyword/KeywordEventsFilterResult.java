package models.view.main.keyword;

import models.domain.orm.Keyword;
import play.data.Form;

public class KeywordEventsFilterResult {
    public KeywordEventsFilterResult(Keyword keyword, Form<KeywordEventsFilter> form) {
        this.keyword = keyword;
        this.form = form;
    }

    public Form<KeywordEventsFilter> form;
    public Keyword keyword;
}
