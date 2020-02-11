/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_info;

import java.util.List;
import nl.fh.group.Element;
import nl.fh.group.GroupDefinition;

/**
 * The concern of this class is to contain everything we know of this group.
 * Its concern is not to keep track of the correctness and consistency of the
 * information contained. Getters should be public, setters package private.
 * 
 * @author frank
 */
public class GroupInfoTable {
    public static final int ERROR = -1;
    
    GroupDefinition definition;
    
    List<Element> groupElements = null;
    Integer order = null;
    Integer unit = null;
    int[][] multiplicationTable = null;

    public GroupInfoTable(GroupDefinition def){
        this.definition = def;
    }
    
    public GroupDefinition getDefinition() {
        return definition;
    }
    
    public List<Element> getGroupElements() throws GroupInfoConstructionException{
        if(groupElements == null){
            GroupInfoTableUpdater.calculateGroupElements(this);
        }
        return this.groupElements;
    }
    
    public int getOrder() throws GroupInfoConstructionException{
        if(this.order == null){
            GroupInfoTableUpdater.calculateOrder(this);
        }
        return this.order;
    }
    
    public int getUnit() throws GroupInfoConstructionException{
        if(this.unit == null){
            GroupInfoTableUpdater.calculateUnit(this);
        }
        return this.unit;
    }
    
    public int[][] getMultiplicationTable() throws GroupInfoConstructionException{
        if(this.multiplicationTable == null){
            GroupInfoTableUpdater.calculateMultiplicationTable(this);
        }
        return this.multiplicationTable;
    }
    
    /**
     * 
     * @param g1
     * @param g2
     * @return the multiplication of g1 * g2, if both are legal values
     * otherwise GroupInfoTable.ERROR is returned
     * @throws nl.fh.group_info.GroupInfoConstructionException
     
     * 
     */
    public int multiply(int g1, int g2) throws GroupInfoConstructionException{
        if((g1<0) || (g1 >= this.order) || (g2<0) || (g2 >= this.order)){
            throw new IllegalArgumentException("index of group element out of range");
        }
        
        return getMultiplicationTable()[g1][g2];
    }
}
