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

import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_def_semidirect_product.GroupSemiDirectProduct;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism.HomomorphismException;

/**
 *
 * 
 * 
 * 
 * 
 * @author frank
 */
public class HolomorphCalculator implements Calculator<Group> {

    /**
     * 
     * @param group 
     * @return Hol(group) , the holomorph of group 
     * @throws EvaluationException
     * @throws HomomorphismException
     * @throws GroupException 
     * 
     * The result is the semi direct product of Aut(group) with group
     */

    @Override
    public Group evaluate(Group group) throws EvaluationException {
        
        try {
            Group aut = (Group) group.getProperty(GroupProperty.AutomorphismGroup);
            GroupHomomorphism morph = aut.embed(aut);
            Group semi = GroupSemiDirectProduct.of(aut, group, morph);
            
            return semi;
        } catch (HomomorphismException|GroupException ex) {
            String mess = "could not calculate Holomorph";
            Logger.getLogger(HolomorphCalculator.class.getName()).log(Level.SEVERE, mess, ex);
            throw new EvaluationException(mess);
        } 
    }
}
