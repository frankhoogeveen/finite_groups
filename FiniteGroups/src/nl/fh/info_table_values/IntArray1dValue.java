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
 * wrapper around a 1d integer array
 * 
 * 
 * @author frank
 */
public class IntArray1dValue implements Value {

    private final int[] content;

    public IntArray1dValue(int[] content) {
        this.content = content;
    }
    
    public int[] content(){
        return content;
    }

    /**
     * 
     * @param k
     * @return the number of occurrences of k in the array
     */
    public int count(int k) {
        int cnt = 0;
        for(int i = 0; i < content.length; i++){
            if(content[i] == k){
                cnt++;
            }
        }
        return cnt;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Arrays.hashCode(this.content);
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
        final IntArray1dValue other = (IntArray1dValue) obj;
        if (!Arrays.equals(this.content, other.content)) {
            return false;
        }
        return true;
    }
    
}
