/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.transferobjects.casemanagement;

import java.math.BigDecimal;
import java.util.Date;
import org.sola.services.common.contracts.AbstractIdTO;

/**
 *
 * @author RizzoM
 */
public class SysRegCertificatesTO extends AbstractIdTO {

    private String nameFirstpart;
    private String nameLastpart;
//    private BigDecimal size;
//    private String landUsecode;
    private String baUnitId;
//    private String concatenatedName;
    private String nr;
//    private String application_status;
//    private String name;
//    private String typeCode;
//    private Date creationDate;

//    public Date getCreationDate() {
//        return creationDate;
//    }
//
//    public void setCreationDate(Date creationDate) {
//        this.creationDate = creationDate;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getTypeCode() {
//        return typeCode;
//    }
//
//    public void setTypeCode(String typeCode) {
//        this.typeCode = typeCode;
//    }
//
//    public String getApplication_status() {
//        return application_status;
//    }
//
//    public void setApplication_status(String application_status) {
//        this.application_status = application_status;
//    }

    public String getBaUnitId() {
        return baUnitId;
    }

    public void setBaUnitId(String baUnitId) {
        this.baUnitId = baUnitId;
    }

//    public String getConcatenatedName() {
//        return concatenatedName;
//    }
//
//    public void setConcatenatedName(String concatenatedName) {
//        this.concatenatedName = concatenatedName;
//    }
//
//    public String getLandUsecode() {
//        return landUsecode;
//    }
//
//    public void setLandUsecode(String landUsecode) {
//        this.landUsecode = landUsecode;
//    }
//
    public String getNameFirstpart() {
        return nameFirstpart;
    }

    public void setNameFirstpart(String nameFirstpart) {
        this.nameFirstpart = nameFirstpart;
    }

    public String getNameLastpart() {
        return nameLastpart;
    }

    public void setNameLastpart(String nameLastpart) {
        this.nameLastpart = nameLastpart;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

//    public BigDecimal getSize() {
//        return size;
//    }
//
//    public void setSize(BigDecimal size) {
//        this.size = size;
//    }
}
