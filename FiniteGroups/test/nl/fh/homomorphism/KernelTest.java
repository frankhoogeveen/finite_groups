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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import nl.fh.calculator.EvaluationException;
import nl.fh.group.Element;
import nl.fh.group.Group;
import nl.fh.group.GroupException;
import nl.fh.group_calculators.GroupProperty;
import nl.fh.group_def_substitutions.StringElement;
import nl.fh.group_def_substitutions.StringMultiplicator;
import nl.fh.group_def_substitutions.StringSubstitution;
import nl.fh.homomorphism.GroupHomomorphism;
import nl.fh.homomorphism.HomomorphismException;
import nl.fh.homomorphism_calculator.HomomorphismProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class KernelTest {

    
    @Test
    public void V4KernelTest() throws GroupException, HomomorphismException, EvaluationException{
        
        // construct the Viergroup
        StringElement unit = new StringElement("");
        StringElement a = new StringElement("a");
        StringElement b = new StringElement("b");
        StringElement ab = new StringElement("ab");
        
        Set<Element> set = new HashSet<Element>();
        set.add(unit);
        set.add(a);
        set.add(b);
        set.add(ab);
        
        StringMultiplicator mult1 = new StringMultiplicator();
        mult1.addSubstitution(new StringSubstitution("aa", ""));
        mult1.addSubstitution(new StringSubstitution("bb", "")); 
        mult1.addSubstitution(new StringSubstitution("ba", "ab")); 
        
        Group viergroup = new Group("V4", set, mult1);
        
        // construct C2
        StringElement x = new StringElement("x");
        
        Set<Element> set2 = new HashSet<Element>();
        set2.add(unit);
        set2.add(x);
        
        StringMultiplicator mult2 = new StringMultiplicator();
        mult2.addSubstitution(new StringSubstitution("xx", ""));
        
        Group c2 = new Group("c2", set2, mult2);
        
        // construct the homomophism
        Map<Element,Element> map = new HashMap<Element,Element>();
        map.put(unit, unit);
        map.put(a, x);
        map.put(b, x);
        map.put(ab, unit);
        
        GroupHomomorphism morph = new GroupHomomorphism(viergroup, c2, map);
        
        // get the kernelset 
        Set<Element> kernelSet = (Set<Element>) morph.getProperty(HomomorphismProperty.KernelSet);
        assertEquals(2, kernelSet.size());
        assertTrue(kernelSet.contains(unit));
        assertTrue(kernelSet.contains(ab));
        
        // get the kernel group
        Group kernelGroup = (Group) morph.getProperty(HomomorphismProperty.KernelGroup);
        Set<Element> kSet = (Set<Element>) kernelGroup.getProperty(GroupProperty.Elements);
        assertTrue(kSet.equals(kernelSet));
        
        // get the kernel embedding
        GroupHomomorphism kMorph = (GroupHomomorphism) morph.getProperty(HomomorphismProperty.KernelEmbedding);
        Group domain = (Group) kMorph.getProperty(HomomorphismProperty.Domain);
        assertTrue(domain.equals(kernelGroup));
        
        Group codomain = (Group) kMorph.getProperty(HomomorphismProperty.Codomain);
        assertTrue(codomain.equals(viergroup));
        
        assertTrue((boolean)kMorph.getProperty(HomomorphismProperty.IsEmbedding));
    }
}
