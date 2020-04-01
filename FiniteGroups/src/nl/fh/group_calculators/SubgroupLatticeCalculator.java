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
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.Calculator;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.lattice.Lattice;
import nl.fh.lattice_implementations.MappedLattice;

/**
 *
 * @author frank
 */
public class SubgroupLatticeCalculator implements Calculator<Group> {

    public SubgroupLatticeCalculator() {
    }

    @Override
    public Lattice<Group> evaluate(Group group) throws EvaluationException {
        Lattice<Set<Element>> sets = (Lattice<Set<Element>>) group.getProperty(GroupProperty.SubgroupSetLattice);
        
        Map<Group,Set<Element>> map = new HashMap<Group,Set<Element>>();
        for(Set<Element> set : sets.sort()){
            try {
                map.put(group.generateSubgroup(set),set);
            } catch (GroupException ex) {
                String mess = "could not generate subgroup";
                Logger.getLogger(SubgroupLatticeCalculator.class.getName()).log(Level.SEVERE, mess, ex);
                throw new EvaluationException(mess);
            }
        }
        
        return new MappedLattice<Group,Set<Element>>(sets, map);
    }
}
