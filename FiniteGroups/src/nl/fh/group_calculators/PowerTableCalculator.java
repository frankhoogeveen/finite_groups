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
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.*;
import nl.fh.group.GroupTable;

/**
 *
 * @author frank
 */
public class PowerTableCalculator implements Calculator<Group> {

    public PowerTableCalculator() {
    }

    @Override
    public Object evaluate(Group group) throws EvaluationException {
        int order = (int) group.getProperty(GroupProperty.Order);
        Element unit = (Element) group.getProperty(GroupProperty.UnitElement);
        GroupTable table = (GroupTable) group.getProperty(GroupProperty.MultiplicationTable);
        
        GroupPowerTable powers = new GroupPowerTable(order);
        
        for(Element g : group){
            powers.put(g, new HashMap<Integer, Element>());
            powers.get(g).put(0, unit);
            for(int i = 1; i < order; i++){
               powers.get(g).put(i, table.getProduct(g, powers.get(g).get(i-1)));
            }
        }
        
        return powers;
    }
    
}
