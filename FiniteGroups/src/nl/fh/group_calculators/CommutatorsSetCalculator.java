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
import java.util.Map;
import java.util.Set;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;

/**
 *
 *  calculates a set that contains all the commutators g.h.g^(-1).h^(-1)
 * 
 * @author frank
 */
public class CommutatorsSetCalculator implements Calculator<Group> {

    @Override
    public Set<Element> evaluate(Group group) throws EvaluationException {
        Map<Element,Map<Element,Element>> commutators = (Map<Element,Map<Element,Element>>)group.getProperty(GroupProperty.CommutatorsMap);
        Map<Element, Map<Element,Element>> conjMap = (Map<Element, Map<Element,Element>>)group.getProperty(GroupProperty.ConjugationMap);
        Map<Element, Element> inv = (Map<Element, Element>)group.getProperty(GroupProperty.Inverses);
        
        Set<Element> result = new HashSet<Element>();
        for(Element g : group){
            for(Element h : group){
                result.add(commutators.get(g).get(h));
            }
        }
        return result;
    }
}
