package models.view.main.actor;

import models.domain.orm.Actor;
import play.data.Form;

public class ActorEventsFilterResult {
    public ActorEventsFilterResult(Actor actor, Form<ActorEventsFilter> form) {
        this.actor = actor;
        this.form = form;
    }

    public Form<ActorEventsFilter> form;
    public Actor actor;
}
