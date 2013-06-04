/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.transferobjects.configuration;

import org.sola.services.common.contracts.AbstractTO;

/**
 *
 * @author Elton Manoku
 */
public class CrsTO extends AbstractTO{
    
    private int srid;
    private String wkt;

    public int getSrid() {
        return srid;
    }

    public void setSrid(int srid) {
        this.srid = srid;
    }

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }
    
}
