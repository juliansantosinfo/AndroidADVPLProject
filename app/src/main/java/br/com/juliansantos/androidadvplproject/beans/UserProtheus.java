package br.com.juliansantos.androidadvplproject.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Class that defines an protheus user.
 * @author Julian de Almeida Santos
 * @since 04/03/2019
 */
public class UserProtheus implements Serializable {

    @SerializedName("CID")
    @Expose
    private String id;
    @SerializedName("CCODIGO")
    @Expose
    private String code;
    @SerializedName("CNOME")
    @Expose
    private String name;
    @SerializedName("CEMAIL")
    @Expose
    private String email;
    @SerializedName("CDEPTO")
    @Expose
    private String department;
    @SerializedName("CCARGO")
    @Expose
    private String office;
    @SerializedName("CRAMAL")
    @Expose
    private String branch;
    @SerializedName("CNIVELCAMPO")
    @Expose
    private Integer levelFields;
    @SerializedName("NMAXACESS")
    @Expose
    private Integer simultaneousAccesses;
    @SerializedName("LBLOQUEADO")
    @Expose
    private Boolean isBlocked;
    @SerializedName("DINCSYSTEM")
    @Expose
    private String dateIncluded;
    @SerializedName("DULTMUPD")
    @Expose
    private String lastChange;
    @SerializedName("DEXPIRA")
    @Expose
    private String expirationDate;
    @SerializedName("NEXPIRA")
    @Expose
    private Integer daysToExpire;
    @SerializedName("LLISTENER")
    @Expose
    private Boolean isListener;
    @SerializedName("LUPDNEXT")
    @Expose
    private Boolean changePassword;
    @SerializedName("LUPDSENHA")
    @Expose
    private Boolean passInNextLogon;

    public UserProtheus() {
    }

    public UserProtheus(String id, String code, String name, String email, String department, String office, String branch, Integer levelFields, Integer simultaneousAccesses, Boolean isBlocked, String dateIncluded, String lastChange, String expirationDate, Integer daysToExpire, Boolean isListener, Boolean changePassword, Boolean passInNextLogon) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
        this.department = department;
        this.office = office;
        this.branch = branch;
        this.levelFields = levelFields;
        this.simultaneousAccesses = simultaneousAccesses;
        this.isBlocked = isBlocked;
        this.dateIncluded = dateIncluded;
        this.lastChange = lastChange;
        this.expirationDate = expirationDate;
        this.daysToExpire = daysToExpire;
        this.isListener = isListener;
        this.changePassword = changePassword;
        this.passInNextLogon = passInNextLogon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getLevelFields() {
        return levelFields;
    }

    public void setLevelFields(Integer levelFields) {
        this.levelFields = levelFields;
    }

    public Integer getSimultaneousAccesses() {
        return simultaneousAccesses;
    }

    public void setSimultaneousAccesses(Integer simultaneousAccesses) {
        this.simultaneousAccesses = simultaneousAccesses;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public String getDateIncluded() {
        return dateIncluded;
    }

    public void setDateIncluded(String dateIncluded) {
        this.dateIncluded = dateIncluded;
    }

    public String getLastChange() {
        return lastChange;
    }

    public void setLastChange(String lastChange) {
        this.lastChange = lastChange;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getDaysToExpire() {
        return daysToExpire;
    }

    public void setDaysToExpire(Integer daysToExpire) {
        this.daysToExpire = daysToExpire;
    }

    public Boolean getListener() {
        return isListener;
    }

    public void setListener(Boolean listener) {
        isListener = listener;
    }

    public Boolean getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(Boolean changePassword) {
        this.changePassword = changePassword;
    }

    public Boolean getPassInNextLogon() {
        return passInNextLogon;
    }

    public void setPassInNextLogon(Boolean passInNextLogon) {
        this.passInNextLogon = passInNextLogon;
    }
}