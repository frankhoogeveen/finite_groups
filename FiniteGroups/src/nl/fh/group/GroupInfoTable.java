/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group;

import java.util.List;

/**
 *
 * @author frank
 */
public class GroupInfoTable {
    public static final int ERROR = -1;
    
    GroupDefinition definition;
    List<Element> groupElements;
    
    int order;
    int unit;
    int[][] multiplicationTable;
    
    public int getOrder(){
        return this.order;
    }
    
    public int getUnit(){
        return this.unit;
    }
    
    /**
     * 
     * @param g1
     * @param g2
     * @return the multiplication of g1 * g2, if both are legal values
     * otherwise GroupInfoTable.ERROR is returned
     * 
     */
    public int multiply(int g1, int g2){
        if((g1<0) || (g1 >= this.order) || (g2<0) || (g2 >= this.order)){
            return GroupInfoTable.ERROR;
        }
        return multiplicationTable[g1][g2];
    }
}
