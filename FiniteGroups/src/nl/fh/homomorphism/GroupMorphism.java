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

import nl.fh.group.Element;
import nl.fh.group.Group;

/**
 *
 * Mapping between groups. Since such a mapping can be an element of an 
 * automorphism group, this interface extends Element
 * 
 * @author frank
 */
public interface GroupMorphism{
    
    /**
     * 
     * @return the domain
     */
    public Group getDomain();
    
    /**
     * 
     * @return the co domain. This can be larger than the image.
     */
    public Group getCodomain();
    
    /**
     * 
     * @param morphism
     * @return this (after) morphism
     * 
     * if domain(this) is not contained in codomain(morphism),
     * an IllegalArgumentException is thrown
     */
    public GroupMorphism compose(GroupMorphism morphism);
    
    /**
     * 
     * @param element
     * @return the image of element under this morphism 
     */
    public Element applyTo(Element element);
    
}
