package org.sola.services.boundary.transferobjects.claim;

import java.util.List;
import org.sola.services.common.contracts.AbstractReadWriteTO;

public class SectionTemplateTO extends AbstractReadWriteTO {
    private String id;
    private String name;
    private String formTemplateName;
    private String displayName;
    private int maxOccurrences;
    private int minOccurrences;
    private String errorMsg;
    private String elementName;
    private String elementDisplayName;
    private List<FieldTemplateTO> fieldTemplateList;
    
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

    public String getFormTemplateName() {
        return formTemplateName;
    }

    public void setFormTemplateName(String formTemplateName) {
        this.formTemplateName = formTemplateName;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
    
    public List<FieldTemplateTO> getFieldTemplateList() {
        return fieldTemplateList;
    }

    public void setFieldTemplateList(List<FieldTemplateTO> fieldTemplateList) {
        this.fieldTemplateList = fieldTemplateList;
    }

    public SectionTemplateTO(){
    }
}
