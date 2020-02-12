/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group.test;

import nl.fh.group_def_permutation.PermutationMultiplicator;
import nl.fh.group_def_permutation.PermutationElement;
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
public class GroupPermutationConstructorTest {
    @Test
    public void S4Test(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(new PermutationElement(new int[]{1,0,2,3}));
        generators.add(new PermutationElement(new int[]{1,2,3,0}));
        
        Multiplicator multiplication = new PermutationMultiplicator(4);
        
        String name = "S4";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            InfoTable info = new InfoTable(definition);
            assertEquals(24, info.getOrder());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (GroupInfoTableException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void A4Test(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(new PermutationElement(new int[]{1,0,3,2}));
        generators.add(new PermutationElement(new int[]{0,2,3,1}));
        
        Multiplicator multiplication = new PermutationMultiplicator(4);
        
        String name = "A4";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            InfoTable info = new InfoTable(definition);
            assertEquals(12, info.getOrder());
            
            GroupInfoTableChecker check = new GroupInfoTableChecker();
            assertTrue(check.isGroup(info));
        } catch (GroupInfoTableException ex) {
            assertTrue(false);
        }
    }
}