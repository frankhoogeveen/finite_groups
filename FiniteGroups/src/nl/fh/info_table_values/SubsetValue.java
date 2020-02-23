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
package nl.fh.info_table_values;

import java.util.Arrays;
import nl.fh.info_table.Value;

/**
 * represents a subset by storing it as an array of booleans
 * 
 * @author frank
 */
public class SubsetValue implements Value {
    private final boolean[] index;
    
    public SubsetValue(boolean[] index){
        this.index = index;
    }
    
    /**
    *
    * @return the number of elements in this subset
    */
    public int count(){
        int result = 0;
        for(int i = 0; i < index.length; i++){
            if(index[i]){
                result++;
            }
        }
        return result;
    }
    
    /**
     * 
     * @return true if all elements are in this subset
     */
    public boolean isAll(){
        boolean result = true;
        for(int i = 0; i < index.length; i++){
                result &= index[i];
            }
        return result;
    }
    
     /**
     * 
     * @return true if all elements are in this subset
     */
    public boolean isEmpty(){
        boolean result = true;
        for(int i = 0; i < index.length; i++){
                result &= !index[i];
            }
        return result;
    }

    /**
     * 
     * @param set another subset of the group this is also a subset of
     * @return the intersection of the two subsets
     */
    public SubsetValue intersection(SubsetValue set) {
        if(this.index.length != set.index.length){
            throw new IllegalArgumentException("cannot take the intersection");
        }
        
        boolean[] result = new boolean[this.index.length];
        for(int i = 0; i < this.index.length; i++){
            result[i] = (this.index[i] & set.index[i]);
        }
        
        return new SubsetValue(result);
    }

    /**
     * 
     * @param set another subset of the group this is also a subset of
     * @return the union of the two subsets
     */
    public SubsetValue union(SubsetValue set) {
        if(this.index.length != set.index.length){
            throw new IllegalArgumentException("cannot take the union");
        }
        
        boolean[] result = new boolean[this.index.length];
        for(int i = 0; i < this.index.length; i++){
            result[i] = (this.index[i] | set.index[i]);
        }
        
        return new SubsetValue(result);
    }

    public boolean[] content() {
        return this.index;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Arrays.hashCode(this.index);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SubsetValue other = (SubsetValue) obj;
        if (!Arrays.equals(this.index, other.index)) {
            return false;
        }
        return true;
    }


    
    
}
