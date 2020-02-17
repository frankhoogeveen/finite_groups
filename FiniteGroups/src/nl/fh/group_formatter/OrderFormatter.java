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
import nl.fh.info_table_values.IntValue;

/**
 *
 *  Formats the report element on the order of the group
 * 
 * @author frank
 */
public class OrderFormatter implements ItemFormatter {

    public OrderFormatter() {
    }

    @Override
    public StringBuilder format(Group g) {
        
        StringBuilder sb = new StringBuilder();
        sb.append("Order: ");
        try {
            int order = ((IntValue)g.getInfo().getValue(GroupProperty.Order)).content();
            sb.append(order);
        } catch (InfoTableException ex) {
            String mess = "cannot retrieve order";
            Logger.getLogger(OrderFormatter.class.getName()).log(Level.SEVERE, mess, ex);
            sb.append(mess);
        }
        sb.append("\n");
        return sb;
    }
    
}
