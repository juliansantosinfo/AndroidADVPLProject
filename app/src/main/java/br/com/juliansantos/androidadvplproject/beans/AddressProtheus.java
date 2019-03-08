package br.com.juliansantos.androidadvplproject.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Class that defines an protheus address.
 * @author Julian de Almeida Santos
 * @since 05/03/2019
 */
public class AddressProtheus implements Serializable {

    @SerializedName("CRUA")
    @Expose
    public String street;
    @SerializedName("CNUMERO")
    @Expose
    public String number;
    @SerializedName("CBAIRRO")
    @Expose
    public String neighborhood;
    @SerializedName("CCEP")
    @Expose
    public String CEP;
    @SerializedName("CCODIGOMUNICIPIO")
    @Expose
    public String cityCode;
    @SerializedName("CCODIGOPAIS")
    @Expose
    public String countryCode;
    @SerializedName("CCOMPLEMENTO")
    @Expose
    public String complement;
    @SerializedName("CMUNICIPIO")
    @Expose
    public String city;
    @SerializedName("CESTADO")
    @Expose
    public String state;
    @SerializedName("CPAIS")
    @Expose
    public String country;

    public AddressProtheus() {
    }

    public AddressProtheus(String street, String number, String neighborhood, String CEP, String cityCode, String countryCode, String complement, String city, String state, String country) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.CEP = CEP;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
