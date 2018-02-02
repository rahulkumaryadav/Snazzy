package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class UserTable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    public String strEmail;//this can be email or mobile no
    public String StrPassword;
    public String strCreatedBy;
    public String strModifiedBy;
    public Date dtCreated;
    public Date dtModified;

    public UserTable() {
    }

    public UserTable(String strEmail, String strPassword, String strCreatedBy, String strModifiedBy, Date dtCreated, Date dtModified) {
        this.strEmail = strEmail;
        StrPassword = strPassword;
        this.strCreatedBy = strCreatedBy;
        this.strModifiedBy = strModifiedBy;
        this.dtCreated = dtCreated;
        this.dtModified = dtModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getStrPassword() {
        return StrPassword;
    }

    public void setStrPassword(String strPassword) {
        StrPassword = strPassword;
    }

    public String getStrCreatedBy() {
        return strCreatedBy;
    }

    public void setStrCreatedBy(String strCreatedBy) {
        this.strCreatedBy = strCreatedBy;
    }

    public String getStrModifiedBy() {
        return strModifiedBy;
    }

    public void setStrModifiedBy(String strModifiedBy) {
        this.strModifiedBy = strModifiedBy;
    }

    public Date getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(Date dtCreated) {
        this.dtCreated = dtCreated;
    }

    public Date getDtModified() {
        return dtModified;
    }

    public void setDtModified(Date dtModified) {
        this.dtModified = dtModified;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", strEmail='" + strEmail + '\'' +
                ", StrPassword='" + StrPassword + '\'' +
                ", strCreatedBy='" + strCreatedBy + '\'' +
                ", strModifiedBy='" + strModifiedBy + '\'' +
                ", dtCreated=" + dtCreated +
                ", dtModified=" + dtModified +
                '}';
    }
}

