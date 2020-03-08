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
import java.util.Map;
import java.util.Set;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.Multiplicator;
import nl.fh.group_def_permutation.PermutationElement;
import nl.fh.group_def_permutation.PermutationMultiplicator;
import nl.fh.group.GroupProperty;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.GroupException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class ConjugationClassCalculatorTest {
    @Test
    public void S4Test() throws EvaluationException, GroupException{
        Set<Element> generators = new HashSet<Element>();
        generators.add(new PermutationElement(new int[]{1,0,2,3}));
        generators.add(new PermutationElement(new int[]{1,2,3,0}));
        
        Multiplicator multiplication = new PermutationMultiplicator(4);
        
        String name = "S4";

        Group group = new Group(name, generators, multiplication);
        
        Map<Element,Set<Element>> classes =  (Map<Element,Set<Element>>)group.getProperty(GroupProperty.ConjugationClassesMap);
        
        // check on the number of classes
        Set<Set<Element>> setOfClasses = new HashSet(classes.values());
        assertEquals(5, setOfClasses.size());
        
        // check that the conjugation classes form a partition
        // 1) check that they add up to the entire group
        Set<Element> union = new HashSet<Element>();
        for(Set<Element> c : classes.values()){
            union.addAll(c);
        }
        assertEquals(24, union.size());
        
        //2) make sure that two conjugation class are either the same or disjoint
        // the union of all classes should be the entire group
        for(Element g : group){
            for(Element h : group){
                boolean same = classes.get(g).equals(classes.get(h));
                
                Set<Element> intersection = (new HashSet(classes.get(g)));
                intersection.retainAll(classes.get(h));
                boolean disjoint = intersection.isEmpty();
                
                assertTrue(disjoint || same);
            }
        }
    }
}
