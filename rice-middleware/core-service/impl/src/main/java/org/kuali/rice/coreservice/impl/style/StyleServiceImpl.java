/**
 * Copyright 2005-2019 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.coreservice.impl.style;

import org.apache.log4j.Logger;
import org.kuali.rice.coreservice.api.style.Style;
import org.kuali.rice.coreservice.api.style.StyleRepositoryService;
import org.kuali.rice.coreservice.api.style.StyleService;

import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.List;


/**
 * Implements generic StyleService via existing EDL style table
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class StyleServiceImpl implements StyleService {
	
    private static final Logger LOG = Logger.getLogger(StyleServiceImpl.class);

    private StyleRepositoryService styleRepositoryService;

    public void setStyleRepositoryService(StyleRepositoryService styleRepositoryService) {
    	this.styleRepositoryService = styleRepositoryService;
    }

    /**
     * Loads the named style from the database, or (if configured) imports it from a file
     * specified via a configuration parameter with a name of the format edl.style.&lt;styleName&gt;
     * {@inheritDoc}
     */
    @Override
    public Style getStyle(String styleName) {
    	return styleRepositoryService.getStyle(styleName);
    }

    @Override
    public Templates getStyleAsTranslet(String name) throws TransformerConfigurationException {
        if (name == null) {
            return null;
        }

        Style style = getStyle(name);
        if (style == null) {
            return null;
        }

        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setURIResolver(new StyleUriResolver(this));

        return factory.newTemplates(new StreamSource(new StringReader(style.getXmlContent())));
    }

    @Override
    public void saveStyle(Style style) {
    	styleRepositoryService.saveStyle(style);
    }
    
    @Override
    public List<String> getAllStyleNames() {
        return styleRepositoryService.getAllStyleNames();
    }
}
