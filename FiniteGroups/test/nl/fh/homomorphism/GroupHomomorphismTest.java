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
package nl.fh.homomorphism;

import nl.fh.homomorphism.GroupHomomorphism;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.checker.GroupChecker;
import nl.fh.group.GroupException;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_def_cyclic.CyclicElement;
import nl.fh.group_def_cyclic.CyclicMultiplicator;
import nl.fh.homomorphism.HomomorphismException;
import nl.fh.homomorphism_calculator.HomomorphismProperty;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class GroupHomomorphismTest {
    @Test
    public void BasicHomomorphismTest() throws GroupException, EvaluationException, HomomorphismException{
        /*
         * set up the cyclic group of order 7
         */
        Set<Element> generators = new HashSet<Element>();
        Element gen7 = CyclicElement.generatorOfOrder(7);
        generators.add(gen7);
        Group c7 = new Group("c7", generators, new CyclicMultiplicator(7));
        
        /*
         * set up the cyclic group of order 14
         */
        generators = new HashSet<Element>();
        Element gen14 = CyclicElement.generatorOfOrder(14);
        generators.add(gen14);
        Group c14 = new Group("c14", generators, new CyclicMultiplicator(14));
        
        /*
         * map the generator of c7 to an even number of times the generator of c14
         */
        Map<Element, Map<Integer,Element>> powerTable = 
                    (Map<Element, Map<Integer,Element>>) c14.getProperty(GroupProperty.PowerTable);
        
        Map<Element, Element> coreMap = new HashMap<Element, Element>();
        coreMap.put(gen7, powerTable.get(gen14).get(4));
        
        GroupHomomorphism m = new GroupHomomorphism(c7, c14, coreMap);
        /*
         * check that the result is a homomorphism 
         */
        
        GroupChecker check = new GroupChecker();
        check.isHomomorphism(m);
    }
    
    @Test
    public void TypesOfMorphismsTest() throws GroupException, EvaluationException, HomomorphismException{
                /*
         * set up the cyclic group of order 7
         */
        Set<Element> generators = new HashSet<Element>();
        Element gen7 = CyclicElement.generatorOfOrder(7);
        generators.add(gen7);
        Group c7 = new Group("c7", generators, new CyclicMultiplicator(7));
        
        /*
         * set up the cyclic group of order 14
         */
        generators = new HashSet<Element>();
        Element gen14 = CyclicElement.generatorOfOrder(14);
        generators.add(gen14);
        Group c14 = new Group("c14", generators, new CyclicMultiplicator(14));
        
        /*
         * map the generator of c7 to an even number of times the generator of c14
         */
        Map<Element, Map<Integer,Element>> powerTable = 
                    (Map<Element, Map<Integer,Element>>) c14.getProperty(GroupProperty.PowerTable);
        
        Map<Element, Element> coreMap = new HashMap<Element, Element>();
        coreMap.put(gen7, powerTable.get(gen14).get(4));
        
        GroupHomomorphism m = new GroupHomomorphism(c7, c14, coreMap);
        
        assertTrue((boolean) m.getProperty(HomomorphismProperty.IsMono));
        assertTrue(!(boolean) m.getProperty(HomomorphismProperty.IsEpi));
        assertTrue(!(boolean) m.getProperty(HomomorphismProperty.IsIso));
        assertTrue(!(boolean) m.getProperty(HomomorphismProperty.IsEndo));
        assertTrue(!(boolean) m.getProperty(HomomorphismProperty.IsAuto));
    }
    
}
