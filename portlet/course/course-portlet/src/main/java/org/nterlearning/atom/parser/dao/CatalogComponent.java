/**
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


package org.nterlearning.atom.parser.dao;

import org.nterlearning.datamodel.catalog.model.Component;
import org.nterlearning.datamodel.catalog.model.Contributor;

import java.util.List;

/**
 * This class represents an abstraction of the Component object that includes
 * the component's contributors.
 */
public class CatalogComponent {

    private Component component;
    private List<Contributor> contributors;


    public CatalogComponent(Component component, List<Contributor> contributors) {
        this.component = component;
        this.contributors = contributors;
    }


    public Component getComponent() {
        return component;
    }


    public void setComponent(Component component) {
        this.component = component;
    }


    public List<Contributor> getContributors() {
        return contributors;
    }


    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }
}