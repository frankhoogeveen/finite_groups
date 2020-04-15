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
import java.util.Map;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;

/**
 * Calculates the order of each element
 * 
 * @author frank
 */
class ElementOrderCalculator implements Calculator<Group> {

    @Override
    public Map<Element, Integer> evaluate(Group group) throws EvaluationException {
        Multiplicator mult = (Multiplicator)group.getProperty(GroupProperty.MultiplicationTable);
        Element unit  = (Element)group.getProperty(GroupProperty.UnitElement);
        
        Map<Element, Integer> map = new HashMap<Element,Integer>();
        for(Element g : group){
           map.put(g, calculateOrder(g, mult, unit));
        }
        
        return map;
    }
    
    /**
     * Determine the order of each element by repeated multiplication
     * 
     * 
     * @param g the value of g
     * @param mult the multiplication table of the group
     * @param unit the unit element of the group
     */
    private Integer calculateOrder(Element g, Multiplicator mult, Element unit) throws EvaluationException {
        int result = 1;
        Element current = g;
        
        while(!current.equals(unit)){
            result++;
            current = mult.getProduct(current, g);
        }
        
        return result;
    }
   
}
