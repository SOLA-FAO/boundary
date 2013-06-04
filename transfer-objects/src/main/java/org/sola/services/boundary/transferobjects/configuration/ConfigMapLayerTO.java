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

import org.sola.services.common.contracts.AbstractTO;

/**
 *
 * @author Elton Manoku
 */
public class ConfigMapLayerTO extends AbstractTO {

    private String id;
    private String typeCode;
    private String url;
    private String wmsLayers;
    private String pojoQueryName;
    private String pojoQueryNameForSelect;
    private String pojoStructure;
    private String shapeLocation;
    private String style;
    private String title;
    private boolean visible;
    private String wmsVersion;
    private String wmsFormat;
    private String securityUser;
    private String securityPassword;
    private boolean showInToc;
    private boolean useInPublicDisplay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getShapeLocation() {
        return shapeLocation;
    }

    public void setShapeLocation(String shapeLocation) {
        this.shapeLocation = shapeLocation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getPojoQueryName() {
        return pojoQueryName;
    }

    public void setPojoQueryName(String pojoQueryName) {
        this.pojoQueryName = pojoQueryName;
    }

    public String getPojoQueryNameForSelect() {
        return pojoQueryNameForSelect;
    }

    public void setPojoQueryNameForSelect(String pojoQueryNameForSelect) {
        this.pojoQueryNameForSelect = pojoQueryNameForSelect;
    }

    public String getWmsLayers() {
        return wmsLayers;
    }

    public void setWmsLayers(String wmsLayers) {
        this.wmsLayers = wmsLayers;
    }

    public String getPojoStructure() {
        return pojoStructure;
    }

    public void setPojoStructure(String pojoStructure) {
        this.pojoStructure = pojoStructure;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getSecurityPassword() {
        return securityPassword;
    }

    public void setSecurityPassword(String securityPassword) {
        this.securityPassword = securityPassword;
    }

    public String getSecurityUser() {
        return securityUser;
    }

    public void setSecurityUser(String securityUser) {
        this.securityUser = securityUser;
    }

    public String getWmsFormat() {
        return wmsFormat;
    }

    public void setWmsFormat(String wmsFormat) {
        this.wmsFormat = wmsFormat;
    }

    public String getWmsVersion() {
        return wmsVersion;
    }

    public void setWmsVersion(String wmsVersion) {
        this.wmsVersion = wmsVersion;
    }

    public boolean isUseInPublicDisplay() {
        return useInPublicDisplay;
    }

    public void setUseInPublicDisplay(boolean useInPublicDisplay) {
        this.useInPublicDisplay = useInPublicDisplay;
    }
    
}
