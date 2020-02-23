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
package nl.fh.group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import nl.fh.info_table_values.SubsetValue;

/**
 * Defines a group as something with a name, a set of generators and a 
 * way to multiply them
 * 
 * 
 * @author frank
 */
public class GroupDefinition {

    private final String name;
    private final Collection<Element> generators;
    private final Multiplicator multiplicator;

    /**
     * abstractly defines a group
     * 
     * @param name
     * @param generators
     * @param multiplication 
     */
    public GroupDefinition(String name, Collection<Element> generators, Multiplicator multiplication) {
        this.name = name;
        this.generators = generators;
        this.multiplicator = multiplication;
    }
    
    /**
     * 
     * @param g
     * @param generators
     * @return the definition of the subgroup generated by the generators 
     */
    public static GroupDefinition subGroup(Group g, Collection<Element> generators){
        return new GroupDefinition("Subgroup of " + g.getName(), generators, g.getDefinition().getMultiplicator());
    }
    
     /**
     * 
     * @param g
     * @param generators 
     * @return the definition of the subgroup generated by the generators 
     */
    public static GroupDefinition subGroup(Group g, SubsetValue generators){
        List<Element> list = new ArrayList<Element>();
        
        for(int i = 0; i < generators.content().length; i++){
            if(generators.content()[i]){
                list.add(g.getElements().get(i));
            }
        }
        
        return new GroupDefinition("Subgroup of " + g.getName(), list, g.getDefinition().getMultiplicator());
    }
    
    
    public String getName() {
        return this.name;
    }

    public Collection<Element> getGenerators() {
        return generators;
    }

    public Multiplicator getMultiplicator() {
        return multiplicator;
    }
}
