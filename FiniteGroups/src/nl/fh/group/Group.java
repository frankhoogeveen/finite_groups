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
        try{
            Set<Element> elements = findGeneratedSet(toBeAdded, multiplication);
            Multiplicator table = createMultiplicationTable(elements, multiplication);
            
            this.cache = new EnumMap<GroupProperty, Object>(GroupProperty.class);
            this.cache.put(GroupProperty.Name, name);
            this.cache.put(GroupProperty.Elements, elements);
            this.cache.put(GroupProperty.MultiplicationTable, table);

        } catch(EvaluationException e){
            throw new GroupException("could not construct group " + name);
        }
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
            return findGeneratedSet(set, mult);
        } catch (EvaluationException ex) {
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
    private Set<Element> findGeneratedSet(Set<Element> set,  Multiplicator multiplication) throws EvaluationException  {
        Set<Element> toBeAdded = new HashSet(set);
        Set<Element> currentTotal = new HashSet();
        
        while(!toBeAdded.isEmpty()){
        Set<Element> nextToBeAdded = new HashSet<Element>();

            for(Element g : toBeAdded){
                for(Element h : currentTotal){
                    Element gh = multiplication.getProduct(g, h);
                    nextToBeAdded.add(gh);

                    Element hg = multiplication.getProduct(h, g);
                    nextToBeAdded.add(hg);
                }

                for(Element g2 : toBeAdded){
                    Element g12 = multiplication.getProduct(g, g2);
                    nextToBeAdded.add(g12);
                }
            }
            
            currentTotal.addAll(toBeAdded);
            toBeAdded = nextToBeAdded;
            toBeAdded.removeAll(currentTotal);
        }
        
        return currentTotal;
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

    /**
     * 
     * @param elements
     * @param multiplication
     * @return a multiplicator with all the products pre-calculated and stored in a table 
     */
    private Multiplicator createMultiplicationTable(Set<Element> elements, Multiplicator multiplication) throws EvaluationException {
        GroupTable table = new GroupTable();
        for(Element g : elements){
            table.put(g, new HashMap<Element, Element>());
            for(Element h : elements){
                table.get(g).put(h, multiplication.getProduct(g, h));
            }
        }
        
        return table;
    }
}
