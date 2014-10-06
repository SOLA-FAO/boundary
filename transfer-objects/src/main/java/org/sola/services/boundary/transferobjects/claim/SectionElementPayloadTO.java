package org.sola.services.boundary.transferobjects.claim;

import java.util.List;
import org.sola.services.common.contracts.AbstractReadWriteTO;

public class SectionElementPayloadTO extends AbstractReadWriteTO {
    private String id;
    private String sectionPayloadId;
    private List<FieldPayloadTO> fieldPayloadList;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<FieldPayloadTO> getFieldPayloadList() {
        return fieldPayloadList;
    }

    public void setFieldPayloadList(List<FieldPayloadTO> fieldPayloadList) {
        this.fieldPayloadList = fieldPayloadList;
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
