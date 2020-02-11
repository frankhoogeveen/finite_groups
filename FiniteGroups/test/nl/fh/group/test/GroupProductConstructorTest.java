/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.group.test;

import nl.fh.group_def_product.GroupProduct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.GroupDefinition;
import nl.fh.group_info.GroupInfoConstructionException;
import nl.fh.group_info.GroupInfoTable;
import nl.fh.group.Multiplicator;
import nl.fh.group_def_cyclic.CyclicElement;
import nl.fh.group_def_cyclic.CyclicMultiplicator;
import nl.fh.group_def_permutation.PermutationElement;
import nl.fh.group_def_permutation.PermutationMultiplicator;
import nl.fh.group_info_table_checker.InfoTableChecker;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class GroupProductConstructorTest {
    @Test
    public void ProductTest(){
        List<GroupDefinition> defs = new ArrayList<GroupDefinition>();
        
        // create an instance of A4
        Set<Element> generators = new HashSet<Element>();
        generators.add(new PermutationElement(new int[]{1,0,3,2}));
        generators.add(new PermutationElement(new int[]{0,2,3,1}));
        
        Multiplicator multiplication = new PermutationMultiplicator(4);
        
        defs.add(new GroupDefinition("A4", generators, multiplication));
        
        // create an instance of C7
        generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(7));
        
        multiplication = new CyclicMultiplicator(7);
        
        defs.add(new GroupDefinition("C7", generators, multiplication));
        
        //define the product
        GroupDefinition product = GroupProduct.of(defs);
        
        // check the assertions
        try {
            GroupInfoTable info = new GroupInfoTable(product);
            assertEquals(12*7, info.getOrder());
            
            InfoTableChecker check = new InfoTableChecker();
            assertTrue(check.isGroup(info));
            
            assertEquals("A4xC7", product.getName());
            
        } catch (GroupInfoConstructionException ex) {
            assertTrue(false);
        }
    }
}
