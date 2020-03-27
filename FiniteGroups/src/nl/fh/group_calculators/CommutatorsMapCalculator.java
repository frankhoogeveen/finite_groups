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
 *  Calculator that pre-calculates all commutators
 * 
 *  When the calculator returns map, then
 * 
 *  map.get(g).get(h) =  g.h.g^(-1).h^(-1)
 * 
 * @author frank
 */
public class CommutatorsMapCalculator implements Calculator<Group> {

    @Override
    public Map<Element, Map<Element, Element>> evaluate(Group group) throws EvaluationException {
        Map<Element, Map<Element, Element>> map = (Map<Element, Map<Element, Element>>)group.getProperty(GroupProperty.ConjugationMap);
        Multiplicator table = (Multiplicator) group.getProperty(GroupProperty.MultiplicationTable);
        Map<Element, Element> inv = (Map<Element,Element>) group.getProperty(GroupProperty.Inverses);
        
        Map<Element, Map<Element, Element>> result = new HashMap<Element, Map<Element, Element>>();
        for(Element g : group){
            result.put(g, new HashMap<Element,Element>());
            for(Element h : group){
                Element comm = table.getProduct(map.get(g).get(h), inv.get(h));
                result.get(g).put(h,comm);
            }
        }
        
        return result;
    }
}
