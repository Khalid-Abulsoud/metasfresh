/*
 * Patient - Warenwirtschaft (Basis)
 * Synchronisation der Patienten mit der Warenwirtschaft
 *
 * OpenAPI spec version: 1.0.5
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import org.threeten.bp.LocalDate;
/**
 * Hospital
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-02-10T19:03:01.229Z[GMT]")
public class Hospital {
  @SerializedName("_id")
  private String _id = null;

  @SerializedName("company")
  private String company = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("companyName")
  private String companyName = null;

  @SerializedName("additionalCompanyName")
  private String additionalCompanyName = null;

  @SerializedName("address")
  private String address = null;

  @SerializedName("postalCode")
  private String postalCode = null;

  @SerializedName("city")
  private String city = null;

  @SerializedName("phone")
  private String phone = null;

  @SerializedName("fax")
  private String fax = null;

  @SerializedName("email")
  private String email = null;

  @SerializedName("website")
  private String website = null;

  @SerializedName("timestamp")
  private LocalDate timestamp = null;

  public Hospital _id(String _id) {
    this._id = _id;
    return this;
  }

   /**
   * Id
   * @return _id
  **/
  @Schema(example = "5ab233be9d69c74b68cec28e", required = true, description = "Id")
  public String getId() {
    return _id;
  }

  public void setId(String _id) {
    this._id = _id;
  }

  public Hospital company(String company) {
    this.company = company;
    return this;
  }

   /**
   * Get company
   * @return company
  **/
  @Schema(example = "Heilig-Geist-bingen", description = "")
  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public Hospital name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @Schema(example = "Heilig-Geist-Hospital Bingen", description = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Hospital companyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

   /**
   * Get companyName
   * @return companyName
  **/
  @Schema(example = "Heilig-Geist-Hospital Bingen", description = "")
  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Hospital additionalCompanyName(String additionalCompanyName) {
    this.additionalCompanyName = additionalCompanyName;
    return this;
  }

   /**
   * Get additionalCompanyName
   * @return additionalCompanyName
  **/
  @Schema(example = "Stiftung des bürgerlichen Rechts", description = "")
  public String getAdditionalCompanyName() {
    return additionalCompanyName;
  }

  public void setAdditionalCompanyName(String additionalCompanyName) {
    this.additionalCompanyName = additionalCompanyName;
  }

  public Hospital address(String address) {
    this.address = address;
    return this;
  }

   /**
   * Get address
   * @return address
  **/
  @Schema(example = "Kapuzinerstr. 15-17", description = "")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Hospital postalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

   /**
   * Get postalCode
   * @return postalCode
  **/
  @Schema(example = "55411", description = "")
  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public Hospital city(String city) {
    this.city = city;
    return this;
  }

   /**
   * Get city
   * @return city
  **/
  @Schema(example = "Bingen", description = "")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Hospital phone(String phone) {
    this.phone = phone;
    return this;
  }

   /**
   * Get phone
   * @return phone
  **/
  @Schema(example = "06721-907-0", description = "")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Hospital fax(String fax) {
    this.fax = fax;
    return this;
  }

   /**
   * Get fax
   * @return fax
  **/
  @Schema(example = "06721-907-133", description = "")
  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public Hospital email(String email) {
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @Schema(example = "info@heilig-geist-hospital.de", description = "")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Hospital website(String website) {
    this.website = website;
    return this;
  }

   /**
   * Get website
   * @return website
  **/
  @Schema(example = "http://www.heilig-geist-hospital.de/", description = "")
  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public Hospital timestamp(LocalDate timestamp) {
    this.timestamp = timestamp;
    return this;
  }

   /**
   * Der Zeitstempel der letzten Änderung
   * @return timestamp
  **/
  @Schema(description = "Der Zeitstempel der letzten Änderung")
  public LocalDate getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDate timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Hospital hospital = (Hospital) o;
    return Objects.equals(this._id, hospital._id) &&
        Objects.equals(this.company, hospital.company) &&
        Objects.equals(this.name, hospital.name) &&
        Objects.equals(this.companyName, hospital.companyName) &&
        Objects.equals(this.additionalCompanyName, hospital.additionalCompanyName) &&
        Objects.equals(this.address, hospital.address) &&
        Objects.equals(this.postalCode, hospital.postalCode) &&
        Objects.equals(this.city, hospital.city) &&
        Objects.equals(this.phone, hospital.phone) &&
        Objects.equals(this.fax, hospital.fax) &&
        Objects.equals(this.email, hospital.email) &&
        Objects.equals(this.website, hospital.website) &&
        Objects.equals(this.timestamp, hospital.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_id, company, name, companyName, additionalCompanyName, address, postalCode, city, phone, fax, email, website, timestamp);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Hospital {\n");
    
    sb.append("    _id: ").append(toIndentedString(_id)).append("\n");
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    companyName: ").append(toIndentedString(companyName)).append("\n");
    sb.append("    additionalCompanyName: ").append(toIndentedString(additionalCompanyName)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    fax: ").append(toIndentedString(fax)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    website: ").append(toIndentedString(website)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
