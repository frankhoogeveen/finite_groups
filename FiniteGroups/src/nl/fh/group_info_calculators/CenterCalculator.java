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

import nl.fh.info_table.Calculator;
import nl.fh.info_table.InfoTable;
import nl.fh.info_table.InfoTableException;
import nl.fh.info_table.Property;
import nl.fh.info_table.Value;
import nl.fh.info_table_values.IntArray2dValue;
import nl.fh.info_table_values.SubsetValue;

/**
 *  Calculates the center of the group
 * 
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
