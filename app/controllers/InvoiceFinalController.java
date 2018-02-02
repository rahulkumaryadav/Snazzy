package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class InvoiceFinalController extends Controller {
    public Result index(){
        return ok(views.html.invoiceFinal.render(null));


    }
}
