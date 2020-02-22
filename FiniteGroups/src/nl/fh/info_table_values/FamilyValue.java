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
 * this represents a set of subsets of a group.
 * Examples of use are the conjugation classes and/or the 
 * maximal (normal) subgroups.
 * 
 * this.set[alpha][i] is true means that group element i is in subset alpha
 * 
 * @author frank
 */
public class FamilyValue implements Value {

    private final boolean[][] sets;

    public FamilyValue(boolean[][] sets) {
        this.sets = sets;
    }
    
    /**
     * 
     * @return the number of classes
     */
    public int getCount(){
        return sets.length;
    }

    /**
     * 
     * @param alpha
     * @return subset with index alpha
     */
    public SubsetValue getSubset(int alpha) {
        return new SubsetValue(sets[alpha]);
    }

    public boolean[][] content() {
        return this.sets;
    }
    
}
