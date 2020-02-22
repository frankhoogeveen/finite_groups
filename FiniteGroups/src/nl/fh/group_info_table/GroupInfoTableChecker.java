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
package nl.fh.group_info_table;

import nl.fh.group_info_calculators.GroupProperty;
import nl.fh.info_table.InfoTable;
import nl.fh.info_table.InfoTableException;
import nl.fh.info_table_values.IntArray2dValue;
import nl.fh.info_table_values.IntValue;

/**
 *
 * @author frank
 */
public class GroupInfoTableChecker {
    
    public boolean isGroup(InfoTable info) throws InfoTableException{

        
        try{
            checkOrder(info);
            checkClosed(info);
            checkNeutralElement(info);
            checkInverses(info);
            checkAssociativity(info);
        } catch(InfoTableException e){
            return false;
        }
        return true;
    }

    private void checkOrder(InfoTable info) throws InfoTableException{
        int order = ((IntValue)info.getValue(GroupProperty.Order)).content();
        if(order < 1){
            throw new InfoTableException("Non positive order");
        }
    }

    private void checkClosed(InfoTable info) throws InfoTableException {
        int n = ((IntValue)info.getValue(GroupProperty.Order)).content();
        int[][] table = ((IntArray2dValue)info.getValue(GroupProperty.MultiplicationTable)).content();
        
        for(int i1 = 0; i1<n; i1++){
            for(int i2 = 0; i2<n; i2++){
                int i12 = table[i1][i2];
                if((i12<0) || (i12  >= n)){
                    throw new InfoTableException("Table not closed");
                }
            }
        }
    }

    private void checkNeutralElement(InfoTable info) throws InfoTableException{
        int u = ((IntValue)info.getValue(GroupProperty.UnitElement)).content();
        int n = ((IntValue)info.getValue(GroupProperty.Order)).content();
        int[][] table = ((IntArray2dValue)info.getValue(GroupProperty.MultiplicationTable)).content();
        for(int i = 0; i < n; i++){
            if((table[u][i] != i) || (table[i][u] != i)){
                throw new GroupInfoTableException("Incorrect unit");
            }
        }
    }

    private void checkInverses(InfoTable info) throws InfoTableException, GroupInfoTableException {
        int n = ((IntValue)info.getValue(GroupProperty.Order)).content();
        for(int i = 0; i < n; i++){
            checkHasInverse(i, info);
        }
    }
    
    private void checkHasInverse(int i, InfoTable info) throws InfoTableException, GroupInfoTableException {
        int u = ((IntValue)info.getValue(GroupProperty.UnitElement)).content();
        int n = ((IntValue)info.getValue(GroupProperty.Order)).content();
        int[][] table = ((IntArray2dValue)info.getValue(GroupProperty.MultiplicationTable)).content();
        
        for(int j = 0; j < n; j++){
            if((table[i][j] == u) && (table[j][i] == u)){
                return;
            }
        }
        throw new InfoTableException("Inverses do not exist for every element " + Integer.toString(i));
    }

    private void checkAssociativity(InfoTable info) throws InfoTableException, GroupInfoTableException {
        int u = ((IntValue)info.getValue(GroupProperty.UnitElement)).content();
        int n = ((IntValue)info.getValue(GroupProperty.Order)).content();
        int[][] table = ((IntArray2dValue)info.getValue(GroupProperty.MultiplicationTable)).content();
        
        for(int i1 = 0; i1 < n; i1++){
            for(int i2 = 0; i2 < n; i2++){
                for(int i3 = 0; i3 < n; i3++){
                    int n23 = table[i1][table[i2][i3]];
                    int n12 = table[table[i1][i2]][i3];
                    if(n12 != n23){
                        throw new InfoTableException("Not associative");
                    }
                } 
            }
        }
    }

}
