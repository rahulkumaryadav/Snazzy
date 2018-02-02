
package models;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * Created by admin on 09-06-2017.
 */

@Entity
@Table(name = "STPL_USER")
public class STPL_USER {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name ="User_Name")
    private String userName ;

    @Column(name = "Password")
    private String password;

    @Column(name = "Role")
    private String role;

    public STPL_USER() {
    }

    public STPL_USER(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    @Transactional
    public static Long validateLogin(String name, String password){
        return JPA.em().createQuery("SELECT COUNT(a) FROM  STPL_USER a WHERE a.userName=:name AND a.password=:password", Long.class).setParameter("name",name).setParameter("password",password).getSingleResult().longValue();
    }

    @Transactional
    public static STPL_USER findByUser(String user){
        return JPA.em().createQuery("SELECT a FROM STPL_USER a WHERE a.userName=:user",STPL_USER.class).setParameter("user",user).getSingleResult();
    }

    @Transactional
    public static List getALlUser(){
        return JPA.em().createQuery("SELECT a FROM  STPL_USER a").getResultList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "STPL_USER{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}