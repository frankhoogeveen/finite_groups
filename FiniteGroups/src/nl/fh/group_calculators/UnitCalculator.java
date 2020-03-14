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

import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Multiplicator;

/**
 * Searches for the unit element of the group 
 * 
 * @author frank
 */
class UnitCalculator  implements Calculator<Group> {

    @Override
    public  Element evaluate(Group group) throws EvaluationException {
        Multiplicator mult = (Multiplicator)(group.getProperty(GroupProperty.MultiplicationTable));
        
        for(Element u : group){
            boolean found = true;
            
            for(Element g : group){
                found &= ((mult.getProduct(u, g).equals(g)) && (mult.getProduct(g, u).equals(g)));
            }
            
            if(found){
                return u;
            }
        }
        
        throw new EvaluationException("could not find unit element");
    }
}
