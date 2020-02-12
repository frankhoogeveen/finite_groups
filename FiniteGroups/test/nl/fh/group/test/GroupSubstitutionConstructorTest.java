/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group.test;

import nl.fh.group_info_table.GroupInfoTableException;
import nl.fh.group.GroupDefinition;
import nl.fh.info_table.InfoTable;
import nl.fh.group_def_substitutions.StringSubstitution;
import nl.fh.group.Element;
import nl.fh.group_def_substitutions.StringElement;
import nl.fh.group_def_substitutions.StringMultiplicator;
import java.util.HashSet;
import java.util.Set;
import nl.fh.group_info_table.GroupInfoTableChecker;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class GroupSubstitutionConstructorTest {
    @Test
    public void Cyclic3Test(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("aaa", ""));
        
        String name = "C3";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            InfoTable info = new InfoTable(definition);
            assertEquals(3, info.getOrder());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (GroupInfoTableException ex) {
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
            InfoTable info = new InfoTable(definition);
            assertEquals(7, info.getOrder());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (GroupInfoTableException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void D3Test(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("a"));
        generators.add(new StringElement("b"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("aa", ""));
        multiplication.addSubstitution(new StringSubstitution("bbb", ""));
        multiplication.addSubstitution(new StringSubstitution("ba", "abb"));
        String name = "D3";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            InfoTable info = new InfoTable(definition);
            assertEquals(6, info.getOrder());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (GroupInfoTableException ex) {
            assertTrue(false);
        }
    }
    

    /**
     * creates a group of order 84
     */
    @Test
    public void G84Test(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        generators.add(new StringElement("z"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyyy", ""));
        multiplication.addSubstitution(new StringSubstitution("zzzzzzz", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xy"));
        multiplication.addSubstitution(new StringSubstitution("zx", "xzz"));
        multiplication.addSubstitution(new StringSubstitution("zy", "yzzzzzz"));        
        
        String name = "G84_2";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            InfoTable info = new InfoTable(definition);
            assertEquals(84, info.getOrder());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (GroupInfoTableException ex) {
            assertTrue(false);
        }
    }
}
