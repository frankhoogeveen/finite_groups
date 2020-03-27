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

/**
 *  Calculate a map from the elements to the conjugation class 
 *  of that element, considered as set
 * 
 * 
 * @author frank
 */
class ConjugationClassesMapCalculator implements Calculator<Group> {

    @Override
    public Map<Element, Set<Element>> evaluate(Group group) throws EvaluationException {
        Map<Element, Map<Element, Element>> map = (Map<Element, Map<Element, Element>>)group.getProperty(GroupProperty.ConjugationMap);
        
        Map<Element, Set<Element>> result = new HashMap<Element, Set<Element>>();
        for(Element g : map.keySet()){
            result.put(g, new HashSet());
            for(Element h : map.keySet()){
                result.get(g).add(map.get(h).get(g));
            }
        } 
         
        return result;
    }
}
