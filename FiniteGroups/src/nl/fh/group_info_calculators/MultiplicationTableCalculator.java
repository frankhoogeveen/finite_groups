/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_info_calculators;

import nl.fh.group_info.GroupInfoConstructionException;
import nl.fh.group.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import nl.fh.group_info.GroupInfoTable;

/**
 *
 * @author frank
 */
public class MultiplicationTableCalculator {

    private final int maxIter;
    public MultiplicationTableCalculator(int maxIter) {
        this.maxIter = maxIter;
    } 

    public int[][] calculate(GroupInfoTable info) throws GroupInfoConstructionException {
        GroupDefinition def = info.getDefinition();
        List<Element> elements = info.getGroupElements();
        Multiplicator multiplicator = info.getDefinition().getMultiplicator();
        
        int[][] result = new int[elements.size()][];
        for(int i1 = 0; i1 < elements.size(); i1++){
            result[i1] = new int[elements.size()];
            for(int i2 = 0; i2 < elements.size(); i2++){
                Element g1 = elements.get(i1);
                Element g2 = elements.get(i2);
                Element g3 = multiplicator.getProduct(g1,g2);
                result[i1][i2] = elements.indexOf(g3);
                
                if(result[i1][i2] < 0){
                    throw new GroupInfoConstructionException("could not domultiplication");
                }
            }
        }
        return result;
        
    }
}
