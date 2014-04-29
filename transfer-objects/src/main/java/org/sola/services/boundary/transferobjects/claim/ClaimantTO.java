package org.sola.services.boundary.transferobjects.claim;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.sola.services.common.contracts.AbstractReadWriteTO;

@XmlRootElement(name = "claimant")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType (propOrder={"id","name", "lastName", "birthDate", 
    "idTypeCode", "idNumber", "genderCode", "mobilePhone", "phone", 
    "email", "address"})
public class ClaimantTO extends AbstractReadWriteTO {
    @XmlElement
    private String id;
    @XmlElement
    private String name;
    @XmlElement
    private String lastName;
    @XmlElement
    private String idTypeCode;
    @XmlElement
    private String idNumber;
    @XmlElement
    private Date birthDate;
    @XmlElement
    private String genderCode;
    @XmlElement
    private String mobilePhone;
    @XmlElement
    private String phone;
    @XmlElement
    private String email;
    @XmlElement
    private String address;
    
    public ClaimantTO(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdTypeCode() {
        return idTypeCode;
    }

    public void setIdTypeCode(String idTypeCode) {
        this.idTypeCode = idTypeCode;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
