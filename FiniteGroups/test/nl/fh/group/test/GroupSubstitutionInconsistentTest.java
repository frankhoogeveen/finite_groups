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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class GroupSubstitutionInconsistentTest {
    /**
     *  The test set up here looks like the construction of Y21, but is 
     *  subtly different.
     * 
     *  Instead of y.x-> x.y.y,, we take y.x-> x.y.y.y
     * 
     * From this relation we can prove
     * y = y.x.x.x = x y.y.y .x.x = ... = x^3 y^6, thus y = y^6 
     * 
     * From this one can conclude y = unit. 
     * 
     * Therefore the group definition is inconsistent. 
     * The expected behavior is to return false when checked.
     * 
     * @throws InfoTableException 
     */
    @Test
    public void PseudoY21Test() throws InfoTableException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new StringElement("x"));
        generators.add(new StringElement("y"));
        
        StringMultiplicator multiplication = new StringMultiplicator();
        multiplication.addSubstitution(new StringSubstitution("xxx", ""));
        multiplication.addSubstitution(new StringSubstitution("yyyyyyy", ""));
        multiplication.addSubstitution(new StringSubstitution("yx", "xyyy"));
        
        String name = "Y21";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            Group g = new Group(definition);
            InfoTable info =  g.getInfo();
            assertEquals(21, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            
            assertFalse(check.isGroup(info));
        } catch (InfoTableException ex) {
            assertTrue(false);
        }
    }
    
 }
