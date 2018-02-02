package models;


import play.Logger;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="CodeGenerator")
public class CodeGenerator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String strItemDescription;
    public String strPurchesRate;
    // public String strUniqueCode;
    public String strSalingRate;
    public String strCode;//this is the unique oode for the product
    public String strCreatedBy;
    public String strModifiedBy;
    public Date dtCreated;
    public Date dtModified;
    public Boolean activeFlag;

    public CodeGenerator() {
    }

    public CodeGenerator(String strItemDescription, String strPurchesRate, String strSalingRate, String strCode, String strCreatedBy, String strModifiedBy, Date dtCreated, Date dtModified, boolean activeFlag) {
        this.strItemDescription = strItemDescription;
        this.strPurchesRate = strPurchesRate;
        this.strSalingRate = strSalingRate;
        this.strCode = strCode;
        this.strCreatedBy = strCreatedBy;
        this.strModifiedBy = strModifiedBy;
        this.dtCreated = dtCreated;
        this.dtModified = dtModified;
        this.activeFlag = activeFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrItemDescription() {
        return strItemDescription;
    }

    public void setStrItemDescription(String strItemDescription) {
        this.strItemDescription = strItemDescription;
    }

    public String getStrPurchesRate() {
        return strPurchesRate;
    }

    public void setStrPurchesRate(String strPurchesRate) {
        this.strPurchesRate = strPurchesRate;
    }

    public String getStrSalingRate() {
        return strSalingRate;
    }

    public void setStrSalingRate(String strSalingRate) {
        this.strSalingRate = strSalingRate;
    }

    public String getStrCode() {
        return strCode;
    }

    public void setStrCode(String strCode) {
        this.strCode = strCode;
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

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    @Override
    public String toString() {
        return "CodeGenerator{" +
                "id=" + id +
                ", strItemDescription='" + strItemDescription + '\'' +
                ", strPurchesRate='" + strPurchesRate + '\'' +
                ", strSalingRate='" + strSalingRate + '\'' +
                ", strCode='" + strCode + '\'' +
                ", strCreatedBy='" + strCreatedBy + '\'' +
                ", strModifiedBy='" + strModifiedBy + '\'' +
                ", dtCreated=" + dtCreated +
                ", dtModified=" + dtModified +
                ", activeFlag=" + activeFlag +
                '}';
    }
}
