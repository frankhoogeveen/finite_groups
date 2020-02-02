/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group;

/**
 *
 * @author frank
 */
public class CyclicGroupFactory implements GroupFactory {
    private int n;
    
    /**
     * @param n    the order of the cyclic groups that are produced
    */
    public CyclicGroupFactory(int n) {
        if(n < 1){ throw new IllegalArgumentException();}
        this.n = n;
    }

    @Override
    public GroupTable createTable() {
        GroupTable result = new GroupTable();
        
        result.order = n;
        
        result.table = new int[n][];
        for(int i = 0; i < n; i++){ 
            result.table[i] = new int[n];
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result.table[i][j] = (i + j) % n;
            }
        }
        
        return result;
    }
    
}
