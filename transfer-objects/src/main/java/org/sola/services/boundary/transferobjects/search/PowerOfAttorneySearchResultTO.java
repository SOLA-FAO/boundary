/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.transferobjects.search;

/**
 *
 * @author Solovov
 */
public class PowerOfAttorneySearchResultTO extends SourceSearchResultTO {
    private String attorneyName;
    private String personName;
    
    public PowerOfAttorneySearchResultTO(){
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
}
