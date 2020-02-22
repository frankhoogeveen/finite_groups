/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.info_table_values;

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
}
