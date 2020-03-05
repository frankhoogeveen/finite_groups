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

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;

/**
 *
 * @author frank
 */
public class Group implements Iterable<Element> {
    
    private final Map<GroupProperty, Object> cache;
    
    /**
     * 
     * @param name
     * @param generators
     * @param multiplication
     * @throws nl.fh.group.GroupException
     * 
     * The constructor calculates and stores the multiplication table and the set of elements (and the name). All other
     * properties are calculated on-demand.
     */
    public Group(String name, Collection<Element> generators, Multiplicator multiplication) throws GroupException {
       
        Set<Element> toBeAdded = new HashSet(generators);
        GroupTable table = findGeneratedGroup(toBeAdded, multiplication);
        
        this.cache = new EnumMap<GroupProperty, Object>(GroupProperty.class);
        this.cache.put(GroupProperty.Name, name);
        this.cache.put(GroupProperty.Elements, table.keySet());
        this.cache.put(GroupProperty.MultiplicationTable, table);
    }


    
    /**
     * 
     * @param property
     * @return the value of the property for this group
     * @throws nl.fh.calculator.EvaluationException 
     * 
     * 
     * The method caches all results
     * The calling code should know how to cast the resulting object
     */
    public Object getProperty(GroupProperty property) throws EvaluationException{
        if(!cache.containsKey(property)){
            cache.put(property, property.getCalculator().evaluate(this));
        }
        return cache.get(property);
    }
    
    /**
     * 
     * @param set of elements
     * @return the smallest set that contains set and is closed under 
     * the group operation
     */
    public Set<Element> generateFrom(Set<Element> set) throws EvaluationException{
        Multiplicator mult = (Multiplicator)this.getProperty(GroupProperty.MultiplicationTable);
        try {
            return (Set<Element>) findGeneratedGroup(set, mult);
        } catch (GroupException ex) {
            String mess = "generatedFrom failed";
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, mess, ex);
            throw new EvaluationException(mess);
        }
    }    
    
    /**
     * 
     * @param set
     * @param multiplication
     * @returnt the smallest set that contains set and is closed under 
     * the group operation
     * @throws GroupException 
     */
    private GroupTable findGeneratedGroup(Set<Element> set,  Multiplicator multiplication) throws GroupException {
        GroupTable table = new GroupTable();
        Set<Element> toBeAdded = new HashSet(set);
        Set<Element> currentTotal = new HashSet();
        
        while(!toBeAdded.isEmpty()){
            Set<Element> nextToBeAdded = new HashSet<Element>();
            
            try{
                for(Element g : toBeAdded){
                    table.put(g, new HashMap<Element, Element>());
                    for(Element h : currentTotal){
                        Element gh = multiplication.getProduct(g, h);
                        table.get(g).put(h, gh);
                        nextToBeAdded.add(gh);

                        Element hg = multiplication.getProduct(h, g);
                        table.get(h).put(g, hg);
                        nextToBeAdded.add(hg);
                    }

                    for(Element g2 : toBeAdded){
                        Element g12 = multiplication.getProduct(g, g2);
                        table.get(g).put(g2, g12);
                        nextToBeAdded.add(g12);
                    }
                }
            } catch(EvaluationException e){
                throw new GroupException(e.getMessage());
            }
            
            currentTotal.addAll(toBeAdded);
            toBeAdded = nextToBeAdded;
            toBeAdded.removeAll(currentTotal);
        }
        
        return table;
    }
    
    
    @Override
    public Iterator<Element> iterator() {
        try {
            return ((Set<Element>)this.getProperty(GroupProperty.Elements)).iterator();
        } catch (EvaluationException ex) {
            String mess = "iterator cannot be defined";
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, mess, ex);
            throw new IllegalStateException(mess);
        }
    }
}
