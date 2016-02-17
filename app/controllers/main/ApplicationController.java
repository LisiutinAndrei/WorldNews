package controllers.main;

import controllers.BaseController;
import play.mvc.Result;

public class ApplicationController extends BaseController {

    public Result index() {


        return ok(views.html.main.application.index.render());
    }
}
