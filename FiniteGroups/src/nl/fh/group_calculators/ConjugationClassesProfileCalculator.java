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

/**
 * returns a orderMap of type Map<Integer, Map<Integer, Integer>>
 
 such that orderMap.get(order).get(size) return the number of conjugation classes 
 with that size, containing elements of that order.
 * 
 * @author frank
 */
public class ConjugationClassesProfileCalculator implements Calculator<Group> {

    @Override
    public Map<Integer, Map<Integer, Integer>> evaluate(Group group) throws EvaluationException {
        Map<Set<Element>, Integer> orderMap = (Map<Set<Element>, Integer>) group.getProperty(GroupProperty.ConjugationClassesOrders);
        
        Map<Integer, Map<Integer, Integer>> result = new HashMap<Integer, Map<Integer, Integer>>();
        for(Set<Element> set : orderMap.keySet()){
            int size = set.size();
            int order = orderMap.get(set);
            
            if(!result.containsKey(order)){
                result.put(order, new HashMap<Integer, Integer>());
            }
            
            if(!result.get(order).containsKey(size)){
                result.get(order).put(size, 0);
            }
            
            int count = result.get(order).get(size);
            result.get(order).put(size, count+1);
        }
        return result;
    }
}
