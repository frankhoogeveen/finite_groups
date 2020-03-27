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
import java.util.Set;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;

/**
 *
 *  Calculator that pre-calculates all conjugations
 * 
 *  When the calculator returns map, then
 * 
 *  map.get(g).get(h) =  g.h.g^(-1)
 * 
 * It therefore maps G -> Inn(G), by mapping g-> conjugation by g.
 * 
 * @author frank
 */
public class ConjugationMapCalculator implements Calculator<Group> {

    @Override
    public Map<Element, Map<Element,Element>> evaluate(Group group) throws EvaluationException {
        Set<Element> set = (Set<Element>) group.getProperty(GroupProperty.Elements);
        Multiplicator table = (Multiplicator) group.getProperty(GroupProperty.MultiplicationTable);
        Map<Element, Element> inv = (Map<Element,Element>) group.getProperty(GroupProperty.Inverses);
        
        Map<Element, Map<Element,Element>> map = new HashMap<Element, Map<Element, Element>>();
        for(Element g : set){
            map.put(g, new HashMap<Element, Element>());
            for(Element h : set){
                Element conj = table.getProduct(g, table.getProduct(h, inv.get(g)));
                map.get(g).put(h, conj);
            }
        }
        
        return map;
    }
}
