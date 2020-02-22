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
package nl.fh.group_info_calculators;

import nl.fh.group_info_table.GroupInfoTableException;
import nl.fh.info_table.Calculator;
import nl.fh.info_table.InfoTable;
import nl.fh.info_table.InfoTableException;
import nl.fh.info_table.Property;
import nl.fh.info_table.Value;
import nl.fh.info_table_values.IntArray1dValue;
import nl.fh.info_table_values.IntArray2dValue;
import nl.fh.info_table_values.IntValue;

/**
 *
 *  Calculates the inverse of each group element
 * 
 * @author frank
 */
public class InverseCalculator implements Calculator {

    public InverseCalculator() {
    }

    @Override
    public Property getProperty() {
        return GroupProperty.Inverses;
    }

    @Override
    public Value evaluate(InfoTable info) throws InfoTableException {
        int[][] table = ((IntArray2dValue)info.getValue(GroupProperty.MultiplicationTable)).content();
        int unit = ((IntValue)info.getValue(GroupProperty.UnitElement)).content();
        
        int[] result = new int[table.length];
        for(int i = 0; i < table.length; i++){
            result[i] = calculateInverseOf(i, unit, table);
        }
        
        return new IntArray1dValue(result);
    }

    private int calculateInverseOf(int n, int unit, int[][] table) throws GroupInfoTableException {
        for(int i = 0; i < table.length; i++){
            if(table[i][n] == unit){
                return i;
            }
        }
        throw new GroupInfoTableException("could not find inverse");
    }
    
}
