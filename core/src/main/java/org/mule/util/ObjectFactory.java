/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the MuleSource MPL
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.util;

/**
 * <code>ObjectFactory</code> is a generic Factory interface.
 */
// @Immutable
public interface ObjectFactory
{
    Object create() throws Exception;
}
