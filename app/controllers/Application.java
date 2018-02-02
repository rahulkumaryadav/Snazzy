
package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by admin on 10-06-2017.
 */
public class Application extends Controller {


    @play.db.jpa.Transactional
    public  Result index(){
        return ok(views.html.index.render(null));
    }


   /* @play.db.jpa.Transactional
    public Result index()  {
       Logger.info("Application ->  index ");
        // session().clear();
        String usr = ctx().session().get("username");
        //String usr="9818";
        Logger.info("user -> " + usr);
        if (usr != null) {
            STPL_USER userTable = STPL_USER.findByUser(usr);
            Logger.info("userTable -> " + userTable);

            if (userTable != null) {
                switch(userTable.getRole()){
                    case "Super User":
                        return redirect("/passengerDetails");
                    case "Operation":
                    case "account":
                        return  redirect("/customerDetail");
                    case "admin":
                        return  redirect("/passengerDetails");
                    case "Accounts":
                        return  redirect("/passengerDetails");
                    default:
                        return ok(views.html.index.render(null));
                }
            } else {
                Logger.debug("Clearing invalid session credentials");
                session().clear();
            }
        }
        Logger.info("no session find");
        return ok(views.html.index.render(null));
    }*/

    @play.db.jpa.Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result validate() {
        Logger.info("Login validate ");
        JsonNode jsonNode = Controller.request().body().asJson();
        Logger.info("jsonNode*****************************************"+jsonNode);
        ObjectMapper objectMapper = new ObjectMapper();
        String strRole = null;
        try {
            String a = objectMapper.writeValueAsString(jsonNode);
            STPL_USER user = objectMapper.readValue(a, STPL_USER.class);
            Long columnCount = STPL_USER.validateLogin(user.getUserName(), user.getPassword());
            STPL_USER userData = STPL_USER.findByUser(user.getUserName());
            if (columnCount > 0) {
                Logger.info("Valid User 1");
                session("username", user.getUserName());
                session("role", userData.getRole());
                Logger.info("Role -> "+userData.toString());
                strRole = userData.getRole();
                Logger.info("strRole  -> "+strRole );
                return ok(strRole);
            } else {
                Logger.info("Not a Valid user ");
                return ok("fail");
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return badRequest("Exception");
        }
    }



    @play.db.jpa.Transactional
    public Result logout() {
        Logger.info("Logout");
        session().clear();
        return redirect("/");
    }


    @play.db.jpa.Transactional
    public Result getAll(){
        Logger.info("GetAll User");
        return TODO;
    }


}
