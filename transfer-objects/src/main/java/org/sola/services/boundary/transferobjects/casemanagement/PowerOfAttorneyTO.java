/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.transferobjects.casemanagement;

import org.sola.services.common.contracts.AbstractIdTO;

public class PowerOfAttorneyTO extends AbstractIdTO {
    
    private SourceTO source;
    private String personName;
    private String attorneyName;
    
    public PowerOfAttorneyTO(){
        super();
    }

    public String getAttorneyName() {
        return attorneyName;
    }

    public void setAttorneyName(String attorneyName) {
        this.attorneyName = attorneyName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public SourceTO getSource() {
        return source;
    }

    public void setSource(SourceTO source) {
        this.source = source;
    }
}
