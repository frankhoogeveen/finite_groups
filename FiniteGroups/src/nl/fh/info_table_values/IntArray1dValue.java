/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.info_table_values;

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
    
}
