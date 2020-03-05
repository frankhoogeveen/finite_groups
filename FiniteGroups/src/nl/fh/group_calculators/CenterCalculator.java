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

import nl.fh.group.GroupProperty;
import java.util.HashSet;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupTable;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;

/**
 *  Calculates the center of the group
 * 
 *  Evaluates groups to objects of type Set<Element>
 * 
 * @author frank
 */
public class CenterCalculator implements Calculator<Group> {

    public CenterCalculator() {
    }

    @Override
    public Set<Element> evaluate(Group group) throws EvaluationException {
        GroupTable table = (GroupTable)group.getProperty(GroupProperty.MultiplicationTable);
        
        Set<Element> result = new HashSet<Element>();
        for(Element z : group){
            boolean commutes = true;
            for(Element g : group){
                commutes &= (table.getProduct(z, g).equals(table.getProduct(g, z)));
            }
            if(commutes){
                result.add(z);
            }
        }
        return result;
    }    

}
