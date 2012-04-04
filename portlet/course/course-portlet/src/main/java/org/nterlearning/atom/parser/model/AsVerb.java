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

public class AsVerb extends ExtensibleElementWrapper {
	
	public enum VerbType {

		ADD("add"),
		DELETE("delete"),
		FAVORITE("favorite"),
		LIKE("like"),
		REMOVE("remove"),
		SHARE("share"),
		UNLIKE("unlike"),
		UPDATE("update"),
		
		// not actual AS verb types, just enumeration values for use in Java
		NONE("none"),ILLEGAL("illegal");

		private final String value;

		VerbType(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		public String getActionTense(){
			
			if (this.equals(ADD)){
				return "added";
			}
			else if (this.equals(DELETE)){
				return "deleted";
			}
			else if (this.equals(FAVORITE)){
				return "favors";
			}
			else if (this.equals(LIKE)){
				return "likes";
			}
			else if (this.equals(SHARE)){
				return "shares";
			}
			else if (this.equals(UNLIKE)){
				return "no longer likes";
			}
			else if (this.equals(UPDATE)){
				return "updated";
			}
			else {
				throw new RuntimeException("Unknown verb type: " + this);
			}
		}
		
		public static VerbType fromValue(String v) {
			for (VerbType c: VerbType.values()) {
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
	public AsVerb(Element internal) {
		super(internal);
	}

	/**
	 * @param factory
	 * @param qname
	 */
	public AsVerb(Factory factory, QName qname) {
		super(factory, qname);
	}
	
	public VerbType getVerb(){
		
		if (getText()==null){
			return VerbType.NONE;
		}
		
		try {
			return VerbType.fromValue(getText());
		}
		catch (IllegalArgumentException e) {
			return VerbType.ILLEGAL;
		}
	}
	
	public void setVerb(VerbType verbType){
		setText(verbType.value());
	}
}
