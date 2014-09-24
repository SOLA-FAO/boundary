package org.sola.services.boundary.transferobjects.claim;

import java.util.List;
import org.sola.services.common.contracts.AbstractReadWriteTO;

public class SectionPayloadTO extends AbstractReadWriteTO {
    private String id;
    private String name;
    private String formPayloadId;
    private String displayName;
    private String elementName;
    private String elementDisplayName;
    private int maxOccurrences;
    private int minOccurrences;
    private List<SectionElementPayloadTO> sectionElementPayloadList;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormPayloadId() {
        return formPayloadId;
    }

    public void setFormPayloadId(String formPayloadId) {
        this.formPayloadId = formPayloadId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementDisplayName() {
        return elementDisplayName;
    }

    public void setElementDisplayName(String elementDisplayName) {
        this.elementDisplayName = elementDisplayName;
    }

    public int getMaxOccurrences() {
        return maxOccurrences;
    }

    public void setMaxOccurrences(int maxOccurrences) {
        this.maxOccurrences = maxOccurrences;
    }

    public int getMinOccurrences() {
        return minOccurrences;
    }

    public void setMinOccurrences(int minOccurrences) {
        this.minOccurrences = minOccurrences;
    }
    
    public List<SectionElementPayloadTO> getSectionElementPayloadList() {
        return sectionElementPayloadList;
    }

    public void setSectionElementPayloadList(List<SectionElementPayloadTO> sectionElementPayloadList) {
        this.sectionElementPayloadList = sectionElementPayloadList;
    }
    
    public SectionPayloadTO(){
    }
}
