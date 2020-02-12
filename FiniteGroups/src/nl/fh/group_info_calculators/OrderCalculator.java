/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_info_calculators;

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
public class OrderCalculator implements GroupCalculator{

    
    @Override
    public Property getProperty() {
        return GroupProperty.Order;
    }
    
    @Override
    public Value evaluate(InfoTable info) throws InfoTableException {
        int[][] table = ((IntArray2dValue)info.getValue(GroupProperty.MultiplicationTable)).content();
        return new IntValue(table.length);
    }


}
