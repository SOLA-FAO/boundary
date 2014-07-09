package org.sola.services.boundary.transferobjects.claim;

import java.util.Date;
import org.sola.services.common.contracts.AbstractReadWriteTO;

public class ClaimCommentTO extends AbstractReadWriteTO {
    private String id;
    private String claimId;
    private String comment;
    private String commentUser;
    private Date creationTime;
    
    public ClaimCommentTO(){
        super();
    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    
}
