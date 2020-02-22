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
import nl.fh.info_table_values.FamilyValue;
import nl.fh.info_table_values.IntArray1dValue;

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
        try { 
            boolean[][] conj = ((FamilyValue)g.getInfo().getValue(GroupProperty.ConjugationClasses)).content();   

            totalNumberOfClasses(sb, conj);
            for(int i = 0; i < conj.length; i++){
                reportConjugationClass(sb, g, i);
            }
                
        } catch (InfoTableException ex) {
            String mess = "cannot retrieve conjugation classes";
            Logger.getLogger(OrderFormatter.class.getName()).log(Level.SEVERE, mess, ex);
            sb.append(mess);
        }
        
        return sb;
    }
    
    private void totalNumberOfClasses(StringBuilder sb, boolean[][] conj){
        sb.append("\nTotal number of conjugation classes: ");
        sb.append(conj.length);
        sb.append("\n");
    }

    private void reportConjugationClass(StringBuilder sb, Group g, int classNumber) throws InfoTableException {
        boolean[] conj = ((FamilyValue)g.getInfo().getValue(GroupProperty.ConjugationClasses)).content()[classNumber];
        int[] order = ((IntArray1dValue)g.getInfo().getValue(GroupProperty.ElementOrders)).content();
        
        // find the first element in the conjugation class and count the class size
        int first = -1;
        int count = 0;
        for(int i = 0; i < conj.length; i++){
            if((first == -1) && conj[i]){
                first = i;
            }
            if(conj[i]){
                count++;
            }
        }
        
        int elementOrder = order[first];
        
        sb.append("conjugation class: ");
        sb.append(count);
        sb.append(" elements of order ");
        sb.append(elementOrder);
        sb.append("\n     ");
        for(int i = 0; i < conj.length; i++){
            if(conj[i]){
                sb.append(g.getElements().get(i).toString());
                sb.append(" ");
            }
        }
        sb.append("\n\n");
    }
}
