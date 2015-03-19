/**
 * ******************************************************************************************
 * Copyright (C) 2015 - Food and Agriculture Organization of the United Nations (FAO).
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice,this list
 *       of conditions and the following disclaimer.
 *    2. Redistributions in binary form must reproduce the above copyright notice,this list
 *       of conditions and the following disclaimer in the documentation and/or other
 *       materials provided with the distribution.
 *    3. Neither the name of FAO nor the names of its contributors may be used to endorse or
 *       promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.transferobjects.transaction;

import java.util.List;
import org.sola.services.boundary.transferobjects.cadastre.CadastreObjectTO;
import org.sola.services.boundary.transferobjects.cadastre.CadastreObjectTargetTO;
import org.sola.services.boundary.transferobjects.cadastre.SurveyPointTO;

/**
 *
 * @author Elton Manoku
 */
public class TransactionCadastreChangeTO extends TransactionTO {

    List<CadastreObjectTO> CadastreObjectList;
    List<SurveyPointTO> surveyPointList;
    private List<CadastreObjectTargetTO> cadastreObjectTargetList;
    private List<TransactionSourceTO> transactionSourceList;

    public List<CadastreObjectTO> getCadastreObjectList() {
        return CadastreObjectList;
    }

    public void setCadastreObjectList(List<CadastreObjectTO> CadastreObjectList) {
        this.CadastreObjectList = CadastreObjectList;
    }

    public List<SurveyPointTO> getSurveyPointList() {
        return surveyPointList;
    }

    public void setSurveyPointList(List<SurveyPointTO> surveyPointList) {
        this.surveyPointList = surveyPointList;
    }

    public List<CadastreObjectTargetTO> getCadastreObjectTargetList() {
        return cadastreObjectTargetList;
    }

    public void setCadastreObjectTargetList(List<CadastreObjectTargetTO> cadastreObjectTargetList) {
        this.cadastreObjectTargetList = cadastreObjectTargetList;
    }

    public List<TransactionSourceTO> getTransactionSourceList() {
        return transactionSourceList;
    }

    public void setTransactionSourceList(List<TransactionSourceTO> transactionSourceList) {
        this.transactionSourceList = transactionSourceList;
    }

}
