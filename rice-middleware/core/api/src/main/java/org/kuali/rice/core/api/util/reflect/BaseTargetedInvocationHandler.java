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
package org.kuali.rice.core.api.util.reflect;




/**
 * A simple TargetedInvocationHandler which extends the BaseInvocationHandler.  Provides
 * construction of the handler with the target object and implements the
 * interface by providing access to the target via the getTarget() method.
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public abstract class BaseTargetedInvocationHandler<T> extends BaseInvocationHandler implements TargetedInvocationHandler<T> {

	private T target;

	public BaseTargetedInvocationHandler(T target) {
		this.target = target;
	}

    @Override
	public T getTarget() {
		return this.target;
	}
	
	public void setTarget(T target) {
		this.target = target;
	}

}
