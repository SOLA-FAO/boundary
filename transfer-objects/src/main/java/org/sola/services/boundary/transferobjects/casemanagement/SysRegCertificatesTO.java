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
    private String baUnitId;
    private String nr;

    public String getBaUnitId() {
        return baUnitId;
    }

    public void setBaUnitId(String baUnitId) {
        this.baUnitId = baUnitId;
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

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

}
