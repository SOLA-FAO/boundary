/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.transferobjects.administrative;

import java.util.Date;
import org.sola.services.common.contracts.AbstractTO;

/**
 *
 * @author RizzoM
 */
public class SysRegManagementParamsTO extends AbstractTO {
    
    private Date fromDate;
    private Date toDate;
     private String nameLastpart;
    
    public SysRegManagementParamsTO(){
    }

    public String getNameLastpart() {
        return nameLastpart;
    }

    public void setNameLastpart(String nameLastpart) {
        this.nameLastpart = nameLastpart;
    }
    
     public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
      
}

