package org.sola.services.boundary.transferobjects.claim;

import java.util.List;
import org.sola.services.common.contracts.AbstractReadWriteTO;

public class FormTemplateTO extends AbstractReadWriteTO {
    private String name;
    private String displayName;
    private List<SectionTemplateTO> sectionTemplateList;
    
    public FormTemplateTO(){
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SectionTemplateTO> getSectionTemplateList() {
        return sectionTemplateList;
    }

    public void setSectionTemplateList(List<SectionTemplateTO> sectionTemplateList) {
        this.sectionTemplateList = sectionTemplateList;
    }
}
