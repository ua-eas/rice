/*
 * Copyright 2007 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.iu.uis.eden.security;

import java.security.Signature;
import java.security.cert.Certificate;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.lang.StringUtils;
import org.kuali.rice.RiceConstants;

/**
 * A DigitalSigner implementation which places the alias and digital signature into the request
 * headers of the commons HttpClient's HttpMethod.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class HttpClientHeaderDigitalSigner extends AbstractDigitalSigner {

	private HttpMethod method;
	private String alias;
	private Certificate certificate;
	
    public HttpClientHeaderDigitalSigner(Signature signature, HttpMethod method, String alias) {
        super(signature);
        this.method = method;
        this.alias = alias;
    }
    
    public HttpClientHeaderDigitalSigner(Signature signature, HttpMethod method, String alias, Certificate certificate) {
        this(signature, method, alias);
        this.certificate = certificate;
    }
    
    public HttpClientHeaderDigitalSigner(Signature signature, HttpMethod method, Certificate certificate) {
        super(signature);
        this.method = method;
        this.certificate = certificate;
    }
    
	public void sign() throws Exception {
        if (StringUtils.isNotBlank(this.alias) ) {
            this.method.addRequestHeader(RiceConstants.KEYSTORE_ALIAS_HEADER, this.alias);
        }
	    if (this.certificate != null) {
	        this.method.addRequestHeader(RiceConstants.KEYSTORE_CERTIFICATE_HEADER, getEncodedCertificate(this.certificate));
	    }
	    this.method.addRequestHeader(RiceConstants.DIGITAL_SIGNATURE_HEADER, getEncodedSignature());
	}

}
