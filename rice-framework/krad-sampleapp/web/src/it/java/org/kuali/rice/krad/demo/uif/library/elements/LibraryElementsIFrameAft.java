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
package org.kuali.rice.krad.demo.uif.library.elements;

import org.junit.Test;

import org.kuali.rice.testtools.selenium.WebDriverLegacyITBase;

/**
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class LibraryElementsIFrameAft extends WebDriverLegacyITBase {

    /**
     * /kr-krad/kradsampleapp?viewId=Demo-IframeView&methodToCall=start
     */
    public static final String BOOKMARK_URL = "/kr-krad/kradsampleapp?viewId=Demo-IframeView&methodToCall=start";

    @Override
    protected String getBookmarkUrl() {
        return BOOKMARK_URL;
    }

    @Override
    protected void navigate() throws Exception {
        waitAndClickLibraryLink();
        waitAndClickByLinkText("Elements");
        waitAndClickByLinkText("Iframe");
    }

    protected void testLibraryElementsIFrame() throws Exception {
        assertElementPresentByXpath("//section[@id='Demo-Iframe-Example1']/iframe");
    }
    
    @Test
    public void testLibraryElementsIFrameBookmark() throws Exception {
        testLibraryElementsIFrame();
        passed();
    }

    @Test
    public void testLibraryElementsIFrameNav() throws Exception {
        testLibraryElementsIFrame();
        passed();
    }  
}
