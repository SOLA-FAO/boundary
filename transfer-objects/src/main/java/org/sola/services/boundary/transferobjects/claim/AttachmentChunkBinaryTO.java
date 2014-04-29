package org.sola.services.boundary.transferobjects.claim;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "attachmentChunk")
@XmlAccessorType(XmlAccessType.NONE)
public class AttachmentChunkBinaryTO extends AttachmentChunkTO {
    
    @XmlElement
    private byte[] body;
    
    public AttachmentChunkBinaryTO(){
        super();
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
