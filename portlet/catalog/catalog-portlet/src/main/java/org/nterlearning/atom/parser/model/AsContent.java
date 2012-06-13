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

/**
 * @author gjiva
 *
 */
public class AsContent extends ExtensibleElementWrapper {

	public static final String NS = AsExtension.AS_NS;

	public enum AsContentTypeType {

		TEXT("text"),
		HTML("html"),
		XHTML("xhtml"),;

		private final String value;

		AsContentTypeType(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		public static AsContentTypeType fromValue(String v) {
			for (AsContentTypeType c: AsContentTypeType.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
	};

	/**
	 * @param internal
	 */
	public AsContent(Element internal) {
		super(internal);
	}

	/**
	 * @param factory
	 * @param qname
	 */
	public AsContent(Factory factory, QName qname) {
		super(factory, qname);
	}

	public String getType(){
		return getAttributeValue(AsExtension.ATOM_TYPE_ATTRIBUTE);
	}
	
	public void setType(AsContentTypeType type){
		setAttributeValue(AsExtension.ATOM_TYPE_ATTRIBUTE, type.value());
	}
	
	public String getContent(){
		return getText();
	}
	
	public void setContent(String content){
		setText(content);
	}
}
