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
package nl.fh.group_test;

import nl.fh.group_def_product.GroupProduct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;
import nl.fh.group_def_cyclic.CyclicElement;
import nl.fh.group_def_cyclic.CyclicMultiplicator;
import nl.fh.group_def_permutation.PermutationElement;
import nl.fh.group_def_permutation.PermutationMultiplicator;
import nl.fh.group.GroupProperty;
import nl.fh.group.GroupChecker;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.GroupException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class GroupProductConstructorTest {
    
    @Test
    
    public void EmptyProductTest(){
        assertTrue(false);
    }
    
    @Test
    public void ProductTest() throws GroupException, EvaluationException{
        List<Group> defs = new ArrayList<Group>();
        
        // create an instance of A4
        Set<Element> generators = new HashSet<Element>();
        generators.add(new PermutationElement(new int[]{1,0,3,2}));
        generators.add(new PermutationElement(new int[]{0,2,3,1}));
        
        Multiplicator multiplication = new PermutationMultiplicator(4);
        
        defs.add(new Group("A4", generators, multiplication));
        
        // create an instance of C7
        generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(7));
        
        multiplication = new CyclicMultiplicator(7);
        
        defs.add(new Group("C7", generators, multiplication));
        
        //define the product
        Group product = GroupProduct.of(defs);
        
        // check the assertions
        try {
            assertEquals(7*12, (int)product.getProperty(GroupProperty.Order));
            
            GroupChecker check = new GroupChecker();
            assertTrue(check.isGroup(product));
            
            assertEquals("A4xC7", product.getProperty(GroupProperty.Name));
            
        } catch (EvaluationException ex) {
            assertTrue(false);
        }
    }
}
