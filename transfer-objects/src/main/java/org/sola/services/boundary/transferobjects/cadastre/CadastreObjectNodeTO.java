/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.transferobjects.cadastre;

import java.util.ArrayList;
import java.util.List;
import org.sola.services.common.contracts.AbstractTO;

/**
 *
 * @author Elton Manoku
 */
public class CadastreObjectNodeTO extends AbstractTO{
    private String id;
    private byte[] geom;
    List<CadastreObjectTO> cadastreObjectList = new ArrayList<CadastreObjectTO>();

    public List<CadastreObjectTO> getCadastreObjectList() {
        return cadastreObjectList;
    }

    public void setCadastreObjectList(List<CadastreObjectTO> cadastreObjectList) {
        this.cadastreObjectList = cadastreObjectList;
    }

    public byte[] getGeom() {
        return geom;
    }

    public void setGeom(byte[] geom) {
        this.geom = geom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
