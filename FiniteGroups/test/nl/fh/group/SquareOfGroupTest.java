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
package nl.fh.group;

import nl.fh.group_calculators.GroupProperty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;
import nl.fh.group_def_cyclic.CyclicElement;
import nl.fh.group_def_cyclic.CyclicMultiplicator;
import nl.fh.group_def_product.GroupProduct;
import nl.fh.checker.GroupChecker;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.GroupException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests what happens if we create the direct product of a group with itself
 * 
 * @author frank
 */
public class SquareOfGroupTest {
    
    @Test 
    public void C3squaredTest() throws EvaluationException, GroupException{

        // create an instance of C3
        Set<Element> generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(3));
        
        Multiplicator multiplication = new CyclicMultiplicator(3);
        
        Group def = new Group("C3", generators, multiplication);
        
        //define the product
        List<Group> defs = new ArrayList<Group>();
        defs.add(def);
        defs.add(def);
        Group product = GroupProduct.of(defs);
        
        // check the assertions
        try {

            assertEquals(3*3, (int)product.getProperty(GroupProperty.Order));
            
            GroupChecker check = new GroupChecker();
            assertTrue(check.isGroup(product));
            
            assertEquals("C3xC3", (String)product.getProperty(GroupProperty.Name));
            
        } catch (EvaluationException ex) {
            assertTrue(false);
        }
    }
}
