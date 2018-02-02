package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import models.CodeGenerator;
import models.CustomerDetail;
import play.Logger;
import play.mvc.Result;
import play.mvc.Controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;


/**
 * Created by DELL on 10/28/17.
 */
public class CustomerDetailController extends Controller{
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "defaultPersistenceUnit" );
    EntityManager entitymanager = emfactory.createEntityManager( );
    public String uniqueCode;

    public Result index(){
        return ok(views.html.customerDetail.render(null));
    }

    @play.db.jpa.Transactional
    public Result create() throws IOException {
        ArrayNode jsonNodeList = new ArrayNode(JsonNodeFactory.instance);
        jsonNodeList = (ArrayNode) Controller.request().body().asJson();
        if(jsonNodeList.size()>0){
            for(int index=0; index<jsonNodeList.size();index++){
                Logger.info(jsonNodeList+"hello customer controller :"+index);
                 String jsonNode= jsonNodeList.get(index).toString();
                ObjectMapper mapper = new ObjectMapper();
                CustomerDetail customerDetail = mapper.readValue(jsonNodeList.get(index).toString(), CustomerDetail.class);

                Logger.info("customerDetail ******************"+customerDetail);
                entitymanager.getTransaction().begin();
                if (!entitymanager.contains(customerDetail)) {
                    entitymanager.merge(customerDetail);
                    entitymanager.flush();
                    uniqueCode = customerDetail.getStrUniqueCode();
                }
                entitymanager.getTransaction().commit();
            }
        }
        return ok(""+uniqueCode);
    }

    @play.db.jpa.Transactional
    public  Result getByInvoice(String invoiceNo) throws IOException {
        Logger.info("ServiceTariffController->getAllByPortId()");

        List<CustomerDetail> customerDetailList= entitymanager.createQuery("select a from CustomerDetail a where  a.strUniqueCode=:invoiceNo ", CustomerDetail.class).setParameter("invoiceNo", invoiceNo).getResultList();
/*
        List<models.serviceModule.HServiceProfile> hServiceProfiles = JPA.em().createQuery("select a from HServiceProfile a where a.isActive=true and a.portName=:portName ", models.serviceModule.HServiceProfile.class).setParameter("portName", port).getResultList();
*/

        /*if(CommonController.checkSessionIfExist()){*/
        ObjectMapper objectMapper = new ObjectMapper();
        String a = objectMapper.writeValueAsString(customerDetailList);
        JsonNode jsonNode = objectMapper.readTree(a);
        Logger.info("jsonNode********"+jsonNode);

        if (jsonNode.has(0))
            return ok(jsonNode);
        else {
            Logger.info("record not found");
            return ok("");
        }
        /*}
        else{
            return Results.badRequest("Error!!!");
        }*/
    }





















}
