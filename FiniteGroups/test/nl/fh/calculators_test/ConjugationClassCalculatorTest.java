/*
 * Copyright (C) 2020 Frank Hoogeveen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nl.fh.calculators_test;

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
               
               // different classes should have nothing in common
               assertTrue(class_i.intersection(class_j).isEmpty());
            }
        }
        
        // the union of all classes should be the entire group
        assertTrue(union.isAll());
    }
}
