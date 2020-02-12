/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_info_calculators;

import nl.fh.group_info_table.GroupInfoTableException;
import nl.fh.info_table.InfoTable;
import nl.fh.info_table.InfoTableException;
import nl.fh.info_table.Property;
import nl.fh.info_table.Value;
import nl.fh.info_table_values.IntArray2dValue;
import nl.fh.info_table_values.IntValue;

/**
 *
 * @author frank
 */
public class UnitCalculator  implements GroupCalculator {

    @Override
    public Value evaluate(InfoTable info) throws InfoTableException {
        int[][] table = ((IntArray2dValue)info.getValue(GroupProperty.MultiplicationTable)).content();
        
        for(int i = 0; i < table.length; i++){
            boolean found = true;
            
            for(int j = 0; j < table.length; j++){
                found &= ((table[i][j] == j) && (table[j][i] == j));
            }
            
            if(found){
                return new IntValue(i);
            }
        }
        
        throw new GroupInfoTableException("could not find unit element");
    }

    @Override
    public Property getProperty() {
        return GroupProperty.UnitElement;
    }
    
}
