package models;

import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 1/4/2017.
 */
@Entity
@Table(name = "RankMaster")
public class RankMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String strRankName;
    private Date dtCreated;
    private Date dtModified;
    private String strCreatedBy;
    private String strModifiedBy;
    private boolean isActive;
    private String strRemarks;

    public static RankMaster findById(Long id) {
        return JPA.em().find(RankMaster.class, id);
    }

    public RankMaster(){}

    public RankMaster(String strRankName, Date dtCreated, Date dtModified, String strCreatedBy, String strModifiedBy, boolean isActive, String strRemarks) {
        this.strRankName = strRankName;
        this.dtCreated = dtCreated;
        this.dtModified = dtModified;
        this.strCreatedBy = strCreatedBy;
        this.strModifiedBy = strModifiedBy;
        this.isActive = isActive;
        this.strRemarks = strRemarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrRankName() {
        return strRankName;
    }

    public void setStrRankName(String strRankName) {
        this.strRankName = strRankName;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getStrRemarks() {
        return strRemarks;
    }

    public void setStrRemarks(String strRemarks) {
        this.strRemarks = strRemarks;
    }

    @Override
    public String toString() {
        return "RankMaster{" +
                "id=" + id +
                ", strRankName='" + strRankName + '\'' +
                ", dtCreated=" + dtCreated +
                ", dtModified=" + dtModified +
                ", strCreatedBy='" + strCreatedBy + '\'' +
                ", strModifiedBy='" + strModifiedBy + '\'' +
                ", isActive=" + isActive +
                ", strRemarks='" + strRemarks + '\'' +
                '}';
    }
}
