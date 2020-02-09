/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group.test;

import nl.fh.group.GroupInfoConstructionException;
import nl.fh.group.GroupDefinition;
import nl.fh.group.GroupInfoTable;
import nl.fh.free_group.StringSubstitution;
import nl.fh.group.Element;
import nl.fh.free_group.StringElement;
import nl.fh.free_group.StringMultiplicator;
import java.util.HashSet;
import java.util.Set;
import nl.fh.info_table_checker.InfoTableChecker;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class GroupConstructorTest {
    @Test
    public void Cyclic3Test(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("aaa", ""));
        
        String name = "C3";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            GroupInfoTable info = definition.constructInfoTable();
            assertEquals(3, info.getOrder());
            
            InfoTableChecker check = new InfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (GroupInfoConstructionException ex) {
            assertTrue(false);
        }
    }
    
        @Test
    public void Cyclic7Test(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("aaaaaaa", ""));
        
        String name = "C7";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            GroupInfoTable info = definition.constructInfoTable();
            assertEquals(7, info.getOrder());
            
            InfoTableChecker check = new InfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (GroupInfoConstructionException ex) {
            assertTrue(false);
        }
    }
}
