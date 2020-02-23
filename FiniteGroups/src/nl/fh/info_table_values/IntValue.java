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

import nl.fh.info_table.Value;

/**
 * wrapper around a 1d integer array
 * 
 * 
 * @author frank
 */
public class IntValue implements Value {

    private final int content;

    public IntValue(int content) {
        this.content = content;
    }
    
    public int content(){
        return content;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.content;
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
        final IntValue other = (IntValue) obj;
        if (this.content != other.content) {
            return false;
        }
        return true;
    }
    
}
