package org.sola.services.boundary.transferobjects.search;

public class PowerOfAttorneySearchParamsTO extends SourceSearchParamsTO {
    private String attorneyName;
    private String personName;
    
    public PowerOfAttorneySearchParamsTO(){
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
