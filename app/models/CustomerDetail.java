package models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name ="CustomerDetail")
public class CustomerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public String strCreatedBy;
    public String strModifiedBy;
    public String quantity;
    public Date dtCreated;
    public Date dtModified;
   @OneToOne
    public CodeGenerator codeGenerator;
    public String uniqueInvoiceNumber;
    public String strPurchesRate;
    public String strUniqueCode;
    public String strSalingRate;
    public String strCode;//this is the unique oode for the product
    public String total;


    public CustomerDetail() {
    }

    public CustomerDetail(String strCreatedBy, String strModifiedBy, String quantity, Date dtCreated, Date dtModified, CodeGenerator codeGenerator, String uniqueInvoiceNumber, String strPurchesRate, String strUniqueCode, String strSalingRate, String strCode, String total) {
        this.strCreatedBy = strCreatedBy;
        this.strModifiedBy = strModifiedBy;
        this.quantity = quantity;
        this.dtCreated = dtCreated;
        this.dtModified = dtModified;
        this.codeGenerator = codeGenerator;
        this.uniqueInvoiceNumber = uniqueInvoiceNumber;
        this.strPurchesRate = strPurchesRate;
        this.strUniqueCode = strUniqueCode;
        this.strSalingRate = strSalingRate;
        this.strCode = strCode;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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

    public CodeGenerator getCodeGenerator() {
        return codeGenerator;
    }

    public void setCodeGenerator(CodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
    }

    public String getUniqueInvoiceNumber() {
        return uniqueInvoiceNumber;
    }

    public void setUniqueInvoiceNumber(String uniqueInvoiceNumber) {
        this.uniqueInvoiceNumber = uniqueInvoiceNumber;
    }

    public String getStrPurchesRate() {
        return strPurchesRate;
    }

    public void setStrPurchesRate(String strPurchesRate) {
        this.strPurchesRate = strPurchesRate;
    }

    public String getStrUniqueCode() {
        return strUniqueCode;
    }

    public void setStrUniqueCode(String strUniqueCode) {
        this.strUniqueCode = strUniqueCode;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CustomerDetail{" +
                "id=" + id +
                ", strCreatedBy='" + strCreatedBy + '\'' +
                ", strModifiedBy='" + strModifiedBy + '\'' +
                ", quantity='" + quantity + '\'' +
                ", dtCreated=" + dtCreated +
                ", dtModified=" + dtModified +
                ", codeGenerator=" + codeGenerator +
                ", uniqueInvoiceNumber='" + uniqueInvoiceNumber + '\'' +
                ", strPurchesRate='" + strPurchesRate + '\'' +
                ", strUniqueCode='" + strUniqueCode + '\'' +
                ", strSalingRate='" + strSalingRate + '\'' +
                ", strCode='" + strCode + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
