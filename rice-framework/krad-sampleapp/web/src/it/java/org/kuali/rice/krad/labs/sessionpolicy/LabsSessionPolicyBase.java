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
package org.kuali.rice.krad.labs.sessionpolicy;

import org.kuali.rice.testtools.selenium.WebDriverAftBase;

/**
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */

public abstract class LabsSessionPolicyBase extends WebDriverAftBase {

    protected void navigateToSessionPolicy(String sessionPolicyText) throws InterruptedException {
        waitAndClickByLinkText(sessionPolicyText);
    }
}
