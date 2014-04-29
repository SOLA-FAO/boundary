package org.sola.services.boundary.transferobjects.claim;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "attachmentBinary")
@XmlAccessorType(XmlAccessType.NONE)
public class AttachmentBinaryTO extends AttachmentTO {
    
    @XmlElement
    private byte[] body;
    
    public AttachmentBinaryTO(){
        super();
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
