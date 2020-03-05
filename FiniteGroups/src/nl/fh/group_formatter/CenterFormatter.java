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
import nl.fh.group.Group;
import nl.fh.group.GroupProperty;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;

/**
 *
 * @author frank
 */
public class CenterFormatter implements ItemFormatter {

    public CenterFormatter() {
    }

    @Override
    public StringBuilder format(Group g) {
        StringBuilder sb = new StringBuilder();
        
        try {   
            Set<Element> center = ((Set<Element>)g.getProperty(GroupProperty.Center));
            
            sb.append("center size: ");
            sb.append(center.size());
            sb.append("\n");
            sb.append("   ");
            for(Element z : center){
                sb.append(z.toString());
                sb.append(" ");
            }
            sb.append("\n");
                   
        } catch (EvaluationException ex) {
            String mess = "cannot retrieve the center";
            Logger.getLogger(CenterFormatter.class.getName()).log(Level.SEVERE, mess, ex);
            sb.append(mess);
        }
        return sb;
    }
    
}
