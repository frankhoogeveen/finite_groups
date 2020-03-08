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

import nl.fh.group_def_permutation.PermutationMultiplicator;
import nl.fh.group_def_permutation.PermutationElement;
import java.util.HashSet;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;
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
public class GroupPermutationConstructorTest {
    @Test
    public void S4Test() throws GroupException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new PermutationElement(new int[]{1,0,2,3}));
        generators.add(new PermutationElement(new int[]{1,2,3,0}));
        
        Multiplicator multiplication = new PermutationMultiplicator(4);
        
        String name = "S4";
        
        Group s4 = new Group(name, generators, multiplication);
        
        try {
            assertEquals(24, (int)s4.getProperty(GroupProperty.Order));
            
            GroupChecker check = new GroupChecker(true);
            assertTrue(check.isGroup(s4));
        } catch (EvaluationException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void A4Test() throws GroupException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new PermutationElement(new int[]{1,0,3,2}));
        generators.add(new PermutationElement(new int[]{0,2,3,1}));
        
        Multiplicator multiplication = new PermutationMultiplicator(4);
        
        String name = "A4";
        
        Group a4 = new Group(name, generators, multiplication);
        
        try {
            assertEquals(12, (int)a4.getProperty(GroupProperty.Order));
            
            GroupChecker check = new GroupChecker(true);
            assertTrue(check.isGroup(a4));
        } catch (EvaluationException ex) {
            assertTrue(false);
        }
    }
}
