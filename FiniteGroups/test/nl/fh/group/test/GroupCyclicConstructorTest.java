/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group.test;

import nl.fh.group_def_cyclic.CyclicMultiplicator;
import nl.fh.group_def_cyclic.CyclicElement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupDefinition;
import nl.fh.group_info_table.GroupInfoTableException;
import nl.fh.info_table.InfoTable;
import nl.fh.group.Multiplicator;
import nl.fh.group_info_calculators.GroupProperty;
import nl.fh.group_info_table.GroupInfoTableChecker;
import nl.fh.info_table.InfoTableException;
import nl.fh.info_table_values.IntValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class GroupCyclicConstructorTest {
    
    @Test
    public void Cyclic11Test(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(11));
        
        Multiplicator multiplication = new CyclicMultiplicator(11);
        
        String name = "C11";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            
            Group g = new Group(definition);
            InfoTable info =  g.getInfo();
            assertEquals(11, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (InfoTableException ex) {
            assertTrue(false);
        } 
    }
    
    @Test
    public void TrivialTest(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(1));
        
        Multiplicator multiplication = new CyclicMultiplicator(1);
        
        String name = "C1";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            Group g = new Group(definition);
            InfoTable info =  g.getInfo();
            assertEquals(1, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (InfoTableException ex) {
            assertTrue(false);
        }
    }
}
