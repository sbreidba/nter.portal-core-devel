/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2012 SRI International
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */


/**
 * 
 */

package org.nterlearning.atom;

import org.nterlearning.atom.enumerations.NterNameSpace;
import org.nterlearning.atom.extension.ActivityStreamsExtensionFactory;
import org.nterlearning.atom.extension.NterCourseExtensionFactory;
import org.apache.abdera.Abdera;

/**
 * The Abdera documentation recommends using a singleton to limit
 * the instances of the Abdera object to 1, since it is an expensive
 * object to create
 * 
 * @author gjiva
 *
 */
public class AbderaSingleton {
	
	private static Abdera abdera = null;
    
    public static synchronized Abdera getInstance() {
      
    	if (abdera == null){
    		abdera = new Abdera();
            addExtensionFactories();
    	}
      
    	return abdera;
    }


    /**
     * Adds all NTER extensions to the Abdera parser
     */
    private static void addExtensionFactories() {
        for (NterNameSpace ns : NterNameSpace.values()) {
            if (ns != NterNameSpace.UNKNOWN) {
                abdera.getFactory().registerExtension(
                        new NterCourseExtensionFactory(ns.getNameSpace()));                
            }
        }

        abdera.getFactory().registerExtension(
                new ActivityStreamsExtensionFactory());
    }
}
