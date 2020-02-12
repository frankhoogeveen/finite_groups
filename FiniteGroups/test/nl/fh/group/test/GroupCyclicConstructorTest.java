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
import nl.fh.group.Element;
import nl.fh.group.GroupDefinition;
import nl.fh.group_info_table.GroupInfoTableException;
import nl.fh.info_table.InfoTable;
import nl.fh.group.Multiplicator;
import nl.fh.group_info_table.GroupInfoTableChecker;
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
            InfoTable info =  new InfoTable(definition);
            assertEquals(11, info.getOrder());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (GroupInfoTableException ex) {
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
            InfoTable info = new InfoTable(definition);
            assertEquals(1, info.getOrder());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (GroupInfoTableException ex) {
            assertTrue(false);
        }
    }
}
