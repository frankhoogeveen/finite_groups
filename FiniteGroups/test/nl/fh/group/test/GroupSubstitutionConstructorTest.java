/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group.test;

import nl.fh.group.GroupDefinition;
import nl.fh.info_table.InfoTable;
import nl.fh.group_def_substitutions.StringSubstitution;
import nl.fh.group.Element;
import nl.fh.group_def_substitutions.StringElement;
import nl.fh.group_def_substitutions.StringMultiplicator;
import java.util.HashSet;
import java.util.Set;
import nl.fh.group.Group;
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
            Group g = new Group(definition);
            InfoTable info =  g.getInfo();
            assertEquals(3, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (InfoTableException ex) {
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
            Group g = new Group(definition);
            InfoTable info =  g.getInfo();
            assertEquals(7, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (InfoTableException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void D3Test() throws InfoTableException{
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
            Group g = new Group(definition);
            InfoTable info =  g.getInfo();
            assertEquals(6, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (InfoTableException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void Y21Test() throws InfoTableException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyyyyyy", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xyy"));
        
        String name = "Y21";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            Group g = new Group(definition);
            InfoTable info =  g.getInfo();
            assertEquals(21, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (InfoTableException ex) {
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
            Group g = new Group(definition);
            InfoTable info =  g.getInfo();
            assertEquals(84, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (InfoTableException ex) {
            assertTrue(false);
        }
    }
}
