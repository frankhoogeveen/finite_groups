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
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_classifier.GroupClassifier;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism_calculator.HomomorphismProperty;
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
            Lattice<GroupHomomorphism> subgroupLattice = (Lattice<GroupHomomorphism>) g.getProperty(GroupProperty.SubgroupEmbeddingLattice);
           
            sb.append("number of subgroups: ");
            sb.append(subgroupLattice.size());
            sb.append("\n");
            
            List<GroupHomomorphism> list = subgroupLattice.sort();
            
            for(GroupHomomorphism embedding : list){
                boolean isMaximal = subgroupLattice.coveredSet(subgroupLattice.top()).contains(embedding);
                reportOnSubgroup(embedding, isMaximal, sb);
            }
            
            sb.append("\n");
            
        } catch (EvaluationException ex) {
            String mess = "could not calculate subgroup lattice";
            Logger.getLogger(SubgroupFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return sb;
    }

    private void reportOnSubgroup(GroupHomomorphism embedding, boolean isMaximal, StringBuilder sb) throws EvaluationException {
        String indent = "    ";
        
        Group subgroup = (Group) embedding.getProperty(HomomorphismProperty.ImageGroup);
        int order = (int) subgroup.getProperty(GroupProperty.Order);
        String name = GroupClassifier.getInstance().identify(subgroup);
        
        boolean isNormal = (boolean) embedding.getProperty(HomomorphismProperty.IsNormal);
        boolean isSylow = (boolean) embedding.getProperty(HomomorphismProperty.IsSylow);
        
        sb.append("----");
        sb.append("subgroup of order: ");
        sb.append(order);
        sb.append(" identified as ");
        sb.append(name);
        sb.append("\n");
        
        if(isMaximal){
            sb.append(indent);
            sb.append("is maximal subgroup");
            sb.append("\n");
        }
        
        if(isSylow){
            sb.append(indent);
            sb.append("is Sylow");
            sb.append("\n");
        }
        
        sb.append(indent);
        sb.append("is normal: ");
        sb.append(isNormal);
        sb.append("\n");
        
        if(isNormal){
            Group factor = (Group) embedding.getProperty(HomomorphismProperty.FactorGroup);
            String factorName = GroupClassifier.getInstance().identify(factor);
            
            sb.append(indent);
            sb.append("quotient is identified as: ");
            sb.append(factorName);
            sb.append("\n");
        }
    }
}
