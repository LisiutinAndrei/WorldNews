package controllers.main;

import _infrastructure.ActionAuthenticator;
import controllers.BaseController;
import models.business.main.KeywordBusinessLogic;
import models.domain.orm.Keyword;
import models.utils.infrastructurePackages.Paging;
import models.utils.infrastructurePackages.Tuple;
import models.utils.infrastructurePackages.response.IResponsePackage;
import models.view.main.keyword.KeywordEventsFilter;
import models.view.main.keyword.KeywordEventsFilterResult;
import models.view.main.keyword.KeywordFilter;
import models.view.main.keyword.KeywordsPaged;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

import static play.data.Form.form;

public class KeywordController extends BaseController {

    @Security.Authenticated(ActionAuthenticator.class)
    public Result details(long keywordID) {
        Form<KeywordEventsFilter> filterForm = form(KeywordEventsFilter.class).bindFromRequest();
        KeywordEventsFilter filter = filterForm.get();
        filter.keywordID = keywordID;
        IResponsePackage<Keyword> resp = new KeywordBusinessLogic().getKeywordByFilter(this._createRequestPackage()
                .setRequestData(filter));
        Keyword keyword = resp.getResponseData();
        flash("keywordJson", this._json(keyword));
        return ok(views.html.main.keyword.details.render(new KeywordEventsFilterResult(keyword, filterForm)));
    }

    @Security.Authenticated(ActionAuthenticator.class)
    public Result list() {
        Form<KeywordFilter> filterForm = form(KeywordFilter.class).bindFromRequest();
        KeywordFilter filter = filterForm.get();
        filter.paging = form(Paging.class).bindFromRequest().get();
        if (filter.paging == null || filter.paging.page < 1 || filter.paging.itemsPerPage < 1) {
            filter.paging = new Paging(1, 10);
        }
        IResponsePackage<Tuple<List<Keyword>, Integer>> resp = new KeywordBusinessLogic().getKeywordsList(this._createRequestPackage()
                .setRequestData(filter));
        Tuple<List<Keyword>, Integer> actors = resp.getResponseData();
        KeywordsPaged model = new KeywordsPaged(actors.x, actors.y, filter.paging, filterForm);
        return ok(views.html.main.keyword.list.render(model));
    }
}
