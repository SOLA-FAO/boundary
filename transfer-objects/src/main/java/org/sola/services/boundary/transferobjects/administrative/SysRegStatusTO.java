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
package org.sola.services.boundary.transferobjects.administrative;

import java.math.BigDecimal;
import org.sola.services.common.contracts.AbstractIdTO;

/**
 *
 * @author RizzoM
 */
public class SysRegStatusTO extends AbstractIdTO {

    private String block;
    private BigDecimal appLodgedNoSP;
    private BigDecimal appLodgedSP;
    private BigDecimal SPnoApp;
    private BigDecimal appPendObj;
    private BigDecimal appIncDoc;
    private BigDecimal appPDisp;
    private BigDecimal appCompPDispNoCert;
    private BigDecimal appCertificate;
    private BigDecimal appPrLand;
    private BigDecimal appPubLand;
    private BigDecimal TotApp;
    private BigDecimal TotSurvPar;

    public BigDecimal getSPnoApp() {
        return SPnoApp;
    }

    public void setSPnoApp(BigDecimal SPnoApp) {
        this.SPnoApp = SPnoApp;
    }

    public BigDecimal getTotApp() {
        return TotApp;
    }

    public void setTotApp(BigDecimal TotApp) {
        this.TotApp = TotApp;
    }

    public BigDecimal getTotSurvPar() {
        return TotSurvPar;
    }

    public void setTotSurvPar(BigDecimal TotSurvPar) {
        this.TotSurvPar = TotSurvPar;
    }

    public BigDecimal getAppCertificate() {
        return appCertificate;
    }

    public void setAppCertificate(BigDecimal appCertificate) {
        this.appCertificate = appCertificate;
    }

    public BigDecimal getAppCompPDispNoCert() {
        return appCompPDispNoCert;
    }

    public void setAppCompPDispNoCert(BigDecimal appCompPDispNoCert) {
        this.appCompPDispNoCert = appCompPDispNoCert;
    }

    public BigDecimal getAppIncDoc() {
        return appIncDoc;
    }

    public void setAppIncDoc(BigDecimal appIncDoc) {
        this.appIncDoc = appIncDoc;
    }

    public BigDecimal getAppLodgedNoSP() {
        return appLodgedNoSP;
    }

    public void setAppLodgedNoSP(BigDecimal appLodgedNoSP) {
        this.appLodgedNoSP = appLodgedNoSP;
    }

    public BigDecimal getAppLodgedSP() {
        return appLodgedSP;
    }

    public void setAppLodgedSP(BigDecimal appLodgedSP) {
        this.appLodgedSP = appLodgedSP;
    }

    public BigDecimal getAppPDisp() {
        return appPDisp;
    }

    public void setAppPDisp(BigDecimal appPDisp) {
        this.appPDisp = appPDisp;
    }

    public BigDecimal getAppPendObj() {
        return appPendObj;
    }

    public void setAppPendObj(BigDecimal appPendObj) {
        this.appPendObj = appPendObj;
    }

    public BigDecimal getAppPrLand() {
        return appPrLand;
    }

    public void setAppPrLand(BigDecimal appPrLand) {
        this.appPrLand = appPrLand;
    }

    public BigDecimal getAppPubLand() {
        return appPubLand;
    }

    public void setAppPubLand(BigDecimal appPubLand) {
        this.appPubLand = appPubLand;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
