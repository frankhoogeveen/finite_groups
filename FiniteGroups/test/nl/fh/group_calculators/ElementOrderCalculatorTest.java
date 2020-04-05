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
package nl.fh.group_calculators;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;
import nl.fh.group_def_permutation.PermutationElement;
import nl.fh.group_def_permutation.PermutationMultiplicator;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.GroupException;
import nl.fh.group_definition_factory.GroupFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class ElementOrderCalculatorTest {
    
    @Test
    public void S4Test() throws EvaluationException, GroupException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new PermutationElement(new int[]{1,0,2,3}));
        generators.add(new PermutationElement(new int[]{1,2,3,0}));
        
        Multiplicator multiplication = new PermutationMultiplicator(4);
        
        String name = "S4";

        Group group = new Group(name, generators, multiplication);
        
        Map<Element, Integer> val = (Map<Element, Integer>)group.getProperty(GroupProperty.ElementOrders);

        
        int[] tally = new int[24];
        for(Element g : group){
            tally[val.get(g)] += 1;
        }
        
        assertEquals(0, tally[0]);
        
        assertEquals(1, tally[1]);            
        assertEquals(9, tally[2]);
        assertEquals(8, tally[3]);
        assertEquals(6, tally[4]);
    }
    
    @Test
    public void ExponentTest() throws EvaluationException{
        GroupFactory factory = new GroupFactory();
        Group s4 = factory.getSymmetricGroup(4);
        
        int exp = (int) s4.getProperty(GroupProperty.Exponent);
        assertEquals(12, exp);
        
        Group c2cubed = factory.getAbeleanGroup(new int[]{2,2,2});
        exp = (int) c2cubed.getProperty(GroupProperty.Exponent);
        assertEquals(2, exp);
    }
}
