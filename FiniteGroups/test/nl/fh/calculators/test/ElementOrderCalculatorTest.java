/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.calculators.test;

import java.util.HashSet;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupDefinition;
import nl.fh.group.Multiplicator;
import nl.fh.group_def_permutation.PermutationElement;
import nl.fh.group_def_permutation.PermutationMultiplicator;
import nl.fh.group_info_calculators.GroupProperty;
import nl.fh.info_table.InfoTable;
import nl.fh.info_table.InfoTableException;
import nl.fh.info_table_values.IntArray1dValue;
import nl.fh.info_table_values.IntValue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class ElementOrderCalculatorTest {
    
    @Test
    public void S4Test() throws InfoTableException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new PermutationElement(new int[]{1,0,2,3}));
        generators.add(new PermutationElement(new int[]{1,2,3,0}));
        
        Multiplicator multiplication = new PermutationMultiplicator(4);
        
        String name = "S4";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);

        Group g = new Group(definition);
        InfoTable info =  g.getInfo();
        
        IntArray1dValue val = (IntArray1dValue)info.getValue(GroupProperty.ElementOrders);

        assertEquals(0, val.count(0));
        
        assertEquals(1, val.count(1));            
        assertEquals(9, val.count(2));
        assertEquals(8, val.count(3));
        assertEquals(6, val.count(4));
        assertEquals(0, val.count(6));  
        assertEquals(0, val.count(8));            
        assertEquals(0, val.count(12));
        assertEquals(0, val.count(24));

        assertEquals(0, val.count(7));
        assertEquals(0, val.count(9));
        assertEquals(0, val.count(11));
    }
}
