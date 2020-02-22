/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
