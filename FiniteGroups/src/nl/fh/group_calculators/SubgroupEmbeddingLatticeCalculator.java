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
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism.HomomorphismException;
import nl.fh.lattice.Lattice;
import nl.fh.lattice_implementations.MappedLattice;

/**
 *
 * @author frank
 */
public class SubgroupEmbeddingLatticeCalculator implements Calculator<Group> {

    @Override
    public Lattice<GroupHomomorphism> evaluate(Group group) throws EvaluationException {
        Lattice<Group> subgroups = (Lattice<Group>) group.getProperty(GroupProperty.SubgroupLattice);
        
        Map<GroupHomomorphism,Group> map = new HashMap<GroupHomomorphism,Group>();
        for(Group subgroup : subgroups.sort()){
            try {
                map.put(group.embed(subgroup),subgroup);
            } catch (HomomorphismException|EvaluationException ex) {
                String mess = "could not generate embedding";
                Logger.getLogger(SubgroupLatticeCalculator.class.getName()).log(Level.SEVERE, mess, ex);
                throw new EvaluationException(mess);
            }
        }
        
        return new MappedLattice<GroupHomomorphism,Group>(subgroups, map);
    }
    
}
