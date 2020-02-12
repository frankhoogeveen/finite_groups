/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group_info_calculators;

import nl.fh.info_table_values.IntArray2dValue;
import nl.fh.group_info_table.GroupInfoTableException;
import nl.fh.group.*;
import java.util.List;
import nl.fh.info_table.InfoTable;
import nl.fh.info_table.Property;
import nl.fh.info_table.Value;

/**
 * Instances of this class calculate the multiplication table based on a list
 * of elements and the multiplicator.
 * 
 * @author frank
 */
public class MultiplicationTableCalculator implements GroupCalculator{

    private final List<Element> elements;
    private final Multiplicator multiplicator;

    public MultiplicationTableCalculator(List<Element> elements, Multiplicator multiplicator) {
        this.elements = elements;
        this.multiplicator = multiplicator;
    }

    @Override
    public Property getProperty() {
        return GroupProperty.MultiplicationTable;
    }
    
    @Override
    public Value evaluate(InfoTable info) throws GroupInfoTableException {
        int[][] result = new int[elements.size()][];
        for(int i1 = 0; i1 < elements.size(); i1++){
            result[i1] = new int[elements.size()];
            for(int i2 = 0; i2 < elements.size(); i2++){
                Element g1 = elements.get(i1);
                Element g2 = elements.get(i2);
                Element g3 = multiplicator.getProduct(g1,g2);
                result[i1][i2] = elements.indexOf(g3);
                
                if(result[i1][i2] < 0){
                    throw new GroupInfoTableException("could not build multiplication table [not closed]");
                }
            }
        }
        
        return new IntArray2dValue(result);
    }
}
