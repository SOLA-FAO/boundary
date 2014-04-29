/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.transferobjects.cadastre;

import org.sola.services.common.contracts.AbstractIdTO;

/**
 * TO for Level entity
 * 
 * @author Elton Manoku
 */
public class LevelTO extends AbstractIdTO {

    private String name;
    private String structureCode;
    private String visualizationLayers;

    public LevelTO() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStructureCode() {
        return structureCode;
    }

    public void setStructureCode(String structureCode) {
        this.structureCode = structureCode;
    }

    public String getVisualizationLayers() {
        return visualizationLayers;
    }

    public void setVisualizationLayers(String visualizationLayers) {
        this.visualizationLayers = visualizationLayers;
    }

}
