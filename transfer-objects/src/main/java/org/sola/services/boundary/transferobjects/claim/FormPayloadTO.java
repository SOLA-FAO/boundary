package org.sola.services.boundary.transferobjects.claim;

import java.util.List;
import org.sola.services.common.contracts.AbstractReadWriteTO;

public class FormPayloadTO extends AbstractReadWriteTO {
    private String id;
    private String formTemplateName;
    private String claimId;
    private List<SectionPayloadTO> sectionPayloadList;
    
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

    public String getFormTemplateName() {
        return formTemplateName;
    }

    public void setFormTemplateName(String formTemplateName) {
        this.formTemplateName = formTemplateName;
    }

    public List<SectionPayloadTO> getSectionPayloadList() {
        return sectionPayloadList;
    }

    public void setSectionPayloadList(List<SectionPayloadTO> sectionPayloadList) {
        this.sectionPayloadList = sectionPayloadList;
    }
    
    public FormPayloadTO(){
    }
}
