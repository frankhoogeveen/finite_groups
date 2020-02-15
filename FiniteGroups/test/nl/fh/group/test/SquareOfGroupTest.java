/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupDefinition;
import nl.fh.group.Multiplicator;
import nl.fh.group_def_cyclic.CyclicElement;
import nl.fh.group_def_cyclic.CyclicMultiplicator;
import nl.fh.group_def_product.GroupProduct;
import nl.fh.group_info_calculators.GroupProperty;
import nl.fh.group_info_table.GroupInfoTableChecker;
import nl.fh.info_table.InfoTable;
import nl.fh.info_table.InfoTableException;
import nl.fh.info_table_values.IntValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests what happens if we create the direct product of a group with itself
 * 
 * @author frank
 */
public class SquareOfGroupTest {
    
    @Test 
    public void C3squaredTest(){

        // create an instance of C3
        Set<Element> generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(3));
        
        Multiplicator multiplication = new CyclicMultiplicator(3);
        
        GroupDefinition def = new GroupDefinition("C3", generators, multiplication);
        
        //define the product
        List<GroupDefinition> defs = new ArrayList<GroupDefinition>();
        defs.add(def);
        defs.add(def);
        GroupDefinition product = GroupProduct.of(defs);
        
        // check the assertions
        try {
            Group g = new Group(product);
            InfoTable info =  g.getInfo();
            assertEquals(3*3, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
            
            assertEquals("C3xC3", product.getName());
            
        } catch (InfoTableException ex) {
            assertTrue(false);
        }
    }
}
