package org.sola.services.boundary.transferobjects.claim;

import java.util.List;
import org.sola.services.common.contracts.AbstractReadWriteTO;

public class SectionElementPayloadTO extends AbstractReadWriteTO {
    private String id;
    private String sectionPayloadId;
    private List<FieldPayloadTO> fieldsList;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<FieldPayloadTO> getFieldsList() {
        return fieldsList;
    }

    public void setFieldsList(List<FieldPayloadTO> fieldsList) {
        this.fieldsList = fieldsList;
    }

    public String getSectionPayloadId() {
        return sectionPayloadId;
    }

    public void setSectionPayloadId(String sectionPayloadId) {
        this.sectionPayloadId = sectionPayloadId;
    }
    
    public SectionElementPayloadTO(){
    }
}
