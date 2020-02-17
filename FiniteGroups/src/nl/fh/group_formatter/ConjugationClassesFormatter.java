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
import nl.fh.info_table_values.FamilyValue;

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
        sb.append("Total number of conjugation classes: ");
        try { 
            boolean[][] conj = ((FamilyValue)g.getInfo().getValue(GroupProperty.ConjugationClasses)).content();
            sb.append(conj.length);
        } catch (InfoTableException ex) {
            String mess = "cannot retrieve conjugation classes";
            Logger.getLogger(OrderFormatter.class.getName()).log(Level.SEVERE, mess, ex);
            sb.append(mess);
        }
        sb.append("\n");
        return sb;
    }
    
}
