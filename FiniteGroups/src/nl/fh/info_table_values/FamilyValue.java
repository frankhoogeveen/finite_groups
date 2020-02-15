/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
}
