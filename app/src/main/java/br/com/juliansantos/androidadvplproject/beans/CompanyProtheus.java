package br.com.juliansantos.androidadvplproject.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Class that defines an protheus company.
 * @author Julian de Almeida Santos
 * @since 05/03/2019
 */
public class CompanyProtheus implements Serializable {

    @SerializedName("CCODIGO")
    @Expose
    public String code;
    @SerializedName("CCODFIL")
    @Expose
    public String branch;
    @SerializedName("CNOME")
    @Expose
    public String name;
    @SerializedName("CNOMECOMPLETO")
    @Expose
    public String fullName;
    @SerializedName("CCGC")
    @Expose
    public String CNPJ;
    @SerializedName("CINSCRICAO")
    @Expose
    public String IE;
    @SerializedName("CTELEFONE")
    @Expose
    public String phone;
    @SerializedName("CFAX")
    @Expose
    public String fax;
    @SerializedName("OENDCOBRANCA")
    @Expose
    public AddressProtheus billingAddress;
    @SerializedName("OENDENTREGA")
    @Expose
    public AddressProtheus deliveryAddress;

    public CompanyProtheus() {
    }

    public CompanyProtheus(String code, String branch, String name, String fullName, String CNPJ, String IE, String phone, String fax, AddressProtheus billingAddress, AddressProtheus deliveryAddress) {
        this.code = code;
        this.branch = branch;
        this.name = name;
        this.fullName = fullName;
        this.CNPJ = CNPJ;
        this.IE = IE;
        this.phone = phone;
        this.fax = fax;
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public AddressProtheus getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressProtheus billingAddress) {
        this.billingAddress = billingAddress;
    }

    public AddressProtheus getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(AddressProtheus deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "CompanyProtheus{" +
                "code='" + code + '\'' +
                ", branch='" + branch + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", CNPJ='" + CNPJ + '\'' +
                ", IE='" + IE + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", billingAddress=" + billingAddress +
                ", deliveryAddress=" + deliveryAddress +
                '}';
    }
}
