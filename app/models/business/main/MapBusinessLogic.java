package models.business.main;

import models.business.BaseBusinessLogic;
import models.domain.orm.GeolocationPolygon;
import models.domain.orm.User;
import models.utils.exceptions.ValidationSummaryException;
import models.utils.infrastructurePackages.accountSession.IAccountSession;
import models.utils.infrastructurePackages.request.IRequestPackage;
import models.utils.infrastructurePackages.response.IResponsePackage;
import models.view.main.account.SignIn;

import java.util.List;

public class MapBusinessLogic extends BaseBusinessLogic {
    public MapBusinessLogic() {
        super();
    }

    /**
     * Trying to log in
     *
     * @param request
     * @return
     */
    public IResponsePackage<List<GeolocationPolygon>> getPolygons(IRequestPackage<SignIn> request) {
        IAccountSession account = request.getAccountSession();

        List<GeolocationPolygon> polygons = this.runSqlAction((entityManager) -> {
            return (List<GeolocationPolygon>)entityManager.createQuery("FROM models.domain.orm.GeolocationPolygon").getResultList();
        });
        return this.<List<GeolocationPolygon>>_createResponse(account)
                .setResponseData(polygons);
    }
}
