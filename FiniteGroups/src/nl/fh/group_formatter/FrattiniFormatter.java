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

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_classifier.GroupClassifier;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism_calculator.HomomorphismProperty;

/**
 *
 * @author frank
 */
public class FrattiniFormatter implements ItemFormatter {

    public FrattiniFormatter(GroupFormatter aThis) {
    }

    @Override
    public StringBuilder format(Group g) {
        StringBuilder sb = new StringBuilder();
        
        try {
            Set<Element> frattiniSet = (Set<Element>) g.getProperty(GroupProperty.FrattiniSet);
            Group frattini = (Group) g.getProperty(GroupProperty.FrattiniGroup);
            int order = (int) frattini.getProperty(GroupProperty.Order);
            
            GroupHomomorphism frattiniMorph = (GroupHomomorphism) g.getProperty(GroupProperty.FrattiniEmbedding);
            Group frattiniQuotient = (Group) frattiniMorph.getProperty(HomomorphismProperty.FactorGroup);
            
            GroupClassifier classify = new GroupClassifier();
            
            sb.append("Order of Phi(G): ");
            sb.append(order);
            sb.append(" identified as:");
            sb.append(classify.identify(frattini));
            sb.append("   G/Phi(G): ");
            sb.append(classify.identify(frattiniQuotient));
            sb.append("\n\n");
            
        } catch (EvaluationException ex) {
            String mess = "could not calculate Frattini subgroup";
            Logger.getLogger(FrattiniFormatter.class.getName()).log(Level.SEVERE, mess, ex);
            sb.append(mess);
        }
        
        return sb;
        
    }
    
}
