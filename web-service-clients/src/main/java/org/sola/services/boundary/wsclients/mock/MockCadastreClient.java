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
package org.sola.services.boundary.wsclients.mock;

import org.sola.services.boundary.wsclients.CadastreClient;
import org.sola.services.boundary.wsclients.CadastreClientImpl;
import org.sola.webservices.cadastre.Cadastre;

/**
 * Mock implementation of the {@linkplain CadastreClient} interface that extends the
 * {@linkplain CadastreClientImpl}, overriding the getPort method to return a mock port object - {@linkplain MockCadastrePort}.
 *
 * <p> Allows testing of the {@linkplain CadastreClientImpl} and classes dependent on the
 * {@linkplain CadastreClient} interface without deploying the SOLA web services </p>
 *
 * @see CadastreClient
 * @see CadastreClientImpl
 * @see MockCadastrePort
 * @see MockServiceManager
 */
public class MockCadastreClient extends CadastreClientImpl implements CadastreClient {

    private MockCadastrePort port = new MockCadastrePort();

    /**
     * Constructor for the mock class.
     */
    public MockCadastreClient() {
        // The URL is irrelevant for the mock client class
        super("");
    }

    /**
     * Overrides the default getPort method on {@linkplain CadastreClientImpl} to return a mock port
     * object - {@linkplain MockCadastrePort}.
     */
    @Override
    protected Cadastre getPort() {
        return port;
    }

    /**
     * Overridden to help avoid leakage of password details during testing. i.e. Only username is
     * saved.
     */
    @Override
    public void setCredentials(String userName, char[] password) {
        super.setCredentials(userName, null);
    }
}
