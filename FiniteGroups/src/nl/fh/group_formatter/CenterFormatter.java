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
import nl.fh.group_info_calculators.GroupProperty;
import nl.fh.info_table.InfoTableException;
import nl.fh.info_table_values.SubsetValue;

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
            SubsetValue center = ((SubsetValue)g.getInfo().getValue(GroupProperty.Center));
            boolean[] inCenter = center.content();
            int centerSize = center.count();
            
            sb.append("center size: ");
            sb.append(centerSize);
            sb.append("\n");
            sb.append("   ");
            for(int i = 0; i < inCenter.length; i++){
                if(inCenter[i]){
                    sb.append(g.getElements().get(i).toString());
                    sb.append(" ");
                }
            }
            sb.append("\n");
                   
        } catch (InfoTableException ex) {
            String mess = "cannot retrieve the center";
            Logger.getLogger(CenterFormatter.class.getName()).log(Level.SEVERE, mess, ex);
            sb.append(mess);
        }
        return sb;
    }
    
}
