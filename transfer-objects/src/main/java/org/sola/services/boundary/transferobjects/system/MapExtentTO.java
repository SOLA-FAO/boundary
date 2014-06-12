package org.sola.services.boundary.transferobjects.system;

import org.sola.services.common.contracts.AbstractTO;

public class MapExtentTO extends AbstractTO {
    private String minX;
    private String minY;
    private String maxX;
    private String maxY;
    
    public MapExtentTO(){
        super();
    }

    public String getMinX() {
        return minX;
    }

    public void setMinX(String minX) {
        this.minX = minX;
    }

    public String getMinY() {
        return minY;
    }

    public void setMinY(String minY) {
        this.minY = minY;
    }

    public String getMaxX() {
        return maxX;
    }

    public void setMaxX(String maxX) {
        this.maxX = maxX;
    }

    public String getMaxY() {
        return maxY;
    }

    public void setMaxY(String maxY) {
        this.maxY = maxY;
    }
}
