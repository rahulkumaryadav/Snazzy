package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import play.Logger;
import play.Play;
import play.db.jpa.JPA;
import play.mvc.*;
import views.html.rankMaster;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class RankMasterController extends Controller {

   /* @play.db.jpa.Transactional
    public  Result index() {
        Logger.info("RankMasterController.index()");
        String email = ctx().session().get("mobileNo");
        if (email != null) {
            EmailAccessUser userTable = EmailAccessUser.findByEmail(email);
            if (userTable != null) {
                // return GO_DASHBOARD;

                return ok(rankMaster.render(userTable));
            } else {
                Logger.debug("Clearing invalid session credentials");
                session().clear();
                return redirect("/");
            }
        }else {
            return redirect("/");
        }
    }*/

    @play.db.jpa.Transactional
    public  Result index(){
        return ok(views.html.rankMaster.render(null));
    }


    @play.db.jpa.Transactional
    public  Result getAll() throws IOException {
        Logger.info("RankMasterController.getAll()");
        List<RankMaster> rankMasterList= JPA.em().createQuery("select a from RankMaster a where isActive=true order by rankName asc ", RankMaster.class).getResultList();
/*
        if(CommonController.checkSessionIfExist()){
*/
            ObjectMapper objectMapper=new ObjectMapper();
            String a=objectMapper.writeValueAsString(rankMasterList);
            JsonNode jsonNode=objectMapper.readTree(a);

            if (jsonNode.has(0))
                return ok(jsonNode);
            else{
                Logger.info("record not found");
                return ok("");
            }
       /* }
        else{
            return Results.badRequest("Error!!!");
        }*/
    }



  /*  @play.db.jpa.Transactional
    public static Result getAllJustForRunQueary(String portName ) throws IOException {
        Logger.info("RankMasterController.getAllJustForRunQueary()");
        Logger.info("Stirng Portname ->"+portName);
        PortName PortId = PortName.findByPortName(portName);
        Logger.info("Port id ->"+PortId.getId());
        List<HusbandryServiceProfile> husbandryServiceProfiles= JPA.em().createQuery("select a from HusbandryServiceProfile a where a.isActive=true and a.portName=:portName ", HusbandryServiceProfile.class).setParameter("portName", PortName.findById(PortId.getId())).getResultList();
        *//*if(CommonController.checkSessionIfExist()){*//*
            ObjectMapper objectMapper=new ObjectMapper();
            String a=objectMapper.writeValueAsString(husbandryServiceProfiles);
            JsonNode jsonNode=objectMapper.readTree(a);

            if (jsonNode.has(0))
                return ok(jsonNode);
            else{
                Logger.info("record not found");
                return ok("");
            }
        *//*}
        else{
            return Results.badRequest("Error!!!");
        }*//*
    }*/


    @play.db.jpa.Transactional
    //@BodyParser.Of(BodyParser.Json.class)
    public  Result create() throws IOException, ParseException {
        Logger.info("RankMasterController - create() ");
        JsonNode jsonNode = Controller.request().body().asJson();
        Logger.info("jsonNode -by rahlul " + jsonNode + "|");
        String resp="";
        Date date=new Date();
        if (jsonNode!=null) {
            Http.Session session = Http.Context.current().session();
            String loginId = session.get("mobileNo");
            Logger.info("loginId - [" + loginId + "]");
            //Employee employee = Employee.findByEmail(loginId);
            ObjectMapper mapper = new ObjectMapper();
            RankMaster rankMaster = mapper.readValue(jsonNode.toString(), RankMaster.class);
            rankMaster.setActive(true);
            rankMaster.setDtCreated(date);
            // rankMaster.setStrCreatedBy(String.valueOf(employee.getId()));
            JPA.em().persist(rankMaster);
        /*    CommonController.createActivityHistory(date, "Rank created By ", serviceModule, new RankMasterController().getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName() + " [Line:" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "]", String.valueOf(rankMaster.getId()));
            resp = "success";
        }else {
            CommonController.createActivityHistory(date, "Rank created unsuccessful By ", serviceModule, new RankMasterController().getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName() + " [Line:" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "]", null);
            resp = "error";
        }*/
        }
        return ok(resp);
    }


/*
    @play.db.jpa.Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public  Result update() throws IOException {
        Logger.info("RoomType - updateA");
        JsonNode jsonNode = Controller.request().body().asJson();
        System.out.println("in update" + jsonNode);
        Long strIndex = jsonNode.get("id").asLong();
        RankMaster rankMaster= RankMaster.findById(strIndex);
        ObjectMapper mapper = new ObjectMapper();
        Http.Session session = Http.Context.current().session();
        String loginId=session.get("mobileNo");
        EmailAccessUser emailAccessUser = EmailAccessUser.findByEmail(loginId);
        Employee employee= Employee.findByEmail(loginId);
        Logger.info("rankMaster - obj="+rankMaster);
        rankMaster=mapper.readValue(jsonNode.toString(), RankMaster.class);
        Date date = new Date();

        rankMaster.setDtModified(date);
        if (employee==null) {
            rankMaster.setStrModifiedBy(loginId + "|" + emailAccessUser.getFullName());
        }
        else{
            rankMaster.setStrModifiedBy(String.valueOf(employee.getId()));
        }
        String resp="";

        RankMaster rankMasterUpdate = JPA.em().merge(rankMaster);
     *//*   if (rankMasterUpdate!=null)
        {
            CommonController.createActivityHistory(date, "Rank updated By ", serviceModule, new RankMasterController().getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName() + " [Line:" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "]", String.valueOf(rankMasterUpdate.getId()));
            resp = "success";
        }else {
            CommonController.createActivityHistory(date, "Rank update unsuccessful By ", serviceModule, new RankMasterController().getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName() + " [Line:" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "]", null);
            resp = "error";
        }*//*
        return Results.ok(resp);
    }*/

  /*  @play.db.jpa.Transactional
    public  Result delete(String id) throws IOException {
        RankMaster rankMaster= RankMaster.findById(Long.parseLong(id));
        rankMaster.setActive(false);
        Date date = new Date();
        rankMaster.setDtModified(date);

        Http.Session session = Http.Context.current().session();
        String loginId=session.get("mobileNo");
        Employee employee = Employee.findByEmail(loginId);
        rankMaster.setStrModifiedBy(String.valueOf(employee.getId()));

        String resp="";
        RankMaster rankMasterUpdate = JPA.em().merge(rankMaster);
        if (rankMasterUpdate!=null){
            CommonController.createActivityHistory(date, "Rank deleted By ", serviceModule, new RankMasterController().getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName() + " [Line:" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "]", String.valueOf(rankMasterUpdate.getId()));
            resp = "success";
        }else {
            CommonController.createActivityHistory(date, "Rank delete unsuccessful By ", serviceModule, new RankMasterController().getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName() + " [Line:" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "]", null);
            resp = "error";
        }
        return Results.ok(resp);
    }
*/

}
