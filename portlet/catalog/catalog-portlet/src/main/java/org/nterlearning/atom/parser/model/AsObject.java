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
import org.nterlearning.atom.parser.model.AsContent.AsContentTypeType;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.AtomDate;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.ExtensibleElementWrapper;

import javax.xml.namespace.QName;
import java.util.Date;
import java.util.List;

public class AsObject extends ExtensibleElementWrapper {

	public static final String NS = AsExtension.AS_NS;

	/**
	 * @param internal
	 */
	public AsObject(Element internal) {
		super(internal);
	}

	/**
	 * @param factory
	 * @param qname
	 */
	public AsObject(Factory factory, QName qname) {
		super(factory, qname);
	}
	
	public AsObjectType.AsObjectTypeType getObjectType(){
		AsObjectType objectType = getExtension(AsExtension.OBJECT_TYPE_ELEMENT);
		return objectType.getType();
	}
	
	public void setObjectType(AsObjectType.AsObjectTypeType objType){
		
		// we can have at most 1 object type element, so get the existing one
		AsObjectType el = getExtension(AsExtension.OBJECT_TYPE_ELEMENT);
		
		// if we don't have one
		if (el == null){
			// add it
			el = addExtension(AsExtension.OBJECT_TYPE_ELEMENT);
		}
		
		// set the object type
		el.setType(objType);
	}
	
	public String getId(){		
		Element el = getExtension(AsExtension.ID_ELEMENT);
		return (el != null) ? el.getText() : null;
	}
	
	public void setId(String id){
		
		// we can have at most 1 id element, so get the existing one
		Element el = getExtension(AsExtension.ID_ELEMENT);
		
		// if we don't have one
		if (el == null){
			// add it
			el = addExtension(AsExtension.ID_ELEMENT);
		}
		
		// set the id
		el.setText(id);
	}
	
	public String getName() {
		Element el = getExtension(AsExtension.NAME_ELEMENT);
		return (el != null) ? el.getText() : null;
	}
	
	public void setName(String name){
		
		// we can have at most 1 name element, so get the existing one
		Element el = getExtension(AsExtension.NAME_ELEMENT);
		
		// if we don't have one
		if (el == null){
			// add it
			el = addExtension(AsExtension.NAME_ELEMENT);
		}
		
		// set the name
		el.setText(name);
	}
	
	public Date getPublished(){
		Element el = getExtension(AsExtension.PUBLISHED_ELEMENT);
		return (el != null) ? AtomDate.parse(el.getText()) : null;
	}
	
	public void setPublished(Date date){
		
		// we can have at most 1 published element, so get the existing one
		Element el = getExtension(AsExtension.PUBLISHED_ELEMENT);
		
		// if we don't have one
		if (el == null){
			// add it
			el = addExtension(AsExtension.PUBLISHED_ELEMENT);
		}
		
		// set the date
		el.setText(AtomDate.valueOf(date).toString());
	}
	
	public Date getUpdated(){		
		Element el = getExtension(AsExtension.UPDATED_ELEMENT);
		return (el != null) ? AtomDate.parse(el.getText()) : null;
	}
	
	public void setUpdated(Date date){
		
		// we can have at most 1 updated element, so get the existing one
		Element el = getExtension(AsExtension.UPDATED_ELEMENT);
		
		// if we don't have one
		if (el == null){
			// add it
			el = addExtension(AsExtension.UPDATED_ELEMENT);
		}
		
		// set the date
		el.setText(AtomDate.valueOf(date).toString());
	}
	
	/**
	 * 
	 * @return the rating, or 0 if the element is empty or missing and -1 if the rating cannot be parsed
	 */
	public float getRating(){
		AsRating rating = getExtension(AsExtension.RATING_ELEMENT);
		return (rating != null) ? rating.getRating() : 0;
	}
	
	public void setRating(float rating){
		
		// we can have at most 1 rating element, so get the existing one
		Element el = getExtension(AsExtension.RATING_ELEMENT);
		
		// if we don't have one
		if (el == null){
			// add it
			el = addExtension(AsExtension.RATING_ELEMENT);
		}
		
		// set the rating
		el.setText(String.valueOf(rating));
	}
	
	public List<AsPermalinkUrl> getPermalinkUrls(){
		return getExtensions(AsExtension.PERMALINK_ELEMENT);
	}
	
	public void addPermalinkUrl(AsPermalinkUrl permalinkUrl){
		addExtension(permalinkUrl);
	}
	
	public String getSummary(){
		Element el = getExtension(AsExtension.SUMMARY_ELEMENT);
        return (el != null) ? el.getText() : null;
	}
	
	public void setSummary(String summary){
		
		// we can have at most 1 summary element, so get the existing one
		Element el = getExtension(AsExtension.SUMMARY_ELEMENT);
		
		// if we don't have one
		if (el == null){
			// add it
			el = addExtension(AsExtension.SUMMARY_ELEMENT);
		}
		
		// set the summary
		el.setText(summary);
	}
	
	public AsContent getContent(){
		return getExtension(AsExtension.CONTENT_ELEMENT);
	}
	
	public void setContent(AsContent content){
		setContent(content.getContent(),AsContentTypeType.fromValue(content.getType()));
	}
	
	public void setContent(String content, AsContentTypeType type){
		
		// we can have at most 1 content element, so get the existing one
		AsContent asc = getContent();
		
		// if we don't have one
		if (asc == null){
			// add it
			asc = addExtension(AsExtension.CONTENT_ELEMENT);
		}
		
		// set the values
		asc.setContent(content);
		asc.setType(type);
	}
}
