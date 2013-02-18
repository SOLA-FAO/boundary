/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.transferobjects.administrative;

import java.math.BigDecimal;
import org.sola.services.common.contracts.AbstractIdTO;

/**
 *
 * @author RizzoM
 */
public class SysRegPubDisStateLandTO extends AbstractIdTO {

    private String nameFirstpart;
    private String nameLastpart;
    private String landUsecode;
    private String baUnitId;
    private String value;
    private String objections;
    private BigDecimal size;
    private BigDecimal residential;
    private BigDecimal commercial;
    private BigDecimal agricultural;
    private BigDecimal industrial;
    private String publicNotificationDuration;

    public String getPublicNotificationDuration() {
        return publicNotificationDuration;
    }

    public void setPublicNotificationDuration(String publicNotificationDuration) {
        this.publicNotificationDuration = publicNotificationDuration;
    }

    public String getBaUnitId() {
        return baUnitId;
    }

    public void setBaUnitId(String baUnitId) {
        this.baUnitId = baUnitId;
    }

    public SysRegPubDisStateLandTO() {
        super();
    }

    public String getLandUsecode() {
        return landUsecode;
    }

    public void setLandUsecode(String landUsecode) {
        this.landUsecode = landUsecode;
    }

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

    public BigDecimal getAgricultural() {
        return agricultural;
    }

    public void setAgricultural(BigDecimal agricultural) {
        this.agricultural = agricultural;
    }

    public BigDecimal getCommercial() {
        return commercial;
    }

    public void setCommercial(BigDecimal commercial) {
        this.commercial = commercial;
    }

    public BigDecimal getIndustrial() {
        return industrial;
    }

    public void setIndustrial(BigDecimal industrial) {
        this.industrial = industrial;
    }

    public BigDecimal getResidential() {
        return residential;
    }

    public void setResidential(BigDecimal residential) {
        this.residential = residential;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String getObjections() {
        return objections;
    }

    public void setObjections(String objections) {
        this.objections = objections;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }
}
