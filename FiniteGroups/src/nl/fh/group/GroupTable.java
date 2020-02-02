/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group;

/**
 *
 * @author frank
 * 
 * Class intended to contain the definition and properties of a 
 * finite group as a table. 
 * 
 * 
 */
public class GroupTable {
    int order;
    int unit;
    int[][] table;
    
    /**
     * @return  the order of the group 
     */
    public int getOrder(){
        return this.order;
    }
    
    /**
     * 
     * @return true of this meets the definition of a group table
     * Note that the check is done by brute force and therefore O(order^3)
     */
    public boolean checkDefinition(){
        if(!checkSize()) { return false;}
        if(!checkClosed()) { return false;}
        if(!neutralElement()) { return false;}
        if(!inverses()) { return false;}
        if(!associativity()) {return false;}
        return true;
    }

    private boolean checkSize() {
        if(order < 1){ return false;}
        if(order != table.length){ return false;}
        for(int i = 0; i < order; i++){
            if(order != table[i].length){ return false;}
        }
        return true;
    }

    private boolean checkClosed() {
        for(int i = 0; i < order; i++){
            for(int j = 0; j < order; j++){
                if(table[i][j] < 0 || table[i][j] >= order) { return false;}
            }
        }
        return true;
    }

    private boolean neutralElement() {
        for(int i = 0; i < order; i++){
            if(table[unit][i] != i || table[i][unit] != i) { return false;}
        }
        return true;
    }

    private boolean inverses() {
        for(int i = 0; i < order; i++){
            boolean exist = false;
            for(int j = 0; j < order && !exist; j++){
                if(table[i][j] == unit && table[j][i] == unit){
                    exist = true;
                }
            }
            if(!exist){return false;}
        }
        return true;
    }

    private boolean associativity() {
        for(int i = 0; i < order; i++){
            for(int j = 0; j < order; j++){
                for(int k = 0; k < order; k++){
                    if(table[i][table[j][k]] != table[table[i][j]][k]) { return false;}
                }
            }
        }
        return true;
    }
}
