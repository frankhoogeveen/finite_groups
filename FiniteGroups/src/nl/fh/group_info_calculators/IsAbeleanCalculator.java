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
import nl.fh.info_table_values.BooleanValue;
import nl.fh.info_table_values.SubsetValue;

/**
 *
 * @author frank
 */
public class IsAbeleanCalculator implements Calculator {

    public IsAbeleanCalculator() {
    }

    @Override
    public Property getProperty() {
        return GroupProperty.IsAbelean;
    }

    @Override
    public Value evaluate(InfoTable info) throws InfoTableException {
        SubsetValue center = (SubsetValue)info.getValue(GroupProperty.Center);
        return new BooleanValue(center.isAll());
    }
   
}
