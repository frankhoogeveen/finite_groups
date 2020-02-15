/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_info_calculators;

import nl.fh.info_table.Calculator;
import nl.fh.info_table.InfoTable;
import nl.fh.info_table.InfoTableException;
import nl.fh.info_table.Property;
import nl.fh.info_table.Value;
import nl.fh.info_table_values.IntArray2dValue;
import nl.fh.info_table_values.SubsetValue;

/**
 *
 * @author frank
 */
public class CenterCalculator implements Calculator {

    public CenterCalculator() {
    }

    @Override
    public Property getProperty() {
        return GroupProperty.Center;
    }

    @Override
    public Value evaluate(InfoTable info) throws InfoTableException {
        int[][] table = ((IntArray2dValue)info.getValue(GroupProperty.MultiplicationTable)).content();
        
        boolean[] result = new boolean[table.length];
        for(int i = 0; i < table.length; i++){
            result[i] = true;
            for(int j = 0; j < table.length; j++){
                result[i] &= (table[i][j] == table[j][i]);
            }
        }
        
        return new SubsetValue(result);
    }
    
    
}
