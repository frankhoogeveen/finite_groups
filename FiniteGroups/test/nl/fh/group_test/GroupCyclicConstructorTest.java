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
import nl.fh.group.Multiplicator;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.checker.GroupChecker;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.GroupException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class GroupCyclicConstructorTest {
    
    @Test
    public void Cyclic11Test() throws GroupException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(11));
        
        Multiplicator multiplication = new CyclicMultiplicator(11);
        
        String name = "C11";
        
        Group c11 = new Group(name, generators, multiplication);
        
        try {
            
            assertEquals(11, (int)c11.getProperty(GroupProperty.Order));
            
            GroupChecker check = new GroupChecker();
            assertTrue(check.isGroup(c11));
        } catch (EvaluationException ex) {
            assertTrue(false);
        } 
    }
    
    @Test
    public void TrivialTest() throws GroupException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(CyclicElement.generatorOfOrder(11));
        
        Multiplicator multiplication = new CyclicMultiplicator(11);
        
        String name = "C11";
        
        Group c1 = new Group(name, generators, multiplication);
        
        try {
            
            assertEquals(11, (int)c1.getProperty(GroupProperty.Order));
            
            GroupChecker check = new GroupChecker();
            assertTrue(check.isGroup(c1));
        } catch (EvaluationException ex) {
            assertTrue(false);
        } 
    }
}
