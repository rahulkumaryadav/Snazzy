package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.CodeGenerator;
import play.Logger;
import play.db.jpa.JPA;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CodeGeneratorController extends Controller {
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "defaultPersistenceUnit" );
    EntityManager entitymanager = emfactory.createEntityManager( );


    @play.db.jpa.Transactional
    public Result index(){
        return ok(views.html.codeGenerator.render(null));
    }


    @play.db.jpa.Transactional
    public Result create() throws IOException{
       // String s1,s2,s3,s4,s5;
        Logger.info("CodeGeneratorController--create()");
        JsonNode jsonNode = Controller.request().body().asJson();
        ObjectMapper mapper = new ObjectMapper();
        CodeGenerator codeGenerator = mapper.readValue(jsonNode.toString(), CodeGenerator.class);
        Date date=new Date();       // s1=codeGenerator.getStrPurchesRate();
        //s2=codeGenerator.getStrCode();
        //s3=codeGenerator.getStrSalingRate();
        //s4=s1.concat(s2);
        //s5=s4.concat(s3);
        //Logger.info("s1***********************"+s5);
        //codeGenerator.setStrUniqueCode(s5);
        codeGenerator.setDtCreated(date);
        codeGenerator.setActiveFlag(true);
        entitymanager.getTransaction().begin();
        if (!entitymanager.contains(codeGenerator)) {
            entitymanager.persist(codeGenerator);
            entitymanager.flush();
        }
        entitymanager.getTransaction().commit();
        return ok("");
    }

    @play.db.jpa.Transactional
    public  Result getAll() throws IOException {

        Logger.info("CodeGeneratorController.getAll()");
       // List<RankMaster> rankMasterList= JPA.em().createQuery("select a from RankMaster a where isActive=true order by rankName asc ", RankMaster.class).getResultList();
        List<CodeGenerator> codeGeneratorList= entitymanager.createQuery("select a from CodeGenerator a where activeFlag=true order by dtCreated desc ", CodeGenerator.class).getResultList();
        ObjectMapper objectMapper=new ObjectMapper();
        String a=objectMapper.writeValueAsString(codeGeneratorList);
        JsonNode jsonNode=objectMapper.readTree(a);
        if (jsonNode.has(0))
            return ok(jsonNode);
        else{
            Logger.info("record not found");
            return ok("");
        }
    }

    @play.db.jpa.Transactional
    public  Result delete(String id) throws IOException {
        Logger.info("id*****************************"+Long.parseLong(id));
        Date date=new Date();
        CodeGenerator codeGenerator= entitymanager.createQuery("select a from CodeGenerator a where a.id=:id",CodeGenerator.class).setParameter("id",Long.parseLong(id)).getSingleResult();
        Logger.info("codeGenerator"+codeGenerator);
        codeGenerator.setDtModified(date);
        codeGenerator.setActiveFlag(false);
        Logger.info("codeGenerator"+codeGenerator);
        entitymanager.getTransaction().begin();
        if (!entitymanager.contains(codeGenerator)) {
            entitymanager.persist(codeGenerator);
            entitymanager.flush();
        }
        entitymanager.getTransaction().commit();
        return ok();
    }


}



