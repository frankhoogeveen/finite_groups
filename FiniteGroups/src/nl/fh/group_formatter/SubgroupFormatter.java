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
package nl.fh.group_formatter;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.lattice.Lattice;

/**
 *
 * @author frank
 */
public class SubgroupFormatter implements ItemFormatter {

    SubgroupFormatter(GroupFormatter overall) {
    }

    @Override
    public StringBuilder format(Group g) {
        StringBuilder sb = new StringBuilder();
        
        try {
            Lattice<Set<Element>> subgroupSetLattice = (Lattice<Set<Element>>) g.getProperty(GroupProperty.SubgroupSetLattice);
           
            sb.append("\nnumber of subgroups: ");
            sb.append(subgroupSetLattice.size());
            sb.append("\n");
            
            List<Set<Element>> list = subgroupSetLattice.sort();
            
            for(Set<Element> set : list){
                sb.append("  subgroup of size: ");
                sb.append(set.size());
                sb.append("\n");
            }
            
            sb.append("\n");
            
        } catch (EvaluationException ex) {
            String mess = "could not calculate subgroup lattice";
            Logger.getLogger(SubgroupFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return sb;
    }
    
}
