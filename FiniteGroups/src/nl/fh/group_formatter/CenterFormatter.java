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
import nl.fh.group.Group;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group_classifier.GroupClassifier;

/**
 *
 * @author frank
 */
public class CenterFormatter implements ItemFormatter {

    private final GroupFormatter overall;

    CenterFormatter(GroupFormatter overall) {
        this.overall = overall;
    }

    @Override
    public StringBuilder format(Group g) {
        StringBuilder sb = new StringBuilder(); 
        
        try {   
            Group center = (Group)g.getProperty(GroupProperty.CenterGroup);
            int order = (int) center.getProperty(GroupProperty.Order);
            
            sb.append("Order of Z(G): ");
            sb.append(order);
            sb.append("  identified as:");
            sb.append(GroupClassifier.getInstance().identify(center));            
            sb.append("\n");
            
            if(this.overall.isVerbose()){
                for(Element z : center){
                    sb.append(z.toString());
                    sb.append(" ");
                }
                sb.append("\n");
            }

        } catch (EvaluationException ex) {
            String mess = "cannot retrieve the center";
            Logger.getLogger(CenterFormatter.class.getName()).log(Level.SEVERE, mess, ex);
            sb.append(mess);
        }
        return sb;
    }
    
}
