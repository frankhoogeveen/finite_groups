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

import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;

/**
 *
 * @author frank
 */
public class ConjugationClassesFormatter implements ItemFormatter {

    public ConjugationClassesFormatter() {
    }

    @Override
    public StringBuilder format(Group g) {
        
        StringBuilder sb = new StringBuilder();
        try { 
            Set<Set<Element>> conj = (Set<Set<Element>>) g.getProperty(GroupProperty.ConjugationClassesSet);
            Map<Set<Element>, Integer> conjOrder = (Map<Set<Element>, Integer>) g.getProperty(GroupProperty.ConjugationsClassesOrders);
            
            totalNumberOfClasses(sb, conj);
            
            for(Set<Element> conjClass : conj){
                reportConjugationClass(sb, g, conjClass, conjOrder.get(conjClass));
            }
                
        } catch (EvaluationException ex) {
            String mess = "cannot report on conjugation classes";
            Logger.getLogger(OrderFormatter.class.getName()).log(Level.SEVERE, mess, ex);
            sb.append(mess);
        }
        
        return sb;
    }
    
    private void totalNumberOfClasses(StringBuilder sb, Set<Set<Element>> conj){
        sb.append("\nTotal number of conjugation classes: ");
        sb.append(conj.size());
        sb.append("\n");
    }

    private void reportConjugationClass(StringBuilder sb, Group g, Set<Element> conjClass, int order) throws EvaluationException {        
        sb.append("conjugation class of size: ");
        sb.append(conjClass.size());
        sb.append(" elements of order ");
        sb.append(order);
        sb.append("\n");
        for(Element c : conjClass){
            sb.append("  ");
            sb.append(c.toString());
            sb.append("\n");
        }
        sb.append("\n\n");
    }
}
