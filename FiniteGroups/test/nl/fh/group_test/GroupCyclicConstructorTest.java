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

import nl.fh.group_def_cyclic.CyclicMultiplicator;
import nl.fh.group_def_cyclic.CyclicElement;
import java.util.HashSet;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupDefinition;
import nl.fh.info_table.Cache;
import nl.fh.group.Multiplicator;
import nl.fh.group.GroupProperty;
import nl.fh.group.GroupChecker;
import nl.fh.calculator.EvaluationException;
import nl.fh.info_table_values.IntValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class GroupCyclicConstructorTest {
    
    @Test
    public void Cyclic11Test(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(11));
        
        Multiplicator multiplication = new CyclicMultiplicator(11);
        
        String name = "C11";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            
            Group g = new Group(definition);
            Cache info =  g.getInfo();
            assertEquals(11, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            GroupChecker check = new GroupChecker();
            assertTrue(check.isGroup(info));
        } catch (EvaluationException ex) {
            assertTrue(false);
        } 
    }
    
    @Test
    public void TrivialTest(){
        Set<Element> generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(1));
        
        Multiplicator multiplication = new CyclicMultiplicator(1);
        
        String name = "C1";
        
        GroupDefinition definition = new GroupDefinition(name, generators, multiplication);
        
        try {
            Group g = new Group(definition);
            Cache info =  g.getInfo();
            assertEquals(1, ((IntValue)info.getValue(GroupProperty.Order)).content());
            
            GroupChecker check = new GroupChecker();
            assertTrue(check.isGroup(info));
        } catch (EvaluationException ex) {
            assertTrue(false);
        }
    }
}
