/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2011  SRI International
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

public class AsObjectType extends ExtensibleElementWrapper {
	
	public enum AsObjectTypeType {

		PERSON("person"),
		REVIEW("review"),
		COURSE("course"),;

		private final String value;

		AsObjectTypeType(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		public static AsObjectTypeType fromValue(String v) {
			for (AsObjectTypeType c: AsObjectTypeType.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
	};
    

	public static final String NS = AsExtension.AS_NS;

	/**
	 * @param internal
	 */
	public AsObjectType(Element internal) {
		super(internal);
	}

	/**
	 * @param factory
	 * @param qname
	 */
	public AsObjectType(Factory factory, QName qname) {
		super(factory, qname);
	}
	
	/**
	 * 
	 * @return
	 */
	public AsObjectTypeType getType(){
		try {
			return AsObjectTypeType.fromValue(getText());
		}
		catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public void setType(AsObjectTypeType type){ 		
		setText(type.value());
	}
}
