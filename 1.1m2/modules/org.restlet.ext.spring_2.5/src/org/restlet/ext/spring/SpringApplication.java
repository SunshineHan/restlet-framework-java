/*
 * Copyright 2005-2008 Noelios Consulting.
 * 
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the "License"). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * http://www.opensource.org/licenses/cddl1.txt See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL HEADER in each file and
 * include the License file at http://www.opensource.org/licenses/cddl1.txt If
 * applicable, add the following below this CDDL HEADER, with the fields
 * enclosed by brackets "[]" replaced with your own identifying information:
 * Portions Copyright [yyyy] [name of copyright owner]
 */

package org.restlet.ext.spring;

import org.restlet.Application;
import org.restlet.Component;

/**
 * Application that is easily configurable with Spring. Here is a usage example:
 * 
 * <pre>
 * ...
 * 
 * &lt;bean id=&quot;application&quot; class=&quot;org.restlet.ext.spring.SpringApplication&quot;&gt;
 *     &lt;constructor-arg ref=&quot;component&quot; /&gt;
 * 
 *     &lt;property name=&quot;root&quot;&gt;
 *         ...
 *     &lt;/property&gt;
 * &lt;/bean&gt;
 * </pre>
 * 
 * Note that the constructor taking a Component is private because it shouldn't
 * be extended to remove the separation between an Application and its parent
 * Component. The application is only allowed to access to the Component's
 * context.
 * 
 * @see <a href="http://www.springframework.org/">Spring home page</a>
 * @author Jerome Louvel (contact@noelios.com)</a>
 */
public class SpringApplication extends Application {

    /**
     * Constructor taking a parent component. Useful because we can't easily
     * pass the component's context via Spring.
     * 
     * @param component
     *                The parent component.
     */
    private SpringApplication(Component component) {
        super(component.getContext());
    }

}