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
package nl.fh.group_calculators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;

/**
 * Calculates the  strongly minimal sets of generators. These are the sets that
 1) Generate the entire group
 2) Do not have a subset the generates the entire group
 3) Are not larger than any other set of of generators of the group
 
 Note that minimal sets of generators that comply with 1,2 but not 3 may have 
 different size, E.g. for C6, <1>, <5>,<2,3> and <3,4> are all minimal generating
 * sets, but only <1> and <5> are strongly minimal.
 
 The trivial implementation is not the most efficient, but gives 
 (hopefully) readable code. 
 * 
 * @author frank
 */
class StronglyMinimalGeneratorsCalculator implements Calculator<Group> {

    @Override
    public Set<Set<Element>> evaluate(Group group) throws EvaluationException {
        Multiplicator mult = (Multiplicator)group.getProperty(GroupProperty.MultiplicationTable);
        Set<Element> allElements = (Set<Element>) group.getProperty(GroupProperty.Elements);
        Element unit =  (Element)group.getProperty(GroupProperty.UnitElement);

        // start out with an trivial allElements, containing the unit element
        Set<Element> empty = new HashSet<Element>();
        Set<Element> trivial = new HashSet<Element>();
        trivial.add(unit);
        
        Map<Set<Element>, Set<Element>> map = new HashMap<Set<Element>, Set<Element>>();
        map.put(empty, trivial);        
        
        // repeatedly expand by a single element,  until we have found the complete allElements
        while(!map.containsValue(allElements)){
            map = expandByOneElement(map, group);
        }
        
        //finally find the subsets that have generated the group
        Set<Set<Element>> result = new HashSet<Set<Element>>();
        for(Set<Element> key : map.keySet()){
            if(map.get(key).equals(allElements)){
                result.add(key);
            }
        }
        
        return result;
    }
    
    /**
     * Given a map S -> <S> (the subgroup generated by S, this method expands
     * all sets S by a single element that is not part of <S>, and 
     * updates the map.
     */
    Map<Set<Element>, Set<Element>> expandByOneElement(Map<Set<Element>, Set<Element>> map, Group group) throws EvaluationException{
        Set<Element> allElements = (Set<Element>)group.getProperty(GroupProperty.Elements);  
        
        Map<Set<Element>, Set<Element>> result = new HashMap<Set<Element>, Set<Element>>();

        for(Set<Element> set : map.keySet()){
            Set<Element> candidates = new HashSet<Element>(allElements);
            candidates.removeAll(map.get(set));
            for(Element c : candidates){
                
                Set<Element> key = new HashSet(set);
                key.add(c);
                
                Set<Element> value = new HashSet<Element>(map.get(set));
                value.add(c);
                value = group.generateFrom(value);
                
                result.put(key, value);
            }
        }
        
        return result;
    }
}
