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

import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;

/**
 *
 * @author frank
 */
public class OuterAutomorphismGroupFormatter implements ItemFormatter {

    OuterAutomorphismGroupFormatter(GroupFormatter overall) {
    }

    @Override
    public StringBuilder format(Group g) {
        StringBuilder sb = new StringBuilder();
        
        if(FormatRules.isExpensive(g)){
            sb.append("Outer automorphism group not calculated\n");
            return sb;
        }
        
        try {
            Group aut = (Group) g.getProperty(GroupProperty.OuterAutomorphismGroup);
            int order = (int) aut.getProperty(GroupProperty.Order);
            
            sb.append("Order of outer automorphism group: ");
            sb.append(order);
            sb.append("\n");
                            
        } catch (EvaluationException ex) {
            String mess = "could not format outer automorphisms";
            Logger.getLogger(InnerAutomorphismGroupFormatter.class.getName()).log(Level.SEVERE, mess, ex);
            sb.append(mess);
        }
        
        return sb;
    }    
    
}
