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
import nl.fh.info_table_values.BooleanValue;

/**
 *
 * @author frank
 */
public class IsAbeleanFormatter implements ItemFormatter {

    public IsAbeleanFormatter() {
    }

    @Override
    public StringBuilder format(Group g) {
        
        StringBuilder sb = new StringBuilder();
        sb.append("Is abelean: ");
        try {
            boolean abelean= ((BooleanValue)g.getInfo().getValue(GroupProperty.IsAbelean)).content();
            sb.append(abelean);
        } catch (InfoTableException ex) {
            String mess = "cannot retrieve IsAbelean";
            Logger.getLogger(OrderFormatter.class.getName()).log(Level.SEVERE, mess, ex);
            sb.append(mess);
        }
        sb.append("\n");
        return sb;
    }
    
}
