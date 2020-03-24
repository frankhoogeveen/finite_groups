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

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group.Multiplicator;

/**
 *
 * @author frank
 */
public class GroupFromSetCalculator implements Calculator<Group>{

    private final String prefix;
    private final GroupProperty property;


    /**
     * 
     * @param z the prefix applied to the name of the original group
     * @param property 
     */
    GroupFromSetCalculator(String z, GroupProperty property) {
        this.prefix = z;
        this.property = property;
    }

    @Override
    public Group evaluate(Group group) throws EvaluationException {
        // retrieve the set of elements that generates the subgroup to be embedded
        Set<Element> set = (Set<Element>) group.getProperty(property);
        
        // the subgroup and the group share the multiplication rules
        Multiplicator mult = (Multiplicator) group.getProperty(GroupProperty.MultiplicationTable);
        
        // construct the name of the to be embedded group
        String groupName = (String) group.getProperty(GroupProperty.Name);
        String resultName = this.prefix + "(" + groupName + ")";
        
        // construct the domain from the set
        try {
            return new Group(resultName, set, mult);
        } catch (GroupException ex) {
            String mess = "could not construct group from set";
            Logger.getLogger(EmbeddingCalculator.class.getName()).log(Level.SEVERE, mess, ex);
            throw new EvaluationException(mess);
        }
    }
    
}
