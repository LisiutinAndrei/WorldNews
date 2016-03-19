package models.business.main;

import models.business.BaseBusinessLogic;
import models.domain.main.KeywordDomainLogic;
import models.domain.orm.Keyword;
import models.utils.infrastructurePackages.Tuple;
import models.utils.infrastructurePackages.accountSession.IAccountSession;
import models.utils.infrastructurePackages.request.IRequestPackage;
import models.utils.infrastructurePackages.response.IResponsePackage;
import models.view.main.keyword.KeywordEventsFilter;
import models.view.main.keyword.KeywordFilter;

import java.util.List;

public class KeywordBusinessLogic extends BaseBusinessLogic {
    public KeywordBusinessLogic() {
        super();
    }

    public IResponsePackage<Keyword> getKeywordByFilter(IRequestPackage<KeywordEventsFilter> request) {
        IAccountSession account = request.getAccountSession();
        KeywordEventsFilter filter = request.getRequestData();
        Keyword keyword = new KeywordDomainLogic().getKeywordByFilter(filter);
        return this.<Keyword>_createResponse(account)
                .setResponseData(keyword);
    }

    public IResponsePackage<Tuple<List<Keyword>, Integer>> getKeywordsList(IRequestPackage<KeywordFilter> request) {
        IAccountSession account = request.getAccountSession();
        KeywordFilter filter = request.getRequestData();
        Tuple<List<Keyword>, Integer> keywords = new KeywordDomainLogic().getKeywordsList(filter);
        return this.<Tuple<List<Keyword>, Integer>>_createResponse(account)
                .setResponseData(keywords);
    }
}
