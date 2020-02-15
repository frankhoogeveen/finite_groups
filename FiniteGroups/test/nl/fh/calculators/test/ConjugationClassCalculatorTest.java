/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.calculators.test;

import nl.fh.info_table_values.FamilyValue;
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
import nl.fh.info_table_values.IntArray2dValue;
import nl.fh.info_table_values.IntValue;
import nl.fh.info_table_values.SubsetValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class ConjugationClassCalculatorTest {
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
        
        FamilyValue classes =  ((FamilyValue)info.getValue(GroupProperty.ConjugationClasses));
        
        int nClass = classes.getCount();
        
        // check on the number of classes
        assertEquals(5, nClass);
        
        // check that the conjugation classes form a partition
        boolean[] empty = new boolean[24];
        for(int i = 0; i < 24; i++){
            empty[i] = false;
        }
        SubsetValue union = new SubsetValue(empty);
        
        for(int i = 0; i < nClass; i++){
            SubsetValue class_i = classes.getSubset(i);
            union = union.union(class_i);
            for(int j = i+1; j < nClass; j++){

               SubsetValue class_j = classes.getSubset(j);
               // differnent classes should have nothing in common
               assertTrue(class_i.intersection(class_j).isEmpty());
            }
        }
        
        // the union of all classes should be the entire group
        assertTrue(union.isAll());
    }
}
