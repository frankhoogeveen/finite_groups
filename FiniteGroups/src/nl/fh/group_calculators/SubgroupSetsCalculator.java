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

import java.util.HashSet;
import java.util.Set;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;

/**
 * Calculator to get the set of subgroups as sets of Element
 * 
 * 
 * @author frank
 */
public class SubgroupSetsCalculator implements Calculator<Group> {

    @Override
    public Set<Set<Element>> evaluate(Group group) throws EvaluationException {
        Set<Element> elements  = (Set<Element>) group.getProperty(GroupProperty.Elements);
        Set<Element> unitSet = (Set<Element>) group.getProperty(GroupProperty.UnitSet);
        
        Set<Set<Element>> result = new HashSet<Set<Element>>();
        result.add(unitSet);
     
        int currentSize = 1;
        int previousSize; 
        do{
           previousSize = currentSize; 
           
           result = addMoreSubgroups(result, elements, group);
           
           currentSize = result.size();
        }while(currentSize != previousSize);
        
        return result;
    }

    private Set<Set<Element>> addMoreSubgroups(Set<Set<Element>> current, Set<Element> elements, Group group) throws EvaluationException {
        Set<Set<Element>> next = new HashSet<Set<Element>>(current);
        
        for(Element g : elements){
            for(Set<Element> set : current){
                if(!set.contains(g)){
                    Set<Element> expanded = new HashSet(set);
                    expanded.add(g);
                    expanded = group.generateSubset(expanded);
                    next.add(expanded);
                }
            }
        }
        return next;
    }
}
