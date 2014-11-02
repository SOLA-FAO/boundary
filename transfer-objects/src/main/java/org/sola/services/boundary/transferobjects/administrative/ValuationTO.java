/**
 * ******************************************************************************************
 * Copyright (C) 2014 - Food and Agriculture Organization of the United Nations
 * (FAO). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,this
 * list of conditions and the following disclaimer. 2. Redistributions in binary
 * form must reproduce the above copyright notice,this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. 3. Neither the name of FAO nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT,STRICT LIABILITY,OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.services.boundary.transferobjects.administrative;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.sola.services.boundary.transferobjects.casemanagement.SourceTO;
import org.sola.services.common.contracts.AbstractIdTO;

public class ValuationTO extends AbstractIdTO {

    private String id;
    private String nr;
    private String baUnitId;
    private BigDecimal amount;
    private Date valuationDate;
    private String typeCode;
    private List<SourceTO> sourceList;
    private String source;
    private String description;
    private String transactionId;
    private String classificationCode;
    private String redactCode;

    public ValuationTO() {
        super();
    }

    public void addSource(SourceTO source) {
        if (sourceList == null) {
            sourceList = new ArrayList<SourceTO>();
        }
        sourceList.add(source);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nr
     */
    public String getNr() {
        return nr;
    }

    /**
     * @param nr the nr to set
     */
    public void setNr(String nr) {
        this.nr = nr;
    }

    /**
     * @return the baUnitId
     */
    public String getBaUnitId() {
        return baUnitId;
    }

    /**
     * @param baUnitId the baUnitId to set
     */
    public void setBaUnitId(String baUnitId) {
        this.baUnitId = baUnitId;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the valuationDate
     */
    public Date getValuationDate() {
        return valuationDate;
    }

    /**
     * @param valuationDate the valuationDate to set
     */
    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
    }

    /**
     * @return the typeCode
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * @param typeCode the typeCode to set
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * @return the sourceList
     */
    public List<SourceTO> getSourceList() {
        return sourceList;
    }

    /**
     * @param sourceList the sourceList to set
     */
    public void setSourceList(List<SourceTO> sourceList) {
        this.sourceList = sourceList;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the transcationId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transcationId the transcationId to set
     */
    public void setTransactionId(String transcationId) {
        this.transactionId = transcationId;
    }

    /**
     * @return the classificationCode
     */
    @Override
    public String getClassificationCode() {
        return classificationCode;
    }

    /**
     * @param classificationCode the classificationCode to set
     */
    public void setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode;
    }

    /**
     * @return the redactCode
     */
    @Override
    public String getRedactCode() {
        return redactCode;
    }

    /**
     * @param redactCode the redactCode to set
     */
    public void setRedactCode(String redactCode) {
        this.redactCode = redactCode;
    }
}
