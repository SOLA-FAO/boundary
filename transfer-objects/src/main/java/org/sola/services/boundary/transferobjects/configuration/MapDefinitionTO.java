/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations (FAO).
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
package org.sola.services.boundary.transferobjects.configuration;

import java.util.ArrayList;
import java.util.List;
import org.sola.services.common.contracts.AbstractTO;

/**
 *
 * @author Elton Manoku
 */
public class MapDefinitionTO extends AbstractTO {
    private List<CrsTO> crsList = new ArrayList<CrsTO>();
    private List<ConfigMapLayerTO> layers = new ArrayList<ConfigMapLayerTO>();
    private double east = -1;
    private double west = -1;
    private double north = -1;
    private double south = -1;
    private double snapTolerance = 0.01;
    private double surveyPointShiftUrbanArea = 5.0;
    private double surveyPointShiftRuralArea = 20.0;
    
    public List<CrsTO> getCrsList() {
        return crsList;
    }

    public void setCrsList(List<CrsTO> crsList) {
        this.crsList = crsList;
    }

    
    /**
     * @return the layers
     */
    public List<ConfigMapLayerTO> getLayers() {
        return layers;
    }

    public void setLayers(List<ConfigMapLayerTO> layers) {
        this.layers = layers;
    }

    /**
     * @return the east
     */
    public double getEast() {
        return east;
    }

    /**
     * @param east the east to set
     */
    public void setEast(double east) {
        this.east = east;
    }

    /**
     * @return the west
     */
    public double getWest() {
        return west;
    }

    /**
     * @param west the west to set
     */
    public void setWest(double west) {
        this.west = west;
    }

    /**
     * @return the north
     */
    public double getNorth() {
        return north;
    }

    /**
     * @param north the north to set
     */
    public void setNorth(double north) {
        this.north = north;
    }

    /**
     * @return the south
     */
    public double getSouth() {
        return south;
    }

    /**
     * @param south the south to set
     */
    public void setSouth(double south) {
        this.south = south;
    }

    public double getSnapTolerance() {
        return snapTolerance;
    }

    public void setSnapTolerance(double snapTolerance) {
        this.snapTolerance = snapTolerance;
    }

    public double getSurveyPointShiftRuralArea() {
        return surveyPointShiftRuralArea;
    }

    public void setSurveyPointShiftRuralArea(double surveyPointShiftRuralArea) {
        this.surveyPointShiftRuralArea = surveyPointShiftRuralArea;
    }

    public double getSurveyPointShiftUrbanArea() {
        return surveyPointShiftUrbanArea;
    }

    public void setSurveyPointShiftUrbanArea(double surveyPointShiftUrbanArea) {
        this.surveyPointShiftUrbanArea = surveyPointShiftUrbanArea;
    }
    
}
