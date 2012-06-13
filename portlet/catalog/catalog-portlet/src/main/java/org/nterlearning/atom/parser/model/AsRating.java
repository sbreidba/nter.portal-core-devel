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

package org.nterlearning.atom.parser.model;

import org.nterlearning.atom.extension.AsExtension;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.ExtensibleElementWrapper;

import javax.xml.namespace.QName;


public class AsRating extends ExtensibleElementWrapper {

	public static final String NS = AsExtension.AS_NS;

	/**
	 * @param internal
	 */
	public AsRating(Element internal) {
		super(internal);
	}

	/**
	 * @param factory
	 * @param qname
	 */
	public AsRating(Factory factory, QName qname) {
		super(factory, qname);
	}
	
	/**
	 * 
	 * @return the rating, or 0 if the element is empty and -1 if the rating
     * cannot be parsed
	 */
	public float getRating(){
		
		if (getText()==null){
			return 0;
		}
		
		try {
			return Float.parseFloat(getText());
		}
		catch (NumberFormatException e) {
			return -1;
		}
	}
	
	public void setRating(float rating){		
		setText(String.valueOf(rating));
	}
}
