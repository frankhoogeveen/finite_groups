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

/**
 *
 *  Formats the report element on the order of the group
 * 
 * @author frank
 */
public class OrderFormatter implements ItemFormatter {
    
    OrderFormatter(GroupFormatter overall) {
    }

    @Override
    public StringBuilder format(Group g) {
        
        StringBuilder sb = new StringBuilder();
        sb.append("Order: ");
        try {
            int order = (int) g.getProperty(GroupProperty.Order);
            sb.append(order);
        } catch (EvaluationException ex) {
            String mess = "cannot retrieve order";
            Logger.getLogger(OrderFormatter.class.getName()).log(Level.SEVERE, mess, ex);
            sb.append(mess);
        }
        sb.append("\n");
        return sb;
    }
    
}
