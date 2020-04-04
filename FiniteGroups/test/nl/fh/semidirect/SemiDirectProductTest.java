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
package nl.fh.semidirect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import nl.fh.calculator.EvaluationException;
import nl.fh.checker.GroupChecker;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group.Multiplicator;
import nl.fh.group_classifier.GroupClassifier;
import nl.fh.group_def_automorphism.Automorphism;
import nl.fh.group_def_automorphism.AutomorphismMultiplicator;
import nl.fh.group_def_cyclic.CyclicElement;
import nl.fh.group_def_semidirect_product.GroupSemiDirectProduct;
import nl.fh.group_definition_factory.GroupFactory;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism.HomomorphismException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class SemiDirectProductTest {
    
    @Test
    public void C2C3SemiDirectProductTest() throws HomomorphismException, GroupException, EvaluationException{
        
        GroupFactory fac = new GroupFactory();
        Group c2 = fac.getCyclicGroup(2);  // H
        Group c3 = fac.getCyclicGroup(3);  // N
        
        Element unit2 = CyclicElement.create(0, 2);
        Element gen2 = CyclicElement.create(1, 2);
        
        Element unit3= CyclicElement.create(0, 3);
        Element gen3 = CyclicElement.create(1, 3);
        Element gen3sq = CyclicElement.create(2, 3);
        
        // make the two automorphisms that define the semidirect product
        
        Map<Element, Element> map0 = new HashMap<Element, Element>();
        map0.put(unit3, unit3);
        map0.put(gen3, gen3);
        map0.put(gen3sq, gen3sq);
        
        Automorphism phi0 = new Automorphism(c3, map0);
        
        Map<Element, Element> map1 = new HashMap<Element, Element>();
        map1.put(unit3, unit3);
        map1.put(gen3, gen3sq);
        map1.put(gen3sq, gen3);
        
        Automorphism phi1 = new Automorphism(c3, map1);
        
        Set<Element> phiSet = new HashSet<Element>();
        phiSet.add(phi0);
        phiSet.add(phi1);
        
        Multiplicator mult = new AutomorphismMultiplicator();
        
        Group aut = new Group("aut", phiSet, mult);
        
        //double check that aut is indeed a group
        GroupChecker checker = new GroupChecker();
        assertTrue(checker.isGroup(aut));
        
        // compose the map from H(c2) to Aut(N) (c3)
        
        Map<Element, Element> phi = new HashMap<Element, Element>();
        phi.put(unit2, phi0);
        phi.put(gen2, phi1);
        
        // make the homomorphism H -> Aut(N)
        GroupHomomorphism morph = new GroupHomomorphism(c2, aut, phi);
        
        // put together the semidirect product
        Group semi = GroupSemiDirectProduct.of(c2, c3, morph);
        
        // see if this group is indeed isomorphic to S3
        String name = GroupClassifier.getInstance().identify(semi);
        assertEquals("S3", name);
    }
}
