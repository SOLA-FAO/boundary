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
public class SysRegProgressTO extends AbstractIdTO {

    private String block;
    private BigDecimal TotAppLod;
    private BigDecimal TotParcLoaded;
    private BigDecimal TotRecObj;
    private BigDecimal TotSolvedObj;
    private BigDecimal TotAppPDisp;
    private BigDecimal TotPrepCertificate;
    private BigDecimal TotIssuedCertificate;

    public BigDecimal getTotAppLod() {
        return TotAppLod;
    }

    public void setTotAppLod(BigDecimal TotAppLod) {
        this.TotAppLod = TotAppLod;
    }

    public BigDecimal getTotAppPDisp() {
        return TotAppPDisp;
    }

    public void setTotAppPDisp(BigDecimal TotAppPDisp) {
        this.TotAppPDisp = TotAppPDisp;
    }

    public BigDecimal getTotIssuedCertificate() {
        return TotIssuedCertificate;
    }

    public void setTotIssuedCertificate(BigDecimal TotIssuedCertificate) {
        this.TotIssuedCertificate = TotIssuedCertificate;
    }

    public BigDecimal getTotParcLoaded() {
        return TotParcLoaded;
    }

    public void setTotParcLoaded(BigDecimal TotParcLoaded) {
        this.TotParcLoaded = TotParcLoaded;
    }

    public BigDecimal getTotPrepCertificate() {
        return TotPrepCertificate;
    }

    public void setTotPrepCertificate(BigDecimal TotPrepCertificate) {
        this.TotPrepCertificate = TotPrepCertificate;
    }

    public BigDecimal getTotRecObj() {
        return TotRecObj;
    }

    public void setTotRecObj(BigDecimal TotRecObj) {
        this.TotRecObj = TotRecObj;
    }

    public BigDecimal getTotSolvedObj() {
        return TotSolvedObj;
    }

    public void setTotSolvedObj(BigDecimal TotSolvedObj) {
        this.TotSolvedObj = TotSolvedObj;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
       
}
