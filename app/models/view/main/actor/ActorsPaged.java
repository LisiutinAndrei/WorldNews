package models.view.main.actor;

import models.domain.orm.Actor;
import models.utils.infrastructurePackages.Paging;
import models.view.main.BasePagedFilterModel;
import play.data.Form;

import java.util.List;

public class ActorsPaged extends BasePagedFilterModel<Actor, ActorFilter> {
    public ActorsPaged(List<Actor> list, int count, Paging paging, Form<ActorFilter> form) {
        super(list, count, paging, form);
    }
}
