package org.sola.services.boundary.transferobjects.claim;

import java.util.List;
import org.sola.services.common.contracts.AbstractReadWriteTO;

public class ClaimShareTO extends AbstractReadWriteTO {
    private String id;
    private String claimId;
    private Short nominator;
    private Short denominator;
    private List<ClaimPartyTO> owners;
    
    public ClaimShareTO(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public Short getNominator() {
        return nominator;
    }

    public void setNominator(Short nominator) {
        this.nominator = nominator;
    }

    public Short getDenominator() {
        return denominator;
    }

    public void setDenominator(Short denominator) {
        this.denominator = denominator;
    }

    public List<ClaimPartyTO> getOwners() {
        return owners;
    }

    public void setOwners(List<ClaimPartyTO> owners) {
        this.owners = owners;
    }
}
