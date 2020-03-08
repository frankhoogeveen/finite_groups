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
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupProperty;

/**
 *
 * @author frank
 */
public class InversesFormatter implements ItemFormatter {

    public InversesFormatter() {
    }

    @Override
    public StringBuilder format(Group group) {
        StringBuilder sb = new StringBuilder();
        try {
           sb.append("Inverses: \n");
           
           Map<Element, Element> inv = (Map<Element, Element>) group.getProperty(GroupProperty.Inverses);
           for(Element g : inv.keySet()){
               sb.append(g);
               sb.append(" ^(-1) = ");
               sb.append(inv.get(g));
               sb.append("\n");
           } 
            
        } catch (EvaluationException ex) {
            String mess = "failed to calculate inverses";
            sb.append(mess);
            Logger.getLogger(InversesFormatter.class.getName()).log(Level.SEVERE, mess, ex);
        }
        return sb;
    }
    
}
