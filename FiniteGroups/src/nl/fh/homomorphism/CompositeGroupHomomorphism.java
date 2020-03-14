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
 *  The group morphism that is the product left * right.
 *  So as a mapping, right is applied first, followed by left.
 * 
 * @author frank
 */
public class CompositeGroupHomomorphism implements GroupMorphism {

    private GroupMorphism left;
    private GroupMorphism right;

    public CompositeGroupHomomorphism(GroupMorphism left, GroupMorphism right) {
        if(!left.getDomain().equals(right.getCodomain())){
            throw new IllegalArgumentException();
        }
        
        this.left = left;
        this.right = right;
    }

    @Override
    public Group getDomain() {
        return this.right.getDomain();
    }

    @Override
    public Group getCodomain() {
        return this.left.getCodomain();
    }

    @Override
    public GroupMorphism compose(GroupMorphism morphism) {
        if(!(morphism instanceof GroupMappingTable)){
            throw new IllegalArgumentException();
        }
        return new CompositeGroupHomomorphism(this, (GroupMappingTable) morphism);
    }

    @Override
    public Element applyTo(Element element) {
         return this.left.applyTo(this.right.applyTo(element));
    }
    
}
