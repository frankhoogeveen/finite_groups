/*
 * Copyright (C) 2020 Frank Hoogeveen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nl.fh.homomorphism;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import nl.fh.group.Element;
import nl.fh.group.Group;

/**
 *
 * @author frank
 */
public class GroupMappingTable implements GroupMorphism{

    private final Group domain;
    private final Group codomain;
    private final Map<Element, Element> map;

    /**
     * 
     * @param domain the group on which this mapping is defined
     * @param codomain the group in which the image of this mapping lives
     * @param map the map from domain to the codomain
     */
    public GroupMappingTable(Group domain, Group codomain, Map<Element, Element> map){
        this.domain = domain;
        this.codomain = codomain;
        this.map = map;
        
    }
    
    /**
     * 
     * @return the original mapping pre-calculated as a table
     */
    public GroupMappingTable(GroupMorphism groupMapping){
        this.domain = groupMapping.getDomain();
        this.codomain = groupMapping.getCodomain();
        this.map = new HashMap<Element, Element>();
        for(Element g : this.domain){
            map.put(g, groupMapping.applyTo(g));
        }
    }
    
    @Override
    public Group getDomain() {
        return this.domain;
    }

    @Override
    public Group getCodomain() {
        return this.codomain;
    }

    @Override
    public GroupMorphism compose(GroupMorphism morphism) {
        return new CompositeGroupHomomorphism(this, morphism);
    }

    @Override
    public Element applyTo(Element element) {
        return this.map.get(element);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.domain);
        hash = 29 * hash + Objects.hashCode(this.codomain);
        hash = 29 * hash + Objects.hashCode(this.map);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GroupMappingTable other = (GroupMappingTable) obj;
        if (!Objects.equals(this.domain, other.domain)) {
            return false;
        }
        if (!Objects.equals(this.codomain, other.codomain)) {
            return false;
        }
        if (!Objects.equals(this.map, other.map)) {
            return false;
        }
        return true;
    }
}
