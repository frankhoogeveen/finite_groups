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
import nl.fh.group.GroupProperty;
import nl.fh.group.Multiplicator;

/**
 *
 *  Calculates the inverse of each group element
 * 
 * @author frank
 */
public class InverseCalculator implements Calculator<Group> {

    public InverseCalculator() {
    }

    @Override
    public Map<Element, Element> evaluate(Group group) throws EvaluationException {
        Multiplicator mult = (Multiplicator)group.getProperty(GroupProperty.MultiplicationTable);
        Element unit  = (Element)group.getProperty(GroupProperty.UnitElement);
        
        Map<Element, Element> result = new HashMap<Element, Element>();
        
        for(Element g : group){
            for(Element h : group){
                if((mult.getProduct(g, h).equals(unit))&&(mult.getProduct(h, g).equals(unit))){
                    result.put(g, h);
                }
            }
        }
        
        return result;
    }

    
}
