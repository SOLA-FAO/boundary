package org.sola.services.boundary.transferobjects.claim;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.sola.services.common.contracts.AbstractReadWriteTO;

public class AttachmentChunkTO extends AbstractReadWriteTO {
    
    private String id;
    private String attachmentId;
    private String claimId;
    private long startPosition;
    private long size;
    private String md5;
    private String userName;
    private Date creationTime;
    
    public AttachmentChunkTO(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public long getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(long startPosition) {
        this.startPosition = startPosition;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @JsonIgnore
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonIgnore
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
