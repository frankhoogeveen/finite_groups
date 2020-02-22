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
import nl.fh.info_table_values.FamilyValue;
import nl.fh.info_table_values.IntArray1dValue;
import nl.fh.info_table_values.IntArray2dValue;

/**
 *
 * @author frank
 */
public class ConjugationClassCalculator implements Calculator {

    public ConjugationClassCalculator() {
    }

    @Override
    public Property getProperty() {
        return GroupProperty.ConjugationClasses;
    }

    @Override
    public Value evaluate(InfoTable info) throws InfoTableException {
        int[][] table = ((IntArray2dValue)info.getValue(GroupProperty.MultiplicationTable)).content();
        int[] inv = ((IntArray1dValue)info.getValue(GroupProperty.Inverses)).content();
        
        int[][] conjugates = calculateConjugates(table, inv);
        boolean[][] classes = aggregateClasses(conjugates);
        
        return new FamilyValue(classes);
    }

    /**
     * 
     * @param table 
     * @param inv
     * @return the conjugates result[i][j] = j * i * j^(-1)
     */
    private int[][] calculateConjugates(int[][] table, int[] inv) {
        int[][] result = new int[table.length][];
        for(int i = 0; i < table.length; i++){
            result[i] = new int[table.length];
            for(int j = 0; j < table.length; j++){
                
                // calculate j * i * j^(-1)
                result[i][j] = table[j][table[i][inv[j]]];
            }
        }
        return result;
    }

    private boolean[][] aggregateClasses(int[][] conjugates) {
        // find the indeces that are minimal in their conjugation class 
        boolean[] minimal = new boolean[conjugates.length];
        int countMinimal = 0;
        for(int i = 0; i < conjugates.length; i++){
            boolean isMinimal = true;
            for(int j = 0; j < conjugates.length; j++){
                if(conjugates[i][j] < i){
                    isMinimal = false;
                }
            }
            minimal[i] = isMinimal;
            if(isMinimal){
                countMinimal++;
            }
        }
        
        // for each minimal index, gather the corresponding class
        boolean[][] result = new boolean[countMinimal][];
        int cnt = 0;
        for(int i = 0 ; i < conjugates.length; i++){
            if(minimal[i]){
                result[cnt] = new boolean[conjugates.length];
                for(int j = 0; j < conjugates.length; j++){
                    result[cnt][j] = false;
                }
                for(int j = 0; j < conjugates.length; j++){
                    result[cnt][conjugates[i][j]] = true;
                }
                cnt++;
            }
        }
        
        return result;
    }
    
}
