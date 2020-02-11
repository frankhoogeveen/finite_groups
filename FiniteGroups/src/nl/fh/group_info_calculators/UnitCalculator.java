/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_info_calculators;

import nl.fh.group.Element;
import nl.fh.group_info.GroupInfoConstructionException;
import nl.fh.group_info.GroupInfoTable;

/**
 *
 * @author frank
 */
public class UnitCalculator{


    public int calculate(GroupInfoTable info) throws GroupInfoConstructionException {
        Element unitElement = info.getDefinition().getMultiplicator().getUnit();
        int index = info.getGroupElements().indexOf(unitElement);
        
        if(index < 0){
            throw new GroupInfoConstructionException("could not find unit element");
        }
        
        return index;
    }
    
}
