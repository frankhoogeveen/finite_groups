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

import java.util.Map;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.number.IntNumber;

/**
 * Calculates the exponent of a group, i.e. the smallest number n
 * such that the n-th power of each group element is the unit element.
 *
 * @author frank
 */
public class ExponentCalculator implements Calculator<Group> {

    @Override
    public Integer evaluate(Group group) throws EvaluationException {
        Map<Element, Integer> orders = (Map<Element, Integer>) group.getProperty(GroupProperty.ElementOrders);
        int result = 1;
        
        for(Element g : orders.keySet()){
            result = IntNumber.lcm(result, orders.get(g));
        }
        
        return result;
    }



    
}
