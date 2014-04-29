package org.sola.services.boundary.transferobjects.claim;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.sola.services.common.contracts.AbstractReadWriteTO;

@XmlRootElement(name = "attachment")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType (propOrder={"id", "typeCode", "referenceNr", "documentDate", "fileName", 
    "fileExtension", "mimeType", "size", "description"})
public class AttachmentTO extends AbstractReadWriteTO {
    @XmlElement
    private String id;
    @XmlElement
    private String typeCode;
    @XmlElement
    private String referenceNr;
    @XmlElement
    private long size;
    @XmlElement
    private String mimeType;
    @XmlElement
    private String fileName;
    @XmlElement
    private String fileExtension;
    @XmlElement
    private Date documentDate;
    @XmlElement
    private String description;
    @XmlElement
    private String md5;
    
    public AttachmentTO(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getReferenceNr() {
        return referenceNr;
    }

    public void setReferenceNr(String referenceNr) {
        this.referenceNr = referenceNr;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
