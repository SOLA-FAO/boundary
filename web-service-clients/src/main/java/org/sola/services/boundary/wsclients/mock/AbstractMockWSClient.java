/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations (FAO). All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,this list of conditions
 * and the following disclaimer. 2. Redistributions in binary form must reproduce the above
 * copyright notice,this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution. 3. Neither the name of FAO nor the names of its
 * contributors may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.wsclients.mock;

import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.AbstractWSClientImpl;

/**
 * Ancestor class to be used by all mock web service clients.
 *
 * @see MockServiceManager
 * @see MockCaseManagementClient
 * @author amcdowell
 */
public class AbstractMockWSClient extends AbstractWSClientImpl {

    public static final String DEFAULT_MOCK_ID = "defaultMockId";

    public AbstractMockWSClient(String url, QName qName) {
        super(url, qName);
    }

    /**
     * Provides access to the {@linkplain MockServiceManager} instance
     */
    public MockServiceManager getManager() {
        return MockServiceManager.getInstance();
    }

    /**
     * Overridden to help avoid leakage of password details during testing. i.e. Credential details
     * are not saved.
     */
    @Override
    public void setCredentials(String userName, char[] password) {
    }

    /**
     * @return Always returns true to indicate a valid connection. 
     */
    @Override
    public boolean checkConnection() {
        return true;
    }
}
