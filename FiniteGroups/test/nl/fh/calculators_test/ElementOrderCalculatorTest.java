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

import java.util.HashSet;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupDefinition;
import nl.fh.group.Multiplicator;
import nl.fh.group_def_permutation.PermutationElement;
import nl.fh.group_def_permutation.PermutationMultiplicator;
import nl.fh.group.GroupProperty;
import nl.fh.info_table.Cache;
import nl.fh.calculator.EvaluationException;
import nl.fh.info_table_values.IntArray1dValue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class ElementOrderCalculatorTest {
    
    @Test
    public void S4Test() throws EvaluationException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new PermutationElement(new int[]{1,0,2,3}));
        generators.add(new PermutationElement(new int[]{1,2,3,0}));
        
        Multiplicator multiplication = new PermutationMultiplicator(4);
        
        String name = "S4";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);

        Group g = new Group(definition);
        Cache info =  g.getInfo();
        
        IntArray1dValue val = (IntArray1dValue)info.getValue(GroupProperty.ElementOrders);

        assertEquals(0, val.count(0));
        
        assertEquals(1, val.count(1));            
        assertEquals(9, val.count(2));
        assertEquals(8, val.count(3));
        assertEquals(6, val.count(4));
        assertEquals(0, val.count(6));  
        assertEquals(0, val.count(8));            
        assertEquals(0, val.count(12));
        assertEquals(0, val.count(24));

        assertEquals(0, val.count(7));
        assertEquals(0, val.count(9));
        assertEquals(0, val.count(11));
    }
}
